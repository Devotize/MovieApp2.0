package com.sychev.movieapp.presentation.ui.movie_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.Multimedia
import com.sychev.movieapp.presentation.ui.movie_list.ListType.*
import com.sychev.movieapp.presentation.ui.movie_list.MovieListEvent.*
import com.sychev.movieapp.repository.MovieRepository
import com.sychev.movieapp.util.TAG
import kotlinx.coroutines.launch

class MovieListViewModel
@ViewModelInject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    val multimedia: MutableState<List<Multimedia>> = mutableStateOf(ArrayList())
    val query: MutableState<String> = mutableStateOf("Transformers")
    val loading = mutableStateOf(false)
    val currentListType: MutableState<ListType> = mutableStateOf(SEARCH_LIST)
    private val listScrollPosition = mutableStateOf(0)
    val page = mutableStateOf(1)
    val currentCategory = mutableStateOf(Categories.MOVIE)

    init {
        onTriggerEvent(SearchMoviesEvent)
    }

    fun onTriggerEvent(event: MovieListEvent){
        when (event){
            is SearchMoviesEvent -> {
                viewModelScope.launch {
                    currentListType.value = SEARCH_LIST
                    resetSearch()
                    when (currentCategory.value) {
                        Categories.MOVIE -> makeMovieSearch()
                        Categories.TV_SHOW -> makeTvShowSearch()
                    }
                }
            }
            is AddMovieSearchToWatchedEvent -> {
                viewModelScope.launch {
                    if (event.multimedia.watchStatus == true) {
                        event.multimedia.id?.let { deleteMovieById(it) }
                    } else {
                        addToWatched(event.multimedia)
                    }
                    updateMovies()
                }

            }
            is AddMovieSearchToWatchlistEvent -> {
                viewModelScope.launch {
                    if (event.multimedia.watchStatus == false) {
                        event.multimedia.id?.let{deleteMovieById(it)}
                    } else {
                        addToWatchList(event.multimedia)
                    }
                    updateMovies()
                }
            }
            is WatchlistMoviesEvent -> {
                viewModelScope.launch {
                    getWatchlistMovies()
                    currentListType.value = WATCH_LIST
                }
            }
            is WatchedMoviesEvent -> {
                viewModelScope.launch {
                    getWatchedMovies()
                    currentListType.value = WATCHED_LIST
                }
            }
            is UpdateMovies -> {
                viewModelScope.launch {
                    updateMovies()
                }
            }
            is NextPageEvent -> {
                viewModelScope.launch {
                    nexPage()
                }
            }
            is ChangeCategoryEvent -> {
                changeCategory(event.category)
            }
        }
    }

    private suspend fun makeMovieSearch(){
        loading.value = true
        val result = repository.searchMovies(
                query = this.query.value,
                page = page.value
            )
        loading.value = false
        if (result != null) {
            multimedia.value = result
            result.forEach { movieSearch ->
                movieSearch.checkMovieForStatus()
            }
            multimedia.value = result
        }
    }

    private suspend fun makeTvShowSearch() {
        loading.value = true
        val result = repository.searchTvShows(
            query = query.value,
            page = page.value
        )
        loading.value = false
    }

    fun onQueryChange(query: String) {
        this@MovieListViewModel.query.value = query
    }

    private suspend fun nexPage() {
        if (this.listScrollPosition.value + 1 == (20 * page.value) && currentListType.value == SEARCH_LIST) {
            loading.value = true
            page.value = page.value + 1
            val newMovies = multimedia.value.toMutableList()
            val result = repository.searchMovies(
                query = query.value,
                page = page.value
            )
            if (result != null) {
                result.forEach {
                    it.checkMovieForStatus()
                }
                newMovies.addAll(result)
            }
            Log.d(TAG, "nexPage triggeres: newMovies: $newMovies")
            multimedia.value = listOf()
            multimedia.value = newMovies
            loading.value = false
        }


    }

    private suspend fun getWatchedMovies() {
        loading.value = true
        multimedia.value = repository.getMultimediaByStatus(true)
        loading.value = false
    }

    private suspend fun getWatchlistMovies() {
        loading.value = true
        multimedia.value = repository.getMultimediaByStatus(false)
        loading.value = false
    }

    private fun resetSearch() {
        multimedia.value = listOf()
        page.value = 1
        listScrollPosition.value = 0
    }

    fun onChangeScrollPosition(position: Int) {
        this.listScrollPosition.value = position

    }

    private suspend fun Multimedia.checkMovieForStatus() {
        this.id?.let{ id ->
            val movie = repository.getMultimediaFromCache(id)
            if (movie != null){
                this.watchStatus = movie.watchStatus
            } else {
                this.watchStatus = null
            }
        }
    }

    private suspend fun addToWatchList(multimedia: Multimedia) {
        multimedia.watchStatus = false
        repository.addMultimediaToCache(multimedia = multimedia)
    }

    private suspend fun deleteMovieById(id: Int) {
        repository.deleteById(id)
    }

    private suspend fun addToWatched(multimedia: Multimedia) {
        multimedia.watchStatus = true
        repository.addMultimediaToCache(multimedia = multimedia)

    }

    private suspend fun updateMovies() {
        val movies = mutableListOf<Multimedia>()
        movies.addAll(this.multimedia.value)
        movies.forEach {
            it.checkMovieForStatus()
        }
        this.multimedia.value = listOf()
        this.multimedia.value = movies
    }
    
    private fun changeCategory(category: Categories) {
        currentCategory.value = category
        Log.d(TAG, "changeCategory: categoryChanged: $category")
    }
}


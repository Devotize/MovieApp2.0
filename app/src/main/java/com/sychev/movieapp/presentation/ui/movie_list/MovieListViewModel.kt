package com.sychev.movieapp.presentation.ui.movie_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.presentation.ui.movie_list.ListType.*
import com.sychev.movieapp.presentation.ui.movie_list.MovieListEvent.*
import com.sychev.movieapp.repository.MovieRepository
import com.sychev.movieapp.util.TAG
import kotlinx.coroutines.launch

class MovieListViewModel
@ViewModelInject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    val movies: MutableState<List<MovieSearch>> = mutableStateOf(ArrayList())
    val query: MutableState<String> = mutableStateOf("Transformers")
    val loading = mutableStateOf(false)
    private val currentListType: MutableState<ListType> = mutableStateOf(SEARCH_LIST)
    private val listScrollPosition = mutableStateOf(0)
    val page = mutableStateOf(1)

    init {
        onTriggerEvent(SearchMoviesEvent)
    }

    fun onTriggerEvent(event: MovieListEvent){
        when (event){
            is SearchMoviesEvent -> {
                viewModelScope.launch {
                    currentListType.value = SEARCH_LIST
                    resetSearch()
                    makeSearch()
                }
            }
            is AddMovieSearchToWatchedEvent -> {
                viewModelScope.launch {
                    if (event.movie.watchStatus == true) {
                        event.movie.id?.let { deleteMovieById(it) }
                    } else {
                        addToWatched(event.movie)
                    }
                    updateMovies()
                }

            }
            is AddMovieSearchToWatchlistEvent -> {
                viewModelScope.launch {
                    if (event.movie.watchStatus == false) {
                        event.movie.id?.let{deleteMovieById(it)}
                    } else {
                        addToWatchList(event.movie)
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
        }
    }

    private suspend fun makeSearch(){
        loading.value = true
        val result = repository.searchMovies(
                query = this.query.value,
                page = page.value
            )
        loading.value = false
        if (result != null) {
            movies.value = result
            result.forEach { movieSearch ->
                movieSearch.checkMovieSearchForStatus()
            }
            movies.value = result
        }
    }

    fun onQueryChange(query: String) {
        this@MovieListViewModel.query.value = query
    }

    private suspend fun nexPage() {
        if (this.listScrollPosition.value + 1 == (20 * page.value) && currentListType.value == SEARCH_LIST) {
            loading.value = true
            page.value = page.value + 1
            val newMovies = movies.value.toMutableList()
            val result = repository.searchMovies(
                query = query.value,
                page = page.value
            )
            if (result != null) {
                result.forEach {
                    it.checkMovieSearchForStatus()
                }
                newMovies.addAll(result)
            }
            Log.d(TAG, "nexPage triggeres: newMovies: $newMovies")
            movies.value = listOf()
            movies.value = newMovies
            loading.value = false
        }


    }

    private suspend fun getWatchedMovies() {
        loading.value = true
        movies.value = repository.getMoviesByStatus(true)
        loading.value = false
    }

    private suspend fun getWatchlistMovies() {
        loading.value = true
        movies.value = repository.getMoviesByStatus(false)
        loading.value = false
    }

    private fun resetSearch() {
        movies.value = listOf()
        page.value = 1
        listScrollPosition.value = 0
    }

    fun onChangeScrollPosition(position: Int) {
        this.listScrollPosition.value = position

    }

    private suspend fun MovieSearch.checkMovieSearchForStatus() {
        this.id?.let{ id ->
            val movie = repository.getMovieFromCache(id)
            if (movie != null){
                this.watchStatus = movie.watchStatus
            } else {
                this.watchStatus = null
            }
        }
    }

    private suspend fun addToWatchList(movie: MovieSearch) {
        movie.watchStatus = false
        repository.addMovieSearchToCache(movie = movie)
    }

    private suspend fun deleteMovieById(id: Int) {
        repository.deleteById(id)
    }

    private suspend fun addToWatched(movie: MovieSearch) {
        movie.watchStatus = true
        repository.addMovieSearchToCache(movie = movie)

    }

    private suspend fun updateMovies() {
        val movies = mutableListOf<MovieSearch>()
        movies.addAll(this.movies.value)
        movies.forEach {
            it.checkMovieSearchForStatus()
        }
        this.movies.value = listOf()
        this.movies.value = movies
    }
}


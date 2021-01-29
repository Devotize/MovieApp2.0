package com.sychev.movieapp.presentation.ui.movie_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.presentation.ui.movie_list.MovieListEvent.*
import com.sychev.movieapp.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieListViewModel
@ViewModelInject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    val movies: MutableState<List<MovieSearch>> = mutableStateOf(ArrayList())
    val query: MutableState<String> = mutableStateOf("Transformers")
    val loading = mutableStateOf(false)
    private val listScrollPosition = mutableStateOf(0)
    private val page = mutableStateOf(1)

    init {
        onTriggerEvent(SearchMoviesEvent)
    }

    fun onTriggerEvent(event: MovieListEvent){
        when (event){
            is SearchMoviesEvent -> {
                viewModelScope.launch {
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
            is AddMovieSearchToWatchLaterEvent -> {
                viewModelScope.launch {
                    if (event.movie.watchStatus == false) {
                        event.movie.id?.let{deleteMovieById(it)}
                    } else {
                        addToWatchLater(event.movie)
                    }
                    updateMovies()
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
        movies.value = result
        loading.value = false
        result.forEach { movieSearch ->
            movieSearch.checkMovieSearchForStatus()
        }
        movies.value = result
    }

    fun onQueryChange(query: String) {
        this@MovieListViewModel.query.value = query

    }

    private fun resetSearch() {
        movies.value = listOf()
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

    private suspend fun addToWatchLater(movie: MovieSearch) {
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


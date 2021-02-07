package com.sychev.movieapp.presentation.ui.person

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.domain.model.Person
import com.sychev.movieapp.presentation.ui.person.PersonEvent.*
import com.sychev.movieapp.repository.MovieRepository
import com.sychev.movieapp.util.TAG
import kotlinx.coroutines.launch


class PersonViewModel
@ViewModelInject constructor(
    val repository: MovieRepository
): ViewModel() {

    val person: MutableState<Person?> = mutableStateOf(null)
    val loading: MutableState<Boolean> = mutableStateOf(false)
    val movies: MutableState<List<MovieSearch>?> = mutableStateOf(null)

    fun onTriggerEvent(event: PersonEvent) {
        when (event) {
            is GetPersonEvent -> {
                Log.d(TAG, "onTriggerEvent: personId: ${event.personId}")
                viewModelScope.launch {
                    getPerson(event.personId)
                    getPersonMovieCredits(event.personId)
                }
            }
            is AddMovieToWatchedEvent -> {
                viewModelScope.launch {
                    if (event.movie.watchStatus == true) {
                        event.movie.id?.let { deleteMovieById(it) }
                    } else {
                        addToWatched(event.movie)
                    }
                    updateMovies()
                }
            }
            is AddMovieToWatchlistEvent -> {
                viewModelScope.launch {
                    if (event.movie.watchStatus == false) {
                        event.movie.id?.let { deleteMovieById(it) }
                    } else {
                        addToWatchList(event.movie)
                    }
                    updateMovies()
                }
            }
        }
    }

    private suspend fun getPerson(id: Int) {
        loading.value = true
        val result = repository.getPerson(id)
        person.value = result
        loading.value = false
    }

    private suspend fun getPersonMovieCredits(id: Int) {
        val movies: ArrayList<MovieSearch> = ArrayList()
        val result = repository.getPersonMovieCredits(id)
        result.cast?.let { movies.addAll(it) }
        result.crew?.let { movies.addAll(it) }
        movies.forEach {
            it.checkMovieSearchForStatus()
        }
        this.movies.value = movies
    }

    private suspend fun deleteMovieById(id: Int) {
        repository.deleteById(id)
    }

    private suspend fun updateMovies() {
        val movies = this.movies.value
        movies?.forEach {
            it.checkMovieSearchForStatus()
        }
        this.movies.value = listOf()
        this.movies.value = movies
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

    private suspend fun addToWatched(movie: Movie) {
        movie.watchStatus = true
        repository.addMovieToCache(movie)
    }

    private suspend fun addToWatchList(movie: Movie) {
        movie.watchStatus = false
        repository.addMovieToCache(movie)
    }

    
}














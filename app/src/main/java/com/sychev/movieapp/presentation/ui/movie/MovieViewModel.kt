package com.sychev.movieapp.presentation.ui.movie

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.movieapp.domain.model.MovieCredits
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.presentation.ui.movie.MovieEvent.*
import com.sychev.movieapp.repository.MovieRepository
import com.sychev.movieapp.util.TAG
import kotlinx.coroutines.launch

class MovieViewModel
@ViewModelInject constructor(
    private val repository: MovieRepository
): ViewModel() {

    val movie: MutableState<Movie?> = mutableStateOf(null)
    val loading: MutableState<Boolean> = mutableStateOf(false)
    val credits: MutableState<MovieCredits?> = mutableStateOf(null)
    val recommendations: MutableState<List<Movie>?> = mutableStateOf(null)

    fun onTriggerEvent(event: MovieEvent) {
        when (event) {
            is GetMovieEvent -> {
                viewModelScope.launch {
                    getMovie(event.movieId)
                    getCredits(event.movieId)
                    getRecommendations(event.movieId)
                }
            }
            is AddMovieToWatchedEvent -> {
                viewModelScope.launch {
                    if (event.movie.watchStatus == true) {
                        event.movie.id?.let { deleteMovieById(it) }
                    } else {
                        addToWatched(event.movie)
                    }
                    updateMovieWatchStatus()
                    updateRecommendations()
                }
            }
            is AddMovieToWatchlistEvent -> {
                viewModelScope.launch {
                    if (event.movie.watchStatus == false) {
                        event.movie.id?.let{deleteMovieById(it)}
                    } else {
                        addToWatchList(event.movie)
                    }
                    updateMovieWatchStatus()
                    updateRecommendations()
                }
            }
        }
    }

    private suspend fun getRecommendations(id: Int) {
        recommendations.value = null
        val result = repository.getRecommendations(id)
        result?.forEach {
            it.checkMovieForStatus()
        }
        recommendations.value = result
    }

    private suspend fun getCredits(id: Int) {
        loading.value = true
        val result = repository.getCredits(id)
        credits.value = result
        Log.d(TAG, "getCredits: ${credits.value}")
        loading.value = false
    }

    private suspend fun getMovie(id: Int){
        loading.value = true
        movie.value = null
        val result = repository.getMovieFromNetwork(id)
        Log.d(TAG, "getMovie: result: $result")
        result.checkMovieForStatus()
        movie.value = result
        Log.d(TAG, "getMovie: movie.value = ${movie.value}")
        loading.value = false
    }

    private suspend fun Movie.checkMovieForStatus() {
        this.id?.let{ id ->
            val movie = repository.getMultimediaFromCache(id)
            if (movie != null){
                this.watchStatus = movie.watchStatus
            } else {
                this.watchStatus = null
            }
        }
    }

    private suspend fun deleteMovieById(id: Int) {
        repository.deleteById(id)
    }

    private suspend fun addToWatched(movie: Movie) {
        movie.watchStatus = true
        repository.addMovieToCache(movie)
    }

    private suspend fun addToWatchList(movie: Movie) {
        movie.watchStatus = false
        repository.addMovieToCache(movie)
    }

    private suspend fun updateMovieWatchStatus() {
        val movie = movie.value
        movie?.checkMovieForStatus()
        this.movie.value = null
        this.movie.value = movie
    }

    private suspend fun updateRecommendations() {
        val recommendations = recommendations.value
        recommendations?.forEach {
            it.checkMovieForStatus()
        }
        this.recommendations.value = recommendations
    }

}
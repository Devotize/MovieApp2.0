package com.sychev.movieapp.presentation.ui.movie

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.movieapp.domain.model.Credits
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.Person
import com.sychev.movieapp.presentation.ui.movie.MovieEvent.GetMovieEvent
import com.sychev.movieapp.repository.MovieRepository
import com.sychev.movieapp.util.TAG
import kotlinx.coroutines.launch

class MovieViewModel
@ViewModelInject constructor(
    private val repository: MovieRepository
): ViewModel() {

    val movie: MutableState<Movie?> = mutableStateOf(null)
    val loading: MutableState<Boolean> = mutableStateOf(false)
    val credits: MutableState<Credits?> = mutableStateOf(null)

    fun onTriggerEvent(event: MovieEvent) {
        when (event) {
            is GetMovieEvent -> {
                viewModelScope.launch {
                    getMovie(event.movieId)
                    getCredits(event.movieId)
                }
            }
        }
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
        val result = repository.getMovieFromNetwork(id)
        Log.d(TAG, "getMovie: result: $result")
        result.checkMovieSearchForStatus()
        movie.value = result
        Log.d(TAG, "getMovie: movie.value = ${movie.value}")
        loading.value = false
    }

    private suspend fun Movie.checkMovieSearchForStatus() {
        this.id?.let{ id ->
            val movie = repository.getMovieFromCache(id)
            if (movie != null){
                this.watchStatus = movie.watchStatus
            } else {
                this.watchStatus = null
            }
        }
    }

}
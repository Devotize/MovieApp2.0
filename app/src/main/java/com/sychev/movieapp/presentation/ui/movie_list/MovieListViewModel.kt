package com.sychev.movieapp.presentation.ui.movie_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.presentation.ui.movie_list.MovieListEvent.SearchMoviesEvent
import com.sychev.movieapp.repository.MovieRepository
import com.sychev.movieapp.util.TAG
import kotlinx.coroutines.launch

class MovieListViewModel
@ViewModelInject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    val movies: MutableState<List<MovieSearch>> = mutableStateOf(ArrayList())
    val query: MutableState<String> = mutableStateOf("")
    val loading = mutableStateOf(false)
    val page = mutableStateOf(1)


    init {
        viewModelScope.launch {
            val movieTest = repository.getMovieFromCache(2)
            Log.d(TAG, "moveTest: $movieTest")
            val movieListTest = repository.getMoviesByStatus(false)
            Log.d(TAG, "movieTestList: $movieListTest")
        }
    }

    fun onTriggerEvent(event: MovieListEvent){
        when (event){
            is SearchMoviesEvent -> {
                viewModelScope.launch {
                    makeSearch()
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
    }

    fun onQueryChange(query: String) {
        this@MovieListViewModel.query.value = query

    }

}


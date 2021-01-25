package com.sychev.movieapp.presentation.ui.movie_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.repository.MovieRepository
import com.sychev.movieapp.repository.MovieRepository_Impl
import kotlinx.coroutines.launch

class MovieListViewModel
@ViewModelInject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    val movies: MutableState<List<MovieSearch>> = mutableStateOf(ArrayList())

    init{
        viewModelScope.launch {
            makeSearch("transformers")
        }
    }

    private suspend fun makeSearch(query: String){
        val result = repository.searchMovies(query = query)
        movies.value = result
    }
}


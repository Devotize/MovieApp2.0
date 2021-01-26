package com.sychev.movieapp.presentation.ui.movie_list

sealed class MovieListEvent {
    object SearchMoviesEvent : MovieListEvent()
}
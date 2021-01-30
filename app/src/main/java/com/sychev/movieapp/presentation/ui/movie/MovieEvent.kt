package com.sychev.movieapp.presentation.ui.movie

sealed class MovieEvent {

    class GetMovieEvent(val movieId: Int): MovieEvent()

}
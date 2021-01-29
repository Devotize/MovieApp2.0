package com.sychev.movieapp.presentation.ui.movie

sealed class MovieEvent {

    class GetMovieEvent(val id: Int): MovieEvent()

}
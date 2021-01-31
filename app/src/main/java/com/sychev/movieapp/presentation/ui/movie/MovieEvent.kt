package com.sychev.movieapp.presentation.ui.movie

import com.sychev.movieapp.domain.model.Movie


sealed class MovieEvent {

    class GetMovieEvent(val movieId: Int): MovieEvent()

    class AddMovieToWatchedEvent(val movie: Movie) : MovieEvent()

    class AddMovieToWatchlistEvent(val movie: Movie) : MovieEvent()

}
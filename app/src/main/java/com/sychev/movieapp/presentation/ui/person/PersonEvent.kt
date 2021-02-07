package com.sychev.movieapp.presentation.ui.person

import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.presentation.ui.movie.MovieEvent

sealed class PersonEvent() {

    class GetPersonEvent(val personId: Int): PersonEvent()

    class AddMovieToWatchedEvent(val movie: Movie) : PersonEvent()

    class AddMovieToWatchlistEvent(val movie: Movie) : PersonEvent()

}
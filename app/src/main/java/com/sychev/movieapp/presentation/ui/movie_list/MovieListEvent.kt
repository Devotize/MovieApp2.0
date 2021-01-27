package com.sychev.movieapp.presentation.ui.movie_list

import com.sychev.movieapp.domain.model.MovieSearch

sealed class MovieListEvent {
    object SearchMoviesEvent : MovieListEvent()

    class AddMovieSearchToWatchedEvent(val movie: MovieSearch) : MovieListEvent()

    class AddMovieSearchToWatchLaterEvent(val movie: MovieSearch) : MovieListEvent()


}
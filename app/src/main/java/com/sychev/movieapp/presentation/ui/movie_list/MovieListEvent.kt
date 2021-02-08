package com.sychev.movieapp.presentation.ui.movie_list

import com.sychev.movieapp.domain.model.Movie


sealed class MovieListEvent {
    object SearchMoviesEvent : MovieListEvent()

    class AddMovieSearchToWatchedEvent(val movie: Movie) : MovieListEvent()

    class AddMovieSearchToWatchlistEvent(val movie: Movie) : MovieListEvent()

    object WatchlistMoviesEvent : MovieListEvent()

    object WatchedMoviesEvent : MovieListEvent()

    object UpdateMovies: MovieListEvent()

    object NextPageEvent : MovieListEvent()
}
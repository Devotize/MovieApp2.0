package com.sychev.movieapp.presentation.ui.movie_list

import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.Multimedia


sealed class MovieListEvent {
    object SearchMoviesEvent : MovieListEvent()

    class AddMovieSearchToWatchedEvent(val multimedia: Multimedia) : MovieListEvent()

    class AddMovieSearchToWatchlistEvent(val multimedia: Multimedia) : MovieListEvent()

    object WatchlistMoviesEvent : MovieListEvent()

    object WatchedMoviesEvent : MovieListEvent()

    object UpdateMovies: MovieListEvent()

    object NextPageEvent : MovieListEvent()

    class ChangeCategoryEvent(val category: Categories): MovieListEvent()
}
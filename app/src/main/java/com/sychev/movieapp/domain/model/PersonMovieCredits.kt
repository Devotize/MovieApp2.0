package com.sychev.movieapp.domain.model

data class PersonMovieCredits(
    val cast: List<Movie>? = null,
    val crew: List<Movie>? = null
) {
}
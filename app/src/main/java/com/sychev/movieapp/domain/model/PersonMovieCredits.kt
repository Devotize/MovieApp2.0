package com.sychev.movieapp.domain.model

data class PersonMovieCredits(
    val cast: List<MovieSearch>? = null,
    val crew: List<MovieSearch>? = null
) {
}
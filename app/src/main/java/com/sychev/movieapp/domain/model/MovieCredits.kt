package com.sychev.movieapp.domain.model

data class MovieCredits(
    val cast: List<Person>?,
    val crew: List<Person>?
) {
}
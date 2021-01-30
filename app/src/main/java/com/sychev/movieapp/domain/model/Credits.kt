package com.sychev.movieapp.domain.model

data class Credits(
    val cast: List<Person>?,
    val crew: List<Person>?
) {
}
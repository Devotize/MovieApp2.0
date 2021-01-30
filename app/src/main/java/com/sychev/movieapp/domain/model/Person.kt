package com.sychev.movieapp.domain.model

data class Person(
    val biography: String? = null,
    val birthDay: String? = null,
    val deathDay: String? = null,
    val gender: Int? = null,  //2-is male, 1-is female
    val id: Int? = null,
    val imdbId: Int? = null,
    val knownForDepartment: String? = null,
    val name: String? = null,
    val placeOfBirth: String? = null,
    val popularity: Float? = null,
    val profilePath: String? = null, //imageUrl
    val character: String? = null,
    val job: String? = null,
) {
}
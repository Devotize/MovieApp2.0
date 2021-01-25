package com.sychev.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val belongsToCollection: Collection? = null,
    val budget: Float? = null,
    val genres: List<Genre>,
    val homepage: String? = null,
    val id: Int? = null,
    val imdbId: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val poserPath: String? = null,
    val releaseDate: String? = null,
    val revenue: Float? = null,
    val runtime: Float? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val voteAverage: Float? = null,
    val voteCount: Float? = null
): Parcelable {
    @Parcelize
    data class Collection(
        val id: Int? = null,
        val name: String? = null,
        val posterPath: String? = null,
        val backdropPath: String? = null
    ): Parcelable

    @Parcelize
    data class Genre(
        val id: Int? = null,
        val name: String? = null
    ): Parcelable

}
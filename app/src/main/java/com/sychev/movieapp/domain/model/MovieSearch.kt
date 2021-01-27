package com.sychev.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieSearch(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genreIds: List<Int>? = null,
    val id: Int? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview:String? = null,
    val popularity: Float? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Float? = null,
    val voteCount:Int? = null,
    val watchStatus: Boolean? = null,
): Parcelable {
}
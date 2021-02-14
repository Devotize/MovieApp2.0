package com.sychev.movieapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Multimedia(
    val posterPath: String? = null,
    val overview: String? = null,
    val releaseDate: String? = null,
    val originalTitle: String? = null,
    val id: Int? = null,
    val mediaType: String? = null,
    val title: String? = null,
    val backdropPath: String? = null,
    val popularity: Float? = null,
    val voteCount: Int? = null,
    val voteAverage: Float? = null,
    val name: String? = null,
    val profilePath: String? = null,
    var watchStatus: Boolean? = null
): Parcelable {
}
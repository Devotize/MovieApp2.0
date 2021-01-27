package com.sychev.movieapp.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey val id: Int,
    val watchStatus: Boolean,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val voteAverage: Float? = null,
) {
}
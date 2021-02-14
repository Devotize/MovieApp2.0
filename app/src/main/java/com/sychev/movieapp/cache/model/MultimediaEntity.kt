package com.sychev.movieapp.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MultimediaEntity(
    @PrimaryKey val id: Int,
    val watchStatus: Boolean,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val voteAverage: Float? = null,
) {
}
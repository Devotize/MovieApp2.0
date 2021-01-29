package com.sychev.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.sychev.movieapp.network.model.MovieDto

data class MovieResponse(
    val movieDto: MovieDto? = null
) {
}
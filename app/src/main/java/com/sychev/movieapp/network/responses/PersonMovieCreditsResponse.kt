package com.sychev.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.network.model.MovieDto

data class PersonMovieCreditsResponse(
    @SerializedName("cast")
    val cast: List<MovieDto>? = null,
    @SerializedName("crew")
    val crew: List<MovieDto>? = null,
) {

}
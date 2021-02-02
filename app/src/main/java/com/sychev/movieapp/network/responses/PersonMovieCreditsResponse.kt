package com.sychev.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.network.model.MovieSearchDto

data class PersonMovieCreditsResponse(
    @SerializedName("cast")
    val cast: List<MovieSearchDto>? = null,
    @SerializedName("crew")
    val crew: List<MovieSearchDto>? = null,
) {

}
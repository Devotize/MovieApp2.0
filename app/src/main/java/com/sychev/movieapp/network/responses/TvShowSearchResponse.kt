package com.sychev.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.sychev.movieapp.network.model.TvShowDto

data class TvShowSearchResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<TvShowDto> = listOf()
) {
}
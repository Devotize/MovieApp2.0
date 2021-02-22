package com.sychev.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.sychev.movieapp.network.model.MultimediaDto

data class MultiSearchResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<MultimediaDto> = listOf(),
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
) {
}
package com.sychev.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.sychev.movieapp.network.model.CastDto
import com.sychev.movieapp.network.model.CrewDto

data class CreditsResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("cast")
    val cast: List<CastDto>? = null,
    @SerializedName("crew")
    val crew: List<CrewDto>? = null,
) {
}
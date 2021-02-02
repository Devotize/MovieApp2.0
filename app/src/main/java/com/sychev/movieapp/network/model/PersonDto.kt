package com.sychev.movieapp.network.model

import com.google.gson.annotations.SerializedName

data class PersonDto(
    @SerializedName("biography")
    val biography: String? = null,
    @SerializedName("birt_day")
    val birthDay: String? = null,
    @SerializedName("death_day")
    val deathDay: String? = null,
    @SerializedName("gender")
    val gender: Int? = null,  //2-is male, 1-is female
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("place_of_birth")
    val placeOfBirth: String? = null,
    @SerializedName("popularity")
    val popularity: Float? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null, //imageUrl
)
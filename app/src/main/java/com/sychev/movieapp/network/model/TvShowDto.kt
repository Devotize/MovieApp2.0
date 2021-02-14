package com.sychev.movieapp.network.model

import com.sychev.movieapp.domain.model.Movie


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class TvShowDto(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("created_by")
    val createdBy: List<CreatorDto>? = null,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Float>? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("genres")
    val genres: List<MovieDto.GenreDto> = listOf(),
    @SerializedName("homepage")
    val homePage: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("in_production")
    val inProduction: Boolean? = null,
    @SerializedName("last_air_date")
    val lastAirDate: String? = null,
    @SerializedName("last_episode_to_air")
    val lasEpisodeToAir: EpisodeDto? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeDto? = null,
    @SerializedName("networks")
    val networks: List<NetworkDto>? = null,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,
    @SerializedName("original_name")
    val originName: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Float? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("seasons")
    val seasons: List<SeasonDto>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Float? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) {

    data class CreatorDto(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("credit_id")
        val creditId: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("gender")
        val gender: Int? = null,
        @SerializedName("profile_path")
        val profilePath: String? = null,
    )

    data class EpisodeDto(
        @SerializedName("air_date")
        val airDate: String? = null,
        @SerializedName("episode_number")
        val episodeNumber: Int? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("overview")
        val overview: String? = null,
        @SerializedName("production_code")
        val productionCode: String? = null,
        @SerializedName("season_number")
        val seasonNumber: Int? = null,
        @SerializedName("still_path")
        val stillPath: String? = null,
        @SerializedName("vote_average")
        val voteAverage: Float? = null,
        @SerializedName("vote_count")
        val voteCount: Float? = null
    )

    data class NetworkDto(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("logo_path")
        val logoPath: String? = null,
        @SerializedName("origin_country")
        val originCountry: String? = null
    )

    data class SeasonDto(
        @SerializedName("air_date")
        val airDate: String? = null,
        @SerializedName("episode_count")
        val episodeCount: Int? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("overview")
        val overview: String? = null,
        @SerializedName("poster_path")
        val posterPath: String? = null,
        @SerializedName("season_number")
        val seasonNumber: Int? = null,
    )
}














package com.sychev.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class TvShow(
    val backdropPath: String? = null,
    val createdBy: List<Creator>? = null,
    val episodeRunTime: List<Float>? = null,
    val firstAirDate: String? = null,
    val genres: List<Movie.Genre>,
    val homePage: String? = null,
    val id: Int? = null,
    val inProduction: Boolean? = null,
    val lastAirDate: String? = null,
    val lastEpisodeToAir: Episode? = null,
    val name: String? = null,
    val nextEpisodeToAir: Episode? = null,
    val networks: List<Network>? = null,
    val numberOfEpisodes: Int? = null,
    val numberOfSeasons: Int? = null,
    val originName: String? = null,
    val overview: String? = null,
    val popularity: Float? = null,
    val posterPath: String? = null,
    val seasons: List<Season>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    val voteAverage: Float? = null,
    val voteCount: Int? = null
) {


    data class Creator(
        val id: Int? = null,
        val creditId: String? = null,
        val name: String? = null,
        val gender: Int? = null,
        val profilePath: String? = null,
    )

    data class Episode(
        val airDate: String? = null,
        val episodeNumber: Int? = null,
        val id: Int? = null,
        val name: String? = null,
        val overview: String? = null,
        val productionCode: String? = null,
        val seasonNumber: Int? = null,
        val stillPath: String? = null,
        val voteAverage: Float? = null,
        val voteCount: Float? = null
    )

    data class Network(
        val name: String? = null,
        val id: Int? = null,
        val logoPath: String? = null,
        val originCountry: String? = null
    )

    data class Season(
        val airDate: String? = null,
        val episodeCount: Int? = null,
        val id: Int? = null,
        val name: String? = null,
        val overview: String? = null,
        val posterPath: String? = null,
        val seasonNumber: Int? = null,
    )
}














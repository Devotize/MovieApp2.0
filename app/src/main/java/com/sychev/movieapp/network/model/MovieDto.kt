package com.sychev.movieapp.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: CollectionDto? = null,
    @SerializedName("budget")
    val budget: Float? = null,
    @SerializedName("genres")
    val genres: List<GenreDto>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Float? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("product_companies")
    val productCompanies: List<ProductCompanies>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountries>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Float? = null,
    @SerializedName("runtime")
    val runtime: Float? = null,
    @SerializedName("spoken_languages")
    val spokenLanguage: List<SpokenLanguage>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Float? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) {
    data class CollectionDto(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("poster_path")
        val posterPath: String? = null,
        @SerializedName("backdrop_path")
        val backdropPath: String? = null
    )
    data class GenreDto(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null
    )

    data class ProductCompanies(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("logo_path")
        val logoPath: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("origin_country")
        val originCountry: String? = null,
    )

    data class ProductionCountries(
        @SerializedName("iso_3166_1")
        val iso: String? = null,
        @SerializedName("name")
        val name: String? = null
    )

    data class SpokenLanguage(
        @SerializedName("english_name")
        val englishName: String? = null,
        @SerializedName("iso_639_1")
        val iso: String? = null,
        @SerializedName("name")
        val name: String? = null
    )
}












package com.sychev.movieapp.util

import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch

const val API_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "cc1f7a9953ead28e45b0c4dd62315ddb"
const val API_LANGUAGE = "en-US"

const val TAG = "AppDebug"

fun MovieSearch.toMovie(): Movie{
    return Movie(
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsToCollection = null,
        budget = null,
        genres = null,
        homepage = null,
        id = this.id,
        imdbId = null,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        revenue = null,
        runtime = null,
        status = null,
        tagline = null,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        watchStatus = this.watchStatus,

    )
}

fun Movie.toMovieSearch(): MovieSearch {
    return MovieSearch(
        adult = this.adult,
        backdropPath = this.backdropPath,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        watchStatus = this.watchStatus,

        )
}
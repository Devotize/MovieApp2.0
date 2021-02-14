package com.sychev.movieapp.util

import com.sychev.movieapp.domain.model.Movie

const val API_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "cc1f7a9953ead28e45b0c4dd62315ddb"
const val API_LANGUAGE = "en-US"

const val TAG = "AppDebug"

enum class MediaType(name: String) {
    MOVIE("movie"), TV("tv"), PERSON("person")
}

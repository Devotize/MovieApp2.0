package com.sychev.movieapp.repository

import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch

interface MovieRepository {

    suspend fun searchMovies(query: String, page: Int): List<MovieSearch>

    suspend fun getMovie(id: Int): Movie
}
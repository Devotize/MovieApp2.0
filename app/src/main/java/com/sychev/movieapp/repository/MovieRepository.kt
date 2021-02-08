package com.sychev.movieapp.repository

import com.sychev.movieapp.domain.model.*

interface MovieRepository {

    suspend fun searchMovies(query: String, page: Int): List<Movie>?

    suspend fun getMovieFromNetwork(id: Int): Movie

    suspend fun getMoviesByStatus(watchStatus: Boolean): List<Movie>

    suspend fun getMovieFromCache(id: Int): Movie?

    suspend fun addMovieToCache(movie: Movie)

    suspend fun deleteMovieFromCache(movie: Movie)

    suspend fun deleteById(id: Int)

    suspend fun getCredits(id: Int): MovieCredits

    suspend fun getRecommendations(id: Int): List<Movie>?

    suspend fun getPerson(id: Int): Person?

    suspend fun getPersonMovieCredits(id: Int): PersonMovieCredits
}
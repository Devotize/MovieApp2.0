package com.sychev.movieapp.repository

import com.sychev.movieapp.domain.model.*

interface MovieRepository {

    suspend fun searchMovies(query: String, page: Int): List<MovieSearch>

    suspend fun getMovieFromNetwork(id: Int): Movie

    suspend fun getMoviesByStatus(watchStatus: Boolean): List<MovieSearch>

    suspend fun getMovieFromCache(id: Int): Movie?

    suspend fun addMovieToCache(movie: Movie)

    suspend fun addMovieSearchToCache(movie: MovieSearch)

    suspend fun deleteMovieFromCache(movie: Movie)

    suspend fun deleteMovieSearchFromCache(movie: MovieSearch)

    suspend fun deleteById(id: Int)

    suspend fun getCredits(id: Int): MovieCredits

    suspend fun getRecommendations(id: Int): List<MovieSearch>?

    suspend fun getPerson(id: Int): Person?

    suspend fun getPersonMovieCredits(id: Int): PersonMovieCredits
}
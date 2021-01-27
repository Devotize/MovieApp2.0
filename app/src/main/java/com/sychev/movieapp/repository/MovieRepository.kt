package com.sychev.movieapp.repository

import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch

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
}
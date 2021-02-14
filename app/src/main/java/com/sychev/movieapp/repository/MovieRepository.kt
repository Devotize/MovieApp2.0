package com.sychev.movieapp.repository

import com.sychev.movieapp.domain.model.*

interface MovieRepository {

    suspend fun searchMovies(query: String, page: Int): List<Multimedia>?

    suspend fun searchTvShows(query: String, page: Int): List<Multimedia>?

    suspend fun getMovieFromNetwork(id: Int): Movie

    suspend fun getMultimediaByStatus(watchStatus: Boolean): List<Multimedia>

    suspend fun getMultimediaFromCache(id: Int): Multimedia?

    suspend fun addMultimediaToCache(multimedia: Multimedia)

    suspend fun deleteMultimediaFromCache(multimedia: Multimedia)

    suspend fun addMovieToCache(movie: Movie)

    suspend fun deleteMovieFromCache(movie: Movie)

    suspend fun deleteById(id: Int)

    suspend fun getCredits(id: Int): MovieCredits

    suspend fun getRecommendations(id: Int): List<Movie>?

    suspend fun getPerson(id: Int): Person?

    suspend fun getPersonMovieCredits(id: Int): PersonMovieCredits
}
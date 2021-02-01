package com.sychev.movieapp.repository

import android.util.Log
import com.sychev.movieapp.cache.dao.MovieDao
import com.sychev.movieapp.cache.mapper.MovieEntityMapper
import com.sychev.movieapp.domain.model.Credits
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.mapper.MovieDtoMapper
import com.sychev.movieapp.util.TAG


class MovieRepository_Impl(
    private val service: MovieApi,
    private val mapperDto: MovieDtoMapper,
    private val mapperEntity: MovieEntityMapper,
    private val movieDao: MovieDao
): MovieRepository {

    override suspend fun searchMovies(query: String, page: Int): List<MovieSearch> {
        return mapperDto.toDomainMovieList(service.searchMovies(query = query, page = page).results)
    }

    override suspend fun getMovieFromNetwork(id: Int): Movie {
        Log.d(TAG, "getMovieFromNetwork: movie: ${service.getMovie(id)}")
        return mapperDto.toDomainMovie(service.getMovie(id))

    }

    override suspend fun getMoviesByStatus(watchStatus: Boolean): List<MovieSearch> {
        return mapperEntity.toDomainMovieList(movieDao.getAllMoviesWithStatus(watchStatus))
    }

    override suspend fun getMovieFromCache(id: Int): Movie? {
        val movieEntity = movieDao.getMovieById(id)
        return if (movieEntity == null) null else mapperEntity.toDomainMovie(movieEntity)
    }

    override suspend fun addMovieToCache(movie: Movie) {
        movieDao.insert(mapperEntity.fromDomainMovie(movie))
    }

    override suspend fun addMovieSearchToCache(movie: MovieSearch) {
        movieDao.insert(mapperEntity.fromDomainMovieSearch(movie))
    }

    override suspend fun deleteMovieFromCache(movie: Movie) {
        movieDao.delete(mapperEntity.fromDomainMovie(movie))
    }

    override suspend fun deleteMovieSearchFromCache(movie: MovieSearch) {
        movieDao.delete(mapperEntity.fromDomainMovieSearch(movie))
    }

    override suspend fun deleteById(id: Int) {
        movieDao.deleteById(id)
    }

    override suspend fun getCredits(id: Int): Credits {
        return mapperDto.toDomainCredits(service.getCredits(id))
    }

    override suspend fun getRecommendations(id: Int): List<MovieSearch>? {
        return mapperDto.toDomainMovieList(service.getRecommendations(id).results)
    }

}
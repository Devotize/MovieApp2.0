package com.sychev.movieapp.repository

import android.net.ConnectivityManager
import android.util.Log
import com.sychev.movieapp.cache.dao.MovieDao
import com.sychev.movieapp.cache.mapper.MovieEntityMapper
import com.sychev.movieapp.domain.model.*
import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.mapper.MovieDtoMapper
import com.sychev.movieapp.network.responses.MovieSearchResponse
import com.sychev.movieapp.network.utils.ConnectionLiveData
import com.sychev.movieapp.util.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRepository_Impl(
    private val service: MovieApi,
    private val mapperDto: MovieDtoMapper,
    private val mapperEntity: MovieEntityMapper,
    private val movieDao: MovieDao,
    private val connectivityManager: ConnectivityManager
): MovieRepository {

    override suspend fun searchMovies(query: String, page: Int): List<MovieSearch>? {
        return if (!connectivityManager.isActiveNetworkMetered)
            mapperDto.toDomainMovieList(service.searchMovies(query = query, page = page).results)
        else
            null
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

    override suspend fun getCredits(id: Int): MovieCredits {
        return mapperDto.toDomainCredits(service.getCredits(id))
    }

    override suspend fun getRecommendations(id: Int): List<MovieSearch>? {
        return mapperDto.toDomainMovieList(service.getRecommendations(id).results)
    }

    override suspend fun getPerson(id: Int): Person? {
        return service.getPerson(id)?.let { mapperDto.toDomainPerson(it) }
    }

    override suspend fun getPersonMovieCredits(id: Int): PersonMovieCredits {
        return mapperDto.toPersonMovieCredits(service.getPersonMovieCredits(id))
    }

}
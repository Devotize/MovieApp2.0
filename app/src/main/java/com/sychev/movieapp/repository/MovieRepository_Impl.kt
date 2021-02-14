package com.sychev.movieapp.repository

import android.net.ConnectivityManager
import android.util.Log
import com.sychev.movieapp.cache.dao.MovieDao
import com.sychev.movieapp.cache.mapper.MultimediaEntityMapper
import com.sychev.movieapp.domain.model.*
import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.mapper.MovieDtoMapper
import com.sychev.movieapp.network.mapper.MultimediaDtoMapper
import com.sychev.movieapp.network.mapper.PersonDtoMapper
import com.sychev.movieapp.network.mapper.TvShowDtoMapper
import com.sychev.movieapp.util.TAG


class MovieRepository_Impl(
    private val service: MovieApi,
    private val movieMapperDto: MovieDtoMapper,
    private val tvShowMapperDto: TvShowDtoMapper,
    private val personMapperDto: PersonDtoMapper,
    private val multimediaMapperDto: MultimediaDtoMapper,
    private val mapperEntity: MultimediaEntityMapper,
    private val movieDao: MovieDao,
    private val connectivityManager: ConnectivityManager
): MovieRepository {

    override suspend fun searchMovies(query: String, page: Int): List<Multimedia>? {
        return if (!connectivityManager.isActiveNetworkMetered)
            movieMapperDto.toDomainMultimediaList(service.searchMovies(query = query, page = page).results)
        else
            null
    }

    override suspend fun searchTvShows(query: String, page: Int): List<Multimedia>? {
        return if (!connectivityManager.isActiveNetworkMetered)
            tvShowMapperDto.toDomainMultimediaList(service.searchTvShows(query = query, page = page).results)
        else
            null
    }

    override suspend fun getMovieFromNetwork(id: Int): Movie {
        Log.d(TAG, "getMovieFromNetwork: movie: ${service.getMovie(id)}")
        return movieMapperDto.toDomainMovie(service.getMovie(id))

    }

    override suspend fun getMultimediaByStatus(watchStatus: Boolean): List<Multimedia> {
        return mapperEntity.toDomainMultimediaList(movieDao.getAllMultimediaWithStatus(watchStatus))
    }

    override suspend fun getMultimediaFromCache(id: Int):Multimedia? {
        val movieEntity = movieDao.getMultimediaById(id)
        return if (movieEntity == null) null else mapperEntity.toDomainMultimedia(movieEntity)
    }

    override suspend fun addMultimediaToCache(multimedia: Multimedia) {
        movieDao.insert(mapperEntity.fromDomainMultimedia(multimedia))
    }

    override suspend fun deleteMultimediaFromCache(multimedia: Multimedia) {
        movieDao.delete(mapperEntity.fromDomainMultimedia(multimedia))
    }

    override suspend fun addMovieToCache(movie: Movie) {
        movieDao.insert(mapperEntity.fromDomainMovie(movie))
    }

    override suspend fun deleteMovieFromCache(movie: Movie) {
        movieDao.delete(mapperEntity.fromDomainMovie(movie))
    }

    override suspend fun deleteById(id: Int) {
        movieDao.deleteById(id)
    }

    override suspend fun getCredits(id: Int): MovieCredits {
        return personMapperDto.toDomainCredits(service.getCredits(id))
    }

    override suspend fun getRecommendations(id: Int): List<Movie> {
        return movieMapperDto.toDomainMovieList(service.getRecommendations(id).results)
    }

    override suspend fun getPerson(id: Int): Person? {
        return service.getPerson(id)?.let { personMapperDto.toDomainPerson(it) }
    }

    override suspend fun getPersonMovieCredits(id: Int): PersonMovieCredits {
        return movieMapperDto.toPersonMovieCredits(service.getPersonMovieCredits(id))
    }

}
package com.sychev.movieapp.repository

import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.model.mapper.MovieDtoMapper


class MovieRepository_Impl(
    private val service: MovieApi,
    private val mapper: MovieDtoMapper
): MovieRepository {

    override suspend fun searchMovies(query: String): List<MovieSearch> {
        return mapper.toDomainMovieList(service.searchMovies(query = query).results)
    }

    override suspend fun getMovie(id: Int): Movie {
        return mapper.toDomainMovie(service.getMovie(id).movieDto)
    }

}
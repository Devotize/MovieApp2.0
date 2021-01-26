package com.sychev.movieapp.repository

import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.mapper.MovieDtoMapper


class MovieRepository_Impl(
    private val service: MovieApi,
    private val mapper: MovieDtoMapper
): MovieRepository {

    override suspend fun searchMovies(query: String, page: Int): List<MovieSearch> {
        return mapper.toDomainMovieList(service.searchMovies(query = query, page = page).results)
    }

    override suspend fun getMovie(id: Int): Movie {
        return mapper.toDomainMovie(service.getMovie(id).movieDto)
    }

}
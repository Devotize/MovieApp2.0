package com.sychev.movieapp.cache.mapper

import com.sychev.movieapp.cache.model.MovieEntity
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.util.DomainMovieMapper
import com.sychev.movieapp.domain.util.DomainMovieSearchMapper

class MovieEntityMapper
    :
        DomainMovieMapper<MovieEntity, Movie>
{
        override fun toDomainMovie(model: MovieEntity): Movie {
            return Movie(
                id = model.id,
                watchStatus = model.watchStatus,
                posterPath = model.posterPath,
                releaseDate = model.releaseDate,
                title = model.title,
                voteAverage = model.voteAverage
            )
        }

        override fun fromDomainMovie(domainModel: Movie): MovieEntity {
            return MovieEntity(
                id = domainModel.id ?: -1,
                watchStatus = domainModel.watchStatus ?: false,
                posterPath = domainModel.posterPath,
                releaseDate = domainModel.releaseDate,
                title = domainModel.title,
                voteAverage = domainModel.voteAverage
            )
        }

        fun toDomainMovieList(list: List<MovieEntity>): List<Movie> {
            return list.map { toDomainMovie(it) }
        }


}
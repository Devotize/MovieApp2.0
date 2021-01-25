package com.sychev.movieapp.network.model.mapper

import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.domain.util.DomainCollectionMapper
import com.sychev.movieapp.domain.util.DomainMovieMapper
import com.sychev.movieapp.domain.util.DomainMovieSearchMapper
import com.sychev.movieapp.network.model.MovieDto
import com.sychev.movieapp.network.model.MovieSearchDto

class MovieDtoMapper
    : DomainMovieSearchMapper<MovieSearchDto, MovieSearch>,
        DomainMovieMapper<MovieDto, Movie>,
        DomainCollectionMapper<MovieDto.Collection, Movie.Collection>


{

    override fun toDomainMovieSearch(model: MovieSearchDto): MovieSearch {
        return MovieSearch(
            adult = model.adult,
            backdropPath = model.backdropPath,
            genreIds = model.genreIds,
            id = model.id,
            originalLanguage = model.originalLanguage,
            originalTitle = model.originalTitle,
            overview = model.overview,
            popularity = model.popularity,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            title = model.title,
            video = model.video,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount,
        )
    }

    override fun fromDomainMovieSearch(domainModel: MovieSearch): MovieSearchDto {
        return MovieSearchDto(
            adult = domainModel.adult,
            backdropPath = domainModel.backdropPath,
            genreIds = domainModel.genreIds,
            id = domainModel.id,
            originalLanguage = domainModel.originalLanguage,
            originalTitle = domainModel.originalTitle,
            overview = domainModel.overview,
            popularity = domainModel.popularity,
            posterPath = domainModel.posterPath,
            releaseDate = domainModel.releaseDate,
            title = domainModel.title,
            video = domainModel.video,
            voteAverage = domainModel.voteAverage,
            voteCount = domainModel.voteCount,
        )
    }

    override fun toDomainMovie(model: MovieDto): Movie {
        return Movie(
            adult = model.adult,
            backdropPath = model.backdropPath,
            belongsToCollection = model.belongsToCollection?.let { toDomainCollection(it) },
            budget = model.budget,
            genres = ,
            homepage = ,
            id = ,
            imdbId = ,
            originalLanguage = ,
            originalTitle = ,
            overview = ,
            poserPath = ,
            releaseDate = ,
            revenue = ,
            runtime = ,
            status = null,
            tagline = ,
            title = ,
            voteAverage = ,
            voteCount = ,
        )
    }

    override fun fromDomainMovie(domainModel: Movie): MovieDto {
        TODO("Not yet implemented")
    }

    override fun toDomainCollection(model: MovieDto.Collection): Movie.Collection {
        return Movie.Collection(
            model.id,
            model.name,
            model.posterPath,
            model.backdropPath
        )
    }

    override fun fromDomainCollection(domainModel: Movie.Collection): MovieDto.Collection {
        return MovieDto.Collection(
            domainModel.id,
            domainModel.name,
            domainModel.posterPath,
            domainModel.backdropPath
        )
    }
}
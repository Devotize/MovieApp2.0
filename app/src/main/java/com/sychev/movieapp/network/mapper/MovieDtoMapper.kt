package com.sychev.movieapp.network.mapper

import com.sychev.movieapp.domain.model.*
import com.sychev.movieapp.domain.model.Movie.Genre
import com.sychev.movieapp.domain.util.*
import com.sychev.movieapp.network.model.*
import com.sychev.movieapp.network.model.MovieDto.*
import com.sychev.movieapp.network.responses.CreditsResponse
import com.sychev.movieapp.network.responses.PersonMovieCreditsResponse
import com.sychev.movieapp.util.MediaType

class MovieDtoMapper
    : DomainMovieMapper<MovieDto, Movie>,
    DomainCollectionMapper<CollectionDto, Movie.Collection>,
        DomainGenreMapper<GenreDto, Genre>,
        DomainMultimediaMapper<MovieDto, Multimedia>
{

    override fun toDomainMovie(model: MovieDto): Movie{
        return Movie(
            adult = model.adult,
            backdropPath = model.backdropPath,
            belongsToCollection = model.belongsToCollection?.let { toDomainCollection(it) },
            budget = model.budget,
            genres = model.genres?.let{toDomainGenreList(it)},
            homepage = model.homepage,
            id = model.id,
            imdbId = model.imdbId,
            originalLanguage = model.originalLanguage,
            originalTitle = model.originalTitle,
            overview = model.overview,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            revenue = model.revenue,
            runtime = model.runtime,
            status = model.status,
            tagline = model.tagline,
            title = model.title,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount,
        )
    }

    override fun fromDomainMovie(domainModel: Movie): MovieDto {
        return MovieDto(
            adult = domainModel.adult,
            backdropPath = domainModel.backdropPath,
            belongsToCollection = domainModel.belongsToCollection?.let { fromDomainCollection(it) },
            budget = domainModel.budget,
            genres = domainModel.genres?.let{fromDomainGenreList(it)},
            homepage = domainModel.homepage,
            id = domainModel.id,
            imdbId = domainModel.imdbId,
            originalLanguage = domainModel.originalLanguage,
            originalTitle = domainModel.originalTitle,
            overview = domainModel.overview,
            posterPath = domainModel.posterPath,
            releaseDate = domainModel.releaseDate,
            revenue = domainModel.revenue,
            runtime = domainModel.runtime,
            status = domainModel.status,
            tagline = domainModel.tagline,
            title = domainModel.title,
            voteAverage = domainModel.voteAverage,
            voteCount = domainModel.voteCount,
            productCompanies = null,
            productionCountries = null,
        )
    }

    fun toDomainMovieList(list: List<MovieDto>): List<Movie> {
        return list.map{toDomainMovie(it)}
    }

    override fun toDomainCollection(model: CollectionDto): Movie.Collection {
        return Movie.Collection(
            model.id,
            model.name,
            model.posterPath,
            model.backdropPath
        )
    }

    override fun fromDomainCollection(domainModel: Movie.Collection): CollectionDto {
        return CollectionDto(
            domainModel.id,
            domainModel.name,
            domainModel.posterPath,
            domainModel.backdropPath
        )
    }

    override fun toDomainGenre(model: GenreDto): Genre {
        return Genre(
            model.id,
            model.name
        )
    }

    override fun fromDomainGenre(domainModel: Genre): GenreDto {
        return GenreDto(
            domainModel.id,
            domainModel.name
        )
    }

    override fun toDomainGenreList(list: List<GenreDto>): List<Genre> {
        return list.map {
            toDomainGenre(it)
        }
    }

    override fun fromDomainGenreList(domainList: List<Genre>): List<GenreDto> {
        return domainList.map{
            fromDomainGenre(it)
        }
    }
    fun toPersonMovieCredits(model: PersonMovieCreditsResponse): PersonMovieCredits {
        return PersonMovieCredits(
            cast = model.cast?.let { toDomainMovieList(it) },
            crew = model.crew?.let{toDomainMovieList(it)}
        )
    }

    override fun toDomainMultimedia(model: MovieDto): Multimedia {
        return Multimedia(
            posterPath = model.posterPath,
            overview = model.overview,
            releaseDate = model.releaseDate,
            originalTitle = model.originalTitle,
            id = model.id,
            mediaType = MediaType.MOVIE.name,
            title = model.title,
            backdropPath = model.backdropPath,
            popularity = model.popularity,
            voteCount = model.voteCount,
            voteAverage = model.voteAverage
        )
    }

    override fun fromDomainMultimedia(domainModel: Multimedia): MovieDto {
        return MovieDto(
            posterPath = domainModel.posterPath,
            overview = domainModel.overview,
            releaseDate = domainModel.releaseDate,
            originalTitle = domainModel.originalTitle,
            id = domainModel.id,
            title = domainModel.title,
            backdropPath = domainModel.backdropPath,
            popularity = domainModel.popularity,
            voteCount = domainModel.voteCount,
            voteAverage = domainModel.voteAverage,
        )
    }

    override fun toDomainMultimediaList(list: List<MovieDto>): List<Multimedia> {
        return list.map{toDomainMultimedia(it)}
    }

    override fun fromDomainMultimediaList(domainList: List<Multimedia>): List<MovieDto> {
        return domainList.map{fromDomainMultimedia(it)}
    }
}
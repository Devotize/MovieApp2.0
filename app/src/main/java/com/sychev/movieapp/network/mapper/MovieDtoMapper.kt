package com.sychev.movieapp.network.mapper

import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.Movie.Genre
import com.sychev.movieapp.domain.model.MovieSearch
import com.sychev.movieapp.domain.util.DomainCollectionMapper
import com.sychev.movieapp.domain.util.DomainGenreMapper
import com.sychev.movieapp.domain.util.DomainMovieMapper
import com.sychev.movieapp.domain.util.DomainMovieSearchMapper
import com.sychev.movieapp.network.model.MovieDto
import com.sychev.movieapp.network.model.MovieDto.*
import com.sychev.movieapp.network.model.MovieSearchDto

class MovieDtoMapper
    : DomainMovieSearchMapper<MovieSearchDto, MovieSearch>,
        DomainMovieMapper<MovieDto, Movie>,
        DomainCollectionMapper<CollectionDto, Movie.Collection>,
        DomainGenreMapper<GenreDto, Genre>


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

    override fun toDomainMovieList(list: List<MovieSearchDto>): List<MovieSearch> {
        return list.map{
            toDomainMovieSearch(it)
        }
    }

    override fun fromDomainMovieList(domainList: List<MovieSearch>): List<MovieSearchDto> {
        return domainList.map{
            fromDomainMovieSearch(it)
        }
    }
}
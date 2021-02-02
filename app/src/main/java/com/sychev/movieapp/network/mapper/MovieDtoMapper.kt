package com.sychev.movieapp.network.mapper

import com.sychev.movieapp.domain.model.*
import com.sychev.movieapp.domain.model.Movie.Genre
import com.sychev.movieapp.domain.util.*
import com.sychev.movieapp.network.model.*
import com.sychev.movieapp.network.model.MovieDto.*
import com.sychev.movieapp.network.responses.CreditsResponse
import com.sychev.movieapp.network.responses.PersonMovieCreditsResponse

class MovieDtoMapper
    : DomainMovieSearchMapper<MovieSearchDto, MovieSearch>,
        DomainMovieMapper<MovieDto, Movie>,
    DomainCollectionMapper<CollectionDto, Movie.Collection>,
        DomainGenreMapper<GenreDto, Genre>,
        DomainPersonCastMapper<CastDto, CrewDto, Person>,
        DomainCreditsMapper<CreditsResponse, MovieCredits>,
        DomainPersonMapper<PersonDto, Person>
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

    override fun toDomainPersonFromCastDto(model: CastDto): Person {
        return Person(
            gender = model.gender,
            id = model.id,
            knownForDepartment = model.knownForDepartment,
            name = model.name,
            popularity = model.popularity,
            profilePath = model.profilePath,
            character = model.character
        )
    }

    override fun fromDomainPersonToCastDto(domainModel: Person): CastDto {
        return CastDto(
            gender = domainModel.gender,
            id = domainModel.id,
            knownForDepartment = domainModel.knownForDepartment,
            name = domainModel.name,
            popularity = domainModel.popularity,
            profilePath = domainModel.profilePath
        )
    }

    override fun toDomainPersonFromCrewDto(model: CrewDto): Person {
        return Person(
            gender = model.gender,
            id = model.id,
            knownForDepartment = model.knownForDepartment,
            name = model.name,
            popularity = model.popularity,
            profilePath = model.profilePath,
            job = model.job
        )
    }

    override fun fromDomainPersonToCrewDto(domainModel: Person): CrewDto {
        return CrewDto(
            gender = domainModel.gender,
            id = domainModel.id,
            knownForDepartment = domainModel.knownForDepartment,
            name = domainModel.name,
            popularity = domainModel.popularity,
            profilePath = domainModel.profilePath
        )
    }

    override fun toDomainCredits(model: CreditsResponse): MovieCredits {
        val cast = model.cast?.map {
            toDomainPersonFromCastDto(it)
        }
        val crew = model.crew?.map{
            toDomainPersonFromCrewDto(it)
        }
        return MovieCredits(
            cast = cast,
            crew = crew
        )
    }

    override fun fromDomainCredits(domainModel: MovieCredits): CreditsResponse {
        val cast = domainModel.cast?.map {
            fromDomainPersonToCastDto(it)
        }
        val crew = domainModel.crew?.map{
            fromDomainPersonToCrewDto(it)
        }
        return CreditsResponse(
            cast = cast,
            crew = crew
        )
    }

    override fun toDomainPerson(model: PersonDto): Person {
        return Person(
            biography = model.biography,
            birthDay = model.birthDay,
            deathDay = model.deathDay,
            gender = model.gender,
            id = model.id,
            imdbId = model.imdbId,
            knownForDepartment = model.knownForDepartment,
            name = model.name,
            placeOfBirth = model.placeOfBirth,
            popularity = model.popularity,
            profilePath = model.profilePath,
        )
    }

    override fun fromDomainPerson(domainModel: Person): PersonDto {
        return PersonDto(
            biography = domainModel.biography,
            birthDay = domainModel.birthDay,
            deathDay = domainModel.deathDay,
            gender = domainModel.gender,
            id = domainModel.id,
            imdbId = domainModel.imdbId,
            knownForDepartment = domainModel.knownForDepartment,
            name = domainModel.name,
            placeOfBirth = domainModel.placeOfBirth,
            popularity = domainModel.popularity,
            profilePath = domainModel.profilePath,
        )
    }

    fun toPersonMovieCredits(model: PersonMovieCreditsResponse): PersonMovieCredits {
        return PersonMovieCredits(
            cast = model.cast?.let { toDomainMovieList(it) },
            crew = model.crew?.let{toDomainMovieList(it)}
        )
    }
}
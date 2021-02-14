package com.sychev.movieapp.domain.util

interface DomainMovieMapper <T, DomainMovieModel> {

    fun toDomainMovie(model: T): DomainMovieModel

    fun fromDomainMovie(domainModel: DomainMovieModel): T

}

interface DomainCollectionMapper<CollectionDto, DomainCollection> {

    fun toDomainCollection(model: CollectionDto): DomainCollection

    fun fromDomainCollection(domainModel: DomainCollection): CollectionDto
}

interface DomainGenreMapper<GenreDto, Genre> {

    fun toDomainGenre(model: GenreDto): Genre

    fun fromDomainGenre(domainModel: Genre): GenreDto

    fun toDomainGenreList(list: List<GenreDto>): List<Genre>

    fun fromDomainGenreList(domainList: List<Genre>): List<GenreDto>
}

interface DomainPersonCastMapper<CastDto, CrewDto, DomainModel> {

    fun toDomainPersonFromCastDto(model: CastDto): DomainModel

    fun fromDomainPersonToCastDto(domainModel: DomainModel): CastDto

    fun toDomainPersonFromCrewDto(model: CrewDto): DomainModel

    fun fromDomainPersonToCrewDto(domainModel: DomainModel): CrewDto
}

interface DomainCreditsMapper<S, DomainModel> {
    fun toDomainCredits(model: S): DomainModel

    fun fromDomainCredits(domainModel: DomainModel): S
}

interface DomainPersonMapper<T, DomainModel> {
    fun toDomainPerson(model: T): DomainModel

    fun fromDomainPerson(domainModel: DomainModel): T
}

interface DomainTvShowMapper<T, DomainModel> {
    fun toDomainTvShow(model: T): DomainModel

}

interface DomainMultimediaMapper<T, DomainModel> {
    fun toDomainMultimedia(model: T): DomainModel

    fun fromDomainMultimedia(domainModel: DomainModel): T

    fun toDomainMultimediaList(list: List<T>): List<DomainModel>

    fun fromDomainMultimediaList(domainList: List<DomainModel>): List<T>
}


























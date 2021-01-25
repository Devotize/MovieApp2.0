package com.sychev.movieapp.domain.util

interface DomainMovieMapper <T, DomainMovieModel> {

    fun toDomainMovie(model: T): DomainMovieModel

    fun fromDomainMovie(domainModel: DomainMovieModel): T

}

interface DomainMovieSearchMapper<T,DomainMovieSearch> {

    fun toDomainMovieSearch(model: T): DomainMovieSearch

    fun fromDomainMovieSearch(domainModel: DomainMovieSearch): T


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
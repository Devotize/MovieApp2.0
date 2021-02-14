package com.sychev.movieapp.cache.mapper

import com.sychev.movieapp.cache.model.MultimediaEntity
import com.sychev.movieapp.domain.model.Movie
import com.sychev.movieapp.domain.model.Multimedia
import com.sychev.movieapp.domain.util.DomainMovieMapper
import com.sychev.movieapp.domain.util.DomainMultimediaMapper

class MultimediaEntityMapper
    :
        DomainMultimediaMapper<MultimediaEntity, Multimedia>,
        DomainMovieMapper<MultimediaEntity, Movie>
{

    override fun toDomainMultimedia(model: MultimediaEntity): Multimedia {
        return Multimedia(
            id = model.id,
            watchStatus = model.watchStatus,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            title = model.title,
            voteAverage = model.voteAverage
        )
    }

    override fun fromDomainMultimedia(domainModel: Multimedia): MultimediaEntity {
        return MultimediaEntity(
            id = domainModel.id ?: -1,
            watchStatus = domainModel.watchStatus ?: false,
            posterPath = domainModel.posterPath,
            releaseDate = domainModel.releaseDate,
            title = domainModel.title,
            voteAverage = domainModel.voteAverage
        )
    }

    override fun toDomainMultimediaList(list: List<MultimediaEntity>): List<Multimedia> {
        return list.map{toDomainMultimedia(it)}
    }

    override fun fromDomainMultimediaList(domainList: List<Multimedia>): List<MultimediaEntity> {
        return domainList.map { fromDomainMultimedia(it) }
    }

    override fun toDomainMovie(model: MultimediaEntity): Movie {
        return Movie(
            id = model.id,
            watchStatus = model.watchStatus,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            title = model.title,
            voteAverage = model.voteAverage
        )
    }

    override fun fromDomainMovie(domainModel: Movie): MultimediaEntity {
        return MultimediaEntity(
            id = domainModel.id ?: -1,
            watchStatus = domainModel.watchStatus ?: false,
            posterPath = domainModel.posterPath,
            releaseDate = domainModel.releaseDate,
            title = domainModel.title,
            voteAverage = domainModel.voteAverage
        )
    }


}
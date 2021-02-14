package com.sychev.movieapp.network.mapper

import com.sychev.movieapp.domain.model.Multimedia
import com.sychev.movieapp.domain.util.DomainMultimediaMapper
import com.sychev.movieapp.network.model.MultimediaDto

class MultimediaDtoMapper: DomainMultimediaMapper<MultimediaDto, Multimedia> {
    override fun toDomainMultimedia(model: MultimediaDto): Multimedia {
        return Multimedia(
            posterPath = model.posterPath,
            overview = model.overview,
            releaseDate = model.releaseDate,
            originalTitle = model.originalTitle,
            id = model.id,
            mediaType = model.mediaType,
            title = model.title,
            backdropPath = model.backdropPath,
            popularity = model.popularity,
            voteCount = model.voteCount,
            voteAverage = model.voteAverage,
            name = model.name,
            profilePath = model.profilePath
        )
    }

    override fun fromDomainMultimedia(domainModel: Multimedia): MultimediaDto {
        return MultimediaDto(
            posterPath = domainModel.posterPath,
            overview = domainModel.overview,
            releaseDate = domainModel.releaseDate,
            originalTitle = domainModel.originalTitle,
            id = domainModel.id,
            mediaType = domainModel.mediaType,
            title = domainModel.title,
            backdropPath = domainModel.backdropPath,
            popularity = domainModel.popularity,
            voteCount = domainModel.voteCount,
            voteAverage = domainModel.voteAverage,
            name = domainModel.name,
            profilePath = domainModel.profilePath
        )
    }

    override fun toDomainMultimediaList(list: List<MultimediaDto>): List<Multimedia> {
        return list.map{toDomainMultimedia(it)}
    }

    override fun fromDomainMultimediaList(domainList: List<Multimedia>): List<MultimediaDto> {
       return domainList.map{fromDomainMultimedia(it)}
    }
}
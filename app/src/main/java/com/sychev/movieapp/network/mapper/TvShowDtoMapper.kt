package com.sychev.movieapp.network.mapper

import com.sychev.movieapp.domain.model.Movie.Genre
import com.sychev.movieapp.domain.model.Multimedia
import com.sychev.movieapp.domain.model.TvShow
import com.sychev.movieapp.domain.model.TvShow.Creator
import com.sychev.movieapp.domain.util.DomainMultimediaMapper
import com.sychev.movieapp.domain.util.DomainTvShowMapper
import com.sychev.movieapp.network.model.MovieDto.GenreDto
import com.sychev.movieapp.network.model.TvShowDto
import com.sychev.movieapp.network.model.TvShowDto.CreatorDto
import com.sychev.movieapp.util.MediaType

class TvShowDtoMapper:
    DomainTvShowMapper<TvShowDto, TvShow>,
        DomainMultimediaMapper<TvShowDto, Multimedia>
{
    override fun toDomainTvShow(model: TvShowDto): TvShow {
        return TvShow(
            backdropPath = model.backdropPath,
            createdBy = model.createdBy?.let { toCreatorList(it) },
            episodeRunTime = model.episodeRunTime,
            firstAirDate = model.firstAirDate,
            genres = toDomainGenreList(model.genres),
            homePage = model.homePage,
            id = model.id,
            inProduction = model.inProduction,
            lastAirDate = model.lastAirDate,
            lastEpisodeToAir = model.lasEpisodeToAir?.let { toDomainEpisode(it) },
            name = model.name,
            nextEpisodeToAir = model.nextEpisodeToAir?.let { toDomainEpisode(it) },
            networks = model.networks?.let { toDomainNetworkList(it) },
            numberOfEpisodes = model.numberOfEpisodes,
            numberOfSeasons = model.numberOfSeasons,
            originName = model.originName,
            overview = model.overview,
            popularity = model.popularity,
            posterPath = model.posterPath,
            seasons = model.seasons?.let{toDomainSeasonList(it)},
            status = model.status,
            tagline = model.tagline,
            type = model.type,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    }


    private fun toCreatorModel(model: CreatorDto): Creator {
        return Creator(
            id = model.id,
            creditId = model.creditId,
            name = model.name,
            gender = model.gender,
            profilePath = model.profilePath
        )
    }

    private fun toCreatorList(list: List<CreatorDto>): List<Creator> {
        return list.map{toCreatorModel(it)}
    }

    private fun toDomainGenre(model: GenreDto): Genre {
        return Genre(
            model.id,
            model.name
        )
    }

    private fun toDomainGenreList(list: List<GenreDto>): List<Genre> {
        return list.map{toDomainGenre(it)}
    }

    private fun toDomainEpisode(model: TvShowDto.EpisodeDto): TvShow.Episode {
        return TvShow.Episode(
            airDate = model.airDate,
            episodeNumber = model.episodeNumber,
            id = model.id,
            name = model.name,
            overview = model.overview,
            productionCode = model.productionCode,
            seasonNumber = model.seasonNumber,
            stillPath = model.stillPath,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    }

    private fun toDomainNetwork(model: TvShowDto.NetworkDto): TvShow.Network {
        return TvShow.Network(
            name = model.name,
            id = model.id,
            logoPath = model.logoPath,
            originCountry = model.originCountry
        )
    }

    private fun toDomainNetworkList(list: List<TvShowDto.NetworkDto>): List<TvShow.Network> {
        return list.map {toDomainNetwork(it)}
    }

    private fun toDomainSeason(model: TvShowDto.SeasonDto): TvShow.Season{
        return TvShow.Season(
            airDate = model.airDate,
            episodeCount = model.episodeCount,
            id = model.id,
            name = model.name,
            overview = model.overview,
            posterPath = model.posterPath,
            seasonNumber = model.seasonNumber
        )
    }

    private fun toDomainSeasonList(list: List<TvShowDto.SeasonDto>): List<TvShow.Season>{
        return list.map { toDomainSeason(it) }
    }

    fun toDomainTvShowList(list: List<TvShowDto>): List<TvShow> {
        return list.map{toDomainTvShow(it)}
    }

    override fun toDomainMultimedia(model: TvShowDto): Multimedia {
        return Multimedia(
            posterPath = model.posterPath,
            overview = model.overview,
            id = model.id,
            mediaType = MediaType.TV.name,
            backdropPath = model.backdropPath,
            popularity = model.popularity,
            voteCount = model.voteCount,
            voteAverage = model.voteAverage,
            name = model.name,
        )
    }

    override fun fromDomainMultimedia(domainModel: Multimedia): TvShowDto {
        return TvShowDto(
            posterPath = domainModel.posterPath,
            overview = domainModel.overview,
            id = domainModel.id,
            backdropPath = domainModel.backdropPath,
            popularity = domainModel.popularity,
            voteCount = domainModel.voteCount,
            voteAverage = domainModel.voteAverage,
            name = domainModel.name,
        )
    }

    override fun toDomainMultimediaList(list: List<TvShowDto>): List<Multimedia> {
        return list.map{toDomainMultimedia(it)}
    }

    override fun fromDomainMultimediaList(domainList: List<Multimedia>): List<TvShowDto> {
        return domainList.map{fromDomainMultimedia(it)}
    }

}




















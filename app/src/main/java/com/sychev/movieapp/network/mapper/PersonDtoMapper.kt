package com.sychev.movieapp.network.mapper

import com.sychev.movieapp.domain.model.MovieCredits
import com.sychev.movieapp.domain.model.Multimedia
import com.sychev.movieapp.domain.model.Person
import com.sychev.movieapp.domain.util.DomainCreditsMapper
import com.sychev.movieapp.domain.util.DomainMultimediaMapper
import com.sychev.movieapp.domain.util.DomainPersonCastMapper
import com.sychev.movieapp.domain.util.DomainPersonMapper
import com.sychev.movieapp.network.model.CastDto
import com.sychev.movieapp.network.model.CrewDto
import com.sychev.movieapp.network.model.PersonDto
import com.sychev.movieapp.network.responses.CreditsResponse
import com.sychev.movieapp.util.MediaType

class PersonDtoMapper
    : DomainPersonMapper<PersonDto, Person>,
    DomainPersonCastMapper<CastDto, CrewDto, Person>,
    DomainCreditsMapper<CreditsResponse, MovieCredits>,
        DomainMultimediaMapper<PersonDto, Multimedia>

{
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

    override fun toDomainMultimedia(model: PersonDto): Multimedia {
        return Multimedia(
            id = model.id,
            mediaType = MediaType.PERSON.typeString,
            popularity = model.popularity,
            name = model.name,
            profilePath = model.profilePath
        )
    }

    override fun fromDomainMultimedia(domainModel: Multimedia): PersonDto {
        return PersonDto(
            id = domainModel.id,
            popularity = domainModel.popularity,
            name = domainModel.name,
            profilePath = domainModel.profilePath
        )
    }

    override fun toDomainMultimediaList(list: List<PersonDto>): List<Multimedia> {
        return list.map{toDomainMultimedia(it)}
    }

    override fun fromDomainMultimediaList(domainList: List<Multimedia>): List<PersonDto> {
        return domainList.map{fromDomainMultimedia(it)}
    }
}
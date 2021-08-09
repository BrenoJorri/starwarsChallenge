package com.trivago.mapper.detail

import com.trivago.core.base.BaseMapper
import com.trivago.domain.detail.model.FilmDomain
import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.entity.FilmResponse
import com.trivago.entity.HomeWorldResponse
import com.trivago.entity.SpeciesResponse

class CharacterFilmMapper : BaseMapper.ToDomain<FilmResponse, FilmDomain> {

    override fun mapToDomain(entity: FilmResponse): FilmDomain =
        FilmDomain(entity.title, entity.description)
}

class CharacterSpeciesMapper : BaseMapper.ToDomain<SpeciesResponse, SpeciesDomain> {

    override fun mapToDomain(entity: SpeciesResponse): SpeciesDomain =
        SpeciesDomain(entity.name, entity.language)
}

class CharacterHomeWorldMapper : BaseMapper.ToDomain<HomeWorldResponse, HomeWorldDomain> {

    override fun mapToDomain(entity: HomeWorldResponse): HomeWorldDomain =
        HomeWorldDomain(entity.name, entity.population)
}
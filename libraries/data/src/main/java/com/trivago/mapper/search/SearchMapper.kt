package com.trivago.mapper.search

import com.trivago.core.base.BaseMapper
import com.trivago.domain.search.model.CharacterDomain
import com.trivago.domain.search.model.SearchDomain
import com.trivago.entity.SearchResponse

class SearchMapper : BaseMapper.ToDomain<SearchResponse, SearchDomain> {

    override fun mapToDomain(entity: SearchResponse): SearchDomain {
        val characterDomainList = entity.results.map { characterResponse ->
            CharacterDomain(
                characterResponse.name,
                characterResponse.height,
                characterResponse.birthYear,
                characterResponse.homeworld,
                characterResponse.films,
                characterResponse.species
            )
        }
        return SearchDomain(characterDomainList)
    }
}
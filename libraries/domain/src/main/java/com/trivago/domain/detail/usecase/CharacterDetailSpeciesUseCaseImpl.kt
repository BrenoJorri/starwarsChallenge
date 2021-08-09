package com.trivago.domain.detail.usecase

import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.domain.detail.repository.CharacterDetailRepository
import kotlinx.coroutines.flow.Flow

class CharacterDetailSpeciesUseCaseImpl(
    private val repository: CharacterDetailRepository
) : CharacterDetailSpeciesUseCase {

    override suspend fun getSpecies(url: String): Flow<SpeciesDomain?> =
        repository.getSpecies(url)
}
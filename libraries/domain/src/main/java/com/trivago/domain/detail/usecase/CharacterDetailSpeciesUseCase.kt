package com.trivago.domain.detail.usecase

import com.trivago.domain.detail.model.SpeciesDomain
import kotlinx.coroutines.flow.Flow

interface CharacterDetailSpeciesUseCase {

    suspend fun getSpecies(url: String): Flow<SpeciesDomain?>
}
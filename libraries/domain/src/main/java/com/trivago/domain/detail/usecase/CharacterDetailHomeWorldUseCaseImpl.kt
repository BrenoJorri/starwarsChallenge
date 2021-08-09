package com.trivago.domain.detail.usecase

import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.repository.CharacterDetailRepository
import kotlinx.coroutines.flow.Flow

class CharacterDetailHomeWorldUseCaseImpl(
    private val repository: CharacterDetailRepository
) : CharacterDetailHomeWorldUseCase {

    override suspend fun getHomeWorld(homeWorld: String): Flow<HomeWorldDomain?> =
        repository.getHomeWorld(homeWorld)
}
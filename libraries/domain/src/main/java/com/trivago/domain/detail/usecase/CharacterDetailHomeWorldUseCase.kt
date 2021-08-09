package com.trivago.domain.detail.usecase

import com.trivago.domain.detail.model.HomeWorldDomain
import kotlinx.coroutines.flow.Flow

interface CharacterDetailHomeWorldUseCase {

    suspend fun getHomeWorld(homeWorld: String): Flow<HomeWorldDomain?>
}
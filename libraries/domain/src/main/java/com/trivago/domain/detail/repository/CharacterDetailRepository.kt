package com.trivago.domain.detail.repository

import com.trivago.domain.detail.model.FilmDomain
import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.model.SpeciesDomain
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRepository {

    suspend fun getFilms(url: String): Flow<FilmDomain?>
    suspend fun getSpecies(url: String): Flow<SpeciesDomain?>
    suspend fun getHomeWorld(homeWorld: String): Flow<HomeWorldDomain?>
}

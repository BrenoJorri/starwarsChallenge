package com.trivago

import com.trivago.core.base.BaseRepository
import com.trivago.core.extesion.toSecUrl
import com.trivago.core.extesion.transform
import com.trivago.domain.detail.model.FilmDomain
import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.domain.detail.repository.CharacterDetailRepository
import com.trivago.mapper.detail.CharacterFilmMapper
import com.trivago.mapper.detail.CharacterHomeWorldMapper
import com.trivago.mapper.detail.CharacterSpeciesMapper
import com.trivago.remote.CharacterDetailApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class CharacterDetailRepositoryImpl(
    private val characterDetailApi: CharacterDetailApi,
    private val filmMapper: CharacterFilmMapper,
    private val speciesMapper: CharacterSpeciesMapper,
    private val homeWorldMapper: CharacterHomeWorldMapper,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : CharacterDetailRepository, BaseRepository() {

    override suspend fun getFilms(url: String): Flow<FilmDomain?> {
        return flow {
            val result = networkBoundResource(
                makeApiCall = {
                    characterDetailApi.getFilms(url.toSecUrl())
                }
            ).transform { filmResponse -> filmMapper.mapToDomain(filmResponse) }

            emit(result)

        }.flowOn(dispatcher)
    }

    override suspend fun getSpecies(url: String): Flow<SpeciesDomain?> {
        return flow {
            val result = networkBoundResource(
                makeApiCall = {
                    characterDetailApi.getSpecies(url.toSecUrl())
                }
            ).transform { speciesResponse -> speciesMapper.mapToDomain(speciesResponse) }

            emit(result)

        }.flowOn(dispatcher)
    }

    override suspend fun getHomeWorld(homeWorld: String): Flow<HomeWorldDomain?> {
        return flow {
            val result = networkBoundResource(
                makeApiCall = {
                    characterDetailApi.getHomeWorld(homeWorld.toSecUrl())
                }
            ).transform { homeWorldResponse -> homeWorldMapper.mapToDomain(homeWorldResponse) }

            emit(result)

        }.flowOn(dispatcher)
    }

}

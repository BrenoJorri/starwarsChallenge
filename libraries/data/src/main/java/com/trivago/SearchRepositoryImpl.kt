package com.trivago

import com.trivago.core.base.BaseRepository
import com.trivago.core.extesion.transform
import com.trivago.domain.search.model.SearchDomain
import com.trivago.domain.search.repository.SearchRepository
import com.trivago.mapper.search.SearchMapper
import com.trivago.remote.SearchApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepositoryImpl(
    private val searchApi: SearchApi,
    private val mapper: SearchMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SearchRepository, BaseRepository() {

    override suspend fun search(query: String): Flow<SearchDomain?> {
        return flow {
            val result = networkBoundResource(
                makeApiCall = {
                    searchApi.search(query)
                }).transform { searchResponse -> mapper.mapToDomain(searchResponse) }

            emit(result)
        }.flowOn(dispatcher)
    }
}

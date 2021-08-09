package com.trivago.domain.search.repository

import com.trivago.domain.search.model.SearchDomain
import kotlinx.coroutines.flow.Flow

interface  SearchRepository {

    suspend fun search(query: String): Flow<SearchDomain?>
}

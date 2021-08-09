package com.trivago.domain.search.usecase

import com.trivago.domain.search.model.SearchDomain
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {

    suspend fun search(query: String): Flow<SearchDomain?>

}

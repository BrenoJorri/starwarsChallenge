package com.trivago.domain.search.usecase

import com.trivago.domain.search.repository.SearchRepository

class SearchUseCaseImpl(private val repository: SearchRepository) : SearchUseCase {

    override suspend fun search(query: String) = repository.search(query)
}
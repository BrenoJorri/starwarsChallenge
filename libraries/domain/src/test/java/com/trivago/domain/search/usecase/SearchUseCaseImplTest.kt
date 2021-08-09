package com.trivago.domain.search.usecase

import com.trivago.domain.search.model.CharacterDomain
import com.trivago.domain.search.model.SearchDomain
import com.trivago.domain.search.repository.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchUseCaseImplTest {

    @MockK
    private lateinit var repository: SearchRepository

    private lateinit var searchUseCase: SearchUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        searchUseCase = SearchUseCaseImpl(repository)
    }

    @Test
    fun `search should return success`() = runBlocking {
        val query = "irrelevant"
        val searchDomain = SearchDomain(listOf(CharacterDomain("", "", "", "", listOf(), listOf())))
        var result: SearchDomain? = null
        val flow = flow {
            emit(searchDomain)
        }

        coEvery { repository.search(query) }.returns(flow)
        val search = searchUseCase.search(query)

        search.collect {
            result = it
        }

        coVerify { repository.search(query) }
        assertEquals(searchDomain, result)
    }
}
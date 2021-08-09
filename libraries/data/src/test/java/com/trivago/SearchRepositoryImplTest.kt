package com.trivago

import com.trivago.domain.search.model.SearchDomain
import com.trivago.entity.SearchResponse
import com.trivago.mapper.search.SearchMapper
import com.trivago.remote.SearchApi
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {

    @MockK
    private lateinit var mapper: SearchMapper

    @MockK
    private lateinit var searchApi: SearchApi

    private lateinit var repository: SearchRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = SearchRepositoryImpl(searchApi, mapper)
    }

    @Test
    fun `search should return success`() = runBlocking {
        val query = "irrelevant"
        val searchResponse = mockk<SearchResponse>(relaxed = true)
        val searchDomain = mockk<SearchDomain>(relaxed = true)
        every { mapper.mapToDomain(searchResponse) }.returns(searchDomain)
        coEvery { searchApi.search(query) }.returns(searchResponse)
        val search = repository.search(query)

        val first = search.first()

        assertEquals(searchDomain, first)
        verify { mapper.mapToDomain(searchResponse) }
        coVerify { searchApi.search(query) }
    }
}
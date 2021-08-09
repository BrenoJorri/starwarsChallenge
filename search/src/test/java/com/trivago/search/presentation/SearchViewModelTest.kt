package com.trivago.search.presentation

import com.trivago.core.result.Resource
import com.trivago.domain.search.model.CharacterDomain
import com.trivago.domain.search.model.SearchDomain
import com.trivago.domain.search.usecase.SearchUseCase
import com.trivago.search.core.exception.InvalidQueryException
import com.trivago.search.util.getOrAwaitValue
import com.trivago.search.util.instantLiveDataAndCoroutineRules
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    @MockK
    private lateinit var useCase: DummyUseCase

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        searchViewModel = SearchViewModel(useCase)
        mockkObject(Resource)
    }

    @Test
    fun `performSearch should return Success`() = runBlocking {
        val query = "irrelevant"
        val searchDomain = SearchDomain(listOf(CharacterDomain("", "", "", "", listOf(), listOf())))
        val flow = flow {
            emit(searchDomain)
        }
        coEvery { useCase.search(query) }.returns(flow)

        searchViewModel.performSearch(MutableStateFlow(query))
        delay(800)

        coVerify(exactly = 1) { useCase.search(query) }
        assertEquals(
            searchViewModel.searchLiveData.getOrAwaitValue(),
            Resource.success(searchDomain)
        )
    }

    @Test
    fun `performSearch should return empty if list character is empty`() = runBlocking {
        val query = "irrelevant"
        val searchDomain = SearchDomain(listOf())
        val flow = flow {
            emit(searchDomain)
        }
        coEvery { useCase.search(query) }.returns(flow)
        searchViewModel.performSearch(MutableStateFlow(query))
        delay(800)
        coVerify { useCase.search(query) }
        verify(exactly = 0) { Resource.success(searchDomain) }
        assertEquals(
            searchViewModel.searchLiveData.getOrAwaitValue(), Resource.empty(null)
        )
    }

    @Test
    fun `performSearch should return BadFormat if searchDomain is null`() = runBlocking {
        val query = "irrelevant"
        val searchDomain = SearchDomain(listOf())
        val flow = flow {
            emit(null)
        }
        coEvery { useCase.search(query) }.returns(flow)
        searchViewModel.performSearch(MutableStateFlow(query))
        delay(800)
        coVerify { useCase.search(query) }
        verify(exactly = 0) { Resource.success(searchDomain) }
        assertEquals(
            searchViewModel.searchLiveData.getOrAwaitValue().status,
            Resource.error<Throwable>(InvalidQueryException()).status
        )
    }

    class DummyUseCase : SearchUseCase {
        override suspend fun search(query: String): Flow<SearchDomain?> {
            return flow {
                emit(SearchDomain(listOf()))
            }
        }

    }

}
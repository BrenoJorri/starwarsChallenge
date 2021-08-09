package com.trivago.search

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.trivago.core.result.Resource
import com.trivago.domain.search.model.CharacterDomain
import com.trivago.domain.search.model.SearchDomain
import com.trivago.domain.search.repository.SearchRepository
import com.trivago.domain.search.usecase.SearchUseCase
import com.trivago.domain.search.usecase.SearchUseCaseImpl
import com.trivago.search.presentation.SearchViewModel
import com.trivago.search.util.instantLiveDataAndCoroutineRules
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchFeature {

    private lateinit var repository: SearchRepository

    private lateinit var useCase: SearchUseCase

    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    private lateinit var searchViewModel: SearchViewModel

    private val searchDomain =
        SearchDomain(
            listOf(
                CharacterDomain(
                    "",
                    "",
                    "",
                    "",
                    listOf(),
                    listOf()
                )
            )
        )

    private lateinit var uiController: SpyUiController

    @Before
    fun setUp() {
        repository = FakeRepository()
        useCase = SearchUseCaseImpl(repository)
        searchViewModel = SearchViewModel(useCase)
        uiController = SpyUiController().also {
            it.searcher = searchViewModel
        }
        uiController.onCreate()
        mockkObject(Resource)
    }

    @Test
    fun performSearch() = runBlocking {
        val query = "irrelevant"
        searchViewModel.performSearch(MutableStateFlow(query))
        verify { Resource.loading<Nothing>() }
        verify { Resource.success(searchDomain) }
    }

    class SpyUiController : LifecycleOwner {

        private lateinit var lifecycleRegistry: LifecycleRegistry

        val renderedStates = mutableListOf<Resource<SearchDomain>>()

        lateinit var searcher: SearchViewModel

        fun submit(query: MutableStateFlow<String>) {
            searcher.performSearch(query)
        }

        fun onCreate() {
            lifecycleRegistry = LifecycleRegistry(this)
            lifecycleRegistry.currentState = Lifecycle.State.STARTED
            searcher.searchLiveData.observe(this) {
                renderedStates.add(it)
            }
        }

        override fun getLifecycle(): Lifecycle {
            return lifecycleRegistry
        }
    }

    class FakeRepository : SearchRepository {
        override suspend fun search(query: String): Flow<SearchDomain?> {
            return flow {
                emit(
                    SearchDomain(
                        listOf(
                            CharacterDomain(
                                "",
                                "",
                                "",
                                "",
                                listOf(),
                                listOf()
                            )
                        )
                    )
                )
            }
        }
    }
}

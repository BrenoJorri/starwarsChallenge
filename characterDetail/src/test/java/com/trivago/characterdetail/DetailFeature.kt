package com.trivago.characterdetail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.trivago.characterdetail.presentation.DetailViewModel
import com.trivago.characterdetail.util.instantLiveDataAndCoroutineRules
import com.trivago.core.result.Resource
import com.trivago.domain.detail.model.FilmDomain
import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.domain.detail.repository.CharacterDetailRepository
import com.trivago.domain.detail.usecase.*
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailFeature {

    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    private lateinit var repository: CharacterDetailRepository

    private lateinit var speciesUseCase: CharacterDetailSpeciesUseCase

    private lateinit var filmUseCase: CharacterDetailFilmUseCase

    private lateinit var homeWorldUseCase: CharacterDetailHomeWorldUseCase

    private val homeWorldDomain = HomeWorldDomain("", "")

    private val speciesDomain = listOf(SpeciesDomain("", ""))

    private val filmDomain = listOf(FilmDomain("", ""))

    private lateinit var detailViewModel: DetailViewModel

    private lateinit var uiController: SpyUiController

    @Before
    fun setUp() {
        repository = FakeRepository()
        filmUseCase = CharacterDetailFilmUseCaseImpl(repository)
        speciesUseCase = CharacterDetailSpeciesUseCaseImpl(repository)
        homeWorldUseCase = CharacterDetailHomeWorldUseCaseImpl(repository)
        detailViewModel = DetailViewModel(speciesUseCase, filmUseCase, homeWorldUseCase)
        uiController = SpyUiController().also {
            it.detailViewModel = detailViewModel
        }
        uiController.onCreate()
        mockkObject(Resource)
    }

    @Test
    fun performDetailFeature() {
        val films = listOf("irrelevant")
        val species = listOf("irrelevant")
        val homeWorld = "irrelevant"

        detailViewModel.getDetails(films, species, homeWorld)

        verify { Resource.loading<Nothing>() }
        verify { Resource.success(filmDomain) }
        verify { Resource.success(speciesDomain) }
        verify { Resource.success(homeWorldDomain) }
    }


    class SpyUiController : LifecycleOwner {

        private lateinit var lifecycleRegistry: LifecycleRegistry

        val renderedStates = mutableListOf<Resource<List<SpeciesDomain>>>()

        lateinit var detailViewModel: DetailViewModel

        fun submit(films: List<String>, species: List<String>, homeWorld: String) {
            detailViewModel.getDetails(films, species, homeWorld)
        }

        fun onCreate() {
            lifecycleRegistry = LifecycleRegistry(this)
            lifecycleRegistry.currentState = Lifecycle.State.STARTED
            detailViewModel.speciesLiveData.observe(this) {
                renderedStates.add(it)
            }
        }

        override fun getLifecycle(): Lifecycle {
            return lifecycleRegistry
        }
    }

    class FakeRepository : CharacterDetailRepository {
        override suspend fun getFilms(url: String) = flow {
            emit(FilmDomain("", ""))
        }

        override suspend fun getSpecies(url: String) = flow {
            emit(SpeciesDomain("", ""))
        }

        override suspend fun getHomeWorld(homeWorld: String) = flow {
            emit(HomeWorldDomain("", ""))
        }


    }
}
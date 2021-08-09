package com.trivago.characterdetail.presentation

import com.trivago.characterdetail.util.instantLiveDataAndCoroutineRules
import com.trivago.core.result.Resource
import com.trivago.domain.detail.model.FilmDomain
import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.domain.detail.usecase.CharacterDetailFilmUseCase
import com.trivago.domain.detail.usecase.CharacterDetailHomeWorldUseCase
import com.trivago.domain.detail.usecase.CharacterDetailSpeciesUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @MockK
    private lateinit var speciesUseCase: CharacterDetailSpeciesUseCase

    @MockK
    private lateinit var filmUseCase: CharacterDetailFilmUseCase

    @MockK
    private lateinit var homeWorldUseCase: CharacterDetailHomeWorldUseCase

    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        detailViewModel = DetailViewModel(speciesUseCase, filmUseCase, homeWorldUseCase)
        mockkObject(Resource)
    }

    @Test
    fun `getFilms should return success`() {
        val url = "irrelevant"
        val filmDomain = FilmDomain("", "")
        val filmDomainList = listOf(filmDomain)

        val flow = flow {
            emit(filmDomain)
        }

        coEvery { filmUseCase.getFilms(url) }.returns(flow)

        detailViewModel.getFilms(listOf("irrelevant"))

        coVerify { filmUseCase.getFilms(url) }
        verify(exactly = 1) { Resource.loading<Nothing>() }
        verify { Resource.success(filmDomainList) }
    }

    @Test
    fun `getSpecies should return success`() {
        val url = "irrelevant"
        val species = SpeciesDomain("", "")
        val speciesList = listOf(species)

        val flow = flow {
            emit(species)
        }

        coEvery { speciesUseCase.getSpecies(url) }.returns(flow)

        detailViewModel.getSpecies(listOf("irrelevant"))

        coVerify { speciesUseCase.getSpecies(url) }
        verify(exactly = 1) { Resource.loading<Nothing>() }
        verify { Resource.success(speciesList) }
    }

    @Test
    fun `getSpecies should return empty`() {
        val url = "irrelevant"
        coEvery { speciesUseCase.getSpecies(url) }.returns(MutableStateFlow(null))

        detailViewModel.getSpecies(listOf("irrelevant"))

        coVerify { speciesUseCase.getSpecies(url) }
        verify(exactly = 1) { Resource.loading<Nothing>() }
        verify(exactly = 1) { Resource.empty(listOf<Nothing>()) }
    }

    @Test
    fun `getHomeWorld should return success`() {
        val url = "irrelevant"
        val homeWorld = HomeWorldDomain("", "")

        val flow = flow {
            emit(homeWorld)
        }

        coEvery { homeWorldUseCase.getHomeWorld(url) }.returns(flow)

        detailViewModel.getHomeWorld(url)

        coVerify { homeWorldUseCase.getHomeWorld(url) }
        verify(exactly = 1) { Resource.loading<Nothing>() }
        verify { Resource.success(homeWorld) }
    }

}
package com.trivago.domain.detail.usecase

import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.domain.detail.repository.CharacterDetailRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CharacterDetailSpeciesUseCaseImplTest {


    private lateinit var speciesUseCase: CharacterDetailSpeciesUseCase

    @MockK
    private lateinit var repository: CharacterDetailRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        speciesUseCase = CharacterDetailSpeciesUseCaseImpl(repository)
    }

    @Test
    fun `getSpecies should return flow of FilmDomain`() = runBlocking {
        val url = "irrelevant"
        val speciesDomain = mockk<SpeciesDomain>(relaxed = true)
        val flow = flow {
            emit(speciesDomain)
        }
        coEvery { repository.getSpecies(url) }.returns(flow)

        speciesUseCase.getSpecies(url)

        coVerify { repository.getSpecies(url) }
    }
}
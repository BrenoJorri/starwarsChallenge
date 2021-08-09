package com.trivago

import com.trivago.domain.detail.model.FilmDomain
import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.domain.detail.repository.CharacterDetailRepository
import com.trivago.entity.FilmResponse
import com.trivago.entity.HomeWorldResponse
import com.trivago.entity.SpeciesResponse
import com.trivago.mapper.detail.CharacterFilmMapper
import com.trivago.mapper.detail.CharacterHomeWorldMapper
import com.trivago.mapper.detail.CharacterSpeciesMapper
import com.trivago.remote.CharacterDetailApi
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterDetailRepositoryImplTest {

    @MockK
    private lateinit var filmsMapper: CharacterFilmMapper

    @MockK
    private lateinit var speciesMapper: CharacterSpeciesMapper

    @MockK
    private lateinit var homeWorldMapper: CharacterHomeWorldMapper

    @MockK
    private lateinit var characterDetailApi: CharacterDetailApi

    private lateinit var characterDetailRepository: CharacterDetailRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        characterDetailRepository =
            CharacterDetailRepositoryImpl(
                characterDetailApi,
                filmsMapper,
                speciesMapper,
                homeWorldMapper
            )
    }

    @Test
    fun `getFilms should return flow of FilmDomain`() = runBlocking {
        val url = "irrelevant"
        val filmResponse = mockk<FilmResponse>(relaxed = true)
        val filmDomain = mockk<FilmDomain>(relaxed = true)

        every { filmsMapper.mapToDomain(filmResponse) }.returns(filmDomain)
        coEvery { characterDetailApi.getFilms(url) }.returns(filmResponse)

        val films = characterDetailRepository.getFilms(url)
        assertEquals(filmsMapper.mapToDomain(filmResponse), films.first())
        coVerify { characterDetailApi.getFilms(url) }
        verify { filmsMapper.mapToDomain(filmResponse) }
    }

    @Test
    fun `getSpecies should return flow of SpeciesDomain`() = runBlocking {
        val url = "irrelevant"
        val speciesResponse = mockk<SpeciesResponse>(relaxed = true)
        val speciesDomain = mockk<SpeciesDomain>(relaxed = true)

        every { speciesMapper.mapToDomain(speciesResponse) }.returns(speciesDomain)
        coEvery { characterDetailApi.getSpecies(url) }.returns(speciesResponse)

        val films = characterDetailRepository.getSpecies(url)
        assertEquals(speciesMapper.mapToDomain(speciesResponse), films.first())
        coVerify { characterDetailApi.getSpecies(url) }
        verify { speciesMapper.mapToDomain(speciesResponse) }
    }

    @Test
    fun `getHomeWorld should return flow of HomeWorldDomain`() = runBlocking {
        val url = "irrelevant"
        val homeWorldResponse = mockk<HomeWorldResponse>(relaxed = true)
        val homeWorldDomain = mockk<HomeWorldDomain>(relaxed = true)

        every { homeWorldMapper.mapToDomain(homeWorldResponse) }.returns(homeWorldDomain)
        coEvery { characterDetailApi.getHomeWorld(url) }.returns(homeWorldResponse)

        val films = characterDetailRepository.getHomeWorld(url)
        assertEquals(homeWorldMapper.mapToDomain(homeWorldResponse), films.first())
        coVerify { characterDetailApi.getHomeWorld(url) }
        verify { homeWorldMapper.mapToDomain(homeWorldResponse) }
    }

}
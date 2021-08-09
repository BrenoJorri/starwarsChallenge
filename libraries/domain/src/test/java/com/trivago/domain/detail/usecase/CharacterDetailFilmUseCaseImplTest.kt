package com.trivago.domain.detail.usecase

import com.trivago.domain.detail.model.FilmDomain
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

class CharacterDetailFilmUseCaseImplTest {

    private lateinit var filmUseCase: CharacterDetailFilmUseCase

    @MockK
    private lateinit var repository: CharacterDetailRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        filmUseCase = CharacterDetailFilmUseCaseImpl(repository)
    }


    @Test
    fun `getFilms should return flow of FilmDomain`() = runBlocking {
        val url = "irrelevant"
        val filmDomain = mockk<FilmDomain>(relaxed = true)
        val flow = flow {
            emit(filmDomain)
        }
        coEvery { repository.getFilms(url) }.returns(flow)

        filmUseCase.getFilms(url)

        coVerify { repository.getFilms(url) }
    }
}
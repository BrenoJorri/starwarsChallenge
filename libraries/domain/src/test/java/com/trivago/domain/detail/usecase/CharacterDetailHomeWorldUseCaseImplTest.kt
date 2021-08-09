package com.trivago.domain.detail.usecase

import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.repository.CharacterDetailRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterDetailHomeWorldUseCaseImplTest {


    private lateinit var homeWorldUseCase: CharacterDetailHomeWorldUseCase

    @MockK
    private lateinit var repository: CharacterDetailRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        homeWorldUseCase = CharacterDetailHomeWorldUseCaseImpl(repository)
    }

    @Test
    fun `getHomeWorld should return flow of FilmDomain`() = runBlocking {
        val url = "irrelevant"
        val homeWorldDomain = mockk<HomeWorldDomain>(relaxed = true)
        val flow = flow {
            emit(homeWorldDomain)
        }
        coEvery { repository.getHomeWorld(url) }.returns(flow)

        homeWorldUseCase.getHomeWorld(url)

        coVerify { repository.getHomeWorld(url) }
    }
}
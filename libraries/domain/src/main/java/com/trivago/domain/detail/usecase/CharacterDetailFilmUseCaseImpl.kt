package com.trivago.domain.detail.usecase

import com.trivago.domain.detail.model.FilmDomain
import com.trivago.domain.detail.model.HomeWorldDomain
import com.trivago.domain.detail.model.SpeciesDomain
import com.trivago.domain.detail.repository.CharacterDetailRepository
import kotlinx.coroutines.flow.Flow

class CharacterDetailFilmUseCaseImpl(
    private val repository: CharacterDetailRepository
) : CharacterDetailFilmUseCase {

    override suspend fun getFilms(url: String): Flow<FilmDomain?> =
        repository.getFilms(url)

}
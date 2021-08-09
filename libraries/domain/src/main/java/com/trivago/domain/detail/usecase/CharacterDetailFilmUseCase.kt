package com.trivago.domain.detail.usecase

import com.trivago.domain.detail.model.FilmDomain
import kotlinx.coroutines.flow.Flow

interface CharacterDetailFilmUseCase {

    suspend fun getFilms(url: String): Flow<FilmDomain?>
}
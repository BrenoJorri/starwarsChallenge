package com.trivago.domain.favorites.usecase

import com.trivago.domain.favorites.model.FavoriteCharacterDomain
import com.trivago.domain.favorites.repository.FavoritesRepository

class FavoriteCharacterUseCaseImpl(
    private val repository: FavoritesRepository
) : FavoritesCharacterUseCase {

    override suspend fun saveCharacter(domain: FavoriteCharacterDomain) =
        repository.saveCharacter(domain)
}
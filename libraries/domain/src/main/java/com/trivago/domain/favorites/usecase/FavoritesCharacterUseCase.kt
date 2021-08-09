package com.trivago.domain.favorites.usecase

import com.trivago.domain.favorites.model.FavoriteCharacterDomain

interface FavoritesCharacterUseCase {

    suspend fun saveCharacter(domain: FavoriteCharacterDomain)
}
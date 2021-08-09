package com.trivago.domain.favorites.repository

import com.trivago.domain.favorites.model.FavoriteCharacterDomain

interface FavoritesRepository {

    suspend fun saveCharacter(domain: FavoriteCharacterDomain)
}
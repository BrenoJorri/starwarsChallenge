package com.trivago

import com.trivago.domain.favorites.model.FavoriteCharacterDomain
import com.trivago.domain.favorites.repository.FavoritesRepository
import com.trivago.local.dao.CharacterDao
import com.trivago.mapper.favorite.FavoritesCharacterMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FavoritesRepositoryImpl(
    private val characterDao: CharacterDao,
    private val characterMapper: FavoritesCharacterMapper,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : FavoritesRepository {

    override suspend fun saveCharacter(domain: FavoriteCharacterDomain) {
        withContext(dispatcher) {
            characterDao.saveCharacter(characterMapper.mapToEntity(domain))
        }
    }

}
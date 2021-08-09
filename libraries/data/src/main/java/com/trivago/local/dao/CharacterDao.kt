package com.trivago.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.trivago.local.entity.FavoriteCharacterEntity

@Dao
interface CharacterDao {

    @Insert
    suspend fun saveCharacter(entity: FavoriteCharacterEntity)

}

package com.trivago.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trivago.local.dao.CharacterDao
import com.trivago.local.entity.FavoriteCharacterEntity

@Database(entities = [FavoriteCharacterEntity::class], version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}
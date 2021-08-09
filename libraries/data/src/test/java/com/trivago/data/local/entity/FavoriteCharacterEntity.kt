package com.trivago.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCharacterEntity (
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Int
)

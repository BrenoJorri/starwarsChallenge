package com.trivago.mapper.favorite

import com.trivago.domain.favorites.model.FavoriteCharacterDomain
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FavoritesCharacterMapperTest {


    private lateinit var mapper: FavoritesCharacterMapper

    @Before
    fun setUp() {
        mapper = FavoritesCharacterMapper()
    }

    @Test
    fun `mapToEntity should return an entity`() {
        val domain = mockk<FavoriteCharacterDomain>(relaxed = true)

        val entity = mapper.mapToEntity(domain)

        assertEquals(domain.name, entity.name)
    }
}
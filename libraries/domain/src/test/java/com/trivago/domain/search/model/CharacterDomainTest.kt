package com.trivago.domain.search.model

import org.junit.Assert.*
import org.junit.Test

class CharacterDomainTest {

    @Test
    fun `heightInches should return correct value`() {
        val charaterDomain = CharacterDomain("", "1", "", "", listOf(), listOf())
        assertEquals(charaterDomain.heightInches, "0.4")
    }
}
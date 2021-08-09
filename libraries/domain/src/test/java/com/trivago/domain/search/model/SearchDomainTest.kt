package com.trivago.domain.search.model

import org.junit.Assert.*
import org.junit.Test

class SearchDomainTest {

    @Test
    fun `isValid should return true if list of results is not empty`() {
        val resultList = listOf(CharacterDomain("", "", "", "", listOf(), listOf()))
        val searchDomain = SearchDomain(resultList)
        assertTrue(searchDomain.isValid())
    }

    @Test
    fun `isValid should return false if list of results is empty`() {
        val resultList = listOf<CharacterDomain>()
        val searchDomain = SearchDomain(resultList)
        assertFalse(searchDomain.isValid())
    }

}
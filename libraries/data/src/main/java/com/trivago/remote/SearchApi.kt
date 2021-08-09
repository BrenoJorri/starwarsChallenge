package com.trivago.remote

import com.trivago.entity.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("people/?")
    suspend fun search(@Query("search") search: String): SearchResponse

}

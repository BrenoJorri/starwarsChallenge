package com.trivago.remote

import com.trivago.entity.FilmResponse
import com.trivago.entity.HomeWorldResponse
import com.trivago.entity.SpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterDetailApi {

    @GET
    suspend fun getFilms(@Url filmUrl: String): FilmResponse

    @GET
    suspend fun getSpecies(@Url speciesUrl: String): SpeciesResponse

    @GET
    suspend fun getHomeWorld(@Url homeWorld: String): HomeWorldResponse

}

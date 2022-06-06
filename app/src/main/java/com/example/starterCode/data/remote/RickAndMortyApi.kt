package com.example.starterCode.data.remote

import com.example.starterCode.data.remote.dto.RickyAndMortyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getRickyAndMorty(
        @Query("page") page: Int
    ): RickyAndMortyDto
}
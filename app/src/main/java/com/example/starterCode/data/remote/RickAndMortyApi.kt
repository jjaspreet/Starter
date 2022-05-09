package com.example.starterCode.data.remote

import com.example.starterCode.data.remote.dto.RickAndMortyDto
import retrofit.http.GET

interface RickAndMortyApi {

    @GET("/character")
    suspend fun getCoinById(): RickAndMortyDto
}
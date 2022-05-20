package com.example.starterCode.domain.repository

import com.example.starterCode.data.remote.dto.RickyAndMortyDto

interface RickyAndMortyRepository {

    suspend fun getRickyAndMorty(): RickyAndMortyDto
}
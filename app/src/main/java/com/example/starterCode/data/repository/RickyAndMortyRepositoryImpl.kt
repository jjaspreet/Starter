package com.example.starterCode.data.repository

import com.example.starterCode.data.remote.RickAndMortyApi
import com.example.starterCode.data.remote.dto.RickyAndMortyDto
import com.example.starterCode.domain.repository.RickyAndMortyRepository
import javax.inject.Inject

class RickyAndMortyRepositoryImpl @Inject constructor(
    private val rickyAndMortyApi: RickAndMortyApi
) : RickyAndMortyRepository {

    override suspend fun getRickyAndMorty(): RickyAndMortyDto {
      return rickyAndMortyApi.getRickyAndMorty()
    }
}
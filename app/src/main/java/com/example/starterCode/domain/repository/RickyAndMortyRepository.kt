package com.example.starterCode.domain.repository

import androidx.paging.PagingData
import com.example.starterCode.data.remote.dto.Result
import com.example.starterCode.data.remote.dto.RickyAndMortyDto
import com.example.starterCode.domain.model.RickyAndMorty
import kotlinx.coroutines.flow.Flow

interface RickyAndMortyRepository {

     fun getRickyAndMorty(): Flow<PagingData<RickyAndMorty>>
}
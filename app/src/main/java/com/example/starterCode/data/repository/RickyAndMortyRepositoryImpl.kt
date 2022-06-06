package com.example.starterCode.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starterCode.data.remote.RickAndMortyApi
import com.example.starterCode.data.remote.dto.Result
import com.example.starterCode.data.repository.rickymortypaging.RickyMortyPagingSource
import com.example.starterCode.domain.model.RickyAndMorty
import com.example.starterCode.domain.repository.RickyAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickyAndMortyRepositoryImpl @Inject constructor(
    private val rickyAndMortyApi: RickAndMortyApi
) : RickyAndMortyRepository {

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

    override fun getRickyAndMorty(): Flow<PagingData<RickyAndMorty>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RickyMortyPagingSource(rickyAndMortyApi) }
        ).flow
    }
}
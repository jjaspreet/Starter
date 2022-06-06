package com.example.starterCode.presenter.rickyandmorty

import androidx.paging.PagingData
import com.example.starterCode.data.remote.dto.Result
import com.example.starterCode.domain.model.RickyAndMorty
import kotlinx.coroutines.flow.Flow
import java.util.*

sealed class RickyAndMortyUIState{
    data class Success(val data: PagingData<RickyAndMorty>): RickyAndMortyUIState()
    data class Error(val message: String) : RickyAndMortyUIState()
    object Loading : RickyAndMortyUIState()
    object Empty : RickyAndMortyUIState()

}




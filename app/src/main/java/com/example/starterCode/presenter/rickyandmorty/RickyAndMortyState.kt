package com.example.starterCode.presenter.rickyandmorty

import com.example.starterCode.domain.model.RickyAndMorty
import java.util.*

sealed class RickyAndMortyUIState{
    data class Success(val data : List<RickyAndMorty>): RickyAndMortyUIState()
    data class Error(val message: String) : RickyAndMortyUIState()
    object Loading : RickyAndMortyUIState()
    object Empty : RickyAndMortyUIState()

}




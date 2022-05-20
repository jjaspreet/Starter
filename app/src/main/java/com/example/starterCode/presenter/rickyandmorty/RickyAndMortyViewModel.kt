package com.example.starterCode.presenter.rickyandmorty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starterCode.common.Resource
import com.example.starterCode.domain.usecase.GetRickyAndMortyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RickyAndMortyViewModel @Inject constructor(
    private val getRickyAndMortyUseCase: GetRickyAndMortyUseCase,
) : ViewModel() {

    private var _rickyMortyResponse =
        MutableStateFlow<RickyAndMortyUIState>(RickyAndMortyUIState.Empty)
    val rickyMortyResponse: StateFlow<RickyAndMortyUIState> = _rickyMortyResponse

    init {
        getRickyAndMorty()
    }

    private fun getRickyAndMorty()  {
            getRickyAndMortyUseCase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _rickyMortyResponse.value = RickyAndMortyUIState.Loading
                        delay(2000)
                    }
                    is Resource.Success -> {
                        _rickyMortyResponse.value = RickyAndMortyUIState.Success(result.data!!)
                    }
                    is Resource.Error -> {
                        _rickyMortyResponse.value = RickyAndMortyUIState.Error(
                            result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
}
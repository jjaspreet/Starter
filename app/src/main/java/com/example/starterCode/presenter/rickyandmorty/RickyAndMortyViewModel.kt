package com.example.starterCode.presenter.rickyandmorty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starterCode.domain.model.RickyAndMorty
import com.example.starterCode.domain.usecase.GetRickyAndMortyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickyAndMortyViewModel @Inject constructor(
    private val getRickyAndMortyUseCase: GetRickyAndMortyUseCase,
) : ViewModel() {

    fun getMovies(): Flow<PagingData<RickyAndMorty>> {
        return getRickyAndMortyUseCase.getResponse().cachedIn(viewModelScope)
    }


}


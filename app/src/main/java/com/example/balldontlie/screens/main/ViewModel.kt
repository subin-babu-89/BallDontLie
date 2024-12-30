package com.example.balldontlie.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balldontlie.network.model.games.GamesResponse
import com.example.balldontlie.repository.NbaDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: NbaDataRepository) :
    ViewModel() {

    private val _internalViewState =
        MutableStateFlow<MainScreenViewState>(MainScreenViewState.Loading)
    val viewState = _internalViewState.asStateFlow()


    fun getGamesForDay() = viewModelScope.launch {
        _internalViewState.update { MainScreenViewState.Loading }
        repository.getGamesForDay().body()
            ?.let { response ->
                _internalViewState.update { MainScreenViewState.Success(response.response) }
            }
    }
}

sealed interface MainScreenViewState {
    data object Loading : MainScreenViewState
    data class Error(val message: String) : MainScreenViewState
    data class Success(val data: List<GamesResponse.Response>) : MainScreenViewState
}
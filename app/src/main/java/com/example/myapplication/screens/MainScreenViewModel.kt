package com.example.myapplication.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.model.StatusResponse
import com.example.myapplication.repository.NbaDataRepository
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

    fun getServerStatus() = viewModelScope.launch {
        _internalViewState.update {
            MainScreenViewState.Loading
        }
        repository.getServerStatus().body()?.let { response: StatusResponse ->
            _internalViewState.update { MainScreenViewState.Success(response.response.toString()) }
        }
    }

}

sealed interface MainScreenViewState {
    data object Loading : MainScreenViewState
    data class Error(val message: String) : MainScreenViewState
    data class Success(val data: String) : MainScreenViewState
}
package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.components.Greeting

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainScreenViewModel = hiltViewModel()) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getServerStatus()
    }

    Column {
        Greeting("Android", modifier = modifier)
        when (val state = viewState) {
            is MainScreenViewState.Error -> Text(text = "Error")
            MainScreenViewState.Loading -> CircularProgressIndicator()
            is MainScreenViewState.Success -> Text(text = state.data)
        }
    }
}
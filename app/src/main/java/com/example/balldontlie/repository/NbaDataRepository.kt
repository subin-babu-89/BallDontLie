package com.example.balldontlie.repository

import com.example.balldontlie.network.ApiSportsService
import javax.inject.Inject

class NbaDataRepository @Inject constructor(private val apiService: ApiSportsService) {
    suspend fun getGamesForDay() = apiService.getGamesForDay()
}
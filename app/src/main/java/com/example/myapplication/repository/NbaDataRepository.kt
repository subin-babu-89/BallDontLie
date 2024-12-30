package com.example.myapplication.repository

import com.example.myapplication.network.ApiSportsService
import com.example.myapplication.network.model.StatusResponse
import retrofit2.Response
import javax.inject.Inject

class NbaDataRepository @Inject constructor(private val apiService: ApiSportsService) {
    suspend fun getServerStatus(): Response<StatusResponse> {
        return apiService.getStatus()
    }
}
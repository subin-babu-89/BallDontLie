package com.example.myapplication.network

import com.example.myapplication.BuildConfig
import com.example.myapplication.network.model.StatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiSportsService {
    @Headers("x-apisports-key:${BuildConfig.api_sport_key}")
    @GET("status")
    suspend fun getStatus(): Response<StatusResponse>

    companion object {
        const val BASE_URL = "https://v1.basketball.api-sports.io/"
    }
}

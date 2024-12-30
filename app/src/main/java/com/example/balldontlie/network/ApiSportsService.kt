package com.example.balldontlie.network

import com.example.balldontlie.network.model.StatusResponse
import com.example.balldontlie.network.model.games.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiSportsService {
    @Headers("x-apisports-key:${com.example.balldontlie.BuildConfig.api_sport_key}")
    @GET("status")
    suspend fun getStatus(): Response<StatusResponse>


    /// TODO: pass these variables in a better manner
    @Headers("x-apisports-key:${com.example.balldontlie.BuildConfig.api_sport_key}")
    @GET("games")
    suspend fun getGamesForDay(
        @Query("date") date: String = "2023-12-30",
        @Query("league") league: Int = 12,
        @Query("season") season: String = "2023-2024",
        @Query("timezone") timezone: String = "America/Los_Angeles",
    ): Response<GamesResponse>

    companion object {
        const val BASE_URL = "https://v1.basketball.api-sports.io/"
    }
}

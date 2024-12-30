package com.example.myapplication.di

import com.example.myapplication.network.ApiSportsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder().baseUrl(ApiSportsService.BASE_URL).addConverterFactory(
            json.asConverterFactory("application/json; charset=UTF8".toMediaType())
        ).build()
    }

    @Provides
    @Singleton
    fun provideApiSportsService(retrofit: Retrofit): ApiSportsService {
        return retrofit.create(ApiSportsService::class.java)
    }
}
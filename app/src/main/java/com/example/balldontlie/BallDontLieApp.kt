package com.example.balldontlie

import android.app.Application
import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient

@HiltAndroidApp
class BallDontLieApp : Application() {
    private fun createCachedImageLoader(context: Context): ImageLoader {
        val imageLoader = ImageLoader.Builder(context = context)
            .components {
                add(OkHttpNetworkFetcherFactory(
                    callFactory = {
                        OkHttpClient()
                    }
                ))
            }
            .memoryCache { MemoryCache.Builder().maxSizePercent(context, 0.25).build() }
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.02).build()
            }.build()
        return imageLoader
    }

}
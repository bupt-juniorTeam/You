package com.you.android.logic.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object OkHttpClients {
    private const val roomUrl = ""
    val roomClient: OkHttpClient =
            OkHttpClient
                    .Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(50L, TimeUnit.SECONDS)
                    .readTimeout(60L, TimeUnit.SECONDS)
                    .build()
    val roomRequest: Request =
            Request
                    .Builder()
                    .url(roomUrl)
                    .build()

}
package com.you.android.logic.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.concurrent.TimeUnit


object WebSocketCreator {
    const val TEST_URL = "ws://echo.websocket.org"
    val client: OkHttpClient = OkHttpClient.Builder().build()

    fun create(socketListener: WebSocketListener, url: String = TEST_URL): WebSocket {
        val request = Request.Builder().url(url).build()
        return client.newWebSocket(request, socketListener)
    }
}
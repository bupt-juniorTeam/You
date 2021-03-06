package com.you.android.logic.network

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.you.android.logic.model.WebSocketMessage
import com.you.android.util.LogUtil
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class RoomWebSocketListener : WebSocketListener() {

    companion object {
        private const val TAG = "RoomWebSocketListener"
    }

    val messageFromServer = MutableLiveData<WebSocketMessage>()

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        LogUtil.d(TAG, text)
        val message = Gson().fromJson(text,WebSocketMessage::class.java)
        messageFromServer.postValue(message)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
    }

}
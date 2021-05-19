package com.you.android.logic.network

import androidx.lifecycle.MutableLiveData
import com.you.android.logic.Repository.roomLiveData
import com.you.android.util.LogUtil
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class RoomListener : WebSocketListener() {
    companion object {
        private const val TAG = "Room"
    }


    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        LogUtil.i(TAG, "连接成功！")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        roomLiveData.postValue(text)
        LogUtil.i(TAG, "收到消息: $text")
    }
}
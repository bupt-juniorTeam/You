package com.you.android.ui.chatroom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.you.android.logic.Repository
import com.you.android.logic.YouSocket

class ChatroomViewModel() : ViewModel() {

    internal val msgList = ArrayList<Msg>()

    internal lateinit var roomName: String

    private val beginChatFlagLiveData = MutableLiveData<Unit>()

    lateinit var youSocket: YouSocket


    val beginChatLiveData = Transformations.switchMap(beginChatFlagLiveData) {
        youSocket = Repository.createRoomSocket(roomName)
        youSocket.messageFromServer
    }


    fun beginChat() {
        beginChatFlagLiveData.value = beginChatFlagLiveData.value
    }

    fun sendMessage(msg: String) {
        Repository.sendMessage(youSocket, msg)
    }

    fun closeChat() {
        Repository.closeRoomSocket(youSocket)
    }


}
package com.you.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.you.android.logic.Repository
import com.you.android.logic.YouSocket
import com.you.android.logic.model.JoinOrLeaveRoomResponse
import com.you.android.logic.model.RoomListResponse

class ChatRoomViewModel(val roomName:String) : ViewModel() {
    private val joinRoomFlagLiveData = MutableLiveData<Unit>()
    private val leaveRoomFlagLiveData = MutableLiveData<Unit>()
    private val sendMessageFlagLiveData = MutableLiveData<String>()
    private val closeChatFlagLiveData = MutableLiveData<Unit>()

    lateinit var youSocket: YouSocket

    val joinRoomLiveData = Transformations.switchMap(joinRoomFlagLiveData) {
        Repository.joinRoom(roomName)
    }
    val leaveRoomLiveData = Transformations.switchMap(leaveRoomFlagLiveData) {
        Repository.leaveRoom(roomName)
    }

    val chatLiveData = Transformations.switchMap(sendMessageFlagLiveData) { msg ->
        youSocket = Repository.createRoomSocket(roomName)
        Repository.sendMessage(youSocket, msg)
        youSocket.messageFromServer
    }
    val closeChatLiveData = Transformations.switchMap(closeChatFlagLiveData) {
        Repository.closeRoomSocket(youSocket)
        youSocket.messageFromServer
    }

    fun joinRoom() {
        joinRoomFlagLiveData.value = joinRoomFlagLiveData.value
    }

    fun leaveRoom() {
        leaveRoomFlagLiveData.value = leaveRoomFlagLiveData.value
    }

    fun sendMessage(msg: String) {
        sendMessageFlagLiveData.value = msg
    }

    fun closeChat() {
        closeChatFlagLiveData.value = closeChatFlagLiveData.value
    }
}
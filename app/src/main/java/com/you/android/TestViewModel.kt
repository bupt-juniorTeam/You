package com.you.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.you.android.logic.Repository
import com.you.android.logic.YouSocket
import com.you.android.logic.model.JoinOrLeaveRoomResponse
import com.you.android.logic.model.RoomListResponse

class TestViewModel : ViewModel() {
    private val joinRoomFlagLiveData = MutableLiveData<Unit>()
    private val leaveRoomFlagLiveData = MutableLiveData<Unit>()
    private val chatFlagLiveData = MutableLiveData<Unit>()
    private val closeChatFlagLiveData = MutableLiveData<Unit>()

    val joinRes = String()
    val leaveRes = String()
    lateinit var youSocket:YouSocket
    val joinRoom = Transformations.switchMap(joinRoomFlagLiveData) {
        Repository.joinRoom("TEST")
    }
    val leaveRoom = Transformations.switchMap(leaveRoomFlagLiveData) {
        Repository.leaveRoom("TEST")
    }
    val chat = Transformations.switchMap(chatFlagLiveData) {
        Repository.joinRoom("TEST")
        youSocket = Repository.createRoomSocket("TEST")
        Repository.sendMessage(youSocket,"hello")
        youSocket.messageFromServer
    }
    val closeChat = Transformations.switchMap(closeChatFlagLiveData){
        Repository.leaveRoom("TEST")
        Repository.closeRoomSocket(youSocket)
        youSocket.messageFromServer
    }

    fun joinRoom() {
        joinRoomFlagLiveData.value = joinRoomFlagLiveData.value
    }

    fun leaveRoom() {
        leaveRoomFlagLiveData.value = leaveRoomFlagLiveData.value
    }

    fun chat() {
        chatFlagLiveData.value = chatFlagLiveData.value
    }
    fun closeChat(){
        closeChatFlagLiveData.value = closeChatFlagLiveData.value
    }
}
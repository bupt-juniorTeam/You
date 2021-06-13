package com.you.android.ui.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.you.android.logic.Repository

class CreateRoomViewModel: ViewModel(){
    private val flagLiveData = MutableLiveData<Unit>()

    var flag = false

    internal lateinit var roomName: String

    val createRoomLiveData = Transformations.switchMap(flagLiveData) {
        Repository.createRoom(roomName)
    }

    fun createRoom() {
        flagLiveData.value = flagLiveData.value
    }
}
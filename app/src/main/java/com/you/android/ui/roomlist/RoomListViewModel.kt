package com.you.android.ui.roomlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.you.android.logic.Repository
import com.you.android.logic.model.RoomListResponse

class RoomListViewModel : ViewModel() {
    private val flagLiveData = MutableLiveData<Unit>()

    // roomsLiveData中的数据存进roomList
    val roomList = ArrayList<RoomListResponse.Room>()

    val roomsLiveData = Transformations.switchMap(flagLiveData) {
        Repository.searchRooms()
    }

    fun searchRooms() {
        flagLiveData.value = flagLiveData.value
    }
}
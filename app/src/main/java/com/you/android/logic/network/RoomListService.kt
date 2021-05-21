package com.you.android.logic.network

import com.you.android.logic.model.RoomListResponse
import retrofit2.Call
import retrofit2.http.GET

interface RoomListService {
    @GET("chatroom/list")
    fun searchRooms(): Call<RoomListResponse>
}
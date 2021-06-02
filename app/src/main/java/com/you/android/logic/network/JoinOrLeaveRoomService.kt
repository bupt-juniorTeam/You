package com.you.android.logic.network

import com.you.android.logic.model.JoinOrLeaveRoomResponse
import retrofit2.Call
import retrofit2.http.*

interface JoinOrLeaveRoomService {
    @GET("chatroom/room")
    fun join(
        @Query("room_name") room_name: String,
        @Query("user_name") user_name: String,
        @Query("status") status: Int = 1
    ): Call<JoinOrLeaveRoomResponse>

    @GET("chatroom/room")
    fun leave(
        @Query("room_name") room_name: String,
        @Query("user_name") user_name: String,
        @Query("status") status: Int = 0
    ): Call<JoinOrLeaveRoomResponse>
}
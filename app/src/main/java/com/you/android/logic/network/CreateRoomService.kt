package com.you.android.logic.network

import com.you.android.logic.model.CreateRoomResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CreateRoomService {
    @GET("chatroom/create")
    fun createRoom(
        @Query("name") name:String
    ): Call<CreateRoomResponse>
}
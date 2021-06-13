package com.you.android.logic.network

import com.you.android.logic.model.JoinOrLeaveRoomResponse
import com.you.android.logic.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RegisterService {
    @GET("auth/register")
    fun register(
        @Query("user_tel") user_tel: String,
        @Query("user_name") user_name: String,
        @Query("user_password") user_password: String,
    ): Call<RegisterResponse>
}
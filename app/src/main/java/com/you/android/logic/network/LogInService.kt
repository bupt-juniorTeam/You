package com.you.android.logic.network

import com.you.android.logic.model.CreateRoomResponse
import com.you.android.logic.model.LogInResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LogInService {
    @GET("auth/login")
    fun logIn(
        @Query("user_tel") user_tel:String,
        @Query("user_password") user_password:String,
    ): Call<LogInResponse>
}
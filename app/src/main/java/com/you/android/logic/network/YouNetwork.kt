package com.you.android.logic.network

import okhttp3.WebSocket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 在此类中实现retrofit2的所有Interface的网络请求
 */
object YouNetwork {

    // ----------------------
    private val logInService=ServiceCreator.create<LogInService>()

    private val RegisterService=ServiceCreator.create<RegisterService>()

    private val roomListService = ServiceCreator.create<RoomListService>()

    private val createRoomService = ServiceCreator.create<CreateRoomService>()


    suspend fun userRegister(userTel:String,userName: String, userPassword:String)=
        RegisterService.register(userTel,userName,userPassword).await()

    suspend fun userLogIn(userTel:String, userPassword:String)=
        logInService.logIn(userTel,userPassword).await()

    // 返回RoomListResponse
    suspend fun searchRooms() = roomListService.searchRooms().await()

    suspend fun createRoom(name: String) = createRoomService.createRoom(name).await()

    // 封装enqueue的协程
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    // 在onResponse方法中调用body()来获得T类型的数据
                    val body = response.body()
                    if (body != null)
                        continuation.resume(body)
                    else
                        continuation.resumeWithException(
                            RuntimeException("response body is null")
                        )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }
}
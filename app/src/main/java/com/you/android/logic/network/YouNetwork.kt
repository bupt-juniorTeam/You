package com.you.android.logic.network

import com.you.android.logic.network.OkHttpClients.roomClient
import com.you.android.logic.network.OkHttpClients.roomRequest
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

    // 创建webSockets
//    private val roomSocket = roomClient.newWebSocket(roomRequest,RoomListener())

    // ----------------------------

    private val roomListService = ServiceCreator.create<RoomListService>()

    // 返回RoomListResponse
    suspend fun searchRooms() = roomListService.searchRooms().await()

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
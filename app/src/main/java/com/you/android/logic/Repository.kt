package com.you.android.logic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.you.android.logic.network.YouNetwork
import com.you.android.util.LogUtil
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

// 使用livedata对象来向上层提供数据变化
object Repository {
    private const val TAG = "Repository"

    val roomLiveData = MutableLiveData<String>()


    fun searchRooms() = fire(Dispatchers.IO) {
        val roomListResponse = YouNetwork.searchRooms()
        LogUtil.i(TAG,roomListResponse.rooms.toString())
        val rooms = roomListResponse.rooms
        Result.success(rooms)
    }

    // 辅助函数，返回一个包含有result<T>的liveData
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            // 发出修改
            emit(result)
        }
}
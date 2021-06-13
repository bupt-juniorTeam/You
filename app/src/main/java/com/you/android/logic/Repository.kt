package com.you.android.logic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.you.android.logic.dao.UserDao
import com.you.android.logic.model.Status
import com.you.android.logic.model.WebSocketMessage
import com.you.android.logic.network.RoomWebSocketListener
import com.you.android.logic.network.WebSocketCreator
import com.you.android.logic.network.YouNetwork
import com.you.android.util.LogUtil
import kotlinx.coroutines.Dispatchers
import okhttp3.WebSocket
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class YouSocket(
    val roomName: String,
    val socket: WebSocket,
    val messageFromServer: MutableLiveData<WebSocketMessage>
)

// 使用livedata对象来向上层提供数据变化
object Repository {
    private const val TAG = "Repository"

    fun createRoomSocket(roomName: String, url: String = "ws://124.70.97.253:2333"): YouSocket {
        val socketListener = RoomWebSocketListener()
        val socket = WebSocketCreator.create(socketListener, url)
        // 创建webSocket之后要发送join message
        val joinMessage = WebSocketMessage(
            "join",
            WebSocketMessage.Data(
                WebSocketMessage.User(
                    UserDao.getUserName(),
                    UserDao.getUserAvatar(),
                ),
                roomName,
                ""
            )
        )
        socket.send(Gson().toJson(joinMessage))
        return YouSocket(roomName, socket, socketListener.messageFromServer)
    }

    fun closeRoomSocket(youSocket: YouSocket) {
        val socket = youSocket.socket
        // close之前要发送退出聊天室
        val leaveMessage = WebSocketMessage(
            "leave",
            WebSocketMessage.Data(
                WebSocketMessage.User(
                    UserDao.getUserName(),
                    UserDao.getUserAvatar(),
                ),
                youSocket.roomName,
                ""
            )
        )
        socket.send(Gson().toJson(leaveMessage))
        socket.close(1000, "退出聊天室");
    }

    fun sendMessage(youSocket: YouSocket, msg: String) {
        val socket = youSocket.socket
        val message = WebSocketMessage(
            "msg",
            WebSocketMessage.Data(
                WebSocketMessage.User(
                    UserDao.getUserName(),
                    UserDao.getUserAvatar(),
                ),
                youSocket.roomName,
                msg
            )
        )
        socket.send(Gson().toJson(message))
    }
    // ----------

    fun userRegister(userTel: String, userName: String, userPassword: String) =
        fire(Dispatchers.IO) {
            val registerResponse = YouNetwork.userRegister(userTel, userName, userPassword)
            LogUtil.i(TAG, registerResponse.toString())
            val res = registerResponse.res
            return@fire Result.success(res)
        }

    fun userLogIn(userTel: String, userPassword: String) = fire(Dispatchers.IO) {
        val logInResponse = YouNetwork.userLogIn(userTel, userPassword)
        val res = logInResponse.res
        if (res != "login successfully")
            return@fire Result.success(res)
        // 储存UserDao
        var avatar = logInResponse.data.user_avatar_url
        val name = logInResponse.data.user_name
        if (avatar.isEmpty())
            avatar = "default"
        UserDao.saveUserAvatar(avatar)
        UserDao.saveUserName(name)

        LogUtil.i(TAG, logInResponse.toString())
        return@fire Result.success(res)
    }

    fun searchRooms() = fire(Dispatchers.IO) {
        val roomListResponse = YouNetwork.searchRooms()
        LogUtil.i(TAG, roomListResponse.room_list.toString())
        val rooms = roomListResponse.room_list
        Result.success(rooms)
    }

    fun createRoom(roomName: String) = fire(Dispatchers.IO) {
        // 发送Post消息
        val createRoomResponse = YouNetwork.createRoom(roomName)
        LogUtil.i(TAG, createRoomResponse.toString())
        if (createRoomResponse.status == Status.SUCCESS.value) {
            val roomName = createRoomResponse.name
            return@fire Result.success(roomName)
        } else {
            return@fire Result.failure<String>(Exception("创建失败"))
        }
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
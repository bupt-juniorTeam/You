package com.you.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.you.android.logic.Repository
import com.you.android.logic.network.RoomWebSocketListener
import com.you.android.logic.network.WebSocketCreator
import com.you.android.ui.roomlist.RoomListActivity
import com.you.android.ui.roomlist.RoomListViewModel
import com.you.android.util.LogUtil

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    val viewModel by lazy {
        ViewModelProvider(this).get(TestViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // test
        val intent = Intent(this, RoomListActivity::class.java)
        startActivity(intent)
        WebSocketCreator.create(RoomWebSocketListener()).send("dfd")
//        val intent = Intent(this,RoomListActivity::class.java)
//        startActivity(intent)
        test()
//        joinRoomTest()
//        leaveRoomTest()
        chatTest()
        closeChatTest()
    }

    private fun test() {
        viewModel.joinRoom.observe(this, Observer { result ->
            LogUtil.i(TAG, "加入TEST room")
            val res = result.getOrNull()
            LogUtil.i(TAG, res.toString())
        })
        viewModel.leaveRoom.observe(this, Observer { result ->
            LogUtil.i(TAG, "离开TEST room")
            val res = result.getOrNull()
            LogUtil.i(TAG, res.toString())
        })
        viewModel.chat.observe(this, Observer { result ->
            LogUtil.i(TAG, "开始聊天：")
            val res = result.toString()
            LogUtil.i(TAG, "服务器回复: "+res)
        })
        viewModel.closeChat.observe(this,Observer{ result ->
            LogUtil.i(TAG,"结束聊天")
            val res = result.toString()
            LogUtil.i(TAG, "服务器回复: "+res)
        })
    }

    private fun joinRoomTest() {
        // join Test room
        viewModel.joinRoom()
    }

    private fun leaveRoomTest() {
        viewModel.leaveRoom()
    }

    private fun chatTest() {
        viewModel.joinRoom()
        viewModel.chat()
    }
    private fun closeChatTest() {
        viewModel.leaveRoom()
        viewModel.closeChat()
    }
}
package com.you.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.you.android.logic.network.RoomWebSocketListener
import com.you.android.logic.network.WebSocketCreator
import com.you.android.ui.chatroom.ChatroomActivity
import com.you.android.ui.roomlist.RoomListActivity
import com.you.android.util.LogUtil

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //test chatroom
        val intent = Intent(this, ChatroomActivity::class.java)
        intent.putExtra("roomName","TEST")
        startActivity(intent)
    }


}
package com.you.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.you.android.logic.network.RoomWebSocketListener
import com.you.android.logic.network.WebSocketCreator
import com.you.android.ui.roomlist.RoomListActivity
import com.you.android.util.LogUtil

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    val viewModel by lazy {
        ViewModelProvider(this).get(ChatRoomViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // test
        val intent = Intent(this, RoomListActivity::class.java)
        startActivity(intent)
    }


}
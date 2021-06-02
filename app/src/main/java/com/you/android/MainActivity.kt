package com.you.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.you.android.logic.network.RoomWebSocketListener
import com.you.android.logic.network.WebSocketCreator
import com.you.android.ui.roomlist.RoomListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // test
        val intent = Intent(this, RoomListActivity::class.java)
        startActivity(intent)
        WebSocketCreator.create(RoomWebSocketListener()).send("dfd")
    }
}
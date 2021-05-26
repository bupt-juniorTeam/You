package com.you.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.you.android.ui.roomlist.RoomListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // test
        val intent = Intent(this,RoomListActivity::class.java)
        startActivity(intent)
        
    }
}
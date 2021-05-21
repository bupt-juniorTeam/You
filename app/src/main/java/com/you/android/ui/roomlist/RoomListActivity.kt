package com.you.android.ui.roomlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.you.android.R
import com.you.android.util.LogUtil

class RoomListActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "RoomListActivity"
    }

    val viewModel by lazy {
        ViewModelProvider(this).get(RoomListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        viewModel.roomsLiveData.observe(this, Observer { result ->
            LogUtil.i(TAG,"获取聊天室")
            val rooms = result.getOrNull()
            LogUtil.i(TAG,rooms.toString())
            if (rooms != null) {
                viewModel.roomList.clear()
                viewModel.roomList.addAll(rooms)
                LogUtil.i(TAG,"聊天列表如下：")
                for(room in viewModel.roomList) {
                    LogUtil.i(TAG,room.name)
                }
            } else {
                Toast.makeText(this, "暂时没有聊天室", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })

        //test
        viewModel.searchRooms()

    }
}
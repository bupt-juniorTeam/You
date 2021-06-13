package com.you.android.ui.homepage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.you.android.R
import com.you.android.logic.model.RoomListResponse
import com.you.android.ui.roomlist.RoomListAdapter
import com.you.android.ui.roomlist.RoomListViewModel
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.view.Gravity

import android.widget.EditText

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.lxj.xpopup.XPopup
import com.you.android.ui.chatroom.ChatroomActivity
import com.you.android.ui.login.LoginViewModel
import com.lxj.xpopup.interfaces.OnInputConfirmListener
import java.security.AccessController.getContext


class HomePageActivity : AppCompatActivity() {
    private val roomListViewModel by lazy {
        ViewModelProvider(this).get(RoomListViewModel::class.java)
    }
    private val createRoomViewModel by lazy {
        ViewModelProvider(this).get(CreateRoomViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        var toolbar = findViewById<Toolbar>(R.id.toolbar_person_center)

        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout_person_center)

        var navigationView = findViewById<NavigationView>(R.id.navigationView_person_center)

        var actionBarDrawerToggle: ActionBarDrawerToggle

        val buttonCreateRoom:Button=findViewById(R.id.ButtonCreateChatRoom)

        buttonCreateRoom.setOnClickListener {
            val poper=XPopup.Builder(this).asInputConfirm(
                "创建聊天室", "请输入聊天室名",
                {text:String -> createRoomViewModel.roomName=text
                createRoomViewModel.createRoom()
                roomListViewModel.searchRooms()
                })
                .show()
        }



        val roomRecyclerView = findViewById<RecyclerView>(R.id.room_list)
        roomRecyclerView.layoutManager = LinearLayoutManager(this@HomePageActivity)
        val adapter = RoomListAdapter()
        roomRecyclerView.adapter = AlphaInAnimationAdapter(adapter)

        createRoomViewModel.createRoomLiveData.observe(this,{result->
            val res=result.getOrNull()

            if (res == "chatroom create successfully") {
                Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show()
                createRoomViewModel.flag = true
                runOnUiThread {
                    val intent = Intent(this, ChatroomActivity::class.java)
                    intent.putExtra("roomName", createRoomViewModel.roomName)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "创建失败: $res", Toast.LENGTH_SHORT).show()
                createRoomViewModel.flag = false
            }
        })

        roomListViewModel.roomsLiveData.observe(this, { result ->
//            LogUtil.i(RoomListActivity.TAG, "获取聊天室")
            val rooms = result.getOrNull()
//            LogUtil.i(RoomListActivity.TAG, rooms.toString())
            if (rooms != null) {
                roomListViewModel.roomList.clear()
                roomListViewModel.roomList.addAll(rooms)
//                LogUtil.i(RoomListActivity.TAG, "聊天列表如下：")
//                for (room in viewModel.roomList) {
//                    LogUtil.i(RoomListActivity.TAG, room.name)
//                }
                adapter.room_list = rooms as ArrayList<RoomListResponse.Room>
                lifecycleScope.launch(Dispatchers.Main) {
                    adapter.notifyDataSetChanged()
                }
            } else {
                Toast.makeText(this, "暂时没有聊天室", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })

        //test
        roomListViewModel.searchRooms()
    }
}
package com.you.android.ui.homepage

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.lxj.xpopup.XPopup
import com.you.android.R
import com.you.android.logic.model.RoomListResponse
import com.you.android.ui.chatroom.ChatroomActivity
import com.you.android.ui.roomlist.RoomListAdapter
import com.you.android.ui.roomlist.RoomListViewModel
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

        var rooms: List<RoomListResponse.Room>?=ArrayList()

        var toolbar = findViewById<Toolbar>(R.id.toolbar_person_center)

        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout_person_center)

        var navigationView = findViewById<NavigationView>(R.id.navigationView_person_center)

        var actionBarDrawerToggle: ActionBarDrawerToggle

        val editTextSearchRoomName: EditText = findViewById(R.id.textViewSearchRoomName)

        val buttonCreateRoom: FloatingActionButton =findViewById(R.id.ButtonCreateChatRoom)

        val buttonSearchRoom: ImageView = findViewById(R.id.searchRoomButton)

        val buttonRandomRoom: ImageView =findViewById(R.id.ButtonRandomRoom)

        buttonCreateRoom.setOnClickListener {
            val poper = XPopup.Builder(this).asInputConfirm(
                "创建聊天室", "请输入聊天室名"
            ) { text: String ->
                createRoomViewModel.roomName = text
                createRoomViewModel.createRoom()
                roomListViewModel.searchRooms()
            }
                .show()
        }

        buttonSearchRoom.setOnClickListener {
            val targetText: String = editTextSearchRoomName.text.toString()
            var roomSearchedList = ArrayList<String>()
            rooms?.forEach { element: RoomListResponse.Room ->
                    if (element.name.contains(targetText)) {
                        roomSearchedList.add(element.name)
                    }
            }
            val poper = XPopup.Builder(this).asCenterList(
                "",
                roomSearchedList.toTypedArray()
            ) { position: Int, text: String ->
                val intent = Intent(this, ChatroomActivity::class.java)
                intent.putExtra("roomName", text)
                startActivity(intent)
            }.show()
        }

        buttonRandomRoom.setOnClickListener {
            if(rooms.isNullOrEmpty()){
                Toast.makeText(this, "当前没有聊天室", Toast.LENGTH_SHORT).show()
            }
            else{
                val randomNumber=(0..rooms!!.size-1).random()
                val intent = Intent(this, ChatroomActivity::class.java)
                intent.putExtra("roomName", rooms!![randomNumber].name)
                startActivity(intent)
            }

        }


        val roomRecyclerView = findViewById<RecyclerView>(R.id.room_list)
        roomRecyclerView.layoutManager = LinearLayoutManager(this@HomePageActivity)
        val adapter = RoomListAdapter(window.decorView)
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
            rooms = result.getOrNull()
//            LogUtil.i(RoomListActivity.TAG, rooms.toString())
            if (rooms != null) {
                roomListViewModel.roomList.clear()
                roomListViewModel.roomList.addAll(rooms!!)
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
            findViewById<SwipeRefreshLayout>(R.id.refresh).isRefreshing = false
        })

        findViewById<SwipeRefreshLayout>(R.id.refresh).apply {
            setOnRefreshListener {
                roomListViewModel.searchRooms()
            }
            setColorSchemeColors(Color.parseColor("#DB4D6D"),Color.parseColor("#FC9F4D"),Color.parseColor("#227D51"),Color.parseColor("#0eb0c9"))
            //test
            roomListViewModel.searchRooms()
        }
        val radius = 20f

        val decorView = window.decorView

        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup

        val windowBackground = decorView.background

        findViewById<BlurView>(R.id.blur_view).setupWith(rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
    }
}
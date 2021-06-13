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
import com.you.android.ui.roomlist.RoomListActivity
import com.you.android.ui.roomlist.RoomListAdapter
import com.you.android.ui.roomlist.RoomListViewModel
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val viewModel by lazy {
            ViewModelProvider(this).get(RoomListViewModel::class.java)
        }

        var toolbar = findViewById<Toolbar>(R.id.toolbar_person_center)

        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout_person_center)

        var navigationView = findViewById<NavigationView>(R.id.navigationView_person_center)

        var actionBarDrawerToggle: ActionBarDrawerToggle

        findViewById<Button>(R.id.searchRoomButton).setOnClickListener {
            val intent: Intent = Intent(this, RoomListActivity::class.java)
            startActivity(intent)
        }

        val roomRecyclerView = findViewById<RecyclerView>(R.id.room_list)
        roomRecyclerView.layoutManager = LinearLayoutManager(this@HomePageActivity)
        val adapter = RoomListAdapter()
        roomRecyclerView.adapter = AlphaInAnimationAdapter(adapter)

        viewModel.roomsLiveData.observe(this, { result ->
//            LogUtil.i(RoomListActivity.TAG, "获取聊天室")
            val rooms = result.getOrNull()
//            LogUtil.i(RoomListActivity.TAG, rooms.toString())
            if (rooms != null) {
                viewModel.roomList.clear()
                viewModel.roomList.addAll(rooms)
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
        viewModel.searchRooms()
    }
}
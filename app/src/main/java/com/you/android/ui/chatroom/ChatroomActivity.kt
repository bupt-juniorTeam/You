package com.you.android.ui.chatroom

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.you.android.R
import com.you.android.logic.dao.UserDao

class ChatroomActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val TAG = "ChatroomActivity"
    }

    private lateinit var adapter: MsgAdapter
    private val viewModel by lazy {
        ViewModelProvider(this).get(ChatroomViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatroom)
        initRoom()
        setOnClick()
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = this.findViewById<RecyclerView>(R.id.chatArea)
        recyclerView.layoutManager = layoutManager
        if (!::adapter.isInitialized)
            adapter = MsgAdapter(viewModel.msgList)
        recyclerView.adapter = adapter


        // observe
        viewModel.beginChatLiveData.observe(this, { result ->
            when (result.type) {
                "msg" -> {
                    val msg = Msg(Msg.TYPE_RECEIVED, result.data.user_name, result.data.msg)
                    if(msg.userName!=UserDao.getUserName()){
                        addToView(msg)
                    }
                }
                "user" -> {
                    when (result.data.msg) {
                        "join" -> {
                            Toast.makeText(
                                this,
                                result.data.user_name + "进入了房间",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        "leave" -> {
                            Toast.makeText(
                                this,
                                result.data.user_name + "退出了房间",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                "error" -> {
                    Toast.makeText(this, "error: " + result.data.msg, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    // 向recycleview添加消息
    private fun addToView(msg: Msg) {
        viewModel.msgList.add(msg)
        adapter.notifyItemInserted(viewModel.msgList.size - 1)
        val recyclerView = this.findViewById<RecyclerView>(R.id.chatArea)
        recyclerView.scrollToPosition(viewModel.msgList.size - 1)
    }

    // 进入房间
    private fun initRoom() {
        viewModel.roomName = intent.getStringExtra("roomName").toString()
        findViewById<TextView>(R.id.roomName).text = viewModel.roomName
        viewModel.joinRoom()
        viewModel.beginChat()
    }

    private fun setOnClick() {
        val sendButton = findViewById<Button>(R.id.sendButton)
        val backButton = findViewById<Button>(R.id.backButton)
        sendButton.setOnClickListener(this)
        backButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.sendButton -> {
                    val inputText = this.findViewById<EditText>(R.id.inputText)
                    val content = inputText.text.toString()
                    val msg = Msg(Msg.TYPE_SENT, UserDao.getUserName(), content)
                    if (content.isNotEmpty()) {
                        addToView(msg)
                        inputText.setText("")
                        // 将消息发送给服务器
                        viewModel.sendMessage(content)
                    } else {
                        Toast.makeText(this, "不能发送空消息", Toast.LENGTH_SHORT).show()
                    }
                }
                // 退出房间
                R.id.backButton -> {
                    viewModel.leaveRoom()
                    viewModel.closeChat()
                    this.finish()
                }
            }
        }
    }
}
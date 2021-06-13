package com.you.android.ui.chatroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.you.android.R
import java.lang.Exception

class MsgAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ReceiveViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val receiveMsg: TextView = view.findViewById(R.id.receiveMessage)
        val receiveUserName: TextView = view.findViewById(R.id.receiveUserName)
    }

    inner class SendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sendMsg: TextView = view.findViewById(R.id.sendMessage)
        val sendUserName: TextView = view.findViewById(R.id.sendUserName)
    }

    class ImgViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: ImageView = view.findViewById(R.id.imageView)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            Msg.TYPE_RECEIVED -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.receive_message, parent, false)
                ReceiveViewHolder(view)
            }
            Msg.TYPE_SENT -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.send_message, parent, false)
                SendViewHolder(view)
            }
            Msg.TYPE_SENT_IMG -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.receive_img, parent, false)
                ImgViewHolder(view)
            }
            Msg.TYPE_RECEIVED_PICTURE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.send_picture, parent,false)
                ImgViewHolder(view)
            }
            else -> throw Exception("不支持的消息类型")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is ReceiveViewHolder -> {
                holder.receiveMsg.text = msg.message
                holder.receiveUserName.text = msg.userName
            }
            is SendViewHolder -> {
                holder.sendMsg.text = msg.message
                holder.sendUserName.text = msg.userName
            }
        }
    }

    override fun getItemCount() = msgList.size

}
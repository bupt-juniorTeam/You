package com.you.android.ui.chatroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.you.android.R

class MsgAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ReceiveViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val receiveMsg: TextView = view.findViewById(R.id.receiveMessage)
        val receiveUserName: TextView = view.findViewById(R.id.receiveUserName)
    }

    inner class SendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sendMsg: TextView = view.findViewById(R.id.sendMessage)
        val sendUserName: TextView = view.findViewById(R.id.sendUserName)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == Msg.TYPE_RECEIVED) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.receive_message, parent, false)
            ReceiveViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.send_message, parent, false)
            SendViewHolder(view)
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
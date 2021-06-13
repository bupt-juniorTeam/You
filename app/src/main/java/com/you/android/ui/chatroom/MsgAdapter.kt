package com.you.android.ui.chatroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.you.android.R
import java.lang.Exception

class MsgAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    open inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userAvatar: ImageView = view.findViewById(R.id.avatar)
    }

    inner class ReceiveViewHolder(view: View) : BaseViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.receiveUserName)
        val receiveMsg: TextView = view.findViewById(R.id.receiveMessage)
    }

    inner class SendViewHolder(view: View) : BaseViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.sendUserName)
        val sendMsg: TextView = view.findViewById(R.id.sendMessage)
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
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.receive_img, parent, false)
                ImgViewHolder(view)
            }
            Msg.TYPE_RECEIVED_PICTURE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.send_img, parent, false)
                ImgViewHolder(view)
            }
            else -> throw Exception("不支持的消息类型")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is ReceiveViewHolder -> {
                holder.receiveMsg.text = msg.message
                holder.userName.text = msg.userName
                setAvatar(holder, msg.userAvatar)
            }
            is SendViewHolder -> {
                holder.sendMsg.text = msg.message
                holder.userName.text = msg.userName
                setAvatar(holder, msg.userAvatar)

            }
        }
    }

    private fun setAvatar(holder: BaseViewHolder, avatarId: String) {
        when (avatarId) {
//            "default" -> holder.userAvatar.setImageResource(R.mipmap.avatar_default)
            "default" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_default).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "1" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_1).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "2" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_2).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "3" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_3).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "4" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_4).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "5" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_5).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "6" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_6).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "7" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_7).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "8" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_8).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "9" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_9).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "10" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_10).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "11" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_11).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)
            "12" -> Glide.with(holder.itemView.context).load(R.mipmap.avatar_12).apply(
                RequestOptions.bitmapTransform(RoundedCorners(24))).into(holder.userAvatar)

        }
    }

    override fun getItemCount() = msgList.size

}
package com.you.android.ui.roomlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.you.android.R
import com.you.android.logic.model.RoomListResponse

class RoomListAdapter : RecyclerView.Adapter<RoomListAdapter.ViewHolder>() {
    var room_list = ArrayList<RoomListResponse.Room>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val room_name = itemView.findViewById<TextView>(R.id.room_name)
        val population = itemView.findViewById<TextView>(R.id.population)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.room_name.text = room_list[position].name
        holder.population.text = "房间里有${room_list[position].population}人在聊天"
    }

    override fun getItemCount(): Int {
        return room_list.size
    }

}
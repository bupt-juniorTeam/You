package com.you.android.ui.roomlist

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.you.android.R
import com.you.android.logic.model.RoomListResponse
import com.you.android.ui.chatroom.ChatroomActivity
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur


class RoomListAdapter(val decorView: View) : RecyclerView.Adapter<RoomListAdapter.ViewHolder>() {
    var room_list = ArrayList<RoomListResponse.Room>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val room_name = itemView.findViewById<TextView>(R.id.room_name)
        val population = itemView.findViewById<TextView>(R.id.population)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_list_item, parent, false)
        val viewHolder = ViewHolder(view)

        val radius = 20f
        val rootView: ViewGroup = decorView.findViewById(android.R.id.content)
        val windowBackground: Drawable = decorView.getBackground()
        view.findViewById<BlurView>(R.id.blur_view)
            .setupWith(decorView.findViewById(android.R.id.content))
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(parent.context))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true) // Or false if it's in a scrolling container or might be animated

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(view.context, ChatroomActivity::class.java)
            intent.putExtra("roomName", viewHolder.room_name.text)
            view.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.room_name.text = room_list[position].name
        holder.population.text = "${room_list[position].population}"
    }

    override fun getItemCount(): Int {
        return room_list.size
    }

}
package com.you.android.logic.model
//{
//    "room_list":[
//    {
//        "name":"",
//        "population":0
//    }
//    ]
//}
data class RoomListResponse(val room_list: List<Room>) {
    data class Room(val name: String, val population: Int)
}

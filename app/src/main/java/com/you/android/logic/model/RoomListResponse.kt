package com.you.android.logic.model
//{
//    "rooms":[{
//    "id":0,
//    "name":"test",
//    "people_number":0
//}]
//}
data class RoomListResponse(val room_list: List<Room>) {
    data class Room(val name: String, val population: Int)
}

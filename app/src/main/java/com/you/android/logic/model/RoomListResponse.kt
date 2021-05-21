package com.you.android.logic.model
//{
//    "rooms":[{
//    "id":0,
//    "name":"test",
//    "people_number":0
//}]
//}
data class RoomListResponse(val rooms: List<Room>) {
    data class Room(val id: Int, val name: String, val people_number: Int)
}

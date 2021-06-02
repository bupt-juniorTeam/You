package com.you.android.logic.model

//{
//    "type":"",
//    "data":{
//    "user_name":"",
//    "room_name":"",
//    "msg":"",
//    }
//}
data class WebSocketMessage(
    val type: String,
    val data: Data
    ) {
    data class Data(
        val user_name: String,
        val room_name: String,
        val msg: String,
    )
}

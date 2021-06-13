package com.you.android.logic.model

//{
//    "type":"",
//    "data":{
//    "user":{
//            "user_name":"",
//            "user_avatar":""
//        },
//     "room_name":"",
//     "msg":""
//    }
//}
data class WebSocketMessage(
    val type: String,
    val data: Data
) {
    data class Data(
        val user: User,
        val room_name: String,
        val msg: String,
    )

    data class User(
        val user_name: String,
        val user_avatar: String
    )

}

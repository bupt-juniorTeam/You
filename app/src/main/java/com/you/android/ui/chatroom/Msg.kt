package com.you.android.ui.chatroom

data class Msg(val type: Int, val userName: String, val message: String) {
    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
        const val TYPE_SENT_IMG = 2
        const val TYPE_RECEIVED_PICTURE =3
    }
}
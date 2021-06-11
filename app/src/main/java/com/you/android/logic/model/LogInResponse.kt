package com.you.android.logic.model

data class LogInResponse(val res:String, val data:Data){
    data class Data(
        val user_tel: Int,
        val user_name: String,
        val user_password: String,
        val user_avatar_url:String,
    )
}
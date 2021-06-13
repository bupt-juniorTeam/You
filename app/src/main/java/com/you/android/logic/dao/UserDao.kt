package com.you.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.you.android.YouApplication

object UserDao {


    fun saveUserName(userName: String) {
        sharedPreferences().edit {
            putString("userName", userName)
        }
    }

    fun getUserName(): String {
        return sharedPreferences().getString("userName", "").toString()
    }

    // TODO
    fun getUserAvatar(): String {
        return "default"
    }

    fun isNameSaved() = sharedPreferences().contains("userName")

    private fun sharedPreferences() = YouApplication.context.getSharedPreferences(
        "you",
        Context.MODE_PRIVATE
    )
}
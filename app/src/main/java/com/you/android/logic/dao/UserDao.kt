package com.you.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.you.android.YouApplication

object UserDao {

    fun saveUserTel(userTel: String) {
        sharedPreferences().edit {
            putString("userTel", userTel)
        }
    }

    fun saveUserName(userName: String) {
        sharedPreferences().edit {
            putString("userName", userName)
        }
    }

    fun saveUserAvatar(userAvatar: String) {
        sharedPreferences().edit() {
            putString("userAvatar", userAvatar)
        }
    }

    fun getUserTel(): String {
        return sharedPreferences().getString("userTel", "").toString()
    }

    fun getUserName(): String {
        return sharedPreferences().getString("userName", "").toString()
    }

    fun getUserAvatar(): String {
        return sharedPreferences().getString("userAvatar", "").toString()
    }


    fun isTelSaved() = sharedPreferences().contains("userTel")

    fun isNameSaved() = sharedPreferences().contains("userName")

    fun isAvatarSaved() = sharedPreferences().contains("userAvatar")

    private fun sharedPreferences() = YouApplication.context.getSharedPreferences(
        "you",
        Context.MODE_PRIVATE
    )
}
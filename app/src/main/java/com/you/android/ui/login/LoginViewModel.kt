package com.you.android.ui.login

import androidx.lifecycle.ViewModel
import com.you.android.logic.YouSocket
import com.you.android.logic.Repository

class LoginViewModel: ViewModel() {

    internal lateinit var userTel: String
    internal lateinit var userPassword: String
    lateinit var youSocket: YouSocket
    fun logIn(){
        Repository.userLogIn(userTel,userPassword)
    }

}
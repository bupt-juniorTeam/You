package com.you.android.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.you.android.logic.Repository

class LoginViewModel : ViewModel() {
    private val flagLiveData = MutableLiveData<Unit>()

    var flag = false

    internal lateinit var userTel: String
    internal lateinit var userPassword: String

    val loginLiveData = Transformations.switchMap(flagLiveData) {
        Repository.userLogIn(userTel, userPassword)
    }

    fun logIn() {
        flagLiveData.value = flagLiveData.value
    }

}
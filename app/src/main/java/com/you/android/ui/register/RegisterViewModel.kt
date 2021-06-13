package com.you.android.ui.register
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.you.android.logic.Repository


class RegisterViewModel : ViewModel() {
    private val flagLiveData = MutableLiveData<Unit>()

    internal lateinit var userTel: String
    internal lateinit var userName: String
    internal lateinit var userPassword: String

    val registerLiveData = Transformations.switchMap(flagLiveData) {
        Repository.userRegister(userTel,userName,userPassword)
    }

    fun register() {
        flagLiveData.value = flagLiveData.value
    }
}



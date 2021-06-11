package com.you.android.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.you.android.R
import com.you.android.ui.chatroom.ChatroomViewModel
import com.you.android.ui.login.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextPhoneNumber:EditText=findViewById(R.id.editTextPhoneNumber)
        val editTextPassword:EditText=findViewById(R.id.editTextPassword)

        val buttonLogIn:Button=findViewById(R.id.buttonLogIn)



        buttonLogIn.setOnClickListener{
            val userTel=editTextPhoneNumber.text.toString()
            val userPassword=editTextPassword.text.toString()
            viewModel.userTel=userTel
            viewModel.userPassword=userPassword
            var a=viewModel.logIn()
            Log.d("asd","asd")
        }
    }
}
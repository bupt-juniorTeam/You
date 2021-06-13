package com.you.android.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.you.android.R
import com.you.android.logic.model.RoomListResponse
import com.you.android.ui.chatroom.ChatroomViewModel
import com.you.android.ui.login.LoginViewModel
import com.you.android.ui.register.RegisterActivity
import com.you.android.ui.roomlist.RoomListActivity
import com.you.android.util.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextPhoneNumber: EditText = findViewById(R.id.editTextPhoneNumber)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)

        val buttonLogIn: Button = findViewById(R.id.buttonLogIn)
        val buttonLogInRegister:Button=findViewById(R.id.buttonLogInRegister)

        viewModel.loginLiveData.observe(this, { result ->
            val res = result.getOrNull()
            if (res == "login successfully") {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "登录失败: $res", Toast.LENGTH_SHORT).show()
            }
        })


        buttonLogIn.setOnClickListener {
            val userTel = editTextPhoneNumber.text.toString()
            val userPassword = editTextPassword.text.toString()
            viewModel.userTel = userTel
            viewModel.userPassword = userPassword
            if (userTel.isNotEmpty() && userPassword.isNotEmpty())
                viewModel.logIn()
            else
                Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show()
        }

        buttonLogInRegister.setOnClickListener {
            val intent=Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
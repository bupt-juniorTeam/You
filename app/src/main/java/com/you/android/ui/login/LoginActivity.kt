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
import com.you.android.logic.dao.UserDao
import com.you.android.logic.model.RoomListResponse
import com.you.android.ui.chatroom.ChatroomViewModel
import com.you.android.ui.homepage.HomePageActivity
import com.you.android.ui.login.LoginViewModel
import com.you.android.ui.roomlist.RoomListActivity
import com.you.android.util.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextPhoneNumber: EditText = findViewById(R.id.editTextPhoneNumber)
        // 如果userdao有值，将userdao中的user_name设为默认值
        if (UserDao.isNameSaved()) {
            val name = UserDao.getUserName()
            editTextPhoneNumber.setText(name)
        }
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)

        val buttonLogIn: Button = findViewById(R.id.buttonLogIn)


        viewModel.loginLiveData.observe(this, { result ->
            val res = result.getOrNull()
            if (res == "login successfully") {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                viewModel.flag = true
            } else {
                Toast.makeText(this, "登录失败: $res", Toast.LENGTH_SHORT).show()
                viewModel.flag = false
            }
        })


        buttonLogIn.setOnClickListener {
            val userTel = editTextPhoneNumber.text.toString()
            val userPassword = editTextPassword.text.toString()
            viewModel.userTel = userTel
            viewModel.userPassword = userPassword
            if (userTel.isNotEmpty() && userPassword.isNotEmpty()) {
                // 将用户名存入userDao
                UserDao.saveUserName(userTel)
                viewModel.logIn()
                if (viewModel.flag) {
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                }
            } else
                Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show()
        }
    }
}
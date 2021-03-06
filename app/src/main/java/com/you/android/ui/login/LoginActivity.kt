package com.you.android.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lxj.xpopup.XPopup
import com.you.android.R
import com.you.android.logic.dao.UserDao
import com.you.android.ui.homepage.HomePageActivity
import com.you.android.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loading = XPopup.Builder(this)
            .asLoading("绝赞请求登录中")

        val editTextPhoneNumber: EditText = findViewById(R.id.editTextPhoneNumber)
        // 记住账号
        if (UserDao.isTelSaved()) {
            val tel = UserDao.getUserTel()
            editTextPhoneNumber.setText(tel)
        }
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)

        val buttonLogIn: Button = findViewById(R.id.buttonLogIn)
        val buttonLogInRegister: Button = findViewById(R.id.buttonLogInRegister)

        viewModel.loginLiveData.observe(this, { result ->
            val res = result.getOrNull()
            if (res == "login successfully") {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                viewModel.flag = true
                runOnUiThread {
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "登录失败: $res", Toast.LENGTH_SHORT).show()
                viewModel.flag = false
            }
            loading.dismiss()
        })


        buttonLogIn.setOnClickListener {
            val userTel = editTextPhoneNumber.text.toString()
            val userPassword = editTextPassword.text.toString()
            viewModel.userTel = userTel
            viewModel.userPassword = userPassword
            if (userTel.isNotEmpty() && userPassword.isNotEmpty()) {
                loading.show()
                UserDao.saveUserTel(userTel)
                viewModel.logIn()
            } else
                Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show()
        }

        buttonLogInRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
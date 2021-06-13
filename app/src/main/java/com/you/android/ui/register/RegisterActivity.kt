package com.you.android.ui.register

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.you.android.R
import com.you.android.ui.login.LoginViewModel

class RegisterActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val editTextUserName: EditText =findViewById(R.id.editTextRegisterPersonName)
        val editTextPhone: EditText=findViewById(R.id.editTextRegisterPhone)
        val editTextPassword:EditText=findViewById(R.id.editTextTextRegisterPassword)
        val regiterButton:Button=findViewById(R.id.buttonRegister)


        viewModel.registerLiveData.observe(this, {result->
            val res=result.getOrNull()
            if(res=="register successfully"){
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(this,"该手机号已被注册",Toast.LENGTH_SHORT).show()
            }
        })

        regiterButton.setOnClickListener{
            val userName:String=editTextUserName.text.toString()
            val userTel:String=editTextPhone.text.toString()
            val userPassword:String=editTextPassword.text.toString()

            viewModel.userName=userName
            viewModel.userTel=userTel
            viewModel.userPassword=userPassword

            if(userName.isNotEmpty()&&userPassword.isNotEmpty()&&userTel.isNotEmpty()){
                viewModel.register()
            }

        }

    }
}
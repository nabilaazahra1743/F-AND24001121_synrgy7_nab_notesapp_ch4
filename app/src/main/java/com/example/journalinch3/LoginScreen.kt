package com.example.journalinch3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class LoginScreen : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText

    private lateinit var username: String
    private lateinit var password: String

    //validasi data
    private var validUsername = "nabilaazahr"
    private var validPassword = "0174"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        init()
        checkLogin()
    }

    private fun init(){
        prefManager = PrefManager(this)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)

    }

    private fun checkLogin(){
        if(prefManager.isLogin()!!){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun clickLogin(view: View){
        username = etUsername.text.toString().trim()
        password = etPassword.text.toString().trim()

        if(username.isEmpty() || username == ""){
            etUsername.error = "Username harus diisi"
            etUsername.requestFocus()
        } else if(password.isEmpty() || password==""){
            etPassword.error = "Password harus diisi"
            etPassword.requestFocus()
        } else if(username != validUsername) {
            etUsername.error = "Invalid Username"
            etUsername.requestFocus()
        } else {
            prefManager.setLogin(true)
            prefManager.setUsername(username)

            val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
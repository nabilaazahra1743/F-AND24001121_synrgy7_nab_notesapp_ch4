package com.example.journalinch3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.journalinch3.database.NoteDatabase
import com.example.journalinch3.repository.NoteRepository
import com.example.journalinch3.viewmodel.NoteViewModel
import com.example.journalinch3.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    private lateinit var prefManager: PrefManager
    private lateinit var username: String
    private lateinit var tvData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        init()
        checkLogin()
        setupUI()
    }

    private fun init(){
        prefManager = PrefManager(this)
        username = prefManager.getUsername().toString()
        tvData = findViewById(R.id.tvData)
    }

    private fun checkLogin(){
        if(prefManager.isLogin() == false){
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun setupUI(){
        tvData.text = "Hello, $username"
    }

    fun clickLogout(view: View){
        prefManager.removeData()
        val intent = Intent(this, LoginScreen::class.java)
        startActivity(intent)
        finish()
    }

    private fun setupViewModel(){
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }
}
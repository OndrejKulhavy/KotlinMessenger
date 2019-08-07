package com.example.kotlinmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button.setOnClickListener(){
       val username = username_edittext.text.toString()
       val email = email_edittext.text.toString()
       val password = password_edittext.text.toString()
            Log.d("MainActivity", "tvuj emaile je: " + email)
            Log.d("MainActivity", "tvoje heslo je: " + password)
            Log.d("MainActivity", "tvuj uzivatelske jmeno je: " + username)
            Log.d("MainActivity", "----------------------------------------------------")
        }
        Already_have_an_acount_edittext.setOnClickListener(){
            Log.d("MainActivity", "Funguje nacitani loginu")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }













    }
}

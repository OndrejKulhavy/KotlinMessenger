package com.example.kotlinmessenger

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        log_in_button.setOnClickListener(){
        val  email = email_login_edittext.text.toString()
        val  password = password_login_edittext.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            //    .addOnCompleteListener()
            // .add
        }

        i_have_an_account.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
















    }
}
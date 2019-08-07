package com.example.kotlinmessenger

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button.setOnClickListener(){
            performRegister()
        }

        Already_have_an_acount_edittext.setOnClickListener(){
            Log.d("MainActivity", "Funguje nacitani loginu")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        select_photo_button.setOnClickListener {
        val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }



    }
    var selectedPhotoUri :   Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode ==Activity.RESULT_OK && data != null){
             selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapDrawable =BitmapDrawable(bitmap)
            select_photo_button.setBackgroundDrawable(bitmapDrawable)
        }

    }

private fun  performRegister(){
    val username = username_edittext.text.toString()
    val email = email_edittext.text.toString()
    val password = password_edittext.text.toString()
    if (email.isEmpty() || password.isEmpty()){
        Toast.makeText(this, "Pro pokračování musíš vyplnit email a heslo", Toast.LENGTH_SHORT).show()
        return
      }
    //ověření firebase
    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener{
            if (!it.isSuccessful) return@addOnCompleteListener
            Log.d("Main", "Succesfully created user")

            uploadImageToFirebaseStorage()
            }
        .addOnFailureListener{
            Toast.makeText(this, "Chyba při vytváření účtu: ${it.message}", Toast.LENGTH_SHORT).show()

        }
    }

    private fun uploadImageToFirebaseStorage(){
        if (selectedPhotoUri == null ) return
    val  filename = UUID.randomUUID().toString()
   val  ref = FirebaseStorage.getInstance().getReference("/images/$filename")
        ref.putFile(selectedPhotoUri !!)
            .addOnSuccessListener {
                Log.d("Register", "Uspesne nahrany obrazek ${it.metadata?.path}")
            }
    }

}

package com.example.ass2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser !=null){
            val it = Intent(this,memer::class.java)
            startActivity(it)
            finish()
        }

        bt.setOnClickListener {
            val it = Intent(this, login::class.java)
            startActivity(it)
        }
    }
}
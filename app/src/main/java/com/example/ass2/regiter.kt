package com.example.ass2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_regiter.*

class regiter : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regiter)
        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser != null) {
            val it = Intent(this, memer::class.java)
            startActivity(it)
            finish()
        }

        button3.setOnClickListener {
            val email = ed1.text.toString().trim()
            val pass = editTextTextPersonName2.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "กรุณากรอก Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                Toast.makeText(this, "กรุณากรอก Password", Toast.LENGTH_SHORT).show()
            }

            auth!!.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (pass.length < 8) {
                        editTextTextPersonName2.error = "กรอกรหัสผ่านให้มากกว่า 8 ตัวอักษร"
                    } else {
                        Toast.makeText(
                            this,
                            "Login ล้มเหลว เนื่องจาก : " + task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
                    val it = Intent(this, memer::class.java)
                    startActivity(it)

                    finish()

                }
            }
        }
        button4.setOnClickListener {
            val it = Intent(this, login::class.java)
            startActivity(it)

        }
    }
}
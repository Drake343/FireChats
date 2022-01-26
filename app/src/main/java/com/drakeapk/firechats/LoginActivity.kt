package com.drakeapk.firechats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password= etPassword.text.toString()

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                Toast.makeText(applicationContext,"email and password are required",Toast.LENGTH_SHORT)
            }else{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        if(it.isSuccessful){
                            etEmail.setText("")
                            etPassword.setText("")

                            val intent= Intent(this@LoginActivity,HomeActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(applicationContext,"email or password invalid",Toast.LENGTH_SHORT)

                        }
                    }

            }

        }
        btnSignUp.setOnClickListener {
            val intent= Intent(this@LoginActivity,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.covac.ui.initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.covac.R
import com.example.covac.ui.login.LoginActivity
import com.example.covac.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_initial.*

class InitialActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        email_login_btn.setOnClickListener(this)
        signup_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.email_login_btn -> startActivity(Intent(this, LoginActivity::class.java))

            R.id.signup_btn -> startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
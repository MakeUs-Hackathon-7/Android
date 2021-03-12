package com.example.covac.ui.talk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covac.R
import com.example.covac.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_upload_post.*

class UploadTalkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_post)

        cancle_btn.setOnClickListener{
            finish()
        }

        upload_btn.setOnClickListener {
            finish()
        }
    }
}
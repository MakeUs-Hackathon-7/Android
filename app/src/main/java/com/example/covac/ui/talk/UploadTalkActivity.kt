package com.example.covac.ui.talk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covac.R
import kotlinx.android.synthetic.main.activity_upload_talk.*

class UploadTalkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_talk)

        cancle_btn.setOnClickListener{
            finish()
        }

        upload_btn.setOnClickListener {
            finish()
        }
    }
}
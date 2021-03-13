package com.example.covac.ui.symptom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covac.R
import kotlinx.android.synthetic.main.activity_upload_talk.*

class RegisterSymptomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_symptom)

        cancle_btn.setOnClickListener{
            finish()
        }

        upload_btn.setOnClickListener {
            finish()
        }
    }
}
package com.example.covac.ui.initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covac.MainActivity
import com.example.covac.R
import com.example.covac.ui.symptom.RegisterSymptomActivity
import kotlinx.android.synthetic.main.activity_induce_symptom.*

class InduceSymptomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_induce_symptom)

        register_symptom_btn.setOnClickListener {
            startActivity(Intent(this, RegisterSymptomActivity::class.java))
        }

        to_home_btn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
package com.example.covac.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covac.R
import com.example.covac.adapter.MySympAdapter
import com.example.covac.adapter.SymptomAdapter
import com.example.covac.data.MySymp
import com.example.covac.data.Symptom
import com.example.covac.ui.symptom.RegisterSymptomActivity
import kotlinx.android.synthetic.main.activity_my_symptom.*
import kotlinx.android.synthetic.main.fragment_symptom.*
import kotlinx.android.synthetic.main.fragment_symptom.recycler_view

class MySymptomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_symptom)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)


        add_symp_btn.setOnClickListener {
            startActivity(Intent(this, RegisterSymptomActivity::class.java))

        }
        val symptomList = arrayListOf<MySymp>()
        symptomList.add(MySymp("당일과 다음날은 무조건 쉬어야 할것 같네요.", false, true, false, true, true))

        symptomList.add(MySymp("온몸에 몸살이나서 주말동안 아무것도 못했습니다ㅠㅠ", true, false, false, false, true))


        symptomList.add(MySymp("울렁거리는 느낌이 들었습니다.", false, true, true, false, false))


        recycler_view_mysymp.apply{
            layoutManager = LinearLayoutManager(context)
            adapter =
                    MySympAdapter(symptomList) { mysymp ->
                        Toast.makeText(context, "$mysymp", Toast.LENGTH_SHORT).show()

//                    startActivity(Intent(this, Symptom))
                    }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
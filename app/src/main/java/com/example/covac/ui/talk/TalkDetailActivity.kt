package com.example.covac.ui.talk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covac.R
import com.example.covac.adapter.CommentAdapter
import com.example.covac.adapter.TalkAdapter
import com.example.covac.data.Comment
import com.example.covac.data.Talk
import kotlinx.android.synthetic.main.fragment_talk.*

class TalkDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_talk_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        val commentList = arrayListOf<Comment>()
        commentList.add(Comment("비누", "2020-03-08", "백신 다들 언제쯤 맞으시나요?"))

        commentList.add(Comment("이고백", "2020-03-09",  "제 지인은 간호사인데 벌써 백신 맞았다구 하네요!"))

        commentList.add(Comment("태은", "2020-03-10", "저 엊그제 백신 맞았는데 휴가냈어요ㅠㅠ"))

        commentList.add(Comment("헤더", "2020-03-10",  "백신 맞기전에 삼겹살 먹으면 안아프다는거 실환가요?"))

        commentList.add(Comment("lofi", "2020-03-10", "아스트라제네카 vs 화이자 ㄱ"))


        recycler_view.apply{
            layoutManager = LinearLayoutManager(context)
            adapter =
                    CommentAdapter(commentList) { comment ->
                        Toast.makeText(context, "$comment", Toast.LENGTH_SHORT).show()
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
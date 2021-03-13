package com.example.covac.ui.mypage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covac.R
import com.example.covac.TestActivity
import com.example.covac.adapter.TalkAdapter
import com.example.covac.data.Talk
import com.example.covac.data.retrofit.UserAuth
import com.example.covac.data.retrofit.UserTalk
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import com.example.covac.ui.talk.TalkDetailActivity
import kotlinx.android.synthetic.main.activity_my_talk.*
import kotlinx.android.synthetic.main.fragment_talk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MyTalkActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var myAPI: RetrofitService
    val TAG: String = TestActivity::class.java.simpleName
    lateinit var token_: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_talk)

        initRetrofit()

        val preferences: SharedPreferences =this@MyTalkActivity.getSharedPreferences("covac", Context.MODE_PRIVATE)
        token_ = preferences.getString("TOKEN", null).toString()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        getUserTalk()

        val talkList = arrayListOf<Talk>()
        talkList.add(Talk("비누", "2020-03-08", "댓글 1개", "백신 다들 언제쯤 맞으시나요?"))

        talkList.add(Talk("비누", "2020-03-09", "댓글 3개", "제 지인은 간호사인데 벌써 백신 맞았다구 하네요!"))

        talkList.add(Talk("비누", "2020-03-10", "댓글 5개", "저 엊그제 백신 맞았는데 휴가냈어요ㅠㅠ"))

        talkList.add(Talk("비누", "2020-03-10", "댓글 3개", "백신 맞기전에 삼겹살 먹으면 안아프다는거 실환가요?"))

        talkList.add(Talk("비누", "2020-03-10", "댓글 7개", "아스트라제네카 vs 화이자 ㄱ"))


        recycler_view_mytalk.apply{
            layoutManager = LinearLayoutManager(context)
            adapter =
                    TalkAdapter(talkList) { talk ->
                        Toast.makeText(context, "$talk", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(context, TalkDetailActivity::class.java))
//                    startActivity(Intent(this, Symptom))
                    }
        }

    }

    private fun initRetrofit() {

        retrofit = RetrofitClient.getInstance() // 2에서 만든 Retrofit client의 instance를 불러옵니다.
        myAPI = retrofit.create(RetrofitService::class.java) // 여기서 retrofit이 우리의 interface를 구현해주고
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

    private fun getUserTalk(){
        Runnable {

            myAPI.myPage_talk(token_).enqueue(object : Callback<UserTalk> {
                override fun onFailure(call: Call<UserTalk>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<UserTalk>, response: Response<UserTalk>) {
                    Log.v("success", response.code().toString())
                    Log.v("success", response.body().toString())
                    val isSuccess: Boolean = response.body()?.isSuccess ?: false
                    val code: Int = response.body()?.code ?: 0
                    val message: String = response.body()?.message ?: "no message"

                    Log.v("success", isSuccess.toString())
                    Log.v("code", code.toString())
                    Log.v("message", message)
                    Log.v("token", token_)
                    Log.d(TAG, "response : ${response.raw().request().url().url()}")

                    if(code == 200){


                    }
//                    toNextPage()
                }
            })


        }.run()
    }
}
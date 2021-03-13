package com.example.covac.ui.talk

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.covac.R
import com.example.covac.TestActivity
import com.example.covac.data.retrofit.DataClass
import com.example.covac.data.retrofit.UserInfo
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import kotlinx.android.synthetic.main.activity_upload_talk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class UploadTalkActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var myAPI: RetrofitService
    val TAG: String = TestActivity::class.java.simpleName
    lateinit var token_: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_talk)

        val preferences: SharedPreferences =this@UploadTalkActivity.getSharedPreferences("covac", Context.MODE_PRIVATE)
        token_ = preferences.getString("TOKEN", null).toString()


        initRetrofit()
        cancle_btn.setOnClickListener{
            finish()
        }

        upload_btn.setOnClickListener {
            uploadTalk()

        }
    }

    private fun initRetrofit() {

        retrofit = RetrofitClient.getInstance() // 2에서 만든 Retrofit client의 instance를 불러옵니다.
        myAPI = retrofit.create(RetrofitService::class.java) // 여기서 retrofit이 우리의 interface를 구현해주고
    }

    private fun uploadTalk() {
        Runnable {
            val content = content_et.text.toString()
            myAPI.uploadTalk(token_, content).enqueue(object : Callback<DataClass> {
                override fun onFailure(call: Call<DataClass>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
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
                        finish()

                    }
//                    toNextPage()
                }
            })


        }.run()

    }
}
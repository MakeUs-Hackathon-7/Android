package com.example.covac.ui.symptom

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.covac.R
import com.example.covac.TestActivity
import com.example.covac.data.retrofit.NewDataClass
import com.example.covac.data.retrofit.UserInfo
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import kotlinx.android.synthetic.main.activity_upload_talk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RegisterSymptomActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var myAPI: RetrofitService
    val TAG: String = TestActivity::class.java.simpleName
    lateinit var token_: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_symptom)


        initRetrofit()

        val preferences: SharedPreferences =this@RegisterSymptomActivity.getSharedPreferences("covac", Context.MODE_PRIVATE)
        token_ = preferences.getString("TOKEN", null).toString()

        getUserInfo()
        cancle_btn.setOnClickListener{
            finish()
        }

        upload_btn.setOnClickListener {
            finish()
        }
    }

    private fun initRetrofit() {

        retrofit = RetrofitClient.getInstance() // 2에서 만든 Retrofit client의 instance를 불러옵니다.
        myAPI = retrofit.create(RetrofitService::class.java) // 여기서 retrofit이 우리의 interface를 구현해주고
    }

    private fun getUserInfo() {

        Runnable {

            myAPI.getUserInfo_register_symptom(token_).enqueue(object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
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
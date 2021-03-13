package com.example.covac.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.covac.R
import com.example.covac.TestActivity
import com.example.covac.data.retrofit.NewDataClass
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import com.example.covac.ui.certificate.InduceCertificateActivity
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class LoginActivity : AppCompatActivity() {

    lateinit var email_: String
    lateinit var password_: String

    lateinit var retrofit: Retrofit
    lateinit var myAPI: RetrofitService
    val TAG: String = TestActivity::class.java.simpleName
    lateinit var token_: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)


        initRetrofit()


        login_btn.setOnClickListener{
            login()
//            startActivity(Intent(this, InduceCertificateActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
//                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun login(){
        email_ = email_et.text.toString()
        password_ = password_et.text.toString()

        Runnable {

            myAPI.loginUser(email_, password_).enqueue(object : Callback<NewDataClass> {
                override fun onFailure(call: Call<NewDataClass>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<NewDataClass>, response: Response<NewDataClass>) {
                    Log.v("success", response.code().toString())
                    Log.v("success", response.body().toString())
                    val isSuccess: Boolean = response.body()?.isSuccess ?: false
                    val code: Int = response.body()?.code ?: 0
                    val message: String = response.body()?.message ?: "no message"
                    token_=response.body()?.result?.token ?: "no token"



                    Log.v("success", isSuccess.toString())
                    Log.v("code", code.toString())
                    Log.v("message", message)
                    Log.v("token", token_)
                    Log.d(TAG, "response : ${response.raw().request().url().url()}")

                    if(code == 200){


                        val preferences: SharedPreferences = this@LoginActivity.getSharedPreferences("covac", Context.MODE_PRIVATE)
                        preferences.edit().putString("TOKEN", token_).apply()
                        toNextPage()
                    }
//                    toNextPage()
                }
            })


        }.run()
    }

    private fun initRetrofit() {

        retrofit = RetrofitClient.getInstance() // 2에서 만든 Retrofit client의 instance를 불러옵니다.
        myAPI = retrofit.create(RetrofitService::class.java) // 여기서 retrofit이 우리의 interface를 구현해주고
    }

    fun toNextPage() {

//        nextIntent.putExtra("email", email_et.text.toString())
//        nextIntent.putExtra("password", password_et.text.toString())
        val nextIntent = Intent(this, InduceCertificateActivity::class.java)
        startActivity(nextIntent)

    }
}
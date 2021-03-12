package com.example.covac.ui.signup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.covac.R
import com.example.covac.TestActivity
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import com.example.covac.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Retrofit
import java.io.File

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)


        signup_btn.setOnClickListener {
            Log.v("test", email_et.text.toString())
            val nextIntent = Intent(this, SetNicknameActivity::class.java)
            nextIntent.putExtra("email", email_et.text.toString())
            nextIntent.putExtra("password", password_et.text.toString())
            startActivity(nextIntent)

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

//    private fun signUp() {
//        Runnable {
//
//            val email_= email_et.text
//            val nickname_: password_et.text
//            val password_: String = "aaa"
//            val email = RequestBody.create(MediaType.parse("text/plain"), email_.toString())
//            val nickname = RequestBody.create(MediaType.parse("text/plain"), nickname_.toString())
//            val password = RequestBody.create(MediaType.parse("text/plain"), password_.toString())
//
//            myAPI.registerUser(email, nickname, password, body)
//                .enqueue(object : Callback<DataClass> {
//                    override fun onFailure(call: Call<DataClass>, t: Throwable) {
//                        t.printStackTrace()
//                    }
//
//                    override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
//                        Log.v("success", response.code().toString())
//                        Log.v("success", response.body().toString())
//                        val isSuccess: Boolean = response.body()?.isSuccess ?: false
//                        val code: Int = response.body()?.code ?: 0
//                        val message: String = response.body()?.message ?: "no message"
//
//                        Log.v("success", isSuccess.toString())
//                        Log.v("code", code.toString())
//                        Log.v("message", message)
//                        Log.d(TAG, "response : ${response.raw().request().url().url()}")
//                    }
//                })
//
//            myAPI.loginUser(email_, nickname_).enqueue(object : Callback<NewDataClass> {
//                override fun onFailure(call: Call<NewDataClass>, t: Throwable) {
//                    t.printStackTrace()
//                }
//
//                override fun onResponse(
//                    call: Call<NewDataClass>,
//                    response: Response<NewDataClass>
//                ) {
//                    Log.v("success", response.code().toString())
//                    Log.v("success", response.body().toString())
//                    val isSuccess: Boolean = response.body()?.isSuccess ?: false
//                    val code: Int = response.body()?.code ?: 0
//                    val message: String = response.body()?.message ?: "no message"
//                    token_ = response.body()?.result?.token ?: "no token"
//
//                    Log.v("success", isSuccess.toString())
//                    Log.v("code", code.toString())
//                    Log.v("message", message)
//                    Log.v("token", token_)
//                    Log.d(TAG, "response : ${response.raw().request().url().url()}")
//
//                    doOCR()
//                }
//            })
//
//
//        }.run()
//    }
}




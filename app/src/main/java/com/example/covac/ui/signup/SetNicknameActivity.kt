package com.example.covac.ui.signup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.covac.R
import com.example.covac.TestActivity
import com.example.covac.data.retrofit.DataClass
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import com.example.covac.ui.login.LoginActivity
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_set_nickname.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File

class SetNicknameActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var email_: String
    lateinit var password_: String
    lateinit var nickname_: String

    lateinit var retrofit: Retrofit
    lateinit var myAPI: RetrofitService
    val TAG: String = TestActivity::class.java.simpleName
    lateinit var filePath: String
    lateinit var uri_: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_nickname)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)

        initRetrofit()
        var intent = intent

        email_ = intent.getStringExtra("email").toString()
        password_ = intent.getStringExtra("password").toString()


        set_image_btn.setOnClickListener(this)
        start_btn.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.set_image_btn -> {
                TedImagePicker.with(this)
                    .title(R.string.select_picture)
                    .backButton(R.drawable.ic_arrow_back)
                    .buttonText(R.string.complete)
                    .buttonBackground(R.drawable.rec_tranparent)
                    .buttonTextColor(R.color.black)
                    .showCameraTile(false)
                    .start { uri -> showSingleImage(uri) }
            }

            R.id.start_btn -> signUp()
        }
    }

    private fun showSingleImage(uri: Uri) {

        imageView.setImageURI(uri)
        uri_ = uri

    }

    fun getRealPathFromURI(contentUri: Uri?): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri!!, proj, null, null, null)
        cursor!!.moveToNext()
        val path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
        val uri = Uri.fromFile(File(path))
        cursor.close()
        return path
    }


    private fun signUp() {
        Log.v("signup", "signup")
        Runnable {

            nickname_ = nickname_et.text.toString()
            val email = RequestBody.create(MediaType.parse("text/plain"), email_.toString())
            val nickname = RequestBody.create(MediaType.parse("text/plain"), nickname_.toString())
            val password = RequestBody.create(MediaType.parse("text/plain"), password_.toString())


            filePath = getRealPathFromURI(uri_)
            Log.v("uri", filePath)
            val file = File(filePath)
            var fileName = "imagename"
            fileName = "$fileName.png"


            val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
            val body: MultipartBody.Part =
                MultipartBody.Part.createFormData("img", fileName, requestBody)


            myAPI.registerUser(email, nickname, password, body)
                .enqueue(object : Callback<DataClass> {
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
                        Log.d(TAG, "response : ${response.raw().request().url().url()}")

//                        if (code == 200){
//                            toNextPage()
//                        }
                        toNextPage()
                    }
                })

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



        }.run()
    }

    fun toNextPage() {
//        val nextIntent = Intent(this, InduceCertificateActivity::class.java)
//        nextIntent.putExtra("email", email_)
//        nextIntent.putExtra("password", nickname_)
        startActivity(Intent(this, LoginActivity::class.java))
    }


}
package com.example.covac.ui.certificate

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.afollestad.materialdialogs.MaterialDialog
import com.example.covac.R
import com.example.covac.TestActivity
import com.example.covac.data.retrofit.DataClass
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import com.example.covac.ui.initial.InduceSymptomActivity
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_induce_certificate.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File


class InduceCertificateActivity : AppCompatActivity() {

    lateinit var token_: String

    lateinit var retrofit: Retrofit
    lateinit var myAPI: RetrofitService
    val TAG: String = TestActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_induce_certificate)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)


        initRetrofit()

        var intent = intent

//        email_ = intent.getStringExtra("email").toString()
//        nickname_ = intent.getStringExtra("nickname").toString()
        val preferences: SharedPreferences =this@InduceCertificateActivity.getSharedPreferences("covac", Context.MODE_PRIVATE)
        token_ = preferences.getString("TOKEN", null).toString() //second parameter default value.

        Log.v("token222", token_)


        certificate_image.setOnClickListener {
            MaterialDialog(this).show {
                title(R.string.upload_picture)
                cornerRadius(0f)
                neutralButton(R.string.cancle)

                positiveButton(R.string.take_picture) {

//                    ImagePicker.with(this@InduceCertificateActivity)
//                        .cameraOnly()    //User can only capture image using Camera
//                        .start()

                }

                negativeButton(R.string.select_gallery) {

                    TedImagePicker.with(this@InduceCertificateActivity)
                        .title(R.string.select_picture)
                        .backButton(R.drawable.ic_arrow_back)
                        .buttonText(R.string.complete)
                        .buttonBackground(R.drawable.rec_tranparent)
                        .buttonTextColor(R.color.black)
                        .showCameraTile(false)
                        .start { uri -> doOCR(uri) }
                }
            }
        }
    }

    private fun initRetrofit() {

        retrofit = RetrofitClient.getInstance() // 2?????? ?????? Retrofit client??? instance??? ???????????????.
        myAPI = retrofit.create(RetrofitService::class.java) // ????????? retrofit??? ????????? interface??? ???????????????
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

    private fun doOCR(uri: Uri) {
        Log.v("uri", uri.toString())
        val filePath = getRealPathFromURI(uri)
        val file = File(filePath)
        var fileName = "imagename"
        fileName = "$fileName.png"

        toNextPage()

//        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
//        val body: MultipartBody.Part =
//            MultipartBody.Part.createFormData("img", fileName, requestBody)
//
//
//        Runnable {
//            //val token_: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MiwiaWF0IjoxNjE1NTQyNTIyLCJleHAiOjE2NDcwNzg1MjIsInN1YiI6InVzZXJJbmZvIn0._bk5Ok7r_bAo8KyQKg65t7vPJ0kflPt5SlX7nLMvNE0"
////            val token = RequestBody.create(MediaType.parse("text/plain"), token_.toString())
//
//            myAPI.ocrAuth(token_, body).enqueue(object : Callback<DataClass> {
//                override fun onFailure(call: Call<DataClass>, t: Throwable) {
//                    t.printStackTrace()
//                }
//
//                override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
//                    Log.v("success", response.code().toString())
//                    Log.v("success", response.body().toString())
//                    val isSuccess: Boolean = response.body()?.isSuccess ?: false
//                    val code: Int = response.body()?.code ?: 0
//                    val message: String = response.body()?.message ?: "no message"
//
//                    Log.v("success", isSuccess.toString())
//                    Log.v("code", code.toString())
//                    Log.v("message", message)
//                    Log.d(TAG,"response : ${response.raw().request().header("access-token")}")
//
////                    if (code == 200){
////                        toNextPage()
////                    }
//
//                    toNextPage()
//                }
//            })
//        }.run()
    }

    fun toNextPage() {
        Thread.sleep(2000)
        val nextIntent = Intent(this, CheckOCRActivity::class.java)
        startActivity(nextIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val nextIntent = Intent(this, InduceSymptomActivity::class.java)
                startActivity(nextIntent)
//                startActivity(Intent(this, MainActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {

//                val fileUri = data?.data
//                val file: File = ImagePicker.getFile(data)!!
//                val filePath: String = ImagePicker.getFilePath(data)!!
//
//                val newImage = ImageView(this)
//                newImage.setImageURI(fileUri)
//
//
//                val lp = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    205.toPx(this)
//                )
//                lp.setMargins(0, 0, 0, 20.toPx(this))
//                newImage.layoutParams = lp
//                picture_layout.addView(newImage)

            }
        }
    }
}
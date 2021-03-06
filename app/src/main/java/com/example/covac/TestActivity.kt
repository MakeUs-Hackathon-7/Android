package com.example.covac

import android.Manifest
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.covac.data.retrofit.DataClass
import com.example.covac.data.retrofit.NewDataClass
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_test.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File


class TestActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var myAPI: RetrofitService
    val TAG: String = TestActivity::class.java.simpleName
    lateinit var token_: String
    lateinit var filePath: String

    var requestPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        initRetrofit()
        PermissionCheck(this, requestPermissions)
        Log.d(TAG, "on create!")
        setContentView(R.layout.activity_test)
        //retrofit setting

        button.setOnClickListener {
            Log.v("click", "clicked")

            TedImagePicker.with(this)
                .title(R.string.select_picture)
                .backButton(R.drawable.ic_arrow_back)
                .buttonText(R.string.complete)
                .buttonBackground(R.drawable.rec_tranparent)
                .buttonTextColor(R.color.black)
                .showCameraTile(false)
                .start { uri -> showSingleImage(uri) }
//            retrofitFun()
        }

    }

    private fun getPathFromUri(uri: Uri): String {
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null);
        cursor?.moveToNext()
        val Path: String = cursor?.getString(cursor.getColumnIndex("_data")) ?: "";

        cursor?.close()

        return Path
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


    private fun showSingleImage(uri: Uri) {
        Log.v("uri", uri.toString())
        Runnable {

            filePath = getRealPathFromURI(uri)
            Log.v("uri", filePath)
            val file = File(filePath)
            var fileName = "imagename"
            fileName = "$fileName.png"


            val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
            val body: MultipartBody.Part =
                MultipartBody.Part.createFormData("base_image", fileName, requestBody)
            val email_: String = "abcde@gmail.com"
            val nickname_: String = "aaa"
            val password_: String = "aaa"
            val email = RequestBody.create(MediaType.parse("text/plain"), email_.toString())
            val nickname = RequestBody.create(MediaType.parse("text/plain"), nickname_.toString())
            val password = RequestBody.create(MediaType.parse("text/plain"), password_.toString())
            val language_: String = "ko"
            val language = RequestBody.create(MediaType.parse("text/plain"), language_.toString())
//

            myAPI.ocrdirect(language_, body).enqueue(object : Callback<DataClass> {
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
                    Log.d(TAG,"response : ${response.raw().request().header("access-token")}")
                }
            })


        }.run()
    }

    private fun doOCR() {

        Log.v("uri", filePath)
        val file = File(filePath)
        var fileName = "imagename"
        fileName = "$fileName.png"


        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val body: MultipartBody.Part =
            MultipartBody.Part.createFormData("img", fileName, requestBody)
        val email_: String = "abcde@gmail.com"
        val nickname_: String = "aaa"
        val password_: String = "aaa"
        val email = RequestBody.create(MediaType.parse("text/plain"), email_.toString())
        val nickname = RequestBody.create(MediaType.parse("text/plain"), nickname_.toString())
        val password = RequestBody.create(MediaType.parse("text/plain"), password_.toString())


        Runnable {
            //val token_: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MiwiaWF0IjoxNjE1NTQyNTIyLCJleHAiOjE2NDcwNzg1MjIsInN1YiI6InVzZXJJbmZvIn0._bk5Ok7r_bAo8KyQKg65t7vPJ0kflPt5SlX7nLMvNE0"
            val token = RequestBody.create(MediaType.parse("text/plain"), token_.toString())
            myAPI.ocrAuth(token_, body).enqueue(object : Callback<DataClass> {
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
                    Log.d(TAG,"response : ${response.raw().request().header("access-token")}")
                }
            })
        }.run()
    }

    private fun initRetrofit() {

        retrofit = RetrofitClient.getInstance() // 2?????? ?????? Retrofit client??? instance??? ???????????????.
        myAPI = retrofit.create(RetrofitService::class.java) // ????????? retrofit??? ????????? interface??? ???????????????
    }


    private fun retrofitFun() {


        //Runnable??? ???????????? ?????????
        // Android ?????? MainThread ?????? ???????????? ?????? ?????? ?????????
        // ????????? ??????????????? ???????????? ?????????. ???????????? .run() ?????? ?????????
//        Runnable {
//            myAPI.getCodingTime("2019-11-09","MY_API_KEY").enqueue(object : Callback<RawResponseData> {
//
//                //?????? onFaliure??? Cal??? ??????????????? ?????? ????????? ?????? ???????????????.
//                override fun onFailure(call: Call<RawResponseData>, t: Throwable) {
//                    t.message?.let { Log.d(TAG, it) }
//                }
//
//
//                //?????? ?????? ?????? ???????????? ????????? resonse??? ????????? ???????????????.
//                //????????? call??? ?????? ??? RawResponseData??? ???????????? Reponse??? ??? ????????? ????????? ?????????.
//                override fun onResponse(call: Call<RawResponseData>, response: Response<RawResponseData>) {
//                    Log.d(TAG,"response : ${response.body()!!.start}") // ??????????????? ?????? ?????????.
//
//                    //?????? ?????? ????????? ?????? ????????? ????????? ?????? ?????????.
//                    //????????? Call??? ????????? ???????????? ???????????? ????????????? ?????? reponse??? ?????? ?????? ?????????.
//                    Log.d(TAG,"response : ${response.errorBody()}")
//                    Log.d(TAG,"response : ${response.message()}")
//                    Log.d(TAG,"response : ${response.code()}") //?????? ?????? ????????? ???????????? ?????? ??? ?????????.
//                    Log.d(TAG,"response : ${response.raw().request().url().url()}") //?????? url??? api call ??? ????????????
//                    //?????? ??? ??? ????????????.
//                }
//            })
//        }.run() //?????? ?????????!
    }

//    fun testRetrofit(path : String){
//
//        //creating a file
//        val file = File(path)
//        var fileName = userData.user_Id.replace("@","").replace(".","")
//        fileName = fileName+".png"
//
//
//        var requestBody : RequestBody = RequestBody.create(MediaType.parse("image/*"),file)
//        var body : MultipartBody.Part = MultipartBody.Part.createFormData("uploaded_file",fileName,requestBody)
//
//
//
//        //creating our api
//
//        var server = retrofit.create(retrofit_interface::class.java)
//
//        // ??????, ????????? ?????????, ????????????
//
//        server.post_Porfile_Request(userData.user_Id,body).enqueue(object:Callback<String>{
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.d("???????????? ??????1",t.message)
//            }
//
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if (response?.isSuccessful) {
//                    Toast.makeText(getApplicationContext(), "File Uploaded Successfully...", Toast.LENGTH_LONG).show();
//                    Log.d("???????????? ??????2",""+response?.body().toString())
//                } else {
//                    Toast.makeText(getApplicationContext(), "Some error occurred...", Toast.LENGTH_LONG).show();
//                }
//            }
//        })
//    }
}
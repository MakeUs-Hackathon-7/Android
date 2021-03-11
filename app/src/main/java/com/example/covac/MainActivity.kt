package com.example.covac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.covac.data.DataClass
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    lateinit var retrofit : Retrofit
    lateinit var myAPI : RetrofitService
    val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRetrofit()

        Log.d(TAG,"on create!")
        setContentView(R.layout.activity_main)
        //retrofit setting

        button.setOnClickListener {
            Log.v("click", "clicked")
            retrofitFun()
        }

    }

    private fun initRetrofit() {

        retrofit = RetrofitClient.getInstance() // 2에서 만든 Retrofit client의 instance를 불러옵니다.
        myAPI = retrofit.create(RetrofitService:: class.java) // 여기서 retrofit이 우리의 interface를 구현해주고
    }


    private fun retrofitFun() {
        Runnable {
            myAPI.getInfo().enqueue(object : Callback<DataClass> {
                override fun onFailure(call: Call<DataClass>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
                    val name: Int = response.body()?.fileSizeBytes ?: 0
                    val address: String = response.body()?.url?: ""

                    Log.v("name", name.toString())
                    Log.v("address", address)
                }
            })
        }.run()

        //Runnable로 감싸주는 이유는
        // Android 에서 MainThread 에서 네트워킹 관련 일을 못해서
        // 새로운 스레드에서 해주어야 합니다. 마지막에 .run() 잊지 마세요
//        Runnable {
//            myAPI.getCodingTime("2019-11-09","MY_API_KEY").enqueue(object : Callback<RawResponseData> {
//
//                //이때 onFaliure는 Cal을 서버쪽으로 아예 보내지 못한 경우입니다.
//                override fun onFailure(call: Call<RawResponseData>, t: Throwable) {
//                    t.message?.let { Log.d(TAG, it) }
//                }
//
//
//                //만약 보낸 것이 성공했을 경우는 resonse를 가지고 들어옵니다.
//                //그리고 call을 때릴 때 RawResponseData로 갔으니까 Reponse도 그 타입을 가지고 옵니다.
//                override fun onResponse(call: Call<RawResponseData>, response: Response<RawResponseData>) {
//                    Log.d(TAG,"response : ${response.body()!!.start}") // 정상출력이 되야 합니다.
//
//                    //만약 정상 출력이 되지 않으면 문제가 있는 겁니다.
//                    //이때는 Call은 제대로 보냈으나 서버에서 이거뭐냐? 하고 reponse를 보낸 경우 입니다.
//                    Log.d(TAG,"response : ${response.errorBody()}")
//                    Log.d(TAG,"response : ${response.message()}")
//                    Log.d(TAG,"response : ${response.code()}") //이게 가장 에러를 알아보기 쉬운 곳 입니다.
//                    Log.d(TAG,"response : ${response.raw().request().url().url()}") //무슨 url로 api call 을 보냈는지
//                    //확인 할 수 있습니다.
//                }
//            })
//        }.run() //잊지 마세요!
    }
}
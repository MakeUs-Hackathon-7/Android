package com.example.covac.ui.mypage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covac.R
import com.example.covac.TestActivity
import com.example.covac.data.retrofit.DataClass
import com.example.covac.data.retrofit.UserAuth
import com.example.covac.network.RetrofitClient
import com.example.covac.network.RetrofitService
import com.example.covac.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_upload_talk.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var retrofit: Retrofit
    lateinit var myAPI: RetrofitService
    val TAG: String = TestActivity::class.java.simpleName
    lateinit var token_: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


        val preferences: SharedPreferences? =activity?.getSharedPreferences("covac", Context.MODE_PRIVATE)
        if (preferences != null) {
            token_ = preferences.getString("TOKEN", null).toString()
        }

        initRetrofit()

        getUserAuth()
        first_info_btn.setOnClickListener {
            startActivity(Intent(context, MyVacHistoryActivity::class.java))
        }

        first_sypmtom.setOnClickListener {
            startActivity(Intent(context, MySymptomActivity::class.java))
        }

        my_talk_btn.setOnClickListener {
            startActivity(Intent(context, MyTalkActivity::class.java))
        }
    }

    private fun initRetrofit() {

        retrofit = RetrofitClient.getInstance() // 2에서 만든 Retrofit client의 instance를 불러옵니다.
        myAPI = retrofit.create(RetrofitService::class.java) // 여기서 retrofit이 우리의 interface를 구현해주고
    }

    private fun getUserAuth() {
        Runnable {

            myAPI.myPage_auth(token_).enqueue(object : Callback<UserAuth> {
                override fun onFailure(call: Call<UserAuth>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<UserAuth>, response: Response<UserAuth>) {
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
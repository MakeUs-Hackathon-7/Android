package com.example.covac.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-11
 */
object RetrofitClient {

    private var instance: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    // 서버 주소
    private const val BASE_URL = "https://random.dog"

    // SingleTon
    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return instance!!
    }
}
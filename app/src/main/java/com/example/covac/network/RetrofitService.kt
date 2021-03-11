package com.example.covac.network

import com.example.covac.data.DataClass
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-11
 */
// 서버에서 호출할 메서드를 선언하는 인터페이스
// POST 방식으로 데이터를 주고 받을 때 넘기는 변수는 Field라고 해야한다.

interface RetrofitService {

    @GET("/woof.json?ref=apilist.fun")
    fun getInfo() : Call<DataClass>

    @POST("userProfile/setUserProfileImage.php/")
    fun post_Porfile_Request(
        @Part("userId") userId: String,
        @Part imageFile : MultipartBody.Part): Call<String>

//    @Query("Type")Type:String,
//    @Query("KEY")KEY:String,
//    @Query("pIndex")pIndex:Int? = null,
//    @Query("pSize")pSize:Int? = null


//    @GET("users/{user}/repos")
//    fun getListRepos(
//        @Query("user") user : String,
//    ): Call<RawResponseData>
//
//    @GET("users/current/durations")
//    fun getCodingTime(
//        @Query("date") date : String,
//        @Query("api_key") string : String
//    ): Call<RawResponseData>
//
//
//    @POST("signup")
//    @FormUrlEncoded
//    fun signup(@Field("email") email : String,
//               @Field("plain_password") password : String,
//               @Field("name") name : String
//    ): Call<DataModel.SignUpResponse>
//
//
//    @PUT("user/basic")
//    fun uploadBasicInfo(@Query("email") email : String,
//                        @Query("age") age : Int,
//                        @Query("gender") gender : Int,
//                        @Query("one_line") one_line : String?
//    ): Call<DataModel.PutResponse>

}
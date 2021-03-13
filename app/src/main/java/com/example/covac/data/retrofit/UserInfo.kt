package com.example.covac.data.retrofit

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-13
 */
data class UserInfo (
        val isSuccess: Boolean,
        val code: Int,
        val message: String,
        val result: UserInfoResultDataClass
)

data class UserInfoResultDataClass(
        val userId: Int,
        val nickname: String,
        val vaccine: String,
        val location: String,
        val age: String,
        val date: String,
        val profileIma: String
)

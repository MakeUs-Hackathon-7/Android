package com.example.covac.data.retrofit

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-13
 */

//M1
data class UserAuth (
        val isSuccess: Boolean,
        val code: Int,
        val message: String,
        val vaccine: String,
        val institution: String,
        val location: String,
        val times: Int,
        val fileImg: String,
        val firstDate: String,
        val secondDate: String,
        val birthday: String
)
//
//data class AuthInfo (
//
//)
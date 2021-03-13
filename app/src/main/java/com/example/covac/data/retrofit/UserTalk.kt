package com.example.covac.data.retrofit

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-13
 */

//M2
data class UserTalk (
        val isSuccess: Boolean,
        val code: Int,
        val message: String,
        val result: noticeInfo
)

data class noticeInfo (
        val isSuccess: Boolean,
        val code: Int,
        val message: String,
        val result: List<UserTalkDataClass>
)

data class UserTalkDataClass(
        val id: Int,
        val content: String,
        val time: String,
        val commentCount: Int,
        val profileImg: String
)

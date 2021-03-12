package com.example.covac.data

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-12
 */
data class NewDataClass(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: TokenDataClass
)

data class TokenDataClass(
    val token: String
)


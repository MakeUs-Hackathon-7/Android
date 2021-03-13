package com.example.covac.data

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-13
 */
data class Symptom (
    val nickname: String,
    val vac_name: String,
    val age: String,
    val region: String,
    val date: String,
    val content: String,
    val sym_1: Boolean,
    val sym_2: Boolean,
    val sym_3: Boolean,
    val sym_4: Boolean,
    val sym_5: Boolean
)
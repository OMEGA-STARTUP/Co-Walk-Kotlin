package com.example.co_walk_kotlin.Data


data class Reqregister(
    val email: String,
    val identifier: String,
    val jwt_token: JwtToken,
    val nickname: String,
    val password: String
)


data class ResregisterCode(
    val code: Int,
    val message: String,
    val method: String,
    val url: String
)

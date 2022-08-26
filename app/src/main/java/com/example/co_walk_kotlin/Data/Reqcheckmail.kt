package com.example.co_walk_kotlin.Data

data class Reqcheckmail(
    val email: String,
    val jwt_token: JwtToken,
    val user_access_code: String
)

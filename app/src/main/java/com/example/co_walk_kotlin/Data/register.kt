package com.example.co_walk_kotlin.Data

data class register(
    val email: String,
    val identifier: String,
    val jwt_token: JwtTokenEmail,
    val nickname: String,
    val password: String
)
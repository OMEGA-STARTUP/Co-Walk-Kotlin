package com.example.co_walk_kotlin.Data

data class registerREq(
    val email: String,
    val identifier: String,
    val jwt_token: JwtTokenEmail,
    val nickname: String,
    val password: String
)

data class Resregister(
    val
)
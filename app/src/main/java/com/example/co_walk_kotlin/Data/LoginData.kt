package com.example.co_walk_kotlin.Data

//DTO
data class UserReq(
    val identifier: String,
    val password: String
)

data class ResUser(
    val access_token: String,
    val code: Int,
    val message: String,
    val refresh_token: String
)
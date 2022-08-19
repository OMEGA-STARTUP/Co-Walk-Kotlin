package com.example.co_walk_kotlin.Data

data class JwtTokenEmail(
    val email: String,
    val is_verified: Boolean,
    val purpose_code: Int,
    val timeout: Int
)
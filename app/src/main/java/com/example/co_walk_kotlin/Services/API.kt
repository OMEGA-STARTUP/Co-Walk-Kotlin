package com.example.co_walk_kotlin.Services

import com.example.co_walk_kotlin.Data.JwtTokenEmail
<<<<<<< HEAD
import com.example.co_walk_kotlin.Data.ResUser
import com.example.co_walk_kotlin.Data.UserReq
import com.example.co_walk_kotlin.Data.register
import retrofit2.Call
import retrofit2.http.*
=======
import com.example.co_walk_kotlin.Data.UserReq
import com.example.co_walk_kotlin.Data.register
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
>>>>>>> c386acd0b77224aec8d4b4081b05455d1ed8343a

interface API {
    @POST("/user/login")
    @FormUrlEncoded
<<<<<<< HEAD
    //fun login(@Field("identifier") id:String, @Field("password") password:String) : Call<UserReq>
    fun login(@Body req: UserReq) : Call<ResUser>
=======
    fun login(@Field("identifier") id:String, @Field("password") password:String) : Call<UserReq>
>>>>>>> c386acd0b77224aec8d4b4081b05455d1ed8343a

    @POST("/user/register")
    @FormUrlEncoded
    fun register(@Field("identifier") id:String, @Field("password") password:String,
                 @Field("email") email:String, @Field("nickname") nickname:String,
                 @Field("jwt_token") jwtTokenEmail : JwtTokenEmail
    ) : Call<register>
<<<<<<< HEAD


=======
>>>>>>> c386acd0b77224aec8d4b4081b05455d1ed8343a
}
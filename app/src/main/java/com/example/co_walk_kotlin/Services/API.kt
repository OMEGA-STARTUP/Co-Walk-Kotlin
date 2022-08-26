package com.example.co_walk_kotlin.Services

import com.example.co_walk_kotlin.Data.*
import com.example.co_walk_kotlin.utils.Paths.EMAIL_URL
import com.example.co_walk_kotlin.utils.Paths.LOGIN_URL
import com.example.co_walk_kotlin.utils.Paths.REGISTER_URL
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface API {

    @POST(LOGIN_URL)
    //fun login(@Field("identifier") id:String, @Field("password") password:String) : Call<UserReqq>
    fun login(@Body req: UserReq) : Call<ResUser>

    @POST(REGISTER_URL)
   // @FormUrlEncoded
    fun register(//@Field("identifier") id: registerREq, @Field("password") password:String,
                 //@Field("email") email:String, @Field("nickname") nickname:String,
                 //@Field("jwt_token") jwtTokenEmail: JwtTokenEmail
    @Body reqregister: Reqregister
    ) : Call<Void>

    @POST(EMAIL_URL)
    fun mailsend(
        @Path("email") Email: String,
        @Body email: String
    ) : Call<JwtToken>
}
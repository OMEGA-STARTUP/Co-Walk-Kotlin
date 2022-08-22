package com.example.co_walk_kotlin.Services


import android.content.Context
import com.example.co_walk_kotlin.utils.Paths.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Client {
    var retrofitService: API
        //통신 상태 로그 작성
    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val logger = OkHttpClient.Builder().addInterceptor(interceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

            //토큰 인터셉터 생성

            //레트로핏 생성
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) //베이스 서버 주소
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofitService = retrofit.create(API::class.java)
        }
}


package com.example.co_walk_kotlin.Services

<<<<<<< HEAD
import android.content.Context
=======
>>>>>>> c386acd0b77224aec8d4b4081b05455d1ed8343a
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Client {
    lateinit var retrofitService: API
        //통신 상태 로그 작성
    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val logger = OkHttpClient.Builder().addInterceptor(interceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

<<<<<<< HEAD
            //토큰 인터셉터 생성


=======
>>>>>>> c386acd0b77224aec8d4b4081b05455d1ed8343a
            //레트로핏 생성
            val retrofit = Retrofit.Builder()
                .baseUrl("http://") //베이스 서버 주소
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(API::class.java)
<<<<<<< HEAD
    }


=======

    }
    //로그인
>>>>>>> c386acd0b77224aec8d4b4081b05455d1ed8343a
}
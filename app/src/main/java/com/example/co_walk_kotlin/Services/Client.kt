package com.example.co_walk_kotlin.Services


import android.content.Context
import com.example.co_walk_kotlin.utils.Paths.BASE_URL
import com.example.co_walk_kotlin.utils.SessionManager
import com.example.co_walk_kotlin.utils.SessionManager.Companion.USER_TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object Client {
    var retrofitService: API
   // lateinit var headerintercept: AppInterceptor
        //통신 상태 로그 작성
    init {
        /*val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val logger = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()*/

        fun okHttpClient(interceptor: AppInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
        }

            //레트로핏 생성
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) //베이스 서버 주소
                .client(okHttpClient(AppInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofitService = retrofit.create(API::class.java)

        }


    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("ACCESS_TOKEN",USER_TOKEN)
                .build()
            proceed(newRequest)
        }
    }
}


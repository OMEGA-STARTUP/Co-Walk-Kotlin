package com.example.co_walk_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.co_walk_kotlin.Data.ResUser
import com.example.co_walk_kotlin.Data.UserReq
import com.example.co_walk_kotlin.Services.Client
import com.example.co_walk_kotlin.databinding.ActivityMainBinding
import com.example.co_walk_kotlin.utils.SessionManager
import retrofit2.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var sessionManger: SessionManager

    var autologcheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //-----------버튼들 처리---------

        //아이디 비밀번호 찾기 ui없음 미구현 -> 아이디비번 찾기 액티비티. Register액티비티 부분에 넣어줘야함
        binding.btnFindIdPw.setOnClickListener {
            Intent(this, RegisterActivity::class.java).run {
                startActivity(this)
            }
        }

        with(binding) {
            btnLogin.setOnClickListener {

                Client.retrofitService.login(
                    UserReq(
                        identifier = textId.text.toString(),
                        password = textPw.text.toString()
                    )
                ).enqueue(object : Callback<ResUser> {
                    override fun onResponse(call: Call<ResUser>, response: Response<ResUser>) {

                        val loginResponse = response.body()

                        if (loginResponse?.code == 200) {
                            sessionManger.saveAccessAuthToken(loginResponse.access_token)
                            sessionManger.saveRefreshToken(loginResponse.refresh_token)
                        } else {
                            Toast.makeText(this@MainActivity, "로그인 실패" , Toast.LENGTH_SHORT).show()
                            Log.d("response : ",response?.body().toString())
                        }
                    }

                    override fun onFailure(call: Call<ResUser>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                        Log.d("response : ",t?.toString())
                    }
                })
            }
            //로그인에서 회원가입 창으로
            binding.btngoRegister.setOnClickListener() {
                // Intent(this,RegisterActivity::class.java).run {
                //   startActivity(this)}
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            }
        }
    }


    //자동로그인 구현 삭제
    /*fun autoLoginRadio (view: View) {
        with(binding) {
            autologcheck = radioAutoLog.isChecked()
            if(autologcheck) {
                Toast.makeText(applicationContext,"자동로그인 설정!",Toast.LENGTH_SHORT).show()
                Log.d("login", "${autologcheck}")
            } else {
                Toast.makeText(applicationContext,"자동로그인 취소!",Toast.LENGTH_SHORT).show()
                Log.d("login", "${autologcheck}")
            }
        }
    }*/
}



package com.example.co_walk_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.co_walk_kotlin.Data.JwtTokenEmail
import com.example.co_walk_kotlin.Data.register
import com.example.co_walk_kotlin.Services.Client
import com.example.co_walk_kotlin.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //메일 인증하기
        binding.editemail.setOnClickListener {

        }



        //임시 토큰값 설정
        lateinit var jwt :JwtTokenEmail
        //비밀번호 같은지 학인 후 회원가입 처리
        with(binding){
            btnRegister.setOnClickListener {
                if(editpwr == editpwr2) {
                    Client.retrofitService.register(
                        textidr.text.toString(),
                        editpwr2.text.toString(), editemail.text.toString(),
                        editnickr.text.toString(), jwt
                    ).enqueue(object : Callback<register> {
                        override fun onResponse(
                            call: Call<register>,
                            response: Response<register>
                        ) {
                            if (response.code() == 200) {
                                Toast.makeText(this@RegisterActivity, "회원가입 성공", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                Toast.makeText(this@RegisterActivity, "회원가입 실패", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<register>, t: Throwable) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "회원가입 실패 : " + t.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })//마지막 jwt 토큰 값
                }
            }
        }
    }
}
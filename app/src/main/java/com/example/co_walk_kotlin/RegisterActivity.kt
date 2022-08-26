package com.example.co_walk_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.co_walk_kotlin.Data.*
import com.example.co_walk_kotlin.Services.Client
import com.example.co_walk_kotlin.databinding.ActivityRegisterBinding
import com.example.co_walk_kotlin.utils.EmaliSessionManager
import com.example.co_walk_kotlin.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.HTTP

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var emailsessionManger: EmaliSessionManager
    lateinit var mailchecktoken : JwtToken // 메일 인증 시  Req jwt 토큰값 저장
    private var mailsendFirst = false //메일 인증 먼저 하고 회원가입 할수 있도록

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //메일 인증하기
        binding.btnMailSend.setOnClickListener {
            with(binding) {
                Client.retrofitService.mailsend(
                    Email = editemail.text.toString(),
                    email = editemail.text.toString()
                ).enqueue(object: Callback<JwtToken>{  //메일인증

                    override fun onResponse(call: Call<JwtToken>, response: Response<JwtToken>) {
                        val mailcheckresponse = response.body()
                        if(response.isSuccessful()){
                            emailsessionManger.saveMailToken(mailcheckresponse!!.access_code)
                            mailsendFirst = true
                            mailchecktoken = response.body()!! // 메일 인증시 Req token
                        }else{
                            onFailure(call,t = Throwable())
                        }
                    }
                    override fun onFailure(call: Call<JwtToken>, t: Throwable) {
                        var dialog = AlertDialog.Builder(this@RegisterActivity)
                        dialog.setTitle("에러")
                        dialog.setMessage("메일 전송에 실패했습니다.")
                    }
                })

            }
        }

        //비밀번호 같은지 학인 후 회원가입 처리
        with(binding) {
            btnRegister.setOnClickListener {
                if (mailsendFirst) {
                    if (editpwr == editpwr2) {
                        Client.retrofitService.register(
                            Reqregister(
                                email = editemail.text.toString(),
                                identifier = textidr.text.toString(),
                                password = editpwr.text.toString(),
                                nickname = editnickr.text.toString(),
                                jwt_token = mailchecktoken
                            )
                        ).enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                                if (response.code() == 200) {
                                    Toast.makeText(
                                        this@RegisterActivity, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                                } else {
                                    onFailure(call,t = Throwable())
                                }

                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Toast.makeText(this@RegisterActivity, "회원가입 실패 : ", Toast.LENGTH_SHORT).show()
                            }
                        })//마지막 jwt 토큰 값
                    } else { //비밀번호 다를시
                        Toast.makeText(this@RegisterActivity,
                            "비밀번호가 일치하지 않습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else { //메일인증 하지 않고 인증번호 확인시
                    Toast.makeText(this@RegisterActivity, "메일인증을 하지 않았습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

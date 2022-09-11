package com.example.co_walk_kotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.co_walk_kotlin.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "access_token"
        const val Refresh_Token = "refresh_token "
    }

    fun saveAccessAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getAccessAuthToken(token: String): String {
        return USER_TOKEN
    }

    fun saveRefreshToken (token: String) {
        val editor = prefs.edit()
        editor.putString(Refresh_Token, token)
        editor.apply()
    }

}
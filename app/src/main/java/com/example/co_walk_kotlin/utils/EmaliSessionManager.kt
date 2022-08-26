package com.example.co_walk_kotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.co_walk_kotlin.R

class EmaliSessionManager (context: Context) {
        private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

        companion object {
            const val Mail_Token = "mail_token"
        }

        fun saveMailToken(token: String) {
            val editor = prefs.edit()
            editor.putString(Mail_Token, token)
            editor.apply()
        }
}
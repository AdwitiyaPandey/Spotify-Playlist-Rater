package com.example.adwi.repository

import android.content.Context
import com.example.adwi.model.UserModel

class UserRepoImpl: UserRepo {
    override fun login(
        context: Context,
        user: UserModel
    ): Boolean {
        if (user.email.isBlank() || user.password.isBlank()) {
            return false
        }
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("loggedIn", true).apply()
        return true
    }

    override fun logout(context: Context) {
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    override fun isLoggedIn(context: Context): Boolean {
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        return prefs.getBoolean("loggedIn", false)
    }
}

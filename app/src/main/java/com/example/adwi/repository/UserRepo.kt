package com.example.adwi.repository

import android.content.Context
import com.example.adwi.model.UserModel

interface UserRepo {
    fun login(context: Context, user: UserModel): Boolean
    fun logout(context: Context)
    fun isLoggedIn(context: Context): Boolean
}

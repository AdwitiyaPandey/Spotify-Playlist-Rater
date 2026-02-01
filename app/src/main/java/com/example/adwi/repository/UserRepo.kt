package com.example.adwi.repository

import com.example.adwi.model.UserModel

interface UserRepo {
    fun register(user: UserModel, onResult: (Boolean, String?) -> Unit)
    fun login(user: UserModel, onResult: (Boolean, String?) -> Unit)
    fun logout()
    fun isLoggedIn(): Boolean
    fun getCurrentUserEmail(): String?
}

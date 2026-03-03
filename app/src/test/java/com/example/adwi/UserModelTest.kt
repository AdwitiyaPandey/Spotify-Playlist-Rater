package com.example.adwi

import com.example.adwi.model.UserModel
import org.junit.Assert.assertEquals
import org.junit.Test

class UserModelTest {
    @Test
    fun userModel_initialization_isCorrect() {
        val user = UserModel(email = "test@example.com", password = "password123", name = "Test User")
        assertEquals("test@example.com", user.email)
        assertEquals("password123", user.password)
        assertEquals("Test User", user.name)
    }

    @Test
    fun userModel_copy_isCorrect() {
        val user = UserModel(email = "test@example.com", password = "password123")
        val copiedUser = user.copy(userId = "12345")
        assertEquals("test@example.com", copiedUser.email)
        assertEquals("12345", copiedUser.userId)
    }
}

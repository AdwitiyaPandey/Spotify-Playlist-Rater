package com.example.adwi

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysWelcomeMessage() {
        composeTestRule.setContent {
            HomeScreen(userEmail = "test@example.com")
        }

        composeTestRule.onNodeWithText("Welcome, test@example.com").assertExists()
    }

    @Test
    fun homeScreen_textField_acceptsInput() {
        composeTestRule.setContent {
            HomeScreen(userEmail = "test@example.com")
        }

        val testUrl = "https://spotify.com/playlist/123"
        composeTestRule.onNodeWithText("Playlist URL").performTextInput(testUrl)
        composeTestRule.onNodeWithText(testUrl).assertExists()
    }
}

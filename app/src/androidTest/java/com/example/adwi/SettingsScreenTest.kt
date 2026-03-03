package com.example.adwi

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun settingsScreen_displaysEmail() {
        val testEmail = "user@test.com"
        composeTestRule.setContent {
            SettingsScreen(userEmail = testEmail, onBack = {})
        }

        composeTestRule.onNodeWithText(testEmail).assertExists()
    }

    @Test
    fun settingsScreen_backButton_triggersCallback() {
        var backClicked = false
        composeTestRule.setContent {
            SettingsScreen(userEmail = "test@test.com", onBack = { backClicked = true })
        }

        composeTestRule.onNodeWithContentDescription("Back").performClick()
        assertTrue(backClicked)
    }
}

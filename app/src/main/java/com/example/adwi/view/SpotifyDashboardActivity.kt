package com.example.adwi.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adwi.repository.UserRepoImpl

class SpotifyDashboardActivity : ComponentActivity() {
    private val userRepo = UserRepoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!userRepo.isLoggedIn()) {
            startActivity(Intent(this, SpotifyLogin::class.java))
            finish()
            return
        }

        setContent {
            MaterialTheme {
                DashboardScreen(
                    userEmail = userRepo.getCurrentUserEmail() ?: "User",
                    onLogout = {
                        userRepo.logout()
                        startActivity(Intent(this, SpotifyLogin::class.java))
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun DashboardScreen(userEmail: String, onLogout: () -> Unit) {
    var selectedIndex by remember { mutableStateOf(0) }

    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Search", Icons.Default.Search),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.Black) {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF1DB954),
                            selectedTextColor = Color(0xFF1DB954),
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.DarkGray
                        )
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome, $userEmail",
                color = Color.White,
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "${bottomNavItems[selectedIndex].label} Content Goes Here",
                color = Color.Gray
            )

            if (selectedIndex == 2) { // Profile tab
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = onLogout,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Logout", color = Color.White)
                }
            }
        }
    }
}

data class BottomNavItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    MaterialTheme {
        DashboardScreen("test@example.com", {})
    }
}

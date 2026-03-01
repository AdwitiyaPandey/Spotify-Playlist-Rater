package com.example.adwi.view
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("loggedIn", false)

        if (!isLoggedIn) {
            finish()
            return
        }

        setContent {
            MaterialTheme {
                DashboardScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {

    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Search", Icons.Default.Search),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.Black) {
                items.forEachIndexed { index, item ->
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


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black)
        ) {
            // Show Top LazyRow only on Home tab for cleaner look
            if (selectedIndex == 0) {
                Spacer(modifier = Modifier.height(64.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(topRowItems) { item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.width(100.dp)
                        ) {
                            Image(
                                painter = painterResource(id = item.imageRes),
                                contentDescription = item.title,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.title,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1
                            )
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(32.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                when (selectedIndex) {
                    0 -> HomeScreen(userEmail = userEmail)
                    1 -> SearchScreen()
                    2 -> ProfileScreen(userEmail = userEmail, onLogout = onLogout)
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
        DashboardScreen()
    }
}

package com.example.adwi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adwi.ui.theme.AdwiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(userEmail: String, onBack: () -> Unit) {
    var isPrivateAccount by remember { mutableStateOf(false) }
    var isDataSaver by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings and Privacy", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SettingsSectionTitle("Account")
            SettingsTextItem("Email", userEmail)
            SettingsToggleItem(
                "Private Account",
                "Only approved people can see your activity and playlists",
                isPrivateAccount
            ) { isPrivateAccount = it }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White.copy(alpha = 0.1f))

            SettingsSectionTitle("Data Saver")
            SettingsToggleItem(
                "Data Saver",
                "Sets audio quality to low and hides images/videos on cellular",
                isDataSaver
            ) { isDataSaver = it }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun SettingsToggleItem(title: String, subtitle: String?, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, color = Color.White, fontSize = 16.sp)
            if (subtitle != null) {
                Text(text = subtitle, color = Color.Gray, fontSize = 12.sp, lineHeight = 16.sp)
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF1DB954),
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color.DarkGray,
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SettingsTextItem(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(text = title, color = Color.White, fontSize = 16.sp)
        Text(text = subtitle, color = Color.Gray, fontSize = 14.sp)
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    AdwiTheme {
        SettingsScreen(userEmail = "user@example.com", onBack = {})
    }
}

package com.example.adwi

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun HomeScreen(userEmail: String) {
    var playlistUrl by remember { mutableStateOf("") }
    var ratingResults by remember { mutableStateOf<List<GenreScore>?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (ratingResults == null) Arrangement.Center else Arrangement.Top
    ) {
        if (ratingResults == null) {
            Text(
                text = "Welcome, $userEmail",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = playlistUrl,
                onValueChange = { playlistUrl = it },
                label = { Text("Playlist URL", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color(0xFF1DB954),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color(0xFF1DB954)
                ),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (playlistUrl.isNotBlank()) {
                        isLoading = true
                        // Simulate analysis delay
                        // In a real app, this would be an API call
                        val mockData = listOf(
                            GenreScore("Hip-Hop", 0.85f),
                            GenreScore("Boom Bap", 0.72f),
                            GenreScore("R&B", 0.60f),
                            GenreScore("Pop", 0.45f),
                            GenreScore("Electronic", 0.38f),
                            GenreScore("Jazz", 0.25f),
                            GenreScore("House", 0.15f)
                        ).shuffled().take(5).sortedByDescending { it.score }
                        
                        ratingResults = mockData
                        isLoading = false
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 8.dp),
                enabled = !isLoading,
                shape = RoundedCornerShape(28.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.Black)
                } else {
                    Text("Rate Playlist", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        } else {
            Text(
                text = "Your Music Taste",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
            )
            
            Text(
                text = "Based on your playlist analysis",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            GenreBarGraph(results = ratingResults!!)

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = { ratingResults = null },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Analyze Another", color = Color.White, fontWeight = FontWeight.SemiBold)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun GenreBarGraph(results: List<GenreScore>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        results.forEach { item ->
            GenreBar(genre = item.genre, score = item.score)
        }
    }
}

@Composable
fun GenreBar(genre: String, score: Float) {
    val animatedProgress by animateFloatAsState(
        targetValue = score,
        animationSpec = tween(durationMillis = 1200),
        label = "BarAnimation"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = genre, 
                color = Color.White, 
                fontSize = 16.sp, 
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "${(score * 100).toInt()}%", 
                color = Color(0xFF1DB954), 
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(14.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(Color.White.copy(alpha = 0.1f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress)
                    .fillMaxHeight()
                    .background(Color(0xFF1DB954))
            )
        }
    }
}

data class GenreScore(val genre: String, val score: Float)

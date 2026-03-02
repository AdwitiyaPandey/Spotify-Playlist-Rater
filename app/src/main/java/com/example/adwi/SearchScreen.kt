package com.example.adwi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Song(val title: String, val artist: String, val imageUrl: String = "")

data class Album(val title: String, val artist: String, val imageRes: Int)

@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    val categories = listOf(
        "Pop" to Color(0xFFE13300),
        "Hip-Hop" to Color(0xFFBC59FF),
        "Rock" to Color(0xFFE91429),
        "Latin" to Color(0xFFE13300),
        "Dance/Electronic" to Color(0xFFD84000),
        "Indie" to Color(0xFFE91429),
        "Chill" to Color(0xFF777777),
        "Workout" to Color(0xFF1DB954)
    )

    val songs = listOf(
        Song("Starboy", "The Weeknd"),
        Song("Blinding Lights", "The Weeknd"),
        Song("Circles", "Post Malone"),
        Song("Stay", "The Kid LAROI"),
        Song("Heat Waves", "Glass Animals")
    )

    // Sample albums data
    val genreAlbums = mapOf(
        "Pop" to List(10) { Album("Pop Album ${it + 1}", "Pop Artist ${it + 1}", R.drawable.spotify) },
        "Hip-Hop" to List(10) { Album("Hip-Hop Album ${it + 1}", "Hip-Hop Artist ${it + 1}", R.drawable.kanye) },
        "Rock" to List(10) { Album("Rock Album ${it + 1}", "Rock Artist ${it + 1}", R.drawable.spotify) },
        "Latin" to List(10) { Album("Latin Album ${it + 1}", "Latin Artist ${it + 1}", R.drawable.kanye) },
        "Dance/Electronic" to List(10) { Album("Dance Album ${it + 1}", "Dance Artist ${it + 1}", R.drawable.spotify) },
        "Indie" to List(10) { Album("Indie Album ${it + 1}", "Indie Artist ${it + 1}", R.drawable.kanye) },
        "Chill" to List(10) { Album("Chill Album ${it + 1}", "Chill Artist ${it + 1}", R.drawable.spotify) },
        "Workout" to List(10) { Album("Workout Album ${it + 1}", "Workout Artist ${it + 1}", R.drawable.kanye) }
    )

    val filteredSongs = if (searchQuery.isEmpty()) {
        emptyList()
    } else {
        songs.filter { it.title.contains(searchQuery, ignoreCase = true) || it.artist.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (selectedCategory == null) {
            Text(
                text = "Search",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                placeholder = { Text("What do you want to listen to?", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )

            if (searchQuery.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(filteredSongs) { song ->
                        SongItem(song)
                    }
                }
            } else {
                Text(
                    text = "Browse all",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(categories) { category ->
                        CategoryCard(
                            title = category.first,
                            color = category.second,
                            onClick = { selectedCategory = category.first }
                        )
                    }
                }
            }
        } else {
            // Genre Details View
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { selectedCategory = null }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = selectedCategory!!,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Latest Albums",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            val albums = genreAlbums[selectedCategory] ?: emptyList()
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(albums) { album ->
                    AlbumCard(album)
                }
            }
        }
    }
}

@Composable
fun AlbumCard(album: Album) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Image(
            painter = painterResource(id = album.imageRes),
            contentDescription = album.title,
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = album.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 1
        )
        Text(
            text = album.artist,
            color = Color.Gray,
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}

@Composable
fun SongItem(song: Song) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.DarkGray)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = song.title, color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = song.artist, color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun CategoryCard(title: String, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
                .padding(12.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
        }
    }
}

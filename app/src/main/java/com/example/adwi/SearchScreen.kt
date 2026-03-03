package com.example.adwi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
        "House" to Color(0xFFE13300),
        "Electronic" to Color(0xFFD84000),
        "Jazz" to Color(0xFFE91429),
        "R&B" to Color(0xFF2D40BD),
        "Boom Bap" to Color(0xFFA43D1F)
    )

    val songs = listOf(
        Song("Starboy", "The Weeknd"),
        Song("Blinding Lights", "The Weeknd"),
        Song("Circles", "Post Malone"),
        Song("Stay", "The Kid LAROI"),
        Song("Heat Waves", "Glass Animals")
    )


    val popImages = listOf(
        R.drawable.pop1, R.drawable.pop2, R.drawable.pop3, R.drawable.pop4, R.drawable.pop5,
        R.drawable.pop6, R.drawable.pop7, R.drawable.pop8, R.drawable.pop9, R.drawable.pop10
    )

    val hiphopImages = listOf(
        R.drawable.hiphop1, R.drawable.hiphop2, R.drawable.hiphop3, R.drawable.hiphop4, R.drawable.hiphop5jpg,
        R.drawable.hiphop6, R.drawable.hiphop7, R.drawable.hiphop8, R.drawable.hiphop9, R.drawable.hiphop10
    )

    val rockImages = listOf(
        R.drawable.rock1, R.drawable.rock2, R.drawable.rock3, R.drawable.rock4, R.drawable.rock5,
        R.drawable.rock6, R.drawable.rock7, R.drawable.rock8, R.drawable.rock9, R.drawable.rock10
    )

    val houseImages = listOf(
        R.drawable.house1, R.drawable.house2, R.drawable.house3, R.drawable.house4, R.drawable.house5,
        R.drawable.house6, R.drawable.house7, R.drawable.house8, R.drawable.house9, R.drawable.house10
    )

    val electronicImages = listOf(
        R.drawable.electronic1, R.drawable.electronic2, R.drawable.electronic3, R.drawable.electronic4, R.drawable.electronic5,
        R.drawable.electronic6, R.drawable.electronic7, R.drawable.electronic8, R.drawable.electronic9, R.drawable.electronic10

    )

    val jazzImages = listOf(
        R.drawable.jazz1, R.drawable.jazz2, R.drawable.jazz3, R.drawable.jazz4, R.drawable.jazz5,
        R.drawable.jazz6, R.drawable.jazz7, R.drawable.jazz8, R.drawable.jazz9, R.drawable.jazz10
    )

    val rnbImages = listOf(
        R.drawable.rnb1, R.drawable.rnb2, R.drawable.rnb3, R.drawable.rnb4, R.drawable.rnb5,
        R.drawable.rnb6, R.drawable.rnb7, R.drawable.rnb8, R.drawable.rnb9, R.drawable.rnb10
    )

    val genreAlbums = mapOf(
        "Pop" to popImages.mapIndexed { index, resId ->
            Album("Pop Hits ${index + 1}", "Pop Artist ${index + 1}", resId)
        },
        "Hip-Hop" to hiphopImages.mapIndexed { index, resId ->
            Album("Hip-Hop Hits ${index + 1}", "Hip-Hop Artist ${index + 1}", resId)
        },
        "Rock" to rockImages.mapIndexed { index, resId ->
            Album("Rock Hits ${index + 1}", "Rock Artist ${index + 1}", resId)
        },
        "House" to houseImages.mapIndexed { index, resId ->
            Album("House Hits ${index + 1}", "House Artist ${index + 1}", resId)
        },
        "Electronic" to electronicImages.mapIndexed { index, resId ->
            Album("Electronic Hits ${index + 1}", "Electronic Artist ${index + 1}", resId)
        },

        "Jazz" to jazzImages.mapIndexed { index, resId ->
            Album("Jazz Hits ${index + 1}", "Jazz Artist ${index + 1}", resId)
        },

        "R&B" to rnbImages.mapIndexed { index, resId ->
            Album("R&B Hits ${index + 1}", "R&B Artist ${index + 1}", resId)
        },



        "Rock" to List(10) { Album("Rock Album ${it + 1}", "Rock Artist ${it + 1}", R.drawable.spotify) },
        "House" to List(10) { Album("House Album ${it + 1}", "House Artist ${it + 1}", R.drawable.kanye) },
        "Electronic" to List(10) { Album("Electronic Album ${it + 1}", "Electronic Artist ${it + 1}", R.drawable.spotify) },
        "Indie" to List(10) { Album("Indie Album ${it + 1}", "Indie Artist ${it + 1}", R.drawable.kanye) },
        "R&B" to List(10) { Album("R&B Album ${it + 1}", "R&B Artist ${it + 1}", R.drawable.spotify) },
        "Jazz" to List(10) { Album("Jazz Album ${it + 1}", " Artist ${it + 1}", R.drawable.kanye) }
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
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
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

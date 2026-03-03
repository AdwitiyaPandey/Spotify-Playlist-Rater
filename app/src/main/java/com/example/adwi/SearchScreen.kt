package com.example.adwi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen() {
    var selectedCategory by remember { mutableStateOf<CategoryData?>(null) }

    if (selectedCategory == null) {
        SearchMainScreen(onCategoryClick = { selectedCategory = it })
    } else {
        GenreDetailScreen(category = selectedCategory!!, onBack = { selectedCategory = null })
    }
}

@Composable
fun SearchMainScreen(onCategoryClick: (CategoryData) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    
    val categories = listOf(
        CategoryData("Pop", Color(0xFF8D67AB), R.drawable.rock1, getImagesForPop()),
        CategoryData("Hip-Hop", Color(0xFFBA5D07), R.drawable.hiphop1, getImagesForHipHop()),
        CategoryData("Jazz", Color(0xFFE8115B), R.drawable.jazz1, getImagesForJazz()),
        CategoryData("R&B", Color(0xFFE1118C), R.drawable.rnb1, getImagesForRnB()),
        CategoryData("House", Color(0xFFD84000), R.drawable.house1, getImagesForHouse()),
        CategoryData("Boom Bap", Color(0xFF608108), R.drawable.boombap1, getImagesForBoomBap()),
        CategoryData("Electronic", Color(0xFFDC148C), R.drawable.electronic1, getImagesForElectronic())
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Search",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("What do you want to listen to?", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Browse all",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(categories) { category ->
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(category.color)
                        .clickable { onCategoryClick(category) }
                ) {
                    Text(
                        text = "Top Albums of\n${category.name}",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.TopStart)
                    )
                    Image(
                        painter = painterResource(id = category.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = 12.dp, y = 12.dp)
                            .rotate(25f),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDetailScreen(category: CategoryData, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category.name, color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
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
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Top Albums of ${category.name}",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(category.allImages) { imageRes ->
                    Card(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                    ) {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

data class CategoryData(val name: String, val color: Color, val imageRes: Int, val allImages: List<Int>)

// Helper functions to get lists of images
fun getImagesForPop() = listOf(
    R.drawable.rock1, R.drawable.rock2jpg, R.drawable.rock3, R.drawable.rock4, R.drawable.rock5,
    R.drawable.rock6, R.drawable.rock7jpg, R.drawable.rock8, R.drawable.rock9, R.drawable.rock10
)

fun getImagesForHipHop() = listOf(
    R.drawable.hiphop1, R.drawable.hiphop2, R.drawable.hiphop3, R.drawable.hiphop4, R.drawable.hiphop5jpg,
    R.drawable.hiphop6, R.drawable.hiphop7, R.drawable.hiphop8, R.drawable.hiphop9, R.drawable.hiphop10
)

fun getImagesForJazz() = listOf(
    R.drawable.jazz1, R.drawable.jazz2, R.drawable.jazz3, R.drawable.jazz4, R.drawable.jazz5,
    R.drawable.jazz6, R.drawable.jazz7, R.drawable.jazz8, R.drawable.jazz9, R.drawable.jazz10
)

fun getImagesForRnB() = listOf(
    R.drawable.rnb1, R.drawable.rnb2, R.drawable.rnb3, R.drawable.rnb4jpg, R.drawable.rnb5,
    R.drawable.rnb6, R.drawable.rnb7, R.drawable.rnb8, R.drawable.rnb9, R.drawable.rnb10
)

fun getImagesForHouse() = listOf(
    R.drawable.house1, R.drawable.house2, R.drawable.house3, R.drawable.house4jpg, R.drawable.house5,
    R.drawable.house6, R.drawable.house7, R.drawable.house8, R.drawable.house9, R.drawable.house10
)

fun getImagesForBoomBap() = listOf(
    R.drawable.boombap1, R.drawable.boombap2, R.drawable.boombap3jpg, R.drawable.boombap4, R.drawable.boombap5,
    R.drawable.boombap6, R.drawable.boombap7, R.drawable.boombap8, R.drawable.boombap9, R.drawable.boombap10
)

fun getImagesForElectronic() = listOf(
    R.drawable.electronic1, R.drawable.electronic2, R.drawable.electronic3, R.drawable.electronic4, R.drawable.electronic5,
    R.drawable.electronic6, R.drawable.electronic7, R.drawable.electronic8, R.drawable.electronic9, R.drawable.electronic10
)

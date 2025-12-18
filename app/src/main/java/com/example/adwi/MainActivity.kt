package com.example.adwi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adwi.ui.theme.AdwiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
         Mainbody()
        }
    }
}

@Composable //used to make ui
fun Mainbody() {
    //Row {
    //Text(text = "Hello")
    //Text(text = "World")
//}
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
            .height(height = 400.dp)
            .background(color =
                colorResource(id = R.color.Dung)
            )


    ) {
        Text(text="Hello",
            style = TextStyle (
            fontSize = 50.sp,
                textDecoration = TextDecoration.Underline,
                color = Color.DarkGray,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Justify,

        ))
        Image (
            painter = painterResource(id = R.drawable.overwatchhearthstoneactivisionblizzardblizzardentertainmentbattlenetblizzard),
            contentDescription = null,
            modifier = Modifier.size(size = 150.dp),

        )
        Text(text="World")

    }
}

@Preview
@Composable
fun MainPreview() {
    Mainbody()
}

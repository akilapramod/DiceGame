package dev.akila.dicegame.ui.components

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.akila.dicegame.ui.theme.DiceGameTheme
import dev.akila.dicegame.ui.theme.happyMonkeyFont
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import dev.akila.dicegame.R
import dev.akila.dicegame.ui.theme.AppColors.buttonColor

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color(0xFF81AFD4))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    DiceGameTheme {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF81AFD4),
                        Color(0xFF34769F)
                    )
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.blue_dice_bokeh_wallapper), // Replace with your image resource
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PrimaryTitleBox(
                modifier = Modifier
                    .padding(bottom = 300.dp)
                    .height(50.dp),
                text = " Dice Game "
            )

            PrimaryButton(modifier = Modifier
                .padding(bottom = 16.dp),
                text = "New Game",
                onClick = {
                    Log.d("Main Activity", "New Game started.")
                })
            PrimaryButton(modifier = Modifier
                .padding(bottom = 16.dp),
                text = "About",
                onClick = {
                    Log.d("Main Activity", "About button pressed.")
                })
        }
    }}

}

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .border(
                border = BorderStroke(6.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp)),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.Black,
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = happyMonkeyFont,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        )
    }
}

@Composable
fun PrimaryTitleBox(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier
            .border(border = BorderStroke(4.dp, Color.Black), shape = RoundedCornerShape(8.dp))
            .background(buttonColor)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            style = TextStyle(
                fontFamily = happyMonkeyFont,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        )
    }
}

//this same
@Composable
fun ContentBox(modifier: Modifier = Modifier, text: String){
    Box(
        modifier = modifier
            .border(border = BorderStroke(4.dp, Color.Black), shape = RoundedCornerShape(8.dp))
            .background(buttonColor)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            style = TextStyle(
                fontFamily = happyMonkeyFont,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        )

    }
}

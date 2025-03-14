package dev.akila.dicegame.ui.components

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.font.FontWeight

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


        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GameTitile(
                modifier = Modifier
                    .padding(bottom = 300.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp)),
            )

            NewGameButton(
                modifier = Modifier.padding(bottom = 16.dp)
            )
            AboutButton(
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }

}

@Composable
fun GameTitile(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(250.dp)
            .border(border = BorderStroke(2.dp, Color.Black))
            .background(Color(0xFF81AFD4))
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = "Dice Game",
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
fun NewGameButton(modifier: Modifier = Modifier) {
    Button(
        modifier = modifier
            .width(250.dp),

        onClick = {
            Log.d("Main Activity", "New Game started.")

        }
    ) {
        Text(
            text = "New Game",
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
fun AboutButton(modifier: Modifier = Modifier) {
    Button(
        modifier = modifier.width(250.dp),
        onClick = {
            Log.d("Main Activity", "About button pressed.")
        }
    ) {
        Text(
            text = "About",
            style = TextStyle(
                fontFamily = happyMonkeyFont,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
        ))
    }
}


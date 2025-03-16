package dev.akila.dicegame.ui.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.akila.dicegame.R
import dev.akila.dicegame.ui.theme.DiceGameTheme
import dev.akila.dicegame.ui.theme.happyMonkeyFont
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


class GameScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF81AFD4),
                        Color(0xFF34769F)
                    )
                )
            )
    ) { //this row is responsible for the score display
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(8.dp)

            )
            {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.robot),
                        contentDescription = "robot Image",
                        modifier = Modifier.size(48.dp)

                    )
                    Text(
                        text = ": 0",
                        style = TextStyle(
                            fontFamily = happyMonkeyFont,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(8.dp)
            )

            {
                Row() {
                    Text(
                        text = ": 0",
                        style = TextStyle(
                            fontFamily = happyMonkeyFont,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                    )

                    Image(
                        painter = painterResource(id = R.drawable.player),
                        contentDescription = "player Image",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }

        //this row is responsible for Win Loose ratio and total score

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "H:10/C:5",
                style = TextStyle(
                    fontFamily = happyMonkeyFont,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            )
            Text(
                text = "Total Score : ",
                style = TextStyle(
                    fontFamily = happyMonkeyFont,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

        }

        //this box is responsible to show the dices and player name "Computer"

        Box() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .padding(6.dp), // Or other modifiers
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Computer",
                    style = TextStyle(
                        fontFamily = happyMonkeyFont,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                )
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(
                        painter = painterResource(id = R.drawable.dice1),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice2),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice3),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice4),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice5),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                }

            }
        }

        //this box is responsible to show the dices and name for human player

        Box() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .padding(6.dp), // Or other modifiers
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(
                        painter = painterResource(id = R.drawable.dice1),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice2),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice3),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice4),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice5),
                        contentDescription = "Image 1",
                        modifier = Modifier.size(50.dp)
                    )
                }

                Text(
                    text = "Player",
                    style = TextStyle(
                        fontFamily = happyMonkeyFont,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Throw",
                    style = TextStyle(
                        fontFamily = happyMonkeyFont,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )

                )

                Text(
                    text = "Score: 12 ",
                    style = TextStyle(
                        fontFamily = happyMonkeyFont,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )

                )
            }
    }
}





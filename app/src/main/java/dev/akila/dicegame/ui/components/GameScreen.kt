package dev.akila.dicegame.ui.components

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.akila.dicegame.R
import dev.akila.dicegame.ui.theme.DiceGameTheme
import dev.akila.dicegame.ui.theme.happyMonkeyFont
import dev.akila.dicegame.ui.theme.AppColors.primaryColor


class GameScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DiceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Image(
                        painter = painterResource(id = R.drawable.blue_dice_bokeh_wallapper),
                        contentDescription = "Background Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    GameScreen(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    BaseScreenLayout {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround

    ) {
        //this row is responsible for the score display

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .background(primaryColor)
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
                    .background(primaryColor)
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
            ContentBox(modifier,"H:10/C:5")
            ContentBox(modifier,"Total Score : ")


        }

        //this box is responsible to show the dices and player name "Computer"

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(primaryColor)
                    .border(border = BorderStroke(4.dp, Color.Black),
                        shape = RoundedCornerShape(8.dp))
                , // Or other modifiers
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
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,


                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice2),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice3),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice4),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice5),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,
                    )
                }

            }


        //this box is responsible to show the dices and name for human player


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(primaryColor)
                    .border(border = BorderStroke(4.dp, Color.Black),
                    shape = RoundedCornerShape(8.dp))
                    , // Or other modifiers
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(
                        painter = painterResource(id = R.drawable.dice1),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice2),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice3),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice4),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dice5),
                        contentDescription = "Image 1",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                        ,
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



        // this row is to display the score botton and the throw botton.
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

            PrimaryButton(modifier = Modifier
                .padding(bottom = 16.dp),
                text = "Score",
                onClick = {
                    Log.d("Game Activity", "Score button pressed.")

                })


            PrimaryButton(modifier = Modifier
                .padding(bottom = 16.dp),
                text = "Throw",
                onClick = {
                    Log.d("Game Activity", "Throw button pressed.")
                    rollPlayerDice()
                    rollComputerDice()
                })
        }
    }}
}

fun rollPlayerDice(): IntArray {
    val diceResults = IntArray(5)
    val random = java.util.Random()

    for (i in 0 until 5) {
        diceResults[i] = random.nextInt(6) + 1
    }
    Log.d("Game Activity","Player rolled: ${diceResults.joinToString(", ")}")
    return diceResults
}

fun rollComputerDice(): IntArray {
    val diceResults = IntArray(5)
    val random = java.util.Random()

    for (i in 0 until 5) {
        diceResults[i] = random.nextInt(6) + 1
    }
    Log.d("Game Activity","Computer rolled: ${diceResults.joinToString(", ")}")

    return diceResults
}

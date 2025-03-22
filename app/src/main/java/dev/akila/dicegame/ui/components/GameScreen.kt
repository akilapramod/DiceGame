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
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.saveable.rememberSaveable
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
import dev.akila.dicegame.Game
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
                    GameScreenContent(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenContent(modifier: Modifier = Modifier) {
    /*
    this creates a object of the game class and
     */

    val gameInstance = remember { Game() }
    var gameState by remember { mutableStateOf(0) }

    var playerScore by rememberSaveable { mutableStateOf(0) }
    var computerScore by rememberSaveable { mutableStateOf(0) }

    var playerWins by rememberSaveable { mutableStateOf(0) }
    var computerWins by rememberSaveable { mutableStateOf(0) }

    var playerDicevalues by rememberSaveable { mutableStateOf(IntArray(5)) }
    var computerDicevalues by rememberSaveable { mutableStateOf(IntArray(5)) }

    var hasThrown by rememberSaveable { mutableStateOf(false) }

    var rerollsLeft by rememberSaveable { mutableStateOf(2) }
    var selectedDice by remember { mutableStateOf(BooleanArray(5)) }


    val updateUI = {
        gameState++
        playerScore = gameInstance.getPlayerSCore()
        computerScore = gameInstance.getComputerScore()
        playerWins = gameInstance.getPlayerWins()
        computerWins = gameInstance.getComputerWins()
        playerDicevalues = gameInstance.getPlayerDiceResult()
        computerDicevalues = gameInstance.getComputerDiceResult()
        selectedDice = gameInstance.getSelectedDice()
        rerollsLeft = gameInstance.getRemainingRerolls()
    }

    fun getDiceDrawable(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice1
            2 -> R.drawable.dice2
            3 -> R.drawable.dice3
            4 -> R.drawable.dice4
            5 -> R.drawable.dice5
            6 -> R.drawable.dice6
            else -> R.drawable.default_dice // Default to dice1 if something goes wrong
        }
    }
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
                ContentBox(modifier = Modifier.padding(8.dp), "H: $playerWins/ C:$computerWins")
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
                            text = ": $computerScore",

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
                            text = "$playerScore :",
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

            //this row is responsible for a Win Loose ratio and total score

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ContentBox(modifier, "Total Score : ")


            }

            //this box is responsible to show the dices and Computer Player
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(primaryColor)
                    .border(
                        border = BorderStroke(4.dp, Color.Black),
                        shape = RoundedCornerShape(8.dp)
                    ),
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

                    //map each value of the dice array to its relevant image.
                    computerDicevalues.forEach { diceValue ->
                        Image(
                            painter = painterResource(id = getDiceDrawable(diceValue)),
                            contentDescription = "Dice showing $diceValue",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                        )
                    }

                }
            }


            //this box is responsible to show the dices and name for human player
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(primaryColor)
                    .border(
                        border = BorderStroke(4.dp, Color.Black),
                        shape = RoundedCornerShape(8.dp)
                    ), // Or other modifiers
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    playerDicevalues.forEachIndexed { index, diceValue ->
                        Image(
                            painter = painterResource(id = getDiceDrawable(diceValue)),
                            contentDescription = "Dice showing $diceValue",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                                .clickable {
                                    if (!hasThrown) { // Only allow holding after initial throw
                                        gameInstance.selectDie(index)
                                        updateUI()
                                    } else {
                                        val success = gameInstance.rerollUnselectedDice()
                                        if (success) {
                                            updateUI()
                                        }
                                    }
                                }
                                .border(
                                    BorderStroke(
                                        2.dp,
                                        if (selectedDice[index]) Color.Green
                                        else Color.Transparent
                                    ), shape = RoundedCornerShape(8.dp)
                                )
                        )
                    }
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


                // this row is to display the score button and the throw button.
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PrimaryButton(modifier = Modifier
                        .padding(bottom = 16.dp),
                        text = "Score",
                        onClick = {
                            gameInstance.calculateScore()
                            updateUI()
                            Log.d("Game Activity", "Score button pressed.")
                        })


                    PrimaryButton(modifier = Modifier
                        .padding(bottom = 16.dp),
                        text = if (hasThrown) "Reroll" else "Throw",
                        onClick = {
                            if (!hasThrown) {
                                gameInstance.rollDice()
                                hasThrown = true
                                updateUI()
                                Log.d("Game Activity", "Throw button pressed.")
                            } else {
                                gameInstance.rollDice()
                                updateUI()
                                Log.d("Game Activity", "Reroll button pressed.")
                            }
                        }
                    )
                }
            }
        }
    }


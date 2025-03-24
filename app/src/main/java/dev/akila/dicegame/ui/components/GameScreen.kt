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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun GameScreenContent(modifier: Modifier = Modifier) {/*
    this creates a object of the game class and
     */

    val gameInstance = remember { Game() }
    var gameState by remember { mutableStateOf(0) }

    var playerScore by rememberSaveable { mutableStateOf(0) }
    var computerScore by rememberSaveable { mutableStateOf(0) }

    var playerRoundScore by rememberSaveable { mutableStateOf(0) }
    var computerRoundScore by rememberSaveable { mutableStateOf(0) }

    var playerWins by rememberSaveable { mutableStateOf(0) }
    var computerWins by rememberSaveable { mutableStateOf(0) }

    var playerDicevalues by rememberSaveable { mutableStateOf(IntArray(5)) }
    var computerDicevalues by rememberSaveable { mutableStateOf(IntArray(5)) }

    //this variable is used to check if the player has thrown the dice for the first time in the round or not.
    var hasThrown by rememberSaveable { mutableStateOf(false) }

    //this tracks the turn of the computer
    var computerTookTurn by rememberSaveable { mutableStateOf(false) }

    //this variable is used to disable the score button until the player has thrown the dice
    var scoreButtonEnabled by rememberSaveable { mutableStateOf(false) }
    var throwButtonEnabled by rememberSaveable { mutableStateOf(true) }

    var rerollsLeft by rememberSaveable { mutableStateOf(2) }
    var selectedDice by remember { mutableStateOf(BooleanArray(5)) }
    var isGameOver by rememberSaveable { mutableStateOf(false) }


    val updateUI = {
        gameState++
        playerScore = gameInstance.getPlayerScore()
        computerScore = gameInstance.getComputerScore()
        playerRoundScore = gameInstance.getPlayerRoundScore()
        computerRoundScore = gameInstance.getComputerRoundScore()
        playerWins = gameInstance.getPlayerWins()
        computerWins = gameInstance.getComputerWins()
        isGameOver = gameInstance.isGameOver()

        playerDicevalues = gameInstance.getPlayerDiceResult()
        computerDicevalues = gameInstance.getComputerDiceResult()

        computerTookTurn = gameInstance.getComputerTookTurn()

        /*
        Create a copy of the array to trigger recomposition. without this
        the UI will not update imedietely when the dice are selected or
         unselected.
         */
        selectedDice = gameInstance.getSelectedDice().copyOf()
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
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //this row is responsible for the score display
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ContentBox(modifier, "H: $playerWins/ C:$computerWins")

                ContentBox(modifier, "C : $computerScore H:$playerScore ")
            }

            //this row is responsible for total score
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ContentBox(modifier, "Total Score : ")


            }

            //this box is responsible to show the dices and Computer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight()
                    .background(primaryColor)
                    .border(
                        border = BorderStroke(4.dp, Color.Black),
                        shape = RoundedCornerShape(8.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Computer", style = TextStyle(
                        fontFamily = happyMonkeyFont,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                )
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {

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
                   .weight(1f)
                    .wrapContentHeight()
                    .background(primaryColor)
                    .border(
                        border = BorderStroke(4.dp, Color.Black),
                        shape = RoundedCornerShape(8.dp)
                    ), // Or other modifiers
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    playerDicevalues.forEachIndexed { index, diceValue ->
                        Image(painter = painterResource(id = getDiceDrawable(diceValue)),
                            contentDescription = "Dice showing $diceValue",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                                .clickable {
                                    // Always allow selecting/deselecting dice after the first throw
                                    if (hasThrown) {
                                        gameInstance.selectDie(index)
                                        updateUI()
                                    }
                                }
                                .border(
                                    BorderStroke(
                                        2.dp, if (selectedDice[index]) Color.Green
                                        else Color.Transparent
                                    ), shape = RoundedCornerShape(8.dp)
                                ))
                    }
                }

                Text(
                    text = "Player", style = TextStyle(
                        fontFamily = happyMonkeyFont,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                )
            }


            // this row is to display the score button and the throw button.
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PrimaryButton(modifier = Modifier.padding(bottom = 16.dp),
                    text = "Score",
                    onClick = {
                        if (!computerTookTurn) {
                            // Player ends turn, computer's turn starts
                            gameInstance.computerTurn()
                            updateUI()
                            Log.d("Game Activity", "Player ended turn, computer's turn started.")
                        } else {
                            // Computer has taken turn, end the round
                            if (gameInstance.calculateScore()) {
                                hasThrown = false
                                scoreButtonEnabled = false
                                throwButtonEnabled = true
                                
                                if (gameInstance.isGameOver()) {
                                    // Show game over state
                                    scoreButtonEnabled = false
                                    throwButtonEnabled = false
                                    isGameOver = true
                                } else {
                                    gameInstance.resetForNewRound()
                                }
                                updateUI()
                                Log.d("Game Activity", "Round completed, scores calculated.")
                            }
                        }
                    },
                    enabled = hasThrown)


                PrimaryButton(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = if (!hasThrown) "Throw" else "Reroll ($rerollsLeft left)",
                        onClick = {
                        if (!hasThrown) {
                            // First throw of the round
                            gameInstance.rollPlayerDice()
                            hasThrown = true
                            updateUI()
                            Log.d("Game Activity", "Throw button pressed.")
                        } else {
                            // Reroll
                            val reroll = gameInstance.rerollUnselectedDice()
                            updateUI()
                            throwButtonEnabled = rerollsLeft > 0 || !hasThrown

                            if (reroll) {
                                updateUI()
                            }else {
                                throwButtonEnabled = false
                                //updateUI()
                                Log.d("Game Activity", "No rerolls left.")
                            }
                        }
                    }, enabled = throwButtonEnabled
                )
            }
        }
        if (isGameOver) {
            AlertDialog(
                onDismissRequest = {
                    // Reset game state when dialog is dismissed
                    isGameOver = false
                    gameInstance.resetGame()
                    updateUI()

                },
                title = { Text(text = "Game Over") },
                text = {
                    Text(
                        text = if (playerWins > computerWins) "Congrats you win!"
                        else "You lose, better luck next time"
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Reset game state when "OK" is clicked
                            isGameOver = false
                            gameInstance.resetGame()
                            updateUI()
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

package dev.akila.dicegame.ui.components

import android.content.Intent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.input.KeyboardType


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.AlertDialog
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
import androidx.compose.foundation.layout.wrapContentHeight
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
import dev.akila.dicegame.Game
import dev.akila.dicegame.ui.theme.DiceGameTheme
import dev.akila.dicegame.ui.theme.happyMonkeyFont
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.text.font.FontWeight
import dev.akila.dicegame.R
import dev.akila.dicegame.ui.theme.AppColors.buttonColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.DialogProperties
import dev.akila.dicegame.ui.theme.AppColors.primaryColor

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
        BaseScreenLayout {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val context = LocalContext.current
                PrimaryTitleBox(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .height(50.dp),
                    text = " Dice Game "
                )

                var showTargetScoreDialog by rememberSaveable { mutableStateOf(false) }
                var targetScoreText by rememberSaveable { mutableStateOf("101") }
                var isHardMode by rememberSaveable { mutableStateOf(false) }
                var showAboutDialog by rememberSaveable { mutableStateOf(false) }

                PrimaryButton(modifier = Modifier,
                    text = "New Game",
                    onClick = {
                        Log.d("Main Activity", "New game button pressed.")
                        showTargetScoreDialog = true
                    })

                if (showTargetScoreDialog) {
                    AlertDialog(
                        modifier = Modifier
                            .wrapContentWidth()  // Takes 90% of screen width
                            .wrapContentHeight(),
                        properties = DialogProperties(usePlatformDefaultWidth = false),
                        onDismissRequest = { showTargetScoreDialog = false },
                        title = {
                            Text(
                                text = "Set Target Score",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = happyMonkeyFont,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        },
                        text = {
                            Column() {
                                Text(
                                    text = "Enter the target score:",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = happyMonkeyFont,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black
                                    )
                                )
                                OutlinedTextField(
                                    value = targetScoreText,
                                    onValueChange = {
                                        // Only allow numeric input
                                        if (it.all { char -> char.isDigit() }) {
                                            targetScoreText = it
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = Color.Black,
                                        unfocusedTextColor = Color.Black,
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        cursorColor = Color.Black,
                                        focusedIndicatorColor = Color.Black,
                                        unfocusedIndicatorColor = Color.Black,
                                    )
                                )

                                Row(modifier = Modifier.padding(top = 8.dp)) {
                                    PrimaryButton(
                                        text = "Hard Mode",
                                        onClick = { isHardMode = true },
                                        enabled = !isHardMode // Disable if already selected
                                    )
                                    PrimaryButton(
                                        text = "Easy Mode",
                                        onClick = { isHardMode = false },
                                        enabled = isHardMode // Disable if already selected
                                    )
                                }
                            }
                        },
                        confirmButton = {
                            PrimaryButton(
                                modifier = Modifier.padding(8.dp),
                                text = "Start Game",
                                onClick = {
                                    showTargetScoreDialog = false
                                    val targetScore = targetScoreText.toIntOrNull() ?: 101
                                    val i = Intent(context, GameScreen::class.java)
                                    i.putExtra("targetScore", targetScore)
                                    i.putExtra("isHardMode", isHardMode)
                                    context.startActivity(i)
                                }
                            )
                        },
                        dismissButton = {
                            PrimaryButton(
                                modifier = Modifier.padding(8.dp),
                                text = "Cancel",
                                onClick = { showTargetScoreDialog = false }
                            )
                        }
                    )
                }

                PrimaryButton(modifier = Modifier
                    .padding(bottom = 16.dp),
                    text = "About",
                    onClick = {
                        Log.d("Main Activity", "About button pressed.")
                        showAboutDialog = true
                    })

                if (showAboutDialog) {
                    AlertDialog(
                        onDismissRequest = { showAboutDialog = false },
                        title = {
                            Text(
                                text = "About",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = happyMonkeyFont,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            )
                        },
                        text = {
                            Text(
                                text = "UoW Student ID: w2083133, Name: Akila Pramod\n" +
                                        "I confirm that I understand what plagiarism is and have read and understood " +
                                        "the section on Assessment Offences in the Essential Information for Students. " +
                                        "The work that I have submitted is entirely my own. Any work from other authors " +
                                        "is duly referenced and acknowledged.",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = happyMonkeyFont,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                )
                            )
                        },
                        confirmButton = {
                            PrimaryButton(modifier = Modifier.padding(8.dp),
                                text = "OK",
                                onClick = { showAboutDialog = false })
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier, text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier
            .padding(8.dp)
            .border(
                border = BorderStroke(6.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp)),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.Black,
            /*this is to show the user tht the button is disabled
            by making the button faded and greyed out
             */
            disabledContainerColor = buttonColor.copy(alpha = 0.5f),
            disabledContentColor = Color.Gray
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

//this is the primary content box
@Composable
fun ContentBox(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .border(
                border = BorderStroke(4.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .background(primaryColor)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(15.dp),
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

//This is the base screen layout
@Composable
fun BaseScreenLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            //.fillMaxSize(1f)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF81AFD4),
                        Color(0xFF34769F)
                    )
                )
            ),

        ) {
        Image(
            painter = painterResource(id = R.drawable.blue_dice_bokeh_wallapper),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        content()
    }
}

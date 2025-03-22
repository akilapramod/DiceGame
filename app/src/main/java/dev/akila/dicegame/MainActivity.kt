package dev.akila.dicegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.akila.dicegame.ui.components.HomeScreen
import dev.akila.dicegame.ui.theme.DiceGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceGameTheme {
                HomeScreen()
                }
            }
        }
    }



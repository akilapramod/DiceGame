package dev.akila.dicegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.akila.dicegame.ui.components.HomeScreen
import dev.akila.dicegame.ui.theme.DiceGameTheme

class MainActivity : ComponentActivity() {
    private var gameState: Bundle? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameState = savedInstanceState?.getBundle("gameState")
        
        setContent {
            DiceGameTheme {
                HomeScreen()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle("gameState", gameState)
    }
}

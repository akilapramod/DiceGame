package dev.akila.dicegame.ui.components

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.akila.dicegame.ui.theme.DiceGameTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )   {
        NewGameButton(
            modifier = Modifier.padding(bottom = 16.dp)
        )
        AboutButton(
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }

}




@Composable
fun NewGameButton(modifier: Modifier = Modifier) {
    ElevatedButton (
        modifier = modifier
            .width(250.dp),

        onClick = {
            Log.d("Main Activity","New Game started.")
        }
    ) {
        Text(text = "New Game")
    }
}

@Composable
fun AboutButton(modifier: Modifier = Modifier) {
    ElevatedButton (
        modifier = modifier.width(250.dp),
        onClick = {
            Log.d("Main Activity","About button pressed.")
        }
    ) {
        Text(text = "About")
    }
}


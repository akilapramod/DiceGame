package dev.akila.dicegame.ui.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.akila.dicegame.ui.theme.DiceGameTheme
import dev.akila.dicegame.ui.theme.happyMonkeyFont
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment

class AboutScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AboutScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    DiceGameTheme {
        BaseScreenLayout {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "About",
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontFamily = happyMonkeyFont,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(vertical = 32.dp)
                )
                
                Text(
                    text = "I confirm that I understand what plagiarism is and have read and understood " +
                            "the section on Assessment Offences in the Essential Information for Students. " +
                            "The work that I have submitted is entirely my own. Any work from other authors " +
                            "is duly referenced and acknowledged.",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = happyMonkeyFont,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

package dev.akila.dicegame

import android.util.Log

class Game {



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
    }}

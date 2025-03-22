package dev.akila.dicegame

import android.util.Log
import kotlin.random.Random

class Game {

    private var playerScore = 0
    private var computerScore = 0

    // Current dice rolls
    private var playerDice = IntArray(5)
    private var computerDice = IntArray(5)

    // Win-loss record
    private var playerWins = 0
    private var computerWins = 0

    // Roll dice for both player and computer
    fun rollDice() {
        playerDice = generateDiceRoll()
        println(playerDice.joinToString(", "))
        computerDice = generateDiceRoll()
        println(playerDice.joinToString(", "))
    }

    //this function is used to roll the dice except the dice that are selected.

    // Create an Integer array and Generate 5 random dice values  then
    // store them in the Array
    private fun generateDiceRoll(): IntArray {
        val diceResults = IntArray(5)
        for (i in 0 until 5) {
            diceResults[i] = Random.nextInt(1, 7) // 1 to 6 inclusive
        }
        return diceResults
    }

    /*
    This function is used to get the summery value of all the dice for
    player and computer.
     */
    private fun calculateRoundScore(dice: IntArray): Int {
        return dice.sum()
    }


    /*
    Calculate the score for the player and computer by adding the
    previous Rice roll rounds.
     */
    fun calculateScore() {
        val playerRoundScore = calculateRoundScore(playerDice)
        val computerRoundScore = calculateRoundScore(computerDice)

        // Update total scores
        playerScore += playerRoundScore
        println("Player score:$playerScore")
        computerScore += computerRoundScore
        println("Computer score:$computerScore")

        // Update win/loss record
        if (playerRoundScore > computerRoundScore) {
            println("Player wins")
            playerWins++
        } else if (computerRoundScore > playerRoundScore) {
            println("Computer wins")
            computerWins++
        }
        // If equal, no change to win/loss record
    }

    //Getters for accessing game data
    fun getPlayerDiceResult(): IntArray = playerDice
    fun getComputerDiceResult(): IntArray = computerDice
    fun getComputerScore(): Int =computerScore
    fun getPlayerSCore(): Int =playerScore
    fun getPlayerWins(): Int = playerWins
    fun getComputerWins(): Int = computerWins
    }





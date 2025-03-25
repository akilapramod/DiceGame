package dev.akila.dicegame

import android.util.Log
import kotlin.random.Random

class Game(targetScore: Int = 101, private val isHardMode: Boolean = true) {

    private var playerScore = 0
    private var computerScore = 0

    // Validate targetScore
    private val winningScore = if (targetScore >= 1) targetScore else 101

    //this variablea are used for the player rerolling
    private var rerollsRemaining = 2
    private var selectedDice = BooleanArray(5) { false }

    private var computerTookTurn = false

    // Current dice rolls
    private var playerDice = IntArray(5)
    private var computerDice = IntArray(5)

    private var playerRoundScore = 0
    private var computerRoundScore = 0

    // Win-loss record
    private var playerWins = 0
    private var computerWins = 0

    //private val winningScore = 50
    private var gameOver = false
    private var winner = ""

    // Roll dice for player only
    fun rollPlayerDice() {
        playerDice = generateDiceRoll()
        Log.d("Game", "Player dice: ${playerDice.joinToString(", ")}")
    }

    // Roll dice for computer only
    fun rollComputerDice() {
        computerDice = generateDiceRoll()
        Log.d("Game", "Computer dice: ${computerDice.joinToString(", ")}")
    }

    /*
    This function allows the player to select or deselect a die by tapping on it. Each time the
    function is called for a particular die, it switches that die's state between selected and not
    selected.
     */
    fun selectDie(index: Int) {
        selectedDice[index] = !selectedDice[index]
        Log.d("Game", "Die $index selected: ${selectedDice[index]}")
    }

    //this function is used to reroll the unselected dice.
    fun rerollUnselectedDice(): Boolean {
        if (rerollsRemaining > 0) {
            Log.d("Game", "Before reroll - Player dice: ${playerDice.joinToString(", ")}")
            for (i in playerDice.indices) {
                if (!selectedDice[i]) {
                    playerDice[i] = Random.nextInt(1, 7)
                }
            }
            rerollsRemaining--
            // Update player round score after reroll
            playerRoundScore = calculateRoundScore(playerDice)

            Log.d("Game", "After reroll - Player dice: ${playerDice.joinToString(", ")}")
            Log.d("Game", "Updated player round score: $playerRoundScore")
            Log.d("Game", "Rerolls remaining: $rerollsRemaining")
            return true
        }
        return false
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
    fun calculateScore(): Boolean {
        if (gameOver) return false
        
        playerRoundScore = calculateRoundScore(playerDice)
        computerRoundScore = calculateRoundScore(computerDice)

        Log.d("Game", "Round scores - Player: $playerRoundScore, Computer: $computerRoundScore")

        // Update total scores
        playerScore += playerRoundScore
        computerScore += computerRoundScore

        //check if the game is over
        if (playerScore >= winningScore) {
            gameOver = true
            winner = "player"
            playerWins++
            Log.d("Game", "Game over! Player wins with score: $playerScore")
        } else if (computerScore >= winningScore) {
            gameOver = true
            winner = "computer"
            computerWins++
            Log.d("Game", "Game over! Computer wins with score: $computerScore")
        }

        // Reset for next round
        computerTookTurn = false
        return true
    }
    
    fun computerTurn() {
        if (isHardMode) {
            computerTurnHard()
        } else {
            computerTurnEasy()
        }
    }

    private fun computerTurnHard() {
        rerollsRemaining = 2
        rollComputerDice()
        Log.d("Game Activity", "Player ended turn, computer's turn started.")
        Log.d("Game", "Initial computer dice: ${computerDice.joinToString(", ")}")

        //calculate computer score after first roll
        computerRoundScore = calculateRoundScore(computerDice)

        val rerollThreshold = 3 // Consider dice with value 3 or lower as low

        // First reroll
        if (rerollsRemaining > 0) {
            for (i in computerDice.indices) {
                if (computerDice[i] <= rerollThreshold) {
                    computerDice[i] = Random.nextInt(1, 7)
                }
            }
            rerollsRemaining--
            Log.d("Game", "After first reroll - Computer dice: ${computerDice.joinToString(", ")}")
        }

        // Second reroll if needed
        if (rerollsRemaining > 0) {
            for (i in computerDice.indices) {
                if (computerDice[i] <= rerollThreshold) {
                    computerDice[i] = Random.nextInt(1, 7)
                }
            }
            rerollsRemaining--
            Log.d("Game", "After second reroll - Computer dice: ${computerDice.joinToString(", ")}")
        }

        // Calculate computer's round score
        computerRoundScore = calculateRoundScore(computerDice)
        Log.d("Game", "Final computer round score: $computerRoundScore")

        computerTookTurn = true
    }

    private fun computerTurnEasy() {
        rerollsRemaining = 2
        rollComputerDice()

        repeat(2) { // Max 2 rerolls
            if (rerollsRemaining > 0 && Random.nextBoolean()) { // 50% chance to reroll
                val keepCount = Random.nextInt(1, 4) // Keep 1-3 dice
                val indicesToKeep = computerDice.indices.shuffled().take(keepCount)

                computerDice.forEachIndexed { i, _ ->
                    if (i !in indicesToKeep) {
                        computerDice[i] = Random.nextInt(1, 7)
                    }
                }
                rerollsRemaining--
                Log.d("Game", "Easy mode reroll ${it+1}: Kept indices $indicesToKeep, Dice: ${computerDice.contentToString()}")
            }
        }

        computerRoundScore = calculateRoundScore(computerDice)
        computerTookTurn = true
    }

    fun resetGame() {
        playerScore = 0
        computerScore = 0
        gameOver = false
        winner = ""
        resetForNewRound()
        Log.d("Game", "Full game reset")
    }

    fun resetForNewRound() {
        rerollsRemaining = 2
        selectedDice = BooleanArray(5) { false }
        playerDice = IntArray(5) { 0 }
        computerDice = IntArray(5) { 0 }
        Log.d("Game", "Reset for new round. Rerolls reset to: $rerollsRemaining")
    }

    //Getters for accessing game data
    fun getPlayerDiceResult(): IntArray = playerDice
    fun getComputerDiceResult(): IntArray = computerDice
    fun getComputerScore(): Int = computerScore
    fun getPlayerScore(): Int = playerScore
    fun getPlayerWins(): Int = playerWins
    fun getComputerWins(): Int = computerWins
    fun getRemainingRerolls(): Int = rerollsRemaining
    fun getSelectedDice(): BooleanArray = selectedDice
    fun getPlayerRoundScore(): Int = playerRoundScore
    fun getComputerRoundScore(): Int = computerRoundScore
    fun getComputerTookTurn(): Boolean = computerTookTurn
    fun isGameOver(): Boolean = gameOver
    fun getWinner(): String = winner
}

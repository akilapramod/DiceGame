# Dice Game Android Application

## Description
This is an Android application built using **Kotlin** and **Jetpack Compose**. The app implements a dice game where a human player competes against the computer. The goal is to reach a target score (default is 101) by rolling five dice. The game follows specific rules, including optional rerolls and tie-breaking mechanisms.

The app adheres to the following constraints:
- No third-party libraries are used.
- Only standard Android API libraries are utilized.
- Jetpack Compose is used for UI (no Views).
- Activity recreation is not disabled during screen orientation changes.

---

## Features
1. **Main Screen**:
   - Two buttons: **New Game** and **About**.
   - The **About** button displays a popup with the author's name and a description of the app.

2. **Game Screen**:
   - Displays dice images for both the human player and the computer.
   - Buttons: **Throw** (to roll the dice) and **Score** (to finalize the score for the current round).
   - Supports up to two optional rerolls for the human player.
   - Displays the current total score for both players.

3. **Game Rules**:
   - Players roll five dice simultaneously.
   - The human player can choose to keep specific dice and reroll the rest.
   - The computer follows a random strategy for rerolls.
   - The first player to reach the target score (default: 101) wins.
   - In case of a tie, players roll all five dice again until a winner is determined.

4. **Customizable Target Score**:
   - The human player can set a custom target score before starting the game.

5. **Win/Loss Tracking**:
   - Displays the total number of wins for the human and computer players (e.g., `H:10/C:5`).

6. **Efficient Computer Strategy**:
   - The computer uses an optimized strategy to decide whether to reroll and which dice to keep.

7. **Orientation Handling**:
   - The app handles device orientation changes gracefully, preserving the current state of the game.

---

## Screenshots
(Add screenshots of the app here if available.)

---

## How to Run the App
1. **Prerequisites**:
   - Android Studio (latest version recommended).
   - An Android device or emulator with API level 21 or higher.

2. **Steps**:
   - Clone this repository.
   - Open the project in Android Studio.
   - Build and run the app on an Android device or emulator.

---

## Implementation Details
- **Jetpack Compose**: Used for building the UI components.
- **Intents**: Used for navigation between screens.
- **State Management**: Jetpack Compose's state and recomposition are used to manage the game state.
- **Randomization**: The `Random` class is used to generate random dice values.
- **Orientation Handling**: The app saves and restores state during orientation changes using `ViewModel` and `rememberSaveable`.

---

## Rules of the Game
1. Both players roll five dice simultaneously.
2. The human player can choose to keep specific dice and reroll the rest (up to two rerolls).
3. The computer follows a random strategy for rerolls.
4. The first player to reach the target score wins.
5. In case of a tie, players roll all five dice again until a winner is determined.

---

## Computer Strategy
The computer uses an optimized strategy to decide whether to reroll and which dice to keep. The strategy is based on the following logic:
- If the current score is below a certain threshold, the computer rerolls all dice.
- If the current score is close to the target, the computer keeps high-value dice and rerolls the rest.
- The strategy is designed to maximize the computer's chances of winning while minimizing unnecessary rerolls.

---

## Code Structure
- `MainActivity.kt`: Entry point of the app.
- `GameScreen.kt`: Handles the game logic and UI.
- `AboutDialog.kt`: Displays the "About" popup.
- `Dice.kt`: Represents a single dice with its value and image.
- `ComputerStrategy.kt`: Implements the computer's strategy.

---

## Future Improvements
- Add animations for dice rolling.
- Persist win/loss statistics using local storage.
- Add sound effects for dice rolls and game outcomes.

---

## Author
- Akila Pramod

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.


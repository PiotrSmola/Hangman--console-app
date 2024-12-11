# Hangman Game

This is a simple command-line Hangman game implemented in Java. The game fetches a random word from an API and challenges the player to guess it within a limited number of attempts.

## Features

- Fetches a random word using the [Random Word API](https://random-word-api.herokuapp.com/).
- Allows guessing letters or the entire word.
- Provides visual feedback for incorrect guesses using a textual hangman representation.
- Limits players to 10 incorrect guesses before the game ends.
- Displays messages for successful or failed guesses.

## How to Play

1. Run the program in a terminal or command prompt.
2. Try to guess the hidden word by entering letters or the entire word.
3. Each correct guess reveals the matching letters in the word.
4. Each incorrect guess adds to the hangman drawing.
5. You win by guessing the word before reaching 10 incorrect attempts.
6. If you fail to guess within 10 attempts, the game ends, and the correct word is revealed.

## Requirements

- Java Development Kit (JDK) 8 or higher.
- Internet connection to fetch the random word from the API.

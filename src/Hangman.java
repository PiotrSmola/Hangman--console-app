import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Hangman {
    private static final String API_URL = "https://random-word-api.herokuapp.com/word";
    private static final int MAX_TRIES = 10;

    public static void main(String[] args) {
        String word = getWord();
        Set<Character> guessedLetters = new HashSet<>();
        int incorrectGuesses = 0;

        StringBuilder hiddenWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.append("_");
        }

        System.out.println("Welcome to HANGMAN!");
        System.out.println("Try to guess the word. You can enter individual letters or a whole word");
        System.out.println("You have 10 attempts to guess the word.");
        System.out.println("Good luck and have fun!");

        while (incorrectGuesses < MAX_TRIES) {
            String guess = readFromUser();
            System.out.println("\nA word to guess: " + hiddenWord.toString());
            System.out.print("Enter a letter or try to guess the whole word: ");

            if (guess.length() == 1) {
                char letter = guess.charAt(0);
                if (guessedLetters.contains(letter)) {
                    System.out.println("This letter has already been given. Try again");
                    continue;
                }

                guessedLetters.add(letter);

                if (word.contains(String.valueOf(letter))) {
                    update(word, hiddenWord, letter); //update hidden word
                    if (!hiddenWord.toString().contains("_")) {
                        System.out.println("Congratulations! You guessed the word: " + hiddenWord.toString());
                        return;
                    }
                } else {
                    incorrectGuesses++;
                    drawHangman(incorrectGuesses);
                    System.out.println("Incorrect letter!");
                    System.out.println("Attempts left: " + (MAX_TRIES - incorrectGuesses));
                }
            } else if (guess.length() > 1) {
                if (guess.equals(word)) {
                    System.out.println("Congratulations! You guessed the word: " + word);
                    return;
                } else {
                    incorrectGuesses++;
                    drawHangman(incorrectGuesses);
                    System.out.println("Incorrect word!");
                    System.out.println("Attempts left: " + (MAX_TRIES - incorrectGuesses));

                }
            }
        }

        System.out.println("\nYou lost! You failed to guess the word. The guessed word is:" + word);
        System.out.println("\nThank you for playing");
    }

    private static String getWord() {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString().replaceAll("[\"\\[\\]]", "");
    }

    private static String readFromUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }

    private static void update(String word, StringBuilder hiddenWord, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                hiddenWord.setCharAt(i, guess);
            }
        }
    }

    private static void drawHangman(int incorrectGuesses) {
        switch (incorrectGuesses) {
            case 1:
                System.out.println("\n ________");
                break;
            case 2:
                System.out.println(" |\n |\n |\n |\n |\n_|_______");
                break;
            case 3:
                System.out.println(" ________\n |/\n |\n |\n |\n |\n_|_______");
                break;
            case 4:
                System.out.println(" ________\n |/      |\n |\n |\n |\n |\n_|_______");
                break;
            case 5:
                System.out.println(" ________\n |/      |\n |      (_)\n |\n |\n |\n_|_______");
                break;
            case 6:
                System.out.println(" ________\n |/      |\n |      (_)\n |       |\n |\n |\n_|_______");
                break;
            case 7:
                System.out.println(" ________\n |/      |\n |      (_)\n |       |\n |       |\n |\n_|_______");
                break;
            case 8:
                System.out.println(" ________\n |/      |\n |      (_)\n |      \\|\n |       |\n |\n_|_______");
                break;
            case 9:
                System.out.println(" ________\n |/      |\n |      (_)\n |      \\|/\n |       |\n |\n_|_______");
                break;
            case 10:
                System.out.println(" ________\n |/      |\n |      (_)\n |      \\|/\n |       |\n |      / \\ \n_|_______");
                break;
        }
    }
}

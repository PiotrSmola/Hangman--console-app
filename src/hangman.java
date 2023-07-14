import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class hangman {
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

        while (incorrectGuesses < MAX_TRIES) {
            String guess = readFromUser();

            if (guess.length() == 1) {
                char letter = guess.charAt(0);
                if (guessedLetters.contains(letter)) {
                    continue;
                }

                guessedLetters.add(letter);

                if (word.contains(String.valueOf(letter))) {
                    update(word, hiddenWord, letter); //update hidden word
                    if (!hiddenWord.toString().contains("_")) {
                        return;
                    }
                } else {
                    incorrectGuesses++;
                    drawHangman(incorrectGuesses);

                }
            } else if (guess.length() > 1) {
                if (guess.equals(word)) {
                    return;
                } else {
                    incorrectGuesses++;
                    drawHangman(incorrectGuesses);

                }
            }
        }

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

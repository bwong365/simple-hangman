import java.util.ArrayList;
import java.util.Scanner;

class Hangman {
    private Dictionary dictionary;
    private Scanner scanner;

    Hangman(Dictionary dictionary) {
        this.dictionary = dictionary;
        scanner = new Scanner(System.in);
    }

    void start() {
        // Initialize Game
        String word;
        ArrayList<Character> revealed = new ArrayList<>();

        displayWelcome();
        word = dictionary.random();

        for (int i = 0; i < word.length(); i++) {
            revealed.add('-');
        }

        // Prompt Guesses
        while (true) {
            char guess;

            // Display revealed
            for (int i = 0; i < revealed.size(); i++) {
                System.out.print(revealed.get(i));
            }
            guess = promptGuess();

            // check if guess is in the word
            // replace dashes with letters in revealed if guessed correctly
            // check for win condition
        }
    }

    private void displayWelcome() {
        System.out.println("Welcome to My Simple Game of Hangman!!!");
        System.out.println("by Ben Wong");
    }

    char promptGuess() {
        String rawGuess = "";
        do {
            System.out.println("Guess one letter at a time");
            rawGuess = scanner.nextLine();
        } while (rawGuess.length() != 1);

        return rawGuess.charAt(0);
    }
}

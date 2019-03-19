import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        Dictionary dictionary;
        try {
             dictionary = new Dictionary();
        } catch (IOException e) {
            System.out.println("Wordlist could not be loaded/file not found...");
            return;
        }

        Hangman game = new Hangman(dictionary);
        game.start();
    }
}


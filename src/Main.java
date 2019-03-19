import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        Dictionary dictionary;
        var ui = new TextInterface();

        try {
             dictionary = new Dictionary();
        } catch (IOException e) {
            System.out.println("Wordlist could not be loaded/file not found...");
            return;
        }

        var game = new Hangman(dictionary, ui);
        game.start();
    }
}


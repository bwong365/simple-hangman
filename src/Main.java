import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        Dictionary dictionary;
        var ui = new TextInterface();
        var filePath = "dictionary.txt";

        try {
             dictionary = new Dictionary(filePath);
        } catch (IOException e) {
            System.out.println("Wordlist could not be loaded/file not found...");
            return;
        }

        var game = new Hangman(dictionary, ui);
        game.start();
    }
}


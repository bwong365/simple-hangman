import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Dictionary dictionary = new Dictionary();
        Hangman game = new Hangman(dictionary);
        game.start();
    }
}


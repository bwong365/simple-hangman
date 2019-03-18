import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        String test = dictionary.random();
        for (int i = 0; i < 10; i++) {
            System.out.println(dictionary.random());
        }

        // Get a list of words
        // Logic
    }
}


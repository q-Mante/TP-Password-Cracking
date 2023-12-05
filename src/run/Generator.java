package run;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import model.Key;

public class Generator {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -cp bin run.Generator \"CC\" \"table.txt\"");
            System.exit(1);
        }

        int CC = Integer.parseInt(args[0]);
        String outputFileName = args[1];
        int B = Key.B;
        int R = Key.R;
        String alphabet = Key.ALPHABET;

        Random rand = new Random(System.currentTimeMillis());

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            for (int i = 0; i < B * CC; i++) {
                StringBuilder generatedString = new StringBuilder();
                for (int j = 0; j < CC; j++) {
                    int r = rand.nextInt(R);
                    generatedString.append(alphabet.charAt(r));
                }
                writer.println(generatedString.toString());
            }

            System.out.println("Generated data has been saved to " + outputFileName);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
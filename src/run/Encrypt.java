package run;

import model.IKey;
import model.Key;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import static utils.WRUtils.readPasswordsFromFile;
import static utils.WRUtils.readTableFromFile;

public class Encrypt {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java -cp bin run.Encrypt \"passwords.txt\" \"table.txt\" \"encrypted.txt\"");
            System.exit(1);
        }

        Key key = new Key();

        // Read passwords from the specified file
        List<IKey.KeyStruct> passwords = readPasswordsFromFile(args[0], key);

        // Read in table T from the provided file
        IKey.KeyStruct[] T = readTableFromFile(args[1], key);

        try (PrintWriter writer = new PrintWriter(new FileWriter(args[2]))) {
            for (IKey.KeyStruct password : passwords) {
                // Print out input passwords
                System.out.print("   ");
                key.KEYshow(password);
                System.out.println();

                // Compute subset sum
                IKey.KeyStruct encrypted = key.KEYsubsetsum(password, T);

                // Print results to the console
                System.out.println();
                System.out.print("   ");
                key.KEYshow(encrypted);
                System.out.println();

                // Write results to the output file
                StringBuilder encryptedString = new StringBuilder();
                for (int i = 0; i < Key.C; i++)
                    encryptedString.append(Key.ALPHABET.charAt(encrypted.digits[i]));
                writer.println(encryptedString);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + args[2]);
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

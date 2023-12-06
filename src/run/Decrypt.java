package run;

import model.IKey;
import model.Key;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static utils.WRUtils.readPasswordsFromFile;
import static utils.WRUtils.readTableFromFile;

public class Decrypt {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage:java -cp bin run.Decrypt \"encrypted.txt\" \"table.txt\" \"decrypted.txt\"");
            System.exit(1);
        }

        // Initialize objects
        Key key = new Key();
        List<HashMap<Byte, IKey.KeyStruct>> SymbolTable = new ArrayList<>(Key.C);

        // Initialize each element of the hashmap list
        for (int i = 0; i < Key.C; i++) {
            SymbolTable.add(new HashMap<>());
        }

        // Read encrypted passwords from the specified file
        List<IKey.KeyStruct> encryptedPasswords = readPasswordsFromFile(args[0], key);

        // Read in table T
        IKey.KeyStruct[] T = readTableFromFile(args[1], key);

        try (PrintWriter writer = new PrintWriter(new FileWriter(args[2]))) {
            for (IKey.KeyStruct encryptedPassword : encryptedPasswords) {

                // Symbol-table decryption
                IKey.KeyStruct password = key.KEYinit();
                IKey.KeyStruct encrypted = key.KEYsubsetsum(password, T, SymbolTable);

                while (!key.equals(encrypted, encryptedPassword)) {
                    //key.KEYshow(password);        // for debugging
                    //key.KEYshow(encrypted);        // for debugging
                    password = key.KEYnext(password);
                    encrypted = key.KEYsubsetsum(password, T, SymbolTable);
                }

                // Write results to the output file
                StringBuilder decryptedString = new StringBuilder();
                for (int i = 0; i < Key.C; i++)
                    decryptedString.append(Key.ALPHABET.charAt(password.digits[i]));

                writer.println(decryptedString);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + args[2]);
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

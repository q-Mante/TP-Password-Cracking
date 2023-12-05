package utils;

import model.IKey;
import model.Key;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WRUtils {

    public static List<IKey.KeyStruct> readPasswordsFromFile(String fileName, Key key) {
        List<IKey.KeyStruct> passwords = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNext()) {
                String buffer = fileScanner.next();
                IKey.KeyStruct password = key.KEYinit(buffer);
                passwords.add(password);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + fileName);
            System.exit(1);
        }

        return passwords;
    }

    public static IKey.KeyStruct[] readTableFromFile(String fileName, Key key) {
        IKey.KeyStruct[] T = new IKey.KeyStruct[Key.N];

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            for (int i = 0; i < Key.N && fileScanner.hasNext(); i++) {
                String buffer = fileScanner.next();
                T[i] = key.KEYinit(buffer);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + fileName);
            System.exit(1);
        }

        return T;
    }
}

//package run;
//
//import model.IKey;
//import model.Key;
//
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class Decrypt {
//
//    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.err.println("Usage: Get-Content \"fileName.txt\" | java -cp bin run.Decrypt \"password\"");
//            System.exit(1);
//        }
//
//        Key key = new Key();
//
//        // The user encrypted password
//        IKey.KeyStruct encryptedPassword = key.KEYinit(args[0]);
//
//        // Print out input encrypted password
//        System.out.print("   ");
//        key.KEYshow(encryptedPassword);
//        System.out.println();
//
//        // Read in table T
//        IKey.KeyStruct[] T = new IKey.KeyStruct[Key.N];
//        Scanner scanner = new Scanner(System.in);
//
//        for (int i = 0; i < Key.N; i++) {
//            String buffer = scanner.next();
//            T[i] = key.KEYinit(buffer);
//        }
//
//        // Initialize symbol table
//        HashMap<IKey.KeyStruct, IKey.KeyStruct> symbolTable = new HashMap<>();
//
//        IKey.KeyStruct[] initialSubsets = new IKey.KeyStruct[Key.C];
//        for (int i = 0; i < Key.C; i++) {
//            initialSubsets[i] = key.KEYinitOneDigit(1, i);
//            for (int j = 0; j < Key.R; j++) {
//                IKey.KeyStruct existingSum = symbolTable.get(initialSubsets[i]);
//                if (existingSum == null) {
//                    IKey.KeyStruct sum = key.KEYsubsetsum(initialSubsets[i], T);
//                    symbolTable.put(initialSubsets[i], sum);
//                } else {
//
//                }
//            }
//
//
//            IKey.KeyStruct sum = key.KEYsubsetsum(initialSubsets[i], T);
//        }
//
//        for (int i = 0; i < Key.C; i++)
//
//        for (IKey.KeyStruct subset : initialSubsets) {
//            for (int i = )
//            IKey.KeyStruct sum = key.KEYsubsetsum(subset, T);
//        }
//
//
//
//
//
//        // Find the decryption key using symbol-table approach
//        for (IKey.KeyStruct subset : initialSubsets) {
//            IKey.KeyStruct sum = key.KEYsubsetsum(subset, T); // Compute the subset sum
//            symbolTable.put(sum, subset); // Store the subset sum and its corresponding subset
//
//            // Expand the subset
//            for (int i = 0; i < Key.N; i++) {
//                if (key.KEYbit(subset, i) == 0) { // Check if the i-th bit is 0
//                    key.KEYshow(subset);
//                    IKey.KeyStruct newSubset = key.KEYnext(subset); // Create a new subset by setting the i-th bit to 1
//                    IKey.KeyStruct newSum = key.KEYsubsetsum(newSubset, T); // Compute the subset sum of the new subset
//
//                    // Check if the new subset sum is already in the symbol table
//                    IKey.KeyStruct existingSubset = symbolTable.get(newSum);
//
//                    if (existingSubset == null) { // If the new subset sum is not in the symbol table
//
//                        symbolTable.put(newSum, newSubset); // Add the new subset sum and its corresponding subset to the symbol table
//                    } else { // If the new subset sum is already in the symbol table
//                        key.KEYshow(existingSubset);
//                        if (newSum.equals(encryptedPassword)) { // Check if the sum equals the encrypted password
//                            System.out.println("Decrypted password: ");
//                            key.KEYshow(existingSubset);
//                            System.exit(0); // Terminate the program
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

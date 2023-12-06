package model;

import java.util.HashMap;
import java.util.List;

public interface IKey {
    int C = 8;      // number of characters in password
    int B = 5;      // number of bits per character
    int R = 1 << B; // size of alphabet (32)
    int N = B * C;  // number of bits per password
    String ALPHABET = "abcdefghijklmnopqrstuvwxyz012345";

    public class KeyStruct {
        public byte[] digits = new byte[C];
    }

    void KEYshow(KeyStruct k);  // print k

    KeyStruct KEYadd(KeyStruct a, KeyStruct b); // return a + b (mod 2^N)

    int KEYbit(KeyStruct k, int i); // return the ith bit of k

    KeyStruct KEYsubsetsum(KeyStruct k, KeyStruct[] T);
    KeyStruct KEYsubsetsum(KeyStruct k, KeyStruct[] T, List<HashMap<Byte, KeyStruct>> SymbolTable);

    KeyStruct KEYinit();
    KeyStruct KEYinit(String s);

    KeyStruct KEYnext(KeyStruct k);
}

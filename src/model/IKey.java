package model;

public interface IKey {
    int C = 4;      // number of characters in password
    int B = 5;      // number of bits per character
    int R = 1 << B; // size of alphabet (32)
    int N = B * C;  // number of bits per password
    String ALPHABET = "abcdefghijklmnopqrstuvwxyz012345";

    // base 64 alphabet
    // String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public class KeyStruct {
        public byte[] digits = new byte[C];
    }

    void KEYshow(KeyStruct k);  // print k

    KeyStruct KEYadd(KeyStruct a, KeyStruct b); // return a + b (mod 2^N)

    int KEYbit(KeyStruct k, int i); // return the ith bit of k

    KeyStruct KEYsubsetsum(KeyStruct k, KeyStruct[] T);

    KeyStruct KEYinit();
    KeyStruct KEYinit(String s);
    KeyStruct KEYinit(char ch, int i);

    KeyStruct KEYnext(KeyStruct k);
}

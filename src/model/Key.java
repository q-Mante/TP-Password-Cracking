package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Key implements IKey {
    @Override
    public void KEYshow(KeyStruct k) {
        int i;
        for (i = 0; i < C; i++)
            System.out.print(ALPHABET.charAt(k.digits[i]));
        System.out.print("  ");
        for (i = 0; i < C; i++)
            System.out.printf("%2d ", k.digits[i]);
        System.out.print("  ");
        for (i = 0; i < N; i++)
            System.out.print(KEYbit(k, i));
        System.out.println();
    }

    @Override
    public KeyStruct KEYadd(KeyStruct a, KeyStruct b) {
        KeyStruct c = new KeyStruct();
        int carry = 0;
        for (int i = C - 1; i >= 0; i--) {
            c.digits[i] = (byte) ((a.digits[i] + b.digits[i] + carry) % R);
            carry = (a.digits[i] + b.digits[i] + carry) >= R ? 1 : 0;
        }
        return c;
    }

    @Override
    public int KEYbit(KeyStruct k, int i) {
        return (k.digits[i / B] >> (B - 1 - i % B)) & 1;
    }

    @Override
    public KeyStruct KEYsubsetsum(KeyStruct k, KeyStruct[] T) {
        KeyStruct sum = new KeyStruct();
        for (int i = 0; i < N; i++) {
            if (KEYbit(k, i) == 1) {
                sum = KEYadd(sum, T[i]);
                //System.out.printf("%2d ", i);   // for debugging
                //KEYshow(T[i]);                  // for debugging
            }
        }
        return sum;
    }

    @Override
    public KeyStruct KEYsubsetsum(KeyStruct k, KeyStruct[] T, List<HashMap<Byte, KeyStruct>> SymbolTable) {
        KeyStruct sum = new KeyStruct();
        for (int i = 0; i < C; i++) {
            KeyStruct storedKey = SymbolTable.get(i).get(k.digits[i]);

            if (storedKey != null) {
                sum = KEYadd(sum, storedKey);
                continue;
            }

            KeyStruct newStoredKey = new KeyStruct();
            for (int j = 0; j < B; j++) {
                int index = i*B + j;
                if (KEYbit(k, index) == 1) {
                    newStoredKey = KEYadd(newStoredKey, T[index]);
                    //System.out.printf("%2d ", i);   // for debugging
                    //KEYshow(T[i]);                  // for debugging
                }
            }
            SymbolTable.get(i).put(k.digits[i], newStoredKey);
            sum = KEYadd(sum, newStoredKey);
        }
        return sum;
    }

    @Override
    public KeyStruct KEYinit() {
        KeyStruct k = new KeyStruct();
        Arrays.fill(k.digits, (byte) 0);
        return k;
    }

    @Override
    public KeyStruct KEYinit(String s) {
        KeyStruct k = new KeyStruct();
        for (int i = 0; i < C; i++)
            for (int j = 0; j < R; j++)
                if (s.charAt(i) == ALPHABET.charAt(j))
                    k.digits[i] = (byte) j;
        return k;
    }

    @Override
    public KeyStruct KEYnext(KeyStruct k) {
        for (int i = C - 1; i >= 0; i--) {
            k.digits[i] += 1;
            if (k.digits[i] < R) {
                break;
            }
            k.digits[i] = 0;
        }
        return k;
    }

    public boolean equals(KeyStruct a, KeyStruct b) {
        for (int i = 0; i < C; i++) {
            if (a.digits[i] != b.digits[i])
                return false;
        }
        return true;
    }
}
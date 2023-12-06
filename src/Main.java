import run.Brute;
import run.Decrypt;
import run.Encrypt;
import run.Generator;

public class Main {

    private static final String C = "8";
    private static final String PASSWORDS = "passwords.txt";
    private static final String TABLE = "rand8.txt";
    private static final String ENCRYPTED = "encrypted.txt";
    private static final String B_DECRYPTED = "brute_decrypted.txt";
    private static final String DECRYPTED = "decrypted.txt";

    public static void main(String[] args) {
        long startTime, endTime;

        // First part for generating and encrypting
        Generator.main(new String[]{C, TABLE});
        Encrypt.main(new String[]{PASSWORDS, TABLE, ENCRYPTED});

        // Second part for decoding using brute force
        startTime = System.currentTimeMillis();
        Brute.main(new String[]{ENCRYPTED, TABLE, B_DECRYPTED});
        endTime = System.currentTimeMillis();
        System.out.println("Time taken for brute force decoding: " + (endTime - startTime) + " milliseconds");

        // Third part for decoding using regular decryption
        startTime = System.currentTimeMillis();
        Decrypt.main(new String[]{ENCRYPTED, TABLE, DECRYPTED});
        endTime = System.currentTimeMillis();
        System.out.println("Time taken for regular decoding: " + (endTime - startTime) + " milliseconds");
    }
}
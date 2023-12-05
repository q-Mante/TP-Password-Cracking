import run.Brute;
import run.Encrypt;
import run.Generator;

public class Main {

    private static final String C = "4";
    private static final String PASSWORDS = "passwords.txt";
    private static final String TABLE = "rand4.txt";
    private static final String ENCRYPTED = "encrypted.txt";
    private static final String DECRYPTED = "decrypted.txt";

    public static void main(String[] args) {

        // First part for generating and encrypting
        //Generator.main(new String[]{C, TABLE});
        Encrypt.main(new String[]{PASSWORDS, TABLE, ENCRYPTED});

        // Second part for decoding the encrypted key
        //Brute.main(new String[]{ENCRYPTED, TABLE, DECRYPTED});
    }
}
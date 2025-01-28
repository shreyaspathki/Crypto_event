import java.util.Scanner;

public class AffineCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Affine Cipher Implementation");
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();
        
        // Using common values for a and b
        // 'a' must be coprime with 26
        int a = 5;
        int b = 8;
        
        String encrypted = encrypt(text, a, b);
        System.out.println("Encrypted text: " + encrypted);
        
        String decrypted = decrypt(encrypted, a, b);
        System.out.println("Decrypted text: " + decrypted);
        
        scanner.close();
    }
    
    // Encryption function: E(x) = (ax + b) mod 26
    public static String encrypt(String plaintext, int a, int b) {
        StringBuilder result = new StringBuilder();
        plaintext = plaintext.toUpperCase();
        
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) {
                int x = ch - 'A';
                int encrypted = (a * x + b) % 26;
                if (encrypted < 0) {
                    encrypted += 26;
                }
                result.append((char) (encrypted + 'A'));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    
    // Decryption function: D(x) = (a^(-1)(x - b)) mod 26
    public static String decrypt(String ciphertext, int a, int b) {
        StringBuilder result = new StringBuilder();
        ciphertext = ciphertext.toUpperCase();
        
        // Calculate multiplicative inverse of a
        int aInverse = multiplicativeInverse(a, 26);
        
        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            if (Character.isLetter(ch)) {
                int x = ch - 'A';
                int decrypted = (aInverse * (x - b)) % 26;
                if (decrypted < 0) {
                    decrypted += 26;
                }
                result.append((char) (decrypted + 'A'));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    
    // Calculate multiplicative inverse using Extended Euclidean Algorithm
    private static int multiplicativeInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;
        
        if (m == 1) {
            return 0;
        }
        
        while (a > 1) {
            int q = a / m;
            int t = m;
            
            m = a % m;
            a = t;
            t = y;
            
            y = x - q * y;
            x = t;
        }
        
        if (x < 0) {
            x += m0;
        }
        
        return x;
    }
} 
import java.util.Scanner;

public class VigenereCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Vigenere Cipher Implementation");
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();
        
        String encrypted = encrypt(text, key);
        System.out.println("Encrypted text: " + encrypted);
        
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted text: " + decrypted);
        
        scanner.close();
    }
    
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        
        int keyLength = key.length();
        int keyIndex = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                // Convert letters to numbers (A=0, B=1, etc.)
                int textNum = ch - 'A';
                int keyNum = key.charAt(keyIndex % keyLength) - 'A';
                
                // Apply Vigenère encryption formula
                int encryptedNum = (textNum + keyNum) % 26;
                
                // Convert back to letter
                result.append((char) (encryptedNum + 'A'));
                keyIndex++;
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    
    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        
        int keyLength = key.length();
        int keyIndex = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                // Convert letters to numbers (A=0, B=1, etc.)
                int textNum = ch - 'A';
                int keyNum = key.charAt(keyIndex % keyLength) - 'A';
                
                // Apply Vigenère decryption formula
                int decryptedNum = (textNum - keyNum + 26) % 26;
                
                // Convert back to letter
                result.append((char) (decryptedNum + 'A'));
                keyIndex++;
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
} 
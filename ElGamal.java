import java.util.Scanner;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("El-Gamal Cryptosystem Implementation");
            
            // Step 1: Key Generation
            int p, e1, d;
            
            while (true) {
                try {
                    System.out.print("Enter prime number (p): ");
                    p = Integer.parseInt(scanner.nextLine());
                    if (p <= 0) {
                        System.out.println("Please enter a positive number.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            
            while (true) {
                try {
                    System.out.print("Enter primitive root (e1): ");
                    e1 = Integer.parseInt(scanner.nextLine());
                    if (e1 <= 0 || e1 >= p) {
                        System.out.println("Please enter a number between 0 and " + p);
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            
            while (true) {
                try {
                    System.out.print("Enter private key (d < p-1): ");
                    d = Integer.parseInt(scanner.nextLine());
                    if (d <= 0 || d >= p-1) {
                        System.out.println("Please enter a number between 0 and " + (p-1));
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            
            // Calculate public key e2 = e1^d mod p
            int e2 = modPow(e1, d, p);
            
            System.out.println("\nPublic Key (e1, e2, p): (" + e1 + ", " + e2 + ", " + p + ")");
            System.out.println("Private Key (d): " + d);
            
            // Step 2: Encryption
            int M;
            while (true) {
                try {
                    System.out.print("\nEnter message to encrypt (number < " + p + "): ");
                    String input = scanner.nextLine();
                    
                    // If input is a character, convert it to ASCII value
                    if (input.length() == 1) {
                        M = (int) input.charAt(0);
                    } else {
                        M = Integer.parseInt(input);
                    }
                    
                    if (M >= p) {
                        System.out.println("Message must be less than " + p);
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number or single character.");
                }
            }
            
            // Generate random r
            SecureRandom random = new SecureRandom();
            int r = random.nextInt(p - 2) + 1; // 1 <= r <= p-2
            
            // Calculate c1 = e1^r mod p
            int c1 = modPow(e1, r, p);
            
            // Calculate c2 = M * e2^r mod p
            int c2 = (M * modPow(e2, r, p)) % p;
            
            System.out.println("\nEncryption:");
            System.out.println("Message (M): " + M + (M < 128 ? " (ASCII '" + (char)M + "')" : ""));
            System.out.println("Random r: " + r);
            System.out.println("Ciphertext (c1, c2): (" + c1 + ", " + c2 + ")");
            
            // Step 3: Decryption
            // M = c2 * (c1^d)^(-1) mod p
            int s = modPow(c1, d, p);
            int sInverse = modInverse(s, p);
            int decryptedM = (c2 * sInverse) % p;
            
            System.out.println("\nDecryption:");
            System.out.println("Decrypted message: " + decryptedM + 
                             (decryptedM < 128 ? " (ASCII '" + (char)decryptedM + "')" : ""));
            
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    // Modular exponentiation: calculates (base^exponent) % modulus
    private static int modPow(int base, int exponent, int modulus) {
        if (modulus == 1) return 0;
        
        int result = 1;
        base = base % modulus;
        
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent >>= 1;
        }
        return result;
    }
    
    // Calculate modular multiplicative inverse using Extended Euclidean Algorithm
    private static int modInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;
        
        if (m == 1) return 0;
        
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
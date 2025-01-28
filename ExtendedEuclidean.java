import java.util.Scanner;

public class ExtendedEuclidean {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Extended Euclidean Algorithm Implementation");
        System.out.print("Enter first number (a): ");
        int a = scanner.nextInt();
        
        System.out.print("Enter second number (m): ");
        int m = scanner.nextInt();
        
        // Store results of Extended Euclidean Algorithm
        int[] result = extendedEuclidean(a, m);
        int gcd = result[0];
        int x = result[1];
        int y = result[2];
        
        System.out.println("\nResults:");
        System.out.println("GCD(" + a + ", " + m + ") = " + gcd);
        System.out.println("Coefficients (x, y): " + x + ", " + y);
        System.out.println("Equation: " + a + "(" + x + ") + " + m + "(" + y + ") = " + gcd);
        
        // Calculate and print multiplicative inverse if it exists
        if (gcd == 1) {
            int multiplicativeInverse = (x % m + m) % m;
            System.out.println("\nMultiplicative Inverse of " + a + " modulo " + m + " is: " + multiplicativeInverse);
            System.out.println("Verification: (" + a + " × " + multiplicativeInverse + ") mod " + m + " = " + 
                             ((a * multiplicativeInverse) % m));
        } else {
            System.out.println("\nMultiplicative inverse doesn't exist since GCD(" + a + ", " + m + ") ≠ 1");
        }
        
        scanner.close();
    }
    
    // Returns array containing GCD and coefficients x, y
    public static int[] extendedEuclidean(int a, int b) {
        if (b == 0) {
            return new int[] { a, 1, 0 };
        }
        
        int[] values = extendedEuclidean(b, a % b);
        int gcd = values[0];
        int x1 = values[1];
        int y1 = values[2];
        
        int x = y1;
        int y = x1 - (a / b) * y1;
        
        System.out.println("Step: " + a + " = " + b + " × " + (a/b) + " + " + (a%b));
        System.out.println("     x = " + x + ", y = " + y);
        
        return new int[] { gcd, x, y };
    }
} 
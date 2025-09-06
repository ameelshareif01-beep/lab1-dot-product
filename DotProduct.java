import java.util.Random;

public class DotProduct {
    public static void main(String[] args) {
        int n = 5;  // length of arrays
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];

        Random rand = new Random();

        // Fill arrays a and b with random numbers
        for (int i = 0; i < n; i++) {
            a[i] = rand.nextInt(10); // random int 0–9
            b[i] = rand.nextInt(10);
            c[i] = a[i] * b[i];
        }

        // Print arrays
        System.out.print("Array a: ");
        printArray(a);

        System.out.print("Array b: ");
        printArray(b);

        System.out.print("Array c (a[i] * b[i]): ");
        printArray(c);
    }

    // Helper method to print arrays
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}

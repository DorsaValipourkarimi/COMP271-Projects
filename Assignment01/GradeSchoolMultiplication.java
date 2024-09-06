import java.util.Arrays;

public class GradeSchoolMultiplication {

    private static final int DEFAULT_BASE = 10;

    public static int[] multiply(int[] x, int[] y, int base) {
        int m = x.length;
        int n = y.length;
        // create a result array with the maximixed possible number of digits (m+n)
        int[] result = new int[m + n];

        // Perform multiplication digit by digit starting from last digits
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int multi = x[i] * y[j];
                int sum = multi + result[i + j + 1]; // adding the result of the multipication to previous result and
                                                     // storing it in a new variable "sum"

                // Now, sum maybe be double digit as the result of the multipication done
                // A double digit cannot be placed on the same index (each digit should be put
                // in its own place value)
                // Therefore, sum is broken down to current digit and carry-on digit
                result[i + j + 1] = sum % base; // place the current digit
                result[i + j] += sum / base; // Carry-over the carry-one digit value to the index before
            }
        }

        // Then, leading zeros before the actual result of the multipication should be
        // removed,
        // considering that length of the result array was maximized
        int startIndex = 0;
        while (startIndex < result.length - 1 && result[startIndex] == 0) {
            startIndex++;
        }
        // Create a final result array with the corrected length of the result
        int[] finalResult = new int[result.length - startIndex];
        for (int i = 0; i < finalResult.length; i++) {
            finalResult[i] = result[startIndex + i];
        }

        // return the final result
        return finalResult;
    }
    // method multiply

    public static int[] multiply(final int[] x, final int[] y) {
        return multiply(x, y, DEFAULT_BASE);
    } // method multiply

    public static void main(String[] args) {
        int[] x = { 1, 2, 3, 4 };
        int[] y = { 5, 6, 7, 8 };
        int base = 10;
        int[] product = multiply(x, y, base);
        System.out.println(Arrays.toString(product));
    }

}
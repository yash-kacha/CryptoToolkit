import java.util.Scanner;

public class XorCipher {

    // XOR Cipher: Encrypts/Decrypts the input text
    public static String xorCipher(String text, char key) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            result.append((char) (ch ^ key)); // XOR operation
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for the text
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        // Get user input for the key
        System.out.print("Enter the key (a single character): ");
        char key = scanner.next().charAt(0);

        // Process the text (encryption or decryption)
        String output = xorCipher(text, key);

        // Output the result
        System.out.println("Output Text: " + output);

        scanner.close();
    }
}

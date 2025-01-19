import javax.crypto.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.Scanner;

public class testjpeg {

    private static SecretKey generateKey(String keyString) throws Exception {
        byte[] keyBytes = keyString.getBytes();
        SecureRandom secureRandom = new SecureRandom(keyBytes);
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, secureRandom); // AES key size is 128 bits
        return keyGen.generateKey();
    }

    public static void encryptFile(String inputFile, String outputFile, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
        System.out.println("File encrypted successfully: " + outputFile);
    }

    public static void decryptFile(String inputFile, String outputFile, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        System.out.println("File decrypted successfully: " + outputFile);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get file path from the user
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        // Get a custom key from the user
        System.out.print("Enter a custom key (minimum 16 characters): ");
        String keyString = scanner.nextLine();

        if (keyString.length() < 16) {
            System.err.println("Key must be at least 16 characters long!");
            return;
        }

        try {
            // Generate a SecretKey from the custom key string
            SecretKey secretKey = generateKey(keyString);

            // Ask user for the operation
            System.out.println("Choose an operation: ");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Encrypt the file
                String encryptedFile = filePath + "_e";
                encryptFile(filePath, encryptedFile, secretKey);
            } else if (choice == 2) {
                // Decrypt the file
                String decryptedFile = filePath + "_d";
                decryptFile(filePath, decryptedFile, secretKey);
            } else {
                System.out.println("Invalid choice! Exiting...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

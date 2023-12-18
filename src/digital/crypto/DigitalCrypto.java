/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package digital.crypto;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class DigitalCrypto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose:");
        System.out.println("1. AES");
        System.out.println("2. RSA");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                performAESEncryptionDecryption(scanner);
                break;
            case 2:
                performRSAEncryptionDecryption(scanner);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void performAESEncryptionDecryption(Scanner scanner) {
        try {
            CryptoUtilImpl cryptoUtil = new CryptoUtilImpl();
            

            System.out.println("Choose:");
            System.out.println("1. Generate a random key");
            System.out.println("2. Enter a key manually");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    SecretKey secretKey = cryptoUtil.generateSecretKey();
                    performEncDecAES(scanner, cryptoUtil, secretKey);
                    break;
                case 2:
                     System.out.println("\n");
                  System.out.println("Enter a hexadecimal key:");
    scanner.nextLine(); // Consume the newline character
    String hexKey = scanner.nextLine();

    // Check for "0x" prefix and remove if present
    if (hexKey.toLowerCase().startsWith("0x")) {
        hexKey = hexKey.substring(2);
    }

    // Check if the key length is correct
    if (hexKey.length() != 32) {
         System.out.println("\n");
        System.out.println("Invalid key length. Key should be 16 bytes for AES-128 in hexadecimal.");
        return;
    }

    SecretKey manualKey = cryptoUtil.generateSecretKey(hexKey);
    performEncDecAES(scanner, cryptoUtil, manualKey);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void performRSAEncryptionDecryption(Scanner scanner) {
        try {
            CryptoUtilImpl cryptoUtil = new CryptoUtilImpl();
           // Get public key from certificate
        PublicKey publicKey = cryptoUtil.publicKeyFromCertificate("myCertificate.cert");
        // Get private key from JKS
        PrivateKey privateKey = cryptoUtil.privateKeyFromJKS("security.jks", "123456", "security");
       performEncryptDecryptRSA(scanner, cryptoUtil, publicKey, privateKey);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    private static void performEncryptDecryptRSA(Scanner scanner, CryptoUtilImpl cryptoUtil, PublicKey publicKey, PrivateKey privateKey) {
    try {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file for encryption and decryption");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File input = fileChooser.getSelectedFile();
            byte[] fileContent = Files.readAllBytes(Paths.get(input.getAbsolutePath()));
            // Encrypt the file content
            String encryptedData = cryptoUtil.encryptRSA(fileContent, publicKey);
            // Sign the encrypted data
            String signature = cryptoUtil.rsaSign(encryptedData.getBytes(), privateKey);
             System.out.println("\n");
            System.out.println("Encrypted Result:");
            System.out.println(encryptedData);
              boolean signatureValid = cryptoUtil.rsaSignVerify(encryptedData + "_.._" + signature, publicKey);
               if (signatureValid) {
                // Decrypt the file content
                 System.out.println("\n");
                  System.out.println("Signature OK,File signed and verified");
                   System.out.println("\n");
                  System.out.println("Signature:"+signature);  
                byte[] decryptedData = cryptoUtil.decryptRSA(encryptedData, privateKey);
                   System.out.println("\n");
                System.out.println("Decrypted Result:");
                System.out.println(new String(decryptedData));     
                 System.out.println("\n");
            System.out.println("Do you want to save? yes | no");
            scanner.nextLine(); 
            String saveChoice = scanner.nextLine().toLowerCase();
            
            if (saveChoice.equals("yes")) {
               String output="Encrypted Result:"+"\n"+encryptedData+"\n"+"Decrypted Result:"+"\n"+new String(decryptedData)+"\n"+"Signature:"+"\n"+signature;
                saveToFile(output);
            }
              } else {
                System.out.println("Signature verification failed. Data may have been tampered with.");
            }
        } else {
            System.out.println("You didn't select a file");
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private static void performEncDecAES(Scanner scanner, CryptoUtilImpl cryptoUtil, SecretKey key) {
        try {
          
            JFileChooser fileChooser = new JFileChooser();
      
            fileChooser.setDialogTitle("Choose a file for encryption");
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File input = fileChooser.getSelectedFile();
                byte[] fileContent = Files.readAllBytes(Paths.get(input.getAbsolutePath()));

                // Encrypt the file content
                String encryptedData = "";
                   encryptedData = cryptoUtil.encryptAES(fileContent, key);
         // encryptedData = cryptoUtil.encryptAESToHex(fileContent, key);
                System.out.println("\n");
                System.out.println("Encrypted Result:");
                System.out.println(encryptedData);
                byte[] decryptedData = {};
                 decryptedData = cryptoUtil.decryptAES(new String(encryptedData),  key);
        //  decryptedData = cryptoUtil.decryptAESFromHex(new String(encryptedData),  key);
                System.out.println("\n");
                System.out.println("Decrypted Result:");
                System.out.println(new String(decryptedData));
                
                String output ="Encrypted Result:"+"\n"+new String(encryptedData)+"\n"+"Decrypted Result:"+"\n"+new String(decryptedData);

                // Ask if the user wants to save the encrypted result
                 System.out.println("\n");
                System.out.println("Do you want to save? yes | no");
                scanner.nextLine(); // Consume the newline character
                String saveChoice = scanner.nextLine().toLowerCase();

                if (saveChoice.equals("yes")) {
                    saveToFile(output);
                                     
                }
            } else {
                System.out.println("You didn't select a file");
            }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    

private static void saveToFile(String data) {
    try {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Ensure the file has a .txt extension
            String filePath = file.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".txt")) {
                file = new File(filePath + ".txt");
            }

            Files.write(Paths.get(file.getAbsolutePath()), data.getBytes());
            System.out.println("File saved successfully");
        } else {
            System.out.println("You didn't select a save location");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

package mainProgram;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/*TODO:
 * Hex to Byte
 * store hex
 * grab hex
 */

/**
 * This example program shows how AES encryption and decryption can be done in Java.
 * Please note that secret key and encrypted text is unreadable binary and hence 
 * in the following program we display it in hexadecimal format of the underlying bytes.
 * @author Jayson
 */
public class Encryption {
	
    /**
     * 1. Generate a plain text for encryption
     * 2. Get a secret key (printed in hexadecimal form). In actual use this must 
     * by encrypted and kept safe. The same key is required for decryption.
     * 3. 
     */
    public static void main(String[] args) throws Exception {
    	//password to be encrypted
        String password = "oh hi #RestroomLogs";
        
        //getting encryption key, should be stored to later use
        SecretKey secKey = getSecretEncryptionKey(); //save key on pi in a encrypted .txt
        
        //converting encryption key to a string
        String encodedKey = Base64.getEncoder().encodeToString(secKey.getEncoded());
        
        //converting encryption string to encryption key
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
        
        //ENCRYPT
        byte[] cipherText = encryptText(password, secKey);
        for(byte item : cipherText) {
        	System.out.print(item + ", ");
        	
        }
        System.out.println("");
        //DECRYPT
        String decryptedText = decryptText(cipherText, originalKey);
        
        //printing
        System.out.println("Original Text:" + password);
        System.out.println("Key ToString:" + encodedKey);
        System.out.println("AES Key (Hex Form):"+bytesToHex(secKey.getEncoded()));
        System.out.println("Encrypted Text (Hex Form):"+bytesToHex(cipherText));
        System.out.println("Descrypted Text:"+decryptedText);
        
        System.out.println(Base64.getEncoder().encodeToString(originalKey.getEncoded()));
    }
    
    /**
     * gets the AES encryption key. In your actual programs, this should be safely
     * stored.
     * @return
     * @throws Exception 
     */
    public static SecretKey getSecretEncryptionKey() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        return secKey;
    }
    
    /**
     * Encrypts plainText in AES using the secret key
     * @param plainText
     * @param secKey
     * @return
     * @throws Exception 
     */
    public static byte[] encryptText(String plainText,SecretKey secKey) throws Exception{
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return byteCipherText;
    }
    
    /**
     * Decrypts encrypted byte array using the key used for encryption.
     * @param byteCipherText
     * @param secKey
     * @return
     * @throws Exception 
     */
    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }
    
    /**
     * Convert a binary byte array into readable hex form
     * @param hash
     * @return 
     */
    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }

}
package mainProgram;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Encryption2 {
	protected final static String SecretKey = "aS+OPrzigQSjtwD5f7pBCg==";
	protected final static String EncryptedPassword = "";
	protected final static String PasswordToEncrypt = "hello World";
	
	public static void main(String[] args) {
		
	}
	
	public static void runEncrpt() {
		byte[] cipherText = encryptText(PasswordToEncrypt, rebuildSecKey());
	}
	
	public static void runDecrypt() throws Exception {
		//rebuild Secret Key
		SecretKey orginal Key = rebuildSecKey
		
        //rebuilding EncryptedPassword
        int numOfIndexRequired;
        for(int i = 0, k = 0; i < EncryptedPassword.length(); i++) {
        	if(EncryptedPassword.substring(i, i+1) != ",") {
        		numOfIndexRequired++;
        	}
        }
        byte[] EncrptedBytePassword = new byte[numOfIndexRequired];
        
        String indexContent = "";
        for(int i = 0, k = 0; i < EncryptedPassword.length(); i++) {
        	if((EncryptedPassword.substring(i, i+1) != ",") && (EncryptedPassword.substring(i, i+1) != " ")) {
        		indexContent += EncryptedPassword.substring(i, i+1);
        	}
        	else if (EncryptedPassword.substring(i, i+1) == ","){
        		byte[] indexContentByte = indexContent.getBytes()
        		EncrptedBytePassword[k] = indexContentByte;
        		k++;
        		indexContent = "";
        		i++;
        	}
        	else {
        		//do nothing, is a space
        	}
        }
        
        //DECRYPT
        String decryptedText = decryptText(EncrptedBytePassword, originalKey);
        
        System.out.print("password: " + decryptedText);
	 }
	
	public static SecretKey rebuildSecKey() {
		//rebuilding SecretKey
        byte[] decodedKey = Base64.getDecoder().decode(SecretKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		return originalKey; 
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

package mainProgram;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import interfaces.IDebug;


/*
javax.crypto.BadPaddingException: Given final block not properly padded
	at com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:991)
	at com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:847)
	at com.sun.crypto.provider.AESCipher.engineDoFinal(AESCipher.java:446)
	at javax.crypto.Cipher.doFinal(Cipher.java:2165)
	at mainProgram.Encryption2.decryptText(Encryption2.java:114)
	at mainProgram.Encryption2.Decrypt(Encryption2.java:69)
	at mainProgram.Encryption2.main(Encryption2.java:26)

Maybe trying converting secret key into a String byte[] with commas instead of String (Is this possible??)
 */





public class Encryption2 implements IDebug{
	protected static String PasswordToEncrypt = "oh hi #RestroomLogs";
	
	private static String SecretKey = "MeSQkwFHsvJdvnWcUh3w7A=="; //for testing
	//private static String SecretKey = getSecretKey();
	private static String EncryptedPassword = "-93, -69, -105, -68, 40, -26, 52, -23, -17, 50, -20, 86, -72, 8, -52, -17, -61, 25, 74, -61, -23, 33, -96, 50, -12, 124, 69, -71, 121, -21, -43, -39, "; 
	
	
	public static void main(String[] args) {
		try {
			System.out.println(Decrypt(EncryptedPassword));
			//System.out.println(Encrypt(PasswordToEncrypt));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//PUBLIC METHODS
	/**
	 * Encrypts a String using a preset Secret Key
	 * @return a String with the Encrypted password
	 * @throws Exception
	 */
	public static String Encrypt(String password) throws Exception{
		//Rebuild Secret Key
		SecretKey SecKey = rebuildSecKey(SecretKey);
		
		System.out.println(secretKeyToString(SecKey));
		
		//ENCRYPT
		byte[] cipherText = encryptText(PasswordToEncrypt, SecKey);
		
		//To String
		EncryptedPassword = encryptedPasswordToString(cipherText);
		
		//Printing
		//System.out.println(EncryptedPassword);
		return EncryptedPassword;
	}
	
	/**
	 * Decrypts a Encrypted String password using a preset Secret Key
	 * @return a String with the decrypted password
	 * @throws Exception
	 */
	public static String Decrypt(String password) throws Exception {
		//rebuild Secret Key
		SecretKey SecKey = rebuildSecKey(SecretKey);
		
        //rebuilding EncryptedPassword
        byte[] encryptedPassword = rebuildEncryptedPassword(EncryptedPassword);
        
        //DECRYPT
        String decryptedText = decryptText(encryptedPassword, SecKey);
        
        //Printing
        //System.out.print("password: " + decryptedText);
        return decryptedText;
	}
	
//GRAB INFO
	public static String getSecretKey() {
		
		//TODO ACCESS DB
		
		String SecKey = "TODO: GET KEY FROM DB";
		return SecKey;
	}
	
	
//ENCRYPT AND DECRYPT
	
    /**
     * Encrypts plainText in AES using the secret key
     * @param plainText
     * @param secKey
     * @return byte[] with the encrypted password. Can be use with {@link #Encrypt(String)}}
     * @throws Exception 
     */
    private static byte[] encryptText(String plainText,SecretKey secKey) throws Exception{
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
     * @return A String with the decrypted password
     * @throws Exception 
     */
    private static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        //byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        
        System.out.println();
        
        byte[] test = aesCipher.doFinal();
        		
        
        return new String(test);
    }
    
    
    
//REBUILD METHODS
    /**
     * Rebuild Secret Key from a String.<br><br>
     * <strong>String format example:</strong><br>
     * jy5VCX7PlS2AqAcLe+Lg5g==
     * @return Secret Key for Encrytion or Decryption
     * @see #decryptText(byte[], SecretKey)
     * @see #encryptText(String, SecretKey)
     */
    private static SecretKey rebuildSecKey(String SecKey) {
		//rebuilding SecretKey
        byte[] decodedKey = Base64.getDecoder().decode(SecKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		return originalKey; 
	}
	
    /**
     * Rebuild Encrypted Password from a String.<br><br>
     * <strong>String format example:</strong><br>
     * 91, 118, 6, -96, -47, -29, 19, -26, 103, 92, -108, -5, -81, 62, -16, -119, 
     * @return Encrypted Password for Decrypting 
     * @see #decryptText(byte[], SecretKey)
     */
    private static byte[] rebuildEncryptedPassword(String password) {
		int numOfIndexRequired = 0;
        for(int i = 0; i < password.length(); i++) {
        	if((password.substring(i, i+1).equals(",")) && (password.substring(i).matches(".*[0-9].*"))) {
        		numOfIndexRequired++;	
        	}
        }
        numOfIndexRequired++;
        
        byte[] EncrptedBytePassword = new byte[numOfIndexRequired];
        
        String indexContent = "";
        for(int i = 0, k = 0; i < password.length(); i++) {
        	if((password.substring(i, i+1) != ",") && (password.substring(i, i+1) != " ")) {
        		indexContent += password.substring(i, i+1);
        	}
        	else if (password.substring(i, i+1) == ","){
        		byte indexContentByte = Byte.parseByte(indexContent);
        		EncrptedBytePassword[k] = indexContentByte;
        		k++;
        		indexContent = "";
        		i++;
        	}
        	else {
        		//do nothing, is a space
        	}
        }
        
        return EncrptedBytePassword;
	}
	
	
	
//TO STRING METHODS
	/**
	 * Converts a byte[] to a String<br>
	 * Used to convert the Encrypted Password to a String with commas<br>
	 * 
	 * <strong>Example:</strong>
     * 91, 118, 6, -96, -47, -29, 19, -26, 103, 92, -108, -5, -81, 62, -16, -119, 
	 * @param encryptedByte a byte[] with the encrypted password
	 * @return byte[] as a string
	 */
	private static String encryptedPasswordToString(byte[] encryptedByte) {
		String decryptedPassword = "";
		
		for(byte item : encryptedByte) {
			decryptedPassword += item + ", ";
		}
		return decryptedPassword;
	}
	
	/**
	 * Converts a SecretKey to a String
	 * @param SecKey SecretKey
	 * @return String wtih SecretKey
	 */
	private static String secretKeyToString(SecretKey SecKey) {
		String encodedKey = Base64.getEncoder().encodeToString(SecKey.getEncoded());
		
		return encodedKey;
	}
	
	
	
//GEN NEW SEC KEY
	/**
	 * <strong>Deprecated because this should not ever be used unless only SecretKey was compromised.</strong>
	 * <br><br>
	 * Create a new SecretKey
	 * @return a SecretKey in <strong>128</strong> bit
	 */
	@Deprecated
	private static SecretKey getNewSecretKey(){
        KeyGenerator generator = null;
		try {
			generator = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        return secKey;
	}
}

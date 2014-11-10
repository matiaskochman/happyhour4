package com.happyhour.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;

public class TextEncryptionUtils {
	private static final String ALGORITHM_TYPE = "AES";
    private static final byte[] keyValue = new byte[] { '0', '0', 'm', 'a', 't', 'i', 'a','s', '0', '0', '0','0', '0', '0', '0', '0' };	

	public static String encrypt(String Data) throws Exception {
		
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM_TYPE);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        /*
        */
        return encryptedValue;
    }
	
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM_TYPE);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }	
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM_TYPE);
        return key;
    }	
}

package com.happyhour.utils;

import org.junit.Assert;
import org.junit.Test;

public class TextEncryptionUtilsTest {

	@Test
	public void testEncryptionDecryption(){
		String source = "hola pianola 2014";
		
		try {
			String encrypted = TextEncryptionUtils.encrypt(source);
			
			String decryptedResult = TextEncryptionUtils.decrypt(encrypted);
			
			Assert.assertTrue(source.equals(decryptedResult));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

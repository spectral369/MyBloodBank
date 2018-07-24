package com.bloodbank.database;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Decryption {

	public static String decrypt(String encryptedText, SecretKey secretKey, Cipher cipher) throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
}
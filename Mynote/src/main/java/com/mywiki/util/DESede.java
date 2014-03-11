package com.mywiki.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * 3DES CBC加解密 
 */
public class DESede {
	private static String iv = "12345678";
	/**
	 * 加密以后可能会出现空格、换行、加号字符串
	 * @param encryptString
	 * @param encryptKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptDES(String encryptString, String encryptKey) throws Exception {

		IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DESede");
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
	 
		return Base64.encodeByte(encryptedData);
	}
	/**
	 * 解密字符串
	 * @param decryptString
	 * @param decryptKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptDES(String decryptString, String decryptKey) throws Exception {
		byte[] byteMi = Base64.decodeByte(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());

		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DESede");
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
	 
		return new String(decryptedData);
	}
}
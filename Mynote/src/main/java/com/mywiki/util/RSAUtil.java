package com.mywiki.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RSAUtil {
	
	/** 
     * sha-1加密 
     * @param inputText  要加密的内容 
     * @return
     */  
	public static String encrypt(String inputText) {  
        if (inputText == null || "".equals(inputText.trim())) {  
            return null;
        }
        String encryptText = null;  
        try {  
            MessageDigest m = MessageDigest.getInstance("sha-1");  
            byte s[] =m.digest(inputText.getBytes("UTF8"));  
            return hex(s);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return encryptText;  
    }  
  
    // 返回十六进制字符串  
    private static String hex(byte[] arr) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < arr.length; ++i) {  
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));  
        }  
        return sb.toString();  
    }
}

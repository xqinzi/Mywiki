package com.mywiki.util;

public class Base64 {

	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	@SuppressWarnings("restriction")
	public static String encode(String str) {
		return new sun.misc.BASE64Encoder().encode(str.getBytes());
	}
	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	@SuppressWarnings("restriction")
	public static String encodeByte(byte[] str) {
		return new sun.misc.BASE64Encoder().encode(str);
	}

	/**
	 * 解码
	 * @param str
	 * @return string
	 */
	@SuppressWarnings("restriction")
	public static String decode(String str) {
		if (str == null)
			return null;
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			byte[] btr = decoder.decodeBuffer(str);
			return new String(btr);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 解码
	 * @param str
	 * @return string
	 */
	@SuppressWarnings("restriction")
	public static byte[] decodeByte(String str) {
		if (str == null)
			return null;
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			byte[] btr = decoder.decodeBuffer(str);
			return btr;
		} catch (Exception e) {
			return null;
		}
	}
}

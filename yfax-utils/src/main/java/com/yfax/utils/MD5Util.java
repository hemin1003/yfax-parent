package com.yfax.utils;

import java.security.MessageDigest;
import java.text.ParseException;

public class MD5Util {
	// 十六进制下数字到字符的映射数组
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/** 对字符串进行MD5加密 */
	public static String encodeByMD5(String originString) {
		if (originString != null && originString != "") {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toLowerCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为十六进制字符串
	 * 
	 * @param 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	public static void main(String[] args) throws ParseException {
		String pasdfsdfd = "184143588test11891棋牌151602060210M4dFboQJ3oo7TLhiY5toKuJiy0snWpMl";
		String parameter = "1841425851003test11891棋牌151602060210M4dFboQJ3oo7TLhiY5toKuJiy0snWpMl";
//		System.out.println(DateUtil.stampToDate(1501646898));
//		System.out.println(encodeByMD5(parameter).toLowerCase().equals("6d14631d36e9607a8e49a8f909c07e9e"));
		//6d14631d36e9607a8e49a8f909c07e9e
		System.out.println(encodeByMD5(parameter));
	}
}

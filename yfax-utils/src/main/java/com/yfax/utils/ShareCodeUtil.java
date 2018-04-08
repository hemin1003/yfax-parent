package com.yfax.utils;

import java.util.Random;

public class ShareCodeUtil {
	
	/** 自定义进制(0,1没有加入,容易与o,l混淆) */
	private static final char[] r = new char[] { 'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p',
			'5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h' };
	
	/** (不能与自定义进制有重复) */
	private static final char b = 'o';

	/** 进制长度 */
	private static final int binLen = r.length;

	/** 序列最小长度 */
	private static final int s = 8;

	/**
	 * 根据ID生成六位随机码
	 * @param id ID
	 * @return 随机码
	 */
	public static String toSerialCode(long id) {
		char[] buf = new char[32];
		int charPos = 32;

		while ((id / binLen) > 0) {
			int ind = (int) (id % binLen);
			buf[--charPos] = r[ind];
			id /= binLen;
		}
		buf[--charPos] = r[(int) (id % binLen)];
		String str = new String(buf, charPos, (32 - charPos));
		// 不够长度的自动随机补全
		if (str.length() < s) {
			StringBuilder sb = new StringBuilder();
			sb.append(b);
			Random rnd = new Random();
			for (int i = 1; i < s - str.length(); i++) {
				sb.append(r[rnd.nextInt(binLen)]);
			}
			str += sb.toString();
		}
		return str.toUpperCase();
	}

	public static long codeToId(String code) {
		char chs[] = code.toCharArray();
		long res = 0L;
		for (int i = 0; i < chs.length; i++) {
			int ind = 0;
			for (int j = 0; j < binLen; j++) {
				if (chs[i] == r[j]) {
					ind = j;
					break;
				}
			}
			if (chs[i] == b) {
				break;
			}
			if (i > 0) {
				res = res * binLen + ind;
			} else {
				res = ind;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(toSerialCode(123123L));
		System.out.println("oN9UryuC0Y01aQt0jKxZXbfe658w");
	}
}

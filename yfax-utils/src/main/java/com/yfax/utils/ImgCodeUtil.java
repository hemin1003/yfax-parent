package com.yfax.utils;

import java.util.Random;

public class ImgCodeUtil {

	/**
	 * 四位随机数
	 */
	public static String getImgCode() {
		Random rd = new Random(); // 创建随机对象
		String n = ""; // 保存随机数
		int rdGet; // 取得随机数
		do {
			if (rd.nextInt() % 2 == 1) {
				rdGet = Math.abs(rd.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
			} else {
				rdGet = Math.abs(rd.nextInt()) % 26 + 97; // 产生97到122的随机数(a-z的键位值)
			}
			char num1 = (char) rdGet; // int转换char
			String dd = Character.toString(num1);
			n += dd;
		} while (n.length() < 4);// 设定长度，此处假定长度小于4
		return n.toUpperCase();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getImgCode());
		}
	}
}

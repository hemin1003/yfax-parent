package com.yfax.utils;

/**
 * 生成随机字符串
 */
public class UUID {
	
	/**
	 * 获取随机字符串，默认30位长度
	 * @return 获取随机字符串 至少30位
	 */
	public static final String getUUID() {
		return getUUID(0);
	}

	/**
	 * 获取随机字符串 至少30位
	 * @param i 随机字符串长度
	 * @return 获取随机字符串 至少30位
	 */
	public static final String getUUID(int i) {
		if (i < 30) {
			i = 30;
		}
		StringBuffer UUID = new StringBuffer();
		UUID.append(Long.toHexString(System.nanoTime()));
		UUID.append(Long.toHexString(new java.util.Date().getTime()));
		while (UUID.length() < i) {
			String random = String.valueOf(Math.random());
			try {
				UUID.append(Long.toHexString(Long.parseLong(random.substring(2))));
			} catch (NumberFormatException e) {
			}
		}
		return UUID.substring(0, i).toUpperCase();
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}

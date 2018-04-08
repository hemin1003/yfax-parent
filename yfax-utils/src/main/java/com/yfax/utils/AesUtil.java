package com.yfax.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

public class AesUtil {
	
	// 加密
	@SuppressWarnings("unused")
	public static String encrypt(String sSrc, String sKey, String ivParameter) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(sKey.getBytes("utf-8"), "AES"), iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		return encodeBytes(encrypted);// 此处使用encodeBytes做转码。
	}

	// 解密
	public static String decrypt(String sSrc, String sKey, String ivParameter) throws Exception {

		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = decodeBytes(sSrc);// 先用decodeBytes解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}

	public static String encodeBytes(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
			sb.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
		}
		return sb.toString();
	}

	public static byte[] decodeBytes(String str) {
		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < str.length(); i += 2) {
			char c = str.charAt(i);
			bytes[i / 2] = (byte) ((c - 'a') << 4);
			c = str.charAt(i + 1);
			bytes[i / 2] += (c - 'a');
		}
		return bytes;
	}
	
	public static void main(String[] args) throws Exception {
		JSONObject json = new JSONObject();
		json.put("task_code", "A_0001");
		json.put("task_key", "0000000001");
		json.put("token","C40B2E29BE71E1484338DC2D5B41C139252A03D8D37A17DE35D1B3FAEAA42997");
		String sig = AesUtil.encrypt(json.toString(), userid, userpwd);
		System.out.println("加密：" + sig);
		
		String sigDec = AesUtil.decrypt(sig, userid, userpwd);
		System.out.println("解密：" + sigDec);
	}
	
	
	public static final String userid = "QxCsdDfelQZlMMx1w";
	public static final String userpwd = "XjwPsmcSR2fNg5SD";
	
	/**
	 * 回调加密参数
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static String getTySig(String token) throws Exception {
		JSONObject json = new JSONObject();
		json.put("task_code", "A_0001");
		json.put("task_key", "0000000001");
		json.put("token", token);
		return AesUtil.encrypt(json.toString(), userid, userpwd);
	}
}

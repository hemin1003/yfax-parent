package com.yfax.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class BillNumUtils {

	/**
	 * 生成唯一订单号
	 * 规则：会员id+"M"+格式化到秒的时间+"R"+六位随机数
	 * @author Wwx
	 *
	 */
	public static String getBillNum(Integer memberId){
		if(memberId == null)memberId = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String SNDate = sdf.format(new Date());
		String orderCode = memberId+"M"+SNDate+"R"+Math.round(Math.random()*1000000);
		return orderCode;
	}
	
	/**
	 * 生成唯一订单号
	 * 规则：四位随机数+"M"+格式化到秒的时间+"R"+六位随机数
	 * @author Wwx
	 *
	 */
	public static String getBillCode() {
        Random rd = new Random(); // 创建随机对象
        String n = "";            //保存随机数
        int rdGet;                // 取得随机数
        do {
            if (rd.nextInt() % 2 == 1) {
            	rdGet = Math.abs(rd.nextInt()) % 10 + 48;  // 产生48到57的随机数(0-9的键位值)
	        }else{
	            rdGet = Math.abs(rd.nextInt()) % 26 + 97;  // 产生97到122的随机数(a-z的键位值)
	        }
	        char num1 = (char) rdGet;                      //int转换char
	        String dd = Character.toString(num1);
	        n += dd;
        } while (n.length() < 8);// 设定长度，此处假定长度小于8
		String r1= (((Math.random()*9+1)*100000)+"").substring(0, 6);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String SNDate = sdf.format(new Date());
		String orderCode = r1 + "M" + SNDate + "R" + n.toUpperCase();
        return orderCode;
	}
	
//	/**
//	 * 根据电话号码后四位生成订单号
//	 * @param mobilePhone
//	 * @return
//	 */
//	public synchronized static String getBillCode(String mobilePhone){
//		String billCode = null;
//		String last4Phone = "0000";
//		if(VerificationUtils.isValid(mobilePhone, VerificationUtils.MOBILE)){
//			last4Phone = mobilePhone.substring(mobilePhone.length()-4, mobilePhone.length());
//		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
//		String SNDate = sdf.format(new Date());
//		billCode = "T" + SNDate  + last4Phone;
//		return billCode;
//	}
	
//	/**
//	 * 根据电话号码后四位生成订单号
//	 * @param mobilePhone
//	 * @return
//	 */
//	public synchronized static String getBillCode(String billPrefix, String mobilePhone){
//		String billCode = null;
//		String last4Phone = "0000";
//		if(StringUtil.isEmpty(billPrefix)){
//			billPrefix = "DD";
//		}
//		if(VerificationUtils.isValid(mobilePhone, VerificationUtils.MOBILE)){
//			last4Phone = mobilePhone.substring(mobilePhone.length()-4, mobilePhone.length());
//		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
//		String SNDate = sdf.format(new Date());
//		billCode = billPrefix + SNDate  + last4Phone ;
//		return billCode;
//	}
	
//	/**
//	 * 根据电话号码后四位生成订单号
//	 * @param mobilePhone
//	 * @return
//	 */
//	public synchronized static String getBillCode(String billPrefix, String mobilePhone, int index){
//		String billCode = null;
//		String last4Phone = "0000";
//		if(StringUtil.isEmpty(billPrefix)){
//			billPrefix = "DD";
//		}
//		if(VerificationUtils.isValid(mobilePhone, VerificationUtils.MOBILE)){
//			last4Phone = mobilePhone.substring(mobilePhone.length()-4, mobilePhone.length());
//		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
//		String SNDate = sdf.format(new Date());
//		billCode = billPrefix + SNDate + index + last4Phone ;
//		return billCode;
//	}
	
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++){
			String billCode = BillNumUtils.getBillCode();
			System.out.println(billCode);
//			long nowtime = new java.util.Date().getTime(); 
//			System.out.println(nowtime);
//			System.out.println(BillNumUtils.getBillCode());
//			System.out.println(getBillCode(null,"13502922979",i));
		}
	}
}

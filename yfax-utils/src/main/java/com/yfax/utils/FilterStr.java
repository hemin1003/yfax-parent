package com.yfax.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterStr {
	/**
	 * @Description : 过滤出数字
	 * @return
	 */
	public static String filterNumber(String number) {
		number = number.replaceAll("[^(0-9)]", "");
		return number;
	}

	/**
	 * @Description : 过滤出字母
	 * @return
	 */
	public static String filterAlphabet(String alph) {
		alph = alph.replaceAll("[^(A-Za-z)]", "");
		return alph;
	}

	/**
	 * @Description : 过滤出中文
	 */
	public static String filterChinese(String chin) {
		chin = chin.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
		return chin;
	}

	/**
	 * @Description : 过滤出字母、数字和中文
	 */
	public static String filterAll(String character) {
		if(!StrUtil.null2Str(character).equals("")) {
			character = character.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
		}
		return character;
	}

	/**
	 * @Title : main
	 * @Type : FilterStr
	 * @date : 2014年3月12日 下午7:18:22
	 * @Description :
	 * @param args
	 */
	public static void main(String[] args) {
//		/**
//		 * 声明字符串you
//		 */
//		String you = "^&^&^you123$%$%你好";
//		/**
//		 * 调用过滤出数字的方法
//		 */
//		you = filterNumber(you);
//		/**
//		 * 打印结果
//		 */
//		System.out.println("过滤出数字：" + you);
//
//		/**
//		 * 声明字符串hai
//		 */
//		String hai = "￥%……4556ahihdjsadhj$%$%你好吗wewewe";
//		/**
//		 * 调用过滤出字母的方法
//		 */
//		hai = filterAlphabet(hai);
//		/**
//		 * 打印结果
//		 */
//		System.out.println("过滤出字母：" + hai);
//
//		/**
//		 * 声明字符串dong
//		 */
//		String dong = "$%$%$张三34584yuojk李四@#￥#%%￥……%&";
//		/**
//		 * 调用过滤出中文的方法
//		 */
//		dong = filterChinese(dong);
//		/**
//		 * 打印结果
//		 */
//		System.out.println("过滤出中文：" + dong);
//
//		/**
//		 * 声明字符串str
//		 */
//		String str = "$%$%$张三34584yuojk李四@#￥#%%￥……%&";
//		/**
//		 * 调用过滤出字母、数字和中文的方法
//		 */
//		str = filter(str);
//		/**
//		 * 打印结果
//		 */
//		System.out.println("过滤出字母、数字和中文：" + str);
		
		System.out.println(filterEmoji("[{\"a\":\"🍃微凉工具箱🍃\",\"b\":\"com.weilianggjx\"},{\"a\":\"葫芦侠3楼\",\"b\":\"com.huati\"},{\"a\":\"QQ破解神器\",\"b\":\"com.tenct\"},{\"a\":\"网站编程器 \",\"b\":\"com.wzbcq\"},{\"a\":\"高德地图\",\"b\":\"com.autonavi.minimap\"},{\"a\":\"妙健康\",\"b\":\"cn.funtalk.miao\"},{\"a\":\"四川移动掌厅\",\"b\":\"com.sunrise.scmbhc\"},{\"a\":\"同城艳遇\",\"b\":\"com.legion.rodimus\"},{\"a\":\"西瓜视频 \",\"b\":\"com.ss.android.article.video\"},{\"a\":\"手机淘宝\",\"b\":\"com.taobao.taobao\"},{\"a\":\"Color\",\"b\":\"com.picsart.draw\"},{\"a\":\"微云\",\"b\":\"com.qq.qcloud\"},{\"a\":\"视频电话\",\"b\":\"com.mi.vtalk\"},{\"a\":\"IP Tools\",\"b\":\"com.ddm.iptools\"},{\"a\":\"支付宝\",\"b\":\"com.eg.android.AlipayGphone\"},{\"a\":\"一元赚\",\"b\":\"com.qixiao.yyz\"}]"));
	}
	
	/**
	 * 去掉表情符号的字符串
	 * */
	public static String filterEmoji(String str) {
	    if (StrUtil.null2Str(str).equals("") || str.trim().isEmpty()) {
	        return str;
	    }
	    String pattern = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
	    String reStr = "";
	    Pattern emoji = Pattern.compile(pattern);
	    Matcher emojiMatcher = emoji.matcher(str);
	    str = emojiMatcher.replaceAll(reStr);
	    return str;
	}
}

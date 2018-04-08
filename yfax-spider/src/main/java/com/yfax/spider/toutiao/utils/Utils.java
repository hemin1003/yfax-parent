package com.yfax.spider.toutiao.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {
	
	//ES服务器
	public static final String IP = "127.0.0.1";
	
	public static String CHARSET = "UTF-8";
	
	public static long getStamp() {
		return System.currentTimeMillis() / 1000;
	}
	
	/**
	 * 将时间戳转换为日期
	 */
	public static Date dateToStamp(long timeStamp) {
		Timestamp t = new Timestamp(getStamp()*1000);  
        Date d = new Date(t.getTime());
        return d;
	}

	/**
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(long timeStamp) {
		String result = null;
		Date date = new Date(timeStamp * 1000);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = sd.format(date);
		return result;
	}
	
	/**
	 * 根据tag获取爬虫模板信息
	 * @param tagTmp
	 * @return
	 */
	public static Map<String, String> getSpirderInfo(String tagTmp) {
		Map<String, String> map = new HashMap<>();
		if(tagTmp.equals("news_recommend")) {			//推荐
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99340000");	//自定义生成的，1
			map.put("spiderInfoId", "AV8OnikR8-owkfOcKCO3");
			
		}else if(tagTmp.equals("news_hot")) {			//热点
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350000");	//自定义生成的，2
			map.put("spiderInfoId", "AV8LRW-d8-owkfOcKCN8");
			
		}else if(tagTmp.equals("news_society")) {		//社会
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350001");	//自定义生成的，3
			map.put("spiderInfoId", "AV7_qd95b6Sm4fkv7DKG");
			
		}else if(tagTmp.equals("news_entertainment")) {	//娱乐
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350002");	//自定义生成的，4
			map.put("spiderInfoId", "AV8LC59X8-owkfOcKCNb");
			
		}else if(tagTmp.equals("news_tech")) {			//科技
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350004");	//自定义生成的，5
			map.put("spiderInfoId", "AV8LJoWR8-owkfOcKCNo");
			
		}else if(tagTmp.equals("news_car")) {			//汽车
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350005");	//自定义生成的，6
			map.put("spiderInfoId", "AV8LKgls8-owkfOcKCNq");
			
		}else if(tagTmp.equals("news_sports")) {			//体育
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350006");	//自定义生成的，7
			map.put("spiderInfoId", "AV8LOwe38-owkfOcKCNz");
			
		}else if(tagTmp.equals("news_finance")) {		//财经
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99360006");	//自定义生成的，8
			map.put("spiderInfoId", "AV8On5dd8-owkfOcKCO4");
			
		}else if(tagTmp.equals("news_fashion")) {		//时尚
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350007");	//自定义生成的，9
			map.put("spiderInfoId", "AV8LPOL38-owkfOcKCN1");
			
		}else if(tagTmp.equals("news_game")) {			//游戏
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350008");	//自定义生成的，10
			map.put("spiderInfoId", "AV8LQQWc8-owkfOcKCN6");
			
		}else if(tagTmp.equals("news_baby")) {			//育儿
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350009");	//自定义生成的，11
			map.put("spiderInfoId", "AV8LQmrd8-owkfOcKCN7");
			
		}else if(tagTmp.equals("news_travel")) {			//旅游
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350010");	//自定义生成的，12
			map.put("spiderInfoId", "AV8LTYr78-owkfOcKCOC");
			
		}else if(tagTmp.equals("news_discovery")) {		//探索
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350011");	//自定义生成的，13
			map.put("spiderInfoId", "AV8LTwe-8-owkfOcKCOD");
			
		}else if(tagTmp.equals("news_regimen")) {		//养生
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350012");	//自定义生成的，14
			map.put("spiderInfoId", "AV8LUB3P8-owkfOcKCOE");
			
		}else if(tagTmp.equals("news_history")) {		//历史
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350013");	//自定义生成的，15
			map.put("spiderInfoId", "AV8LUVHg8-owkfOcKCOF");
			
		}else if(tagTmp.equals("news_food")) {			//美食
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350014");	//自定义生成的，16
			map.put("spiderInfoId", "AV8LUk4T8-owkfOcKCOG");
			
		}else if(tagTmp.equals("news_story")) {			//故事
			map.put("spiderUUID", "aaaafc39-6ff7-4a95-8024-b85d99350015");	//自定义生成的，17
			map.put("spiderInfoId", "AV8LU0J38-owkfOcKCOH");
		}
		return map;
	}
	
	public static final String HTML_PREFIX = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "    <head>\n"
			+ "        <meta name=\"apple-mobile-web-app-capable\" content=\"yes\"></meta>\n"
			+ "        <meta http-equiv=\"Cache-Control\" name=\"no-store\"></meta>\n"
			+ "        <meta content=\"telephone=no\" name=\"format-detection\"></meta>\n"
			+ "        <meta content=\"email=no\" name=\"format-detection\"></meta>\n"
			+ "        <meta charset=\"utf-8\"></meta>\n"
			+ "        <meta name=\"viewport\" content=\"target-densitydpi=device-dpi,width=decive-width,initial-scale=1,maximum-scale=1\"></meta>\n"
			+ "        <title></title>\n" + "        <style>\n" + "            html,body {\n"
			+ "                width: 100%;\n" + "                margin: 0;\n" + "                padding: 0;\n"
			+ "            }\n" + "            html, body,* {\n" + "                font-family: \"微软雅黑\";\n"
			+ "                line-height: 24px;\n" + "                font-size: 16px;\n" + "            }\n"
			+ "            h1 {\n" + "                font-size: 24px;\n" + "            }\n" + "            body {\n"
			+ "            }\n" + "            .text-center {\n" + "                text-align: center;\n"
			+ "            }\n" + "            ul,li {\n" + "                list-style: none;\n" + "            }\n"
			+ "            ul {\n" + "                margin: 0;\n" + "                padding: 0 10px;\n"
			+ "            }\n" + "            img { height: auto; width: auto\\9; width:100%; }\n"
			+ "        </style>\n" + "    </head>\n" + "<body> ";

	public static final String HTML_SUFFIX = " </body></html>";
}	

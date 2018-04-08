package com.yfax.spider.toutiao.model;

/**
 * 今日头条，爬虫新闻分类tag
 * 
 * @author Minbo
 */
public class Tags {

//	 public static String TAG = "news_hot"; //热点3 ###
	// public static String TAG = "news_society"; //社会4 ###
	// public static String TAG = "news_entertainment"; //娱乐5 ###
	// public static String TAG = "news_military"; //军事6 ###
	// public static String TAG = "news_tech"; //科技7 ###
	// public static String TAG = "news_car"; //汽车8 ###

	// public static String TAG = "news_sports"; //体育9 ###
//	 public static String TAG = "news_finance"; //财经10 ###
	// public static String TAG = "news_fashion"; //时尚12 ###
	// public static String TAG = "news_game"; //游戏13 ###
	// public static String TAG = "news_baby"; //育儿14 ###
	// public static String TAG = "news_travel"; //旅游15 ###
//	public static String TAG = "news_discovery"; // 探索16 ###

	// public static String TAG = "news_regimen"; //养生17 ###
	// public static String TAG = "news_history"; //历史18 ###
	 public static String TAG = "news_food"; //美食19 ###
	// public static String TAG = "news_story"; //故事20 ###

	public static String[] tagList = new String[] { "news_hot", "news_society", "news_entertainment", "news_military",
			"news_tech", "news_car", "news_sports", "news_finance", "news_fashion", "news_game", "news_baby",
			"news_travel", "news_discovery", "news_regimen", "news_history", "news_food", "news_story" };
	
	public static String[] acList = new String[] {"A115B9AD6B2127E", "A1E539BD7B612EC", "A16509DD8B81328", "A1A5C9DD3B91394", 
			"A1E529AD4B613D5", "A1B509ADFB914D0", "A1D5F9FD5B416EE", "A185C9BDCB01765", "A115396E90C184B", "A145E92EF0D18FC"};
	
	public static String[] cpList = new String[] {"59DB6142679E9E1", "59DBE172DE5C2E1", "59DBE133D258EE1", "59DBF153E9D42E1", 
			"59DBB1C3AD65BE1", "59DB21C40DF05E1", "59DB91861EFEFE1", "59DB611726558E1", "59E0217884DB5E1", "59E011989F3CDE1"};
	
	public static String[] cookieList = new String[] {"UM_distinctid=15d4e6b236b8c1-0212d4d6465428-30677808-13c680-15d4e6b236ccc1; uuid=\"w:6eeae48e765542f8816b14bd1a8803f2\"; sso_login_status=0; tt_webid=6464335891330024973; tt_track_id=b4300ddb07cd24b2e473b6ebf33f8fbc; bottom-banner-hide-status=true; __utma=252651093.1935118179.1505121696.1505198559.1507601588.2; __utmc=252651093; __utmz=252651093.1505198559.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=24953151.1935118179.1505121696.1507684915.1507705584.3; __utmc=24953151; __utmz=24953151.1507705584.3.3.utmcsr=47.95.29.119:8080|utmccn=(referral)|utmcmd=referral|utmcct=/java-spider/panel/commons/showWebpageById; W2atIF=1; tt_webid=6464861854974871054; csrftoken=490b809baf80308e71e827d7f578d2fb; _ba=BA0.2-20170912-51225-LXO6Nly1dtclVPwkr0pO; _ga=GA1.2.1935118179.1505121696; _gid=GA1.2.1055165657.1507858509", 
			"utm_medium=webview; utm_source=kuhua_xwz_api; utm_campaign=open; uuid=\"w:39100e39375249d9a69bb476f39ffdc2\"; UM_distinctid=15f0a8033198ff-0425f38acdcb17-31637e01-fa000-15f0a80331a8c1; tt_webid=62762014654; W2atIF=1; __tasessionId=tlmvhambs1507859203210; bottom-banner-hide-status=true; csrftoken=5565330af8e51ceb9d539183ae6d63c6; _ga=GA1.2.2105530003.1507859201; _gid=GA1.2.526548683.1507859201; _ba=BA0.2-20171013-51225-DteSguKoyqBjXGBhnQ0x",
			"UM_distinctid=15e278e78fe17-0d4578d7854104-3a3e5c06-1fa400-15e278e78ff202; tt_webid=62332632968; uuid=\"w:bc59f7c5a3b94a70ab8f695c21708ee1\"; W2atIF=1; _ba=BA0.2-20171018-51225-hk90jaOiL6Kjrfsde3J0; _ga=GA1.2.762905492.1503902200; _gid=GA1.2.1578153490.1508306119; csrftoken=0220f99197298d27b516e75b478358a2; __tasessionId=a1qxs480f1508306120412",
			"UM_distinctid=15f1372fc041d9-0da4886364ae92-474a0521-100200-15f1372fc062de; uuid=\"w:1030f73b65794666929db97806d9ae2b\"; tt_webid=6476208638322312718; W2atIF=1; _ba=BA0.2-20171013-51225-smgGdXvNroIjZ8Hx8QyV; _ga=GA1.2.1578093401.1507859836; _gid=GA1.2.1203217688.1507859836; csrftoken=a2fb9e2d0f5be9e28c324426049b6499; __tasessionId=awexu6m7j1507859839296"};
	
	public static String[] userAgentList = new String[] {"Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1", 
			"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1", 
			"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Mobile Safari/537.36",
			"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Mobile Safari/537.36"};
	

//	strArray[0] = "https://m.toutiao.com/list/?tag=news_society&ac=wap&count=20&format=json_raw&as=A115B9AD6B2127E&cp=59DB6142679E9E1&min_behot_time=0";
//	strArray[1] = "https://m.toutiao.com/list/?tag=news_society&ac=wap&count=20&format=json_raw&as=A1E539BD7B612EC&cp=59DBE172DE5C2E1&max_behot_time=1507528455";
//	strArray[2] = "https://m.toutiao.com/list/?tag=news_society&ac=wap&count=20&format=json_raw&as=A16509DD8B81328&cp=59DBE133D258EE1&max_behot_time=1507527729";
//	strArray[3] = "https://m.toutiao.com/list/?tag=news_society&ac=wap&count=20&format=json_raw&as=A1A5C9DD3B91394&cp=59DBF153E9D42E1&max_behot_time=1507526829";
//	strArray[4] = "https://m.toutiao.com/list/?tag=news_society&ac=wap&count=20&format=json_raw&as=A1E529AD4B613D5&cp=59DBB1C3AD65BE1&max_behot_time=1507525928";
//	strArray[5] = "https://m.toutiao.com/list/?tag=news_society&ac=wap&count=20&format=json_raw&as=A1B509ADFB914D0&cp=59DB21C40DF05E1&max_behot_time=1507521128";
//	strArray[6] = "https://m.toutiao.com/list/?tag=news_society&ac=wap&count=20&format=json_raw&as=A1D5F9FD5B416EE&cp=59DB91861EFEFE1&max_behot_time=1507518728";
//	strArray[7] = "https://m.toutiao.com/list/?tag=news_society&ac=wap&count=20&format=json_raw&as=A185C9BDCB01765&cp=59DB611726558E1&max_behot_time=1507516328";

	public static String getTagName(String tag) {
		switch (tag) {
			case "news_hot":
				return "热点";
			case "news_society":
				return "社会";
			case "news_entertainment":
				return "娱乐";
			case "news_military":
				return "军事";
			case "news_tech":
				return "科技";
			case "news_car":
				return "汽车";
			case "news_sports":
				return "体育";
			case "news_finance":
				return "财经";
			case "news_fashion":
				return "时尚";
			case "news_game":
				return "游戏";
			case "news_baby":
				return "育儿";
			case "news_travel":
				return "旅游";
			case "news_discovery":
				return "探索";
			case "news_regimen":
				return "养生";
			case "news_history":
				return "历史";
			case "news_food":
				return "美食";
			case "news_story":
				return "故事";
			default:
				return "未知";
		}
	}
}

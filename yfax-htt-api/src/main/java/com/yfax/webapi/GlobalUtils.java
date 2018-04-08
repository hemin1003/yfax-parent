package com.yfax.webapi;

import java.util.HashMap;
import java.util.Map;

import com.yfax.utils.MD5Util;
import com.yfax.utils.RandomEngine;

public class GlobalUtils {
	/**
	 * APP放行IMEI
	 */
	public static final String APP_FREE_IMEI = "zM1smVcq4Py";
	/**
	 * 统一访问前缀：/yfax-htt-api
	 */
	public static final String URL = "/yfax-htt-api";
	/**
	 * api接口：/api/htt
	 */
	public static final String PROJECT_HTT = "/api/htt";
	/**
	 * 格式化
	 */
	public static final String DECIMAL_FORMAT = "#0.000";
	/**
	 * 苹果项目名称调用前缀2
	 */
	public static final String IOS_PRO_NAME = "xxx";
//	/**
//	 * 随机奖励
//	 */
//	public static final int[] RANDOM_GOLD = new int[] {20,21,22,23,24,25,26,27,28,29,30};
//	/**
//	 * 首次有效阅读
//	 */
//	public static final int AWARD_TYPE_FIRSTREAD = 1;
	/**
	 * 分步邀请奖励
	 */
	public static final int AWARD_TYPE_INVITE = 2;
	/**
	 * 每日签到
	 */
	public static final int AWARD_TYPE_DAYLY = 3;
	/**
	 * 阅读奖励
	 */
	public static final int AWARD_TYPE_READ = 4;
	/**
	 * 徒弟贡献奖励
	 */
	public static final int AWARD_TYPE_STUDENT = 5;
	/**
	 * 首次分享奖励
	 */
	public static final int AWARD_TYPE_FIRSTSHARE = 6;
	/**
	 * 首次邀请奖励
	 */
	public static final int AWARD_TYPE_FIRSTINVITE = 7;
	/**
	 * 注册奖励
	 */
	public static final int AWARD_TYPE_REGISTER = 9;
	/**
	 * 时段奖励
	 */
	public static final int AWARD_TYPE_TIME = 10;
	/**
	 * 阅读累积时长奖励
	 */
	public static final int AWARD_TYPE_DAILYREADTIME = 11;
	/**
	 * 视频累积时长奖励
	 */
	public static final int AWARD_TYPE_DAILYVIDEOTIME = 12;
	/**
	 * 观看视频奖励
	 */
	public static final int AWARD_TYPE_VIDEO = 13;
	/**
	 * 完成任务奖励
	 */
	public static final int AWARD_TYPE_TASK = 14;
	/**
	 * 金币兑换
	 */
	public static final int AWARD_TYPE_GOLD = 15;
	//16是系统补偿
	/**
	 * 游戏任务奖励
	 */
	public static final int AWARD_TYPE_GAME = 17;
	/**
	 * 抽奖奖励
	 */
	public static final int AWARD_TYPE_DRAW = 18;
	/**
	 * 文章分享点击奖励
	 */
	public static final int AWARD_TYPE_ARTICLE = 19;
	/**
	 * 零钱类型-零钱自动兑换
	 */
	public static final int BALANCE_TYPE_REDEEM = 1;
	/**
	 * 零钱类型-提现申请
	 */
	public static final int BALANCE_TYPE_WITHDRAW = 2;
	/**
	 * 零钱类型-注册红包奖励
	 */
	public static final int BALANCE_TYPE_REGAWARD = 3;
	/**
	 * 验证码-短信模板ID
	 */
	public static final String SMS_TEMPLATE_ID = "xxx";
	/**
	 * 用户拉活-短信模板ID
	 */
	public static final String SMS_USER_TEMPLATE_ID = "xxx";
	/**
	 * 短信APP ID
	 */
	public static final String SMS_APP_ID = "xxx";
	
	/**
	 * 时限任务-看视频赚金币
	 */
	public static final String TASK_TYPE_VIDEO = "t002";
	
	/**
	 * 时限任务-领红包赚金币
	 */
	public static final String TASK_TYPE_MONEY = "t001";
	
	/**
	 * 取零钱类型名
	 * @param balanceType
	 * @return
	 */
	public static String getBalanceTypeName(int balanceType) {
		switch (balanceType) {
			case BALANCE_TYPE_REDEEM:
				return "零钱自动兑换";
			case BALANCE_TYPE_WITHDRAW:
				return "提现申请";
			case BALANCE_TYPE_REGAWARD:
				return "注册红包奖励";
		}
		return "未知奖励类型";
	}
	
	/**
	 * 取奖励类型名
	 * @param award_type
	 * @return
	 */
	public static String getAwardTypeName(int awardType) {
		switch (awardType) {
//			case AWARD_TYPE_FIRSTREAD:
//				return "首次有效阅读";
			case AWARD_TYPE_INVITE:
				return "分步邀请奖励";
			case AWARD_TYPE_DAYLY:
				return "每日签到";
			case AWARD_TYPE_READ:
				return "阅读奖励";
			case AWARD_TYPE_STUDENT:
				return "徒弟贡献奖励";
			case AWARD_TYPE_FIRSTSHARE:
				return "首次分享奖励";
			case AWARD_TYPE_FIRSTINVITE:
				return "首次邀请奖励";
			case AWARD_TYPE_REGISTER:
				return "注册奖励";
			case AWARD_TYPE_TIME:
				return "时段奖励";
			case AWARD_TYPE_DAILYREADTIME:
				return "阅读累积时长奖励";
			case AWARD_TYPE_DAILYVIDEOTIME:
				return "视频累积时长奖励";
			case AWARD_TYPE_VIDEO:
				return "观看视频奖励";
			case AWARD_TYPE_TASK:
				return "完成任务奖励";
			case AWARD_TYPE_GOLD:
				return "金币兑换";
			case AWARD_TYPE_GAME:
				return "游戏任务奖励";
			case AWARD_TYPE_DRAW:
				return "抽奖奖励";
			case AWARD_TYPE_ARTICLE:
				return "文章分享点击奖励";
				
		}
		return "未知奖励类型";
	}
	
//	/**
//	 * 根据金币，返回随机金币值
//	 * @param goldRange
//	 */
//	public static int getRanomGold(String goldRange) {
//		String[] strings = goldRange.split("#");
//		int start = Integer.valueOf(strings[0]);
//		int end = Integer.valueOf(strings[1]);
//		int count = 0;
//		for (int i = start; i <= end; i++) {
//			count++;
//		}
//		int[] golds = new int[count];
//		for (int i = 0; i < golds.length; i++) {
//			golds[i] = start + i;
//		}
//		return golds[new Random().nextInt(golds.length)];
//	}
	
	/**
	 * 根据金币配置，返回概率金币值算法
	 * @param goldRange
	 */
	public static int getProbabilityGold(String goldRange) {
		String[] strings = goldRange.split("#");
		//构建概率值
		Map<String, Integer> keyChanceMap = new HashMap<String, Integer>();
		for (int i = 0; i < strings.length; i++) {
			String[] values = strings[i].split("&");
			keyChanceMap.put(values[0], Integer.valueOf(values[1]));
		}
//		//50出现概率70%，100出现概率20%，200出现概率10%
//		keyChanceMap.put("50", 70);
//		keyChanceMap.put("100", 20);
//		keyChanceMap.put("200", 10);
		String value = RandomEngine.getRandomValue(keyChanceMap);
		return Integer.valueOf(value);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int value = getProbabilityGold("50&70#100&20#200&10");
			System.out.println(value);
		}
	}
	
	/**
	 * 信鸽推送ACCESS_ID
	 */
	public final static long XG_ACCESS_ID = 0L;
	
	/**
	 * 信鸽推送SECRET_KEY
	 */
	public final static String XG_SECRET_KEY = "xxx";
	
	/**
	 * API接口调用验证秘钥/在线网站生成一个秘钥
	 */
	public final static String API_KEY = "xxx";
	
	/**
	 * 验证参数请求合法性
	 * @param request
	 * @return
	 */
	public static boolean verifyParams(String paramsStr, String uId) {
		paramsStr = paramsStr + "&key=" + API_KEY;
		//小写比较
    		String md5Result = MD5Util.encodeByMD5(paramsStr).toLowerCase();		
    		//md5校对结果
    		return md5Result.equals(uId.toLowerCase());
	}
	
//	/**
//	 * 获得连续签到阀值的金币值
//	 * @param gold 随机金币
//	 * @param userGold 金币余额
//	 * @param checkInConfig 金币阀值配置
//	 * @return
//	 */
//	public static int getContinueCheckInGold(int gold, int userGold, String checkInConfig) {
//		//格式化，保留三位小数，四舍五入
//		DecimalFormat dFormat = new DecimalFormat("#0"); 
//		int result = 0;
//		JSONArray jsonArray = JSONArray.fromObject(checkInConfig);
//		for (int i = 0; i < jsonArray.size(); i++) {
//			JSONObject jsonObj = jsonArray.getJSONObject(i);
//			String alpha = jsonObj.get("alpha").toString();
//			String goldRange = jsonObj.get("goldRange").toString();
//			String[] strings = goldRange.split("#");
//			int start = Integer.valueOf(strings[0]);
//			int end = Integer.valueOf(strings[1]);
//			if(userGold>=start && userGold<end) {
//				result = Integer.valueOf(dFormat.format(gold * Double.valueOf(alpha)));
//				break;
//			}
//		}
//		return result;
//	}
}

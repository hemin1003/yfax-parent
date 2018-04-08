package com.yfax.webapi.htt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.JsonResult;
import com.yfax.utils.ResultCode;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.dao.HolidayActivityRankingDao;
import com.yfax.webapi.htt.vo.HolidayActivityRankingVo;

/**
 * 寒假活动-邀请排名
 * 
 * @author Minbo
 */
@Service
public class HolidayActivityRankingService {

	protected static Logger logger = LoggerFactory.getLogger(HolidayActivityRankingService.class);

	@Autowired
	private HolidayActivityRankingDao dao;
	@Autowired
	private AwardHisService awardHisService;

	public List<HolidayActivityRankingVo> selectRanking(String date) {
		return this.dao.selectRanking(date);
	}
	
	public HolidayActivityRankingVo selectRankingByPhoneNum(Map<String, Object> map) {
		return this.dao.selectRankingByPhoneNum(map);
	}
	
	/**
	 * 根据排名查询排行榜上一名
	 * @param map
	 * @return
	 */
	public HolidayActivityRankingVo selectLastRanking(Map<String, Object> map) {
		return this.dao.selectLastRanking(map);
	}

	/**
	 * 个人抽奖结果
	 * 
	 * @param phoneNum
	 * @return
	 */
	public JsonResult getHolidayActivityLuckyDraw(String phoneNum) {
		// 获取用户当天已抽奖的次数
		String luckyDrawCounts = this.awardHisService.holidayActivitySelectLuckyDraw(phoneNum);
		logger.info("phoneNum=" + phoneNum + "，当前已抽奖次数luckyDrawCounts=" + luckyDrawCounts);
		// 返回结果
		Map<String, Object> result = new HashMap<>();
		//每天允许抽奖次数
		Integer allowLuckDrawTimes = 5 ;
		// 如果当天抽奖的次数大于5次
		if (Integer.parseInt(luckyDrawCounts) >= allowLuckDrawTimes) {
			// 返回-1，表示用户已超出当天抽奖次数
			result.put("resultCode", -1);
			// 返回当天已抽奖的次数
			result.put("remainLuckDrawTimes",allowLuckDrawTimes - Integer.parseInt(luckyDrawCounts));
			result.put("gold", 0);
			return new JsonResult(ResultCode.SUCCESS, result);
		} else {
			HashMap<String, Object> goldResult = this.getGold();
			Integer gold = (Integer) goldResult.get("gold");
			//获得奖励
			this.awardHisService.addAwardHis(phoneNum, gold
					, GlobalUtils.AWARD_TYPE_DRAW, null, null, null, null, null, null, null, null, null, null, null);

			// 返回抽奖结果
			result.put("resultCode", goldResult.get("position"));
			result.put("remainLuckDrawTimes",allowLuckDrawTimes - (Integer.parseInt(luckyDrawCounts) + 1));
			result.put("gold", gold);
			return new JsonResult(ResultCode.SUCCESS, result);
		}
	}

	/**
	 * 获得随机奖励算法
	 * 
	 * @return
	 */
	public HashMap<String, Object> getGold() {
		// 概率数组
		Integer[] probabilityList = { 0, 20, 1, 0, 25, 15, 0, 25, 25, 0, 15, 20 };
		// 金币数组
		Integer[] goldList = { 0, 80, 10000, 0, 40, 200, 0, 100, 150, 0, 300, 120 };
		// 抽奖结果数组
		ArrayList<Integer> luckDrawlist = new ArrayList<>();
		for (int i = 0; i < probabilityList.length; i++) {
			for (int j = 0; j < probabilityList[i]; j++) {
				luckDrawlist.add(i);
			}
		}
		// 生成随机索引
		Random random = new Random();
		Integer max = luckDrawlist.size();
		Integer index = random.nextInt(max);

		// 用户抽奖所获得的金币数
		Integer gold = goldList[luckDrawlist.get(index)];

		HashMap<String, Object> map = new HashMap<>();
		map.put("gold", gold);
		map.put("index", index);
		map.put("position", luckDrawlist.get(index));
		return map;
	}

//	public static void main(String[] args) {
//		HolidayActivityRankingService service = new HolidayActivityRankingService();
//
//		HashSet<Object> set = new HashSet<>();
//		HashMap<String, Object> result = new HashMap<>();
//		for (int i = 0; i < 100000; i++) {
//			HashMap<String, Object> map = service.getGold();
//			Integer gold = (Integer) map.get("gold");
//			set.add(gold);
//			for (Iterator it = set.iterator(); it.hasNext();) {
//				String goldTmp = gold + "";
//				if (result.get(goldTmp) != null) {
//					result.put(goldTmp, Integer.valueOf(result.get(goldTmp).toString()) + 1);
//					break;
//				} else {
//					result.put(goldTmp, 1);
//					break;
//				}
//			}
//		}
//		System.out.println(result.toString());
//	}
}

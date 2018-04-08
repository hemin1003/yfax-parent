package com.yfax.webapi.htt.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.DateUtil;
import com.yfax.webapi.htt.dao.AppConfigDao;
import com.yfax.webapi.htt.dao.AwardCheckinHisDao;
import com.yfax.webapi.htt.vo.AppConfigVo;
import com.yfax.webapi.htt.vo.AwardCheckinHisVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 只记录用户签到-金币奖励记录
 * @author Minbo
 */
@Service
public class AwardCheckinHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(AwardCheckinHisService.class);
	
	@Autowired
	private  AwardCheckinHisDao awardCheckinHisDao;
	@Autowired
	private AppConfigDao appConfigDao;
	
	public boolean addAwardCheckinHis(AwardCheckinHisVo awardCheckinHisVo) {
		try {
			return this.awardCheckinHisDao.insertAwardCheckinHis(awardCheckinHisVo);
		} catch (Exception e) {
			logger.error("新增签到记录异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public List<AwardCheckinHisVo> selectAllAwardCheckinHisList(String phoneNum){
		return this.awardCheckinHisDao.selectAllAwardCheckinHisList(phoneNum);
	}
	
	public List<AwardCheckinHisVo> getAwardCheckinHisList(String phoneNum){
		return this.awardCheckinHisDao.selectAwardCheckinHisList(phoneNum);
	}
	
	public Long getAwardHisCheckInAward(Map<String, Object> map) {
		return this.awardCheckinHisDao.selectAwardHisCheckInAward(map);
	}
	
	/**
	 * 根据奖励记录获取七日签到数据
	 * @param phoneNum
	 * @return
	 */
	public Map<Object, Object> getContinueCheckList(String phoneNum) {
		//取七日金币配置数据
		AppConfigVo appConfigVo = this.appConfigDao.selectAppConfig();
		String params = appConfigVo.getParams().toString();
		
		Map<Object, Object> mapDay = new HashMap<>();
		Map<Object, Object> checkInInfoMap = new HashMap<>();
		JSONObject jsonObject = JSONObject.fromObject(params);
		Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
		for (Entry<String, Object> entry : mapJson.entrySet()) {
			if(entry.getKey().toString().equals("checkInConfig")) {
				JSONArray jsonArray = JSONArray.fromObject(entry.getValue().toString());
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					mapDay.put(jsonObj.get("day"), jsonObj.get("gold"));
				}
				break;
			}
		}
		
		//签到历史数据
		List<AwardCheckinHisVo> list = this.awardCheckinHisDao.selectAwardCheckinHisList(phoneNum);
		Map<Object, Object> resultMap = new HashMap<>();
		List<Map<Object, Object>> mapList = new ArrayList<>();
		if(list != null) {
			Map<Object, Object> map = new HashMap<>();
			String currentDate = DateUtil.getCurrentDate();
			int checkInNum = 0;
			int i = 1;
			while(true) {
				String date = DateUtil.formatDate(DateUtils.addDays(DateUtil.parseDate(currentDate), -i));
				int isCheckIn = getCheckInFlag(date, list);
				if(isCheckIn == 1) {
					checkInNum++;
				}else {
					break;
				}
				i++;
			}
			//如果已连续签到7日，则重新计算
			if(checkInNum==7) {
				checkInNum = 0;
				try {
					AwardCheckinHisVo awardHisVo = new AwardCheckinHisVo();
					awardHisVo.setPhoneNum(phoneNum);
					awardHisVo.setAwardType(3);
					this.awardCheckinHisDao.updateUserAwardHisCheckInTotalFlag(awardHisVo);
				} catch (Exception e) {
					logger.error("更新用户签到记录标识异常：" + e.getMessage(), e);
				}
			}
			
			//当天之前的签到数据情况
			for (int j = 1; j <= checkInNum; j++) {
				map = new HashMap<>();
				map.put("day", j);
				map.put("isCheckIn", 1);
				map.put("gold", mapDay.get(j));
				map.put("date", "");
				mapList.add(map);
			}
			
			//当天签到数据情况
			int todayIsCheckIn = getCheckInFlag(currentDate, list);
			int j = checkInNum+1;
			map = new HashMap<>();
			map.put("day", j);
			map.put("isCheckIn", todayIsCheckIn);
			map.put("gold", mapDay.get(j));
			map.put("date", currentDate);
			checkInInfoMap.putAll(map);
			mapList.add(map);
			if(j == 7) {
				checkInInfoMap.put("nextGold", mapDay.get(1));
			}
			
			//当天后签到数据情况
			for (int k = checkInNum+2; k < 8; k++) {
				map = new HashMap<>();
				map.put("day", k);
				map.put("isCheckIn", 0);
				map.put("gold", mapDay.get(k));
				map.put("date", "");
				mapList.add(map);
				//返回第二天奖励金币
				if(k == checkInNum+2) {
					checkInInfoMap.put("nextGold", mapDay.get(k));
				}
			}
		}
		mapList.sort(new Comparator<Map<Object, Object>>() {
			@Override
			public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
				return (int)o1.get("day") - (int)o2.get("day");
			}
		});
		resultMap.put("list", mapList);
		resultMap.put("checkInInfo", checkInInfoMap);
		return resultMap;
	}
	
	/**
	 * 通过签到奖励数据看是否已签到过
	 * @param date
	 * @param list
	 * @return 1=已签到；0=未签到
	 */
	public int getCheckInFlag(String date, List<AwardCheckinHisVo> list) {
		for (AwardCheckinHisVo awardHisVo : list) {
			String createDate = awardHisVo.getCreateDate().substring(0, 10);
			if(createDate.equals(date)) {
				return 1;
			}
		}
		return 0;
	}
}

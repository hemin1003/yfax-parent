package com.yfax.task.htt.service;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttSysUserReportInfoDao;
import com.yfax.task.htt.vo.HttSysUserReportInfoVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.StrUtil;

/**
 * 用户统计
 * 
 * @author Minbo
 */
@Service
public class HttSysUserReportInfoService {

	protected static Logger logger = LoggerFactory.getLogger(HttSysUserReportInfoService.class);

	@Autowired
	private HttSysUserReportInfoDao httSysUserReportInfoDao;
	@Autowired
	private HttUserInfoService httUserInfoService;

	/**
	 * 用户总数
	 */
	public HttSysUserReportInfoVo selectAllUser() {
		return this.httSysUserReportInfoDao.selectAllUser();
	}

	/**
	 * 当日新增用户数
	 */
	public HttSysUserReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.httSysUserReportInfoDao.selectNewUserOfDay(params);
	}

	/**
	 * 自邀请新增（有邀请码的新增注册）
	 */
	public HttSysUserReportInfoVo selectInviteUserOfDay(Map<String, Object> params) {
		return this.httSysUserReportInfoDao.selectInviteUserOfDay(params);
	}

	/**
	 * 阅读文章数
	 */
	public HttSysUserReportInfoVo selectArticleCountOfDay(Map<String, Object> params) {
		return this.httSysUserReportInfoDao.selectArticleCountOfDay(params);
	}

	/**
	 * 文章阅读次数
	 */
	public HttSysUserReportInfoVo selectReadCountOfDay(Map<String, Object> params) {
		return this.httSysUserReportInfoDao.selectReadCountOfDay(params);
	}

	/**
	 * @param day
	 *            0-6
	 * @param type
	 *            101=当日新增用户数, 102=自邀请新增, 103=自邀请比例, 104=阅读文章数, 105=文章阅读次数，106=用户总数
	 * @return
	 */
	private String getResult(int day, int type) {
		// Map<String, Object> params = new HashMap<>();
		// params.put("currentTime", DateUtil.getCurrentDate(day));
		// params.put("tomorrowTime", DateUtil.getCurrentDate(day + 1));

		Map<String, Object> params = new HashMap<>();
		// String curTime = DateUtil.getCurrentLongDateTime();
		// params.put("lastHour",
		// DateUtil.formatLongDate(DateUtils.addHours(DateUtil.parseLongDateTime(curTime),
		// -1)) );
		// params.put("currentTime",curTime );

		params.put("lastHour", DateUtil.getCurrentDate(day));
		params.put("currentTime", DateUtil.getCurrentDate(day + 1));

		DecimalFormat dFormat = new DecimalFormat("#0");
		String newUserOfDay = this.selectNewUserOfDay(params).getValue();
		String inviteUserOfDay = this.selectInviteUserOfDay(params).getValue();
		String result = "";
		switch (type) {
			case 101:
				result = newUserOfDay;
				result = StrUtil.null2Str(result).equals("") ? "0" : result;
				this.httUserInfoService.addHttUserInfo(DateUtil.getCurrentDate(day), "newUserOfDay", "当日新增用户数", result);
				return result;
			case 102:
				result = inviteUserOfDay;
				result = StrUtil.null2Str(result).equals("") ? "0" : result;
				this.httUserInfoService.addHttUserInfo(DateUtil.getCurrentDate(day), "inviteUserOfDay", "自邀请新增", result);
				return result;
			case 103:
				if (newUserOfDay.equals("0")) {
					result = "0";
				} else {
					double a = (Double.valueOf(inviteUserOfDay) / Double.valueOf(newUserOfDay)) * 100;
					result = (a == 0 ? "0" : dFormat.format(a) + "%");
				}
				this.httUserInfoService.addHttUserInfo(DateUtil.getCurrentDate(day), "invitePercentOfDay", "自邀请比例", result);
				return result;
			case 104:
				result = this.selectArticleCountOfDay(params).getValue();
				result = StrUtil.null2Str(result).equals("") ? "0" : result;
				this.httUserInfoService.addHttUserInfo(DateUtil.getCurrentDate(day), "articleCountOfDay", "阅读文章数", result);
				return result;
			case 105:
				result = this.selectReadCountOfDay(params).getValue();
				result = StrUtil.null2Str(result).equals("") ? "0" : result;
				this.httUserInfoService.addHttUserInfo(DateUtil.getCurrentDate(day), "readCountOfDay", "文章阅读次数", result);
				return result;
			case 106:
				result = this.selectAllUser().getValue();
				result = StrUtil.null2Str(result).equals("") ? "0" : result;
				this.httUserInfoService.addHttUserInfo(DateUtil.getCurrentDate(day), "allUser", "用户总数", result);
				return result;
			default:
				throw new RuntimeException("错误类型，请检查。day=" + day + ", type=" + type);
		}
	}

	/**
	 * 用户统计数据处理
	 */
	public void processHttUserInfo() {
		logger.info("开始用户统计数据处理...");
		Map<String, Object> params = new HashMap<>();
		int j = -1;
		// int j = 0;
		// params.put("currentTime", DateUtil.getCurrentDate(j));
		// params.put("tomorrowTime", DateUtil.getCurrentDate(0));
		UserInfoList userInfo = new UserInfoList();
		userInfo.setDateStr(DateUtil.getCurrentDate(j));
		userInfo.setAllUser(this.getResult(j, 106));
		userInfo.setNewUserOfDay(this.getResult(j, 101));
		userInfo.setInviteUserOfDay(this.getResult(j, 102));
		userInfo.setInvitePercentOfDay(this.getResult(j, 103));
		userInfo.setArticleCountOfDay(this.getResult(j, 104));
		userInfo.setReadCountOfDay(this.getResult(j, 105));
		logger.info(userInfo.toString());
		logger.info("用户统计数据处理完成");
	}

	public class UserInfoList implements Serializable {

		private static final long serialVersionUID = -79583709832163829L;

		private String dateStr;
		private String allUser;
		private String newUserOfDay;
		private String inviteUserOfDay;
		private String invitePercentOfDay;
		private String articleCountOfDay;
		private String readCountOfDay;

		public String getDateStr() {
			return dateStr;
		}

		public void setDateStr(String dateStr) {
			this.dateStr = dateStr;
		}

		public String getAllUser() {
			return allUser;
		}

		public void setAllUser(String allUser) {
			this.allUser = allUser;
		}

		public String getNewUserOfDay() {
			return newUserOfDay;
		}

		public void setNewUserOfDay(String newUserOfDay) {
			this.newUserOfDay = newUserOfDay;
		}

		public String getInviteUserOfDay() {
			return inviteUserOfDay;
		}

		public void setInviteUserOfDay(String inviteUserOfDay) {
			this.inviteUserOfDay = inviteUserOfDay;
		}

		public String getArticleCountOfDay() {
			return articleCountOfDay;
		}

		public void setArticleCountOfDay(String articleCountOfDay) {
			this.articleCountOfDay = articleCountOfDay;
		}

		public String getReadCountOfDay() {
			return readCountOfDay;
		}

		public String getInvitePercentOfDay() {
			return invitePercentOfDay;
		}

		public void setInvitePercentOfDay(String invitePercentOfDay) {
			this.invitePercentOfDay = invitePercentOfDay;
		}

		public void setReadCountOfDay(String readCountOfDay) {
			this.readCountOfDay = readCountOfDay;
		}

		@Override
		public String toString() {
			return "UserInfoList [dateStr=" + dateStr + ", allUser=" + allUser + ", newUserOfDay=" + newUserOfDay
					+ ", inviteUserOfDay=" + inviteUserOfDay + ", articleCountOfDay=" + articleCountOfDay
					+ ", readCountOfDay=" + readCountOfDay + "]";
		}
	}
}

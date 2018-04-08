package com.yfax.task.htt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttAppUserDao;
import com.yfax.task.htt.vo.HttAppUserVo;
import com.yfax.task.htt.vo.HttTestVo;
import com.yfax.utils.DateUtil;

/**
 * 用户管理服务
 * 
 * @author Minbo
 */
@Service
public class HttAppUserService {

	protected static Logger logger = LoggerFactory.getLogger(HttAppUserService.class);

	@Autowired
	private HttAppUserDao httAppUserDao;

	public boolean modifyUser(HttAppUserVo appUserVo) {
		return this.httAppUserDao.updateUser(appUserVo);
	}
	
	public List<HttTestVo> selectByTestDataCheckIns(Map<String, Object> params) {
		return this.httAppUserDao.selectByTestDataCheckIns(params);
	}

	public boolean deleteByTestDataCheckIns(Map<String, Object> params) {
		return this.httAppUserDao.deleteByTestDataCheckIns(params);
	}
	
	public boolean deleteTestDataAwardById(String aid) {
		return this.httAppUserDao.deleteTestDataAwardById(aid);
	}
	
	public Long selecgCountByTestData(String phoneNum) {
		return this.httAppUserDao.selecgCountByTestData(phoneNum);
	}

	public boolean updateTestDataUser(String phoneNum) {
		return this.httAppUserDao.updateTestDataUser(phoneNum);
	}


	public boolean updateTestDataStudentAwardHis(String bid) {
		return this.httAppUserDao.updateTestDataStudentAwardHis(bid);
	}
	
	
	public List<HttTestVo> selectByTestDataIds(Map<String, Object> params) {
		return this.httAppUserDao.selectByTestDataIds(params);
	}
	
	public List<HttTestVo> selectByTestData(Map<String, Object> params) {
		return this.httAppUserDao.selectByTestData(params);
	}

	public List<HttAppUserVo> selectAllUser() {
		return this.httAppUserDao.selectAllUser();
	}

	public Long getByRestGold(String phoneNum) {
		return this.httAppUserDao.selectByRestGold(phoneNum);
	}

	public Long getByTotalGold(String phoneNum) {
		return this.httAppUserDao.selectByTotalGold(phoneNum);
	}

	public Long getByStudentTotalGold(String phoneNum) {
		return this.httAppUserDao.selectByStudentTotalGold(phoneNum);
	}

	public List<HttAppUserVo> getHttAllUserNotBlackList() {
		return this.httAppUserDao.selectHttAllUserNotBlackList();
	}

	public HttAppUserVo getHttAppUserVoByPhoneNum(String phoneNum) {
		return this.httAppUserDao.selectHttAppUserVoByPhoneNum(phoneNum);
	}

	public boolean batchUpdateHttAllUser(List<HttAppUserVo> list) {
		return this.httAppUserDao.batchUpdateHttAllUser(list);
	}

	/**
	 * 每日零点重置用户连续签到标识
	 */
	public void batchResetDailyCheckInHtt() {
		logger.info("第一步，开始重置[连续签到标识]，[每次分步邀请奖励标识]，[日常任务-五分钟阅读任务标识]，[日常任务-视频观看五分钟标识]...");
		List<HttAppUserVo> list = this.selectAllUser();
		StringBuffer sBuffer = new StringBuffer();
		// 分片成四份
		List<List<HttAppUserVo>> finalList = new ArrayList<>();
		List<HttAppUserVo> list1 = new ArrayList<>();
		List<HttAppUserVo> list2 = new ArrayList<>();
		List<HttAppUserVo> list3 = new ArrayList<>();
		List<HttAppUserVo> list4 = new ArrayList<>();
		
		for (HttAppUserVo httAppUserVo : list) {
			// 连续签到标识
			if (httAppUserVo.getContinueCheckIn() == 1) {
				httAppUserVo.setContinueCheckIn(0);
			}
			// 每次分步邀请奖励标识
			if (httAppUserVo.getIsInviteStep() == 1) {
				httAppUserVo.setIsInviteStep(0);
			}
			// 日常任务-五分钟阅读任务标识
			if (httAppUserVo.getIsDailyRead() == 1) {
				httAppUserVo.setIsDailyRead(0);
			}
			// 日常任务-视频观看五分钟标识
			if (httAppUserVo.getIsDailyVideo() == 1) {
				httAppUserVo.setIsDailyVideo(0);
			}
			httAppUserVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
			sBuffer.append(httAppUserVo.getPhoneNum() + ", ");
			// 分成四片
			int result = Math.abs(httAppUserVo.getPhoneNum().hashCode() % 4);
			if (result == 0) {
				list1.add(httAppUserVo);
			} else if (result == 1) {
				list2.add(httAppUserVo);
			} else if (result == 2) {
				list3.add(httAppUserVo);
			} else if (result == 3) {
				list4.add(httAppUserVo);
			} else {
				throw new RuntimeException("错误类型");
			}
		}
		finalList.add(list1);
		finalList.add(list2);
		finalList.add(list3);
		finalList.add(list4);
		logger.info("总记录条数：count=" + list.size());
		for (int i = 0; i < finalList.size(); i++) {
			if (finalList.get(i).size() > 0) {
				boolean result = this.batchUpdateHttAllUser(finalList.get(i));
				logger.info("批量操作，单片记录条数list.size()=" + finalList.get(i).size() 
						+ "，重置结果result=" + (result ? "成功" : "失败") + "，休眠两秒");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		logger.info("重置用户id集合：sBuffer=" + sBuffer.toString());
	}
}
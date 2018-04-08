package com.yfax.task.htt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttChannelQualityInfoDao;
import com.yfax.task.htt.vo.HttChannelQualityInfoVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;

/**
 * 用户管理服务
 * 
 * @author Minbo
 */
@Service
public class HttChannelQualityInfoService {

	protected static Logger logger = LoggerFactory.getLogger(HttChannelQualityInfoService.class);

	@Autowired
	private HttChannelQualityInfoDao httChannelQualityInfoDao;

	public List<HttChannelQualityInfoVo> selectChannelQualityInfoByDate() {
		Map<String, Object> params = new HashMap<>();
		String nowTime = DateUtil.getCurrentDate();
		String yesterdayTime = DateUtil.getCurrentDate(-1);
		params.put("nowTime", nowTime);
		params.put("yesterdayTime", yesterdayTime);
		List<HttChannelQualityInfoVo> list = this.httChannelQualityInfoDao.selectChannelQualityInfoByDate(params);
		if (list.size() > 0) {
			for (HttChannelQualityInfoVo httChannelQualityInfoVo : list) {
				httChannelQualityInfoVo.setId(UUID.getUUID());
				String cTime = DateUtil.getCurrentLongDateTime();
				httChannelQualityInfoVo.setCreateDate(cTime);
				httChannelQualityInfoVo.setUpdateDate(cTime);
			}
			this.httChannelQualityInfoDao.batchInsertChannelQualityInfo(list);
		}
		return list;
	}

}
package com.yfax.task.htt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttDakaReportDao;
import com.yfax.task.htt.vo.HttDakaReportVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;

/**
 * 大咖统计
 * 
 * @author Minbo
 */
@Service
public class HttDakaReportService {

	protected static Logger logger = LoggerFactory.getLogger(HttDakaReportService.class);

	@Autowired
	private HttDakaReportDao httDakaReportDao;

	public List<HttDakaReportVo> selectDakaReportByDate() {
		Map<String, Object> params = new HashMap<>();
		String nowTime = DateUtil.getCurrentDate();
		String yesterdayTime = DateUtil.getCurrentDate(-1);
		params.put("nowTime", nowTime);
		params.put("yesterdayTime", yesterdayTime);
		List<HttDakaReportVo> list = this.httDakaReportDao.selectDakaReportByDate(params);
		if (list.size() > 0) {
			for (HttDakaReportVo httDakaReportVo : list) {
				httDakaReportVo.setId(UUID.getUUID());
				String cTime = DateUtil.getCurrentLongDateTime();
				httDakaReportVo.setDate(yesterdayTime);
				httDakaReportVo.setCreateDate(cTime);
				httDakaReportVo.setUpdateDate(cTime);
			}
			this.httDakaReportDao.batchInsertDakaReport(list);
		}
		return list;
	}

}
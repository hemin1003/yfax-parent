package com.yfax.task.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttVideoInfoReportDao;
import com.yfax.task.htt.vo.HttReadInfoReportVo;
import com.yfax.task.htt.vo.HttVideoInfoReportVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;

/**
 * 阅读统计报表服务
 * 
 * @author Minbo
 */
@Service
public class HttVideoInfoReportService {

	protected static Logger logger = LoggerFactory.getLogger(HttVideoInfoReportService.class);

	@Autowired
	private HttVideoInfoReportDao httVideoInfoReportDao;

	public HttVideoInfoReportVo selectVideoInfoReportByDate() {
		String curTime = DateUtil.getCurrentLongDateTime();
		String yesterdayTime = DateUtil.getCurrentDate(-1);
		HttVideoInfoReportVo httVideoInfoReportVo = this.httVideoInfoReportDao.selectVideoInfoReportByDate(yesterdayTime);
		if (httVideoInfoReportVo != null) {
			httVideoInfoReportVo.setId(UUID.getUUID());
			httVideoInfoReportVo.setSatisticDate(yesterdayTime);
			httVideoInfoReportVo.setCreateDate(curTime);
			httVideoInfoReportVo.setUpdateDate(curTime);
			return httVideoInfoReportVo;
		}else {
			return null;
		}
	}

	public Boolean insertVideoInfoReport(HttVideoInfoReportVo httVideoInfoReportVo) {
		return this.httVideoInfoReportDao.insertVideoInfoReport(httVideoInfoReportVo);
	}

}
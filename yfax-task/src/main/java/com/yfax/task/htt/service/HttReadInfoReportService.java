package com.yfax.task.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttReadInfoReportDao;
import com.yfax.task.htt.vo.HttReadInfoReportVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;

/**
 * 阅读统计报表服务
 * 
 * @author Minbo
 */
@Service
public class HttReadInfoReportService {

	protected static Logger logger = LoggerFactory.getLogger(HttReadInfoReportService.class);

	@Autowired
	private HttReadInfoReportDao httReadInfoReportDao;

	public HttReadInfoReportVo selectReadInfoReportByDate() {
		String curTime = DateUtil.getCurrentLongDateTime();
		String yesterdayTime = DateUtil.getCurrentDate(-1);
		HttReadInfoReportVo httReadInfoReportVo = this.httReadInfoReportDao.selectReadInfoReportByDate(yesterdayTime);
		if (httReadInfoReportVo != null) {
			httReadInfoReportVo.setId(UUID.getUUID());
			httReadInfoReportVo.setSatisticDate(yesterdayTime);
			httReadInfoReportVo.setCreateDate(curTime);
			httReadInfoReportVo.setUpdateDate(curTime);
			return httReadInfoReportVo;
		}else {
			return null;
		}
	}

	public Boolean insertReadInfoReport(HttReadInfoReportVo httReadInfoReportVo) {
		return this.httReadInfoReportDao.insertReadInfoReport(httReadInfoReportVo);
	}

}
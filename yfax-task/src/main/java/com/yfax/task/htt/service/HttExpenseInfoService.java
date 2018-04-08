package com.yfax.task.htt.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yfax.task.htt.dao.HttExpenseInfoDao;
import com.yfax.task.htt.vo.HttExpenseInfoVo;
import com.yfax.task.htt.vo.HttUserInfoVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;

/**
 * 金币支出统计
 * @author Minbo
 */
@Service
public class HttExpenseInfoService {
	
	protected static Logger logger = LoggerFactory.getLogger(HttExpenseInfoService.class);
	
	@Autowired
	private HttExpenseInfoDao dao;
	
	public HttExpenseInfoVo selectHttExpenseInfo(Map<String, Object> params) {
		return this.dao.selectHttExpenseInfo(params);
	}

	public boolean updateHttExpenseInfoById(HttExpenseInfoVo httExpenseInfoVo) {
		return this.dao.updateHttExpenseInfo(httExpenseInfoVo);
	}

	public boolean addHttExpenseInfo(String date, String scolumn, String sname, String nums) {
		Map<String, Object> params = new HashMap<>();
		params.put("date",date );
		params.put("scolumn",scolumn );
		//根据date与scolumn查询是否存在记录
		HttExpenseInfoVo httExpenseInfoVo = this.selectHttExpenseInfo(params);
		
		//如果已经存在该记录，插入一条新的记录
		if(httExpenseInfoVo == null) {
			logger.info("insert..................................");
			HttExpenseInfoVo httExpenseInfoVoTmp = new HttExpenseInfoVo();
			httExpenseInfoVoTmp.setId(UUID.getUUID());
			httExpenseInfoVoTmp.setDate(date);
			httExpenseInfoVoTmp.setScolumn(scolumn);
			httExpenseInfoVoTmp.setSname(sname);
			httExpenseInfoVoTmp.setNums(nums);
			httExpenseInfoVoTmp.setCreateDate(DateUtil.getCurrentDate());
			httExpenseInfoVoTmp.setUpdateDate(DateUtil.getCurrentDate());
			return this.dao.insertHttExpenseInfo(httExpenseInfoVoTmp);
		}
		//否则更新该记录
		else {
			logger.info("update........................");
			String lastNums = httExpenseInfoVo.getNums();
			httExpenseInfoVo.setNums(String.valueOf((Integer.parseInt(lastNums) + Integer.parseInt(nums))));
			httExpenseInfoVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
			return this.updateHttExpenseInfoById(httExpenseInfoVo);
		}
	}
}

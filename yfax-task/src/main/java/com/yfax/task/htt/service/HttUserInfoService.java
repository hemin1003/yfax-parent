package com.yfax.task.htt.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttUserInfoDao;
import com.yfax.task.htt.vo.HttUserInfoVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;

/**
 * 金币支出统计
 * @author Minbo
 */
@Service
public class HttUserInfoService {
	
	protected static Logger logger = LoggerFactory.getLogger(HttUserInfoService.class);
	
	@Autowired
	private HttUserInfoDao dao;
	
	public HttUserInfoVo selectHttUserInfo(Map<String, Object> params) {
		return this.dao.selectHttUserInfo(params);
	}

	public boolean updateHttUserInfoById(HttUserInfoVo httUserInfoVo) {
		return this.dao.updateHttUserInfo(httUserInfoVo);
	}
	
	public boolean addHttUserInfo(String date, String scolumn, String sname, String nums) {
		Map<String, Object> params = new HashMap<>();
		params.put("date",date );
		params.put("scolumn",scolumn );
		//根据date与scolumn查询是否存在记录
		HttUserInfoVo httUserInfoVo = this.selectHttUserInfo(params);
		
		//如果已经存在该记录，插入一条新的记录
		if(httUserInfoVo == null) {
			HttUserInfoVo httUserInfoVoTmp = new HttUserInfoVo();
			httUserInfoVoTmp.setId(UUID.getUUID());
			httUserInfoVoTmp.setDate(date);
			httUserInfoVoTmp.setScolumn(scolumn);
			httUserInfoVoTmp.setSname(sname);
			httUserInfoVoTmp.setNums(nums);
			httUserInfoVoTmp.setCreateDate(DateUtil.getCurrentDate());
			httUserInfoVoTmp.setUpdateDate(DateUtil.getCurrentDate());
			return this.dao.insertHttUserInfo(httUserInfoVoTmp);
		}
		//否则更新该记录
		else {
			String lastNums = httUserInfoVo.getNums();
			httUserInfoVo.setNums(String.valueOf((Integer.parseInt(lastNums) + Integer.parseInt(nums))));
			httUserInfoVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
			return this.updateHttUserInfoById(httUserInfoVo);
		}
	}
}

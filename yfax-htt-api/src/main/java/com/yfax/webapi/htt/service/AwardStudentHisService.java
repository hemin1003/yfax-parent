package com.yfax.webapi.htt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.AwardStudentHisDao;
import com.yfax.webapi.htt.vo.AwardStudentHisVo;

/**
 * 只记录徒弟贡献-金币奖励记录
 * @author Minbo
 */
@Service
public class AwardStudentHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(AwardStudentHisService.class);
	
	@Autowired
	private  AwardStudentHisDao awardStudentHisDao;
	
	public boolean addAwardStudentHis(AwardStudentHisVo awardStudentHisVo) {
		try {
			return this.awardStudentHisDao.insertAwardStudentHis(awardStudentHisVo);
		} catch (Exception e) {
			logger.error("新增徒弟贡献记录异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public List<AwardStudentHisVo> getAwardStudentHisList(String phoneNum) {
		return this.awardStudentHisDao.selectAwardStudentHisList(phoneNum);
	}
}

package com.yfax.webapi.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.StudentConfigDao;
import com.yfax.webapi.htt.vo.StudentConfigVo;

/**
 * 徒弟阅读次数alpha值配置
 * @author Minbo
 */
@Service
public class StudentConfigService{
	
	protected static Logger logger = LoggerFactory.getLogger(StudentConfigService.class);
	
	@Autowired
	private StudentConfigDao dao;
	
	public StudentConfigVo selectStudentConfig(long awardCount) {
		return this.dao.selectStudentConfig(awardCount);
	}

}

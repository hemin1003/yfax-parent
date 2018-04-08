package com.yfax.webapi.htt.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.IpShareCodeDao;
import com.yfax.webapi.htt.vo.IpShareCodeVo;

/**
 * 记录邀请用户和IP对应关系
 * @author Minbo
 */
@Service
public class IpShareCodeService{
	
	protected static Logger logger = LoggerFactory.getLogger(IpShareCodeService.class);
	
	@Autowired
	private IpShareCodeDao dao;
	
	@Transactional
	public boolean addIpShareCode(IpShareCodeVo ipShareCodeVo){
		try {
			return this.dao.insertIpShareCode(ipShareCodeVo);
		} catch (Exception e) {
			logger.error("新增邀请用户和IP对应关系异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	@Transactional
	public boolean updateIpShareCode(IpShareCodeVo ipShareCodeVo){
		try {
			return this.dao.updateIpShareCode(ipShareCodeVo);
		} catch (Exception e) {
			logger.error("修改邀请用户和IP对应关系异常：" + e.getMessage(), e);
			return false;
		}
	}
	
//	public IpShareCodeVo selectIpShareCodeByIp(Map<String, Object> map) {
//		return this.dao.selectIpShareCodeByIp(map);
//	}
	
	public IpShareCodeVo selectIpShareCodeIsFromIp(Map<String, Object> map) {
		return this.dao.selectIpShareCodeIsFromIp(map);
	}
	
}
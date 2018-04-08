package com.yfax.webapi.htt.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.DateUtil;
import com.yfax.utils.NetworkUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.htt.dao.TraceHisDao;
import com.yfax.webapi.htt.vo.TraceHisVo;

/**
 * 记录用户接口调用轨迹
 * @author Minbo
 */
@Service
public class TraceHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(TraceHisService.class);
	
	@Autowired
	private TraceHisDao dao;
	
	public boolean addTraceHis(HttpServletRequest request, String apiName){
		try {
			String cTime = DateUtil.getCurrentLongDateTime();
			TraceHisVo traceHisVo = new TraceHisVo();
			traceHisVo.setId(UUID.getUUID());
			traceHisVo.setTraceId(request.getParameter("tId"));
			traceHisVo.setPhoneNum(request.getParameter("phoneNum"));
			traceHisVo.setImei(request.getParameter("mId"));
			traceHisVo.setIp(NetworkUtil.getIpAddress(request));
			traceHisVo.setApiName(apiName);
			traceHisVo.setuId(request.getParameter("uId"));
			traceHisVo.setCreateDate(cTime);
			traceHisVo.setUpdateDate(cTime);
			return this.dao.insertTraceHis(traceHisVo);
		} catch (Exception e) {
			logger.error("记录用户接口调用轨迹异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public boolean initTraceHis(HttpServletRequest request, String traceId, String apiName, Integer source){
		try {
			String cTime = DateUtil.getCurrentLongDateTime();
			TraceHisVo traceHisVo = new TraceHisVo();
			traceHisVo.setId(UUID.getUUID());
			traceHisVo.setTraceId(traceId);
			traceHisVo.setImei(request.getParameter("mId"));
			traceHisVo.setIp(NetworkUtil.getIpAddress(request));
			traceHisVo.setApiName(apiName);
			traceHisVo.setSource(source);
			traceHisVo.setCreateDate(cTime);
			traceHisVo.setUpdateDate(cTime);
			return this.dao.insertTraceHis(traceHisVo);
		} catch (Exception e) {
			logger.error("记录用户接口调用轨迹异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public boolean modifyTraceHis(TraceHisVo traceHisVo){
		try {
			return this.dao.updateTraceHis(traceHisVo);
		} catch (Exception e) {
			logger.error("更新用户接口调用轨迹异常：" + e.getMessage(), e);
			return false;
		}
	}

	public TraceHisVo getTraceHisByUid(String uId) {
		return this.dao.selectTraceHisByUid(uId);
	}
}

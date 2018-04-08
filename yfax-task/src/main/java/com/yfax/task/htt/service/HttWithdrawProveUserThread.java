package com.yfax.task.htt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yfax.task.htt.vo.HttWithdrawHisVo;

/**
 * 自动审核统计主线程类
 * @author Minbo
 *
 */
public class HttWithdrawProveUserThread extends Thread {
	
	protected static Logger logger = LoggerFactory.getLogger(HttWithdrawProveUserThread.class);
	
	@Autowired
	private HttWithdrawProveUserService httWithdrawProveUserService;
	
	List<HttWithdrawHisVo> list;
	
	public HttWithdrawProveUserThread(HttWithdrawProveUserService httWithdrawProveUserService, 
			List<HttWithdrawHisVo> list) {
		this.httWithdrawProveUserService = httWithdrawProveUserService;
		this.list = list;
	}
	
	public HttWithdrawProveUserService getHttWithdrawProveUserService() {
		return httWithdrawProveUserService;
	}
	
	public List<HttWithdrawHisVo> getList() {
		return list;
	}

	@Override
	public void run() {
	}
	
}

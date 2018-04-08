package com.yfax.task.htt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttAppUserDao;
import com.yfax.task.htt.dao.HttAwardHisDao;
import com.yfax.task.htt.dao.HttWithdrawHisDao;
import com.yfax.task.htt.vo.HttAppUserVo;
import com.yfax.task.htt.vo.HttAwardHisVo;
import com.yfax.task.htt.vo.HttWithdrawHisVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;

/**
 * 用户提现记录
 * @author Minbo
 */
@Service
public class HttWithdrawHisService {
	
	protected static Logger logger = LoggerFactory.getLogger(HttWithdrawHisService.class);
	
}

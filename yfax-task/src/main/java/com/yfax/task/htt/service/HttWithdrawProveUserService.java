package com.yfax.task.htt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttInviteUserHisDao;
import com.yfax.task.htt.dao.HttLoginHisDao;
import com.yfax.task.htt.dao.HttWithdrawHisDao;
import com.yfax.task.htt.dao.HttWithdrawProveUserDao;
import com.yfax.task.htt.dao.HttWithdrawProveUserLinkDao;
import com.yfax.task.htt.vo.HttAppUserVo;
import com.yfax.task.htt.vo.HttInviteUserHisVo;
import com.yfax.task.htt.vo.HttLoginHisVo;
import com.yfax.task.htt.vo.HttWithdrawHisVo;
import com.yfax.task.htt.vo.HttWithdrawProveUserLinkVo;
import com.yfax.task.htt.vo.HttWithdrawProveUserVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.MD5Util;
import com.yfax.utils.StrUtil;
import com.yfax.utils.UUID;

/**
 * 自动审核数据统计
 * 
 * @author Minbo
 */
@Service
public class HttWithdrawProveUserService {

	protected static Logger logger = LoggerFactory.getLogger(HttWithdrawProveUserService.class);
}
package com.yfax.task.htt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttAppUserDao;
import com.yfax.task.htt.dao.HttInviteUserHisDao;
import com.yfax.task.htt.dao.HttLoginHisDao;
import com.yfax.task.htt.dao.HttWithdrawHisDao;
import com.yfax.task.htt.vo.HttAppUserVo;
import com.yfax.task.htt.vo.HttBlacklistHisVo;
import com.yfax.task.htt.vo.HttInviteUserHisVo;
import com.yfax.task.htt.vo.HttLoginHisVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.StrUtil;

/**
 * 自动防作弊系统
 * 
 * @author Minbo
 */
@Service
public class HttAutoCheatSystemService {

	protected static Logger logger = LoggerFactory.getLogger(HttAutoCheatSystemService.class);

}

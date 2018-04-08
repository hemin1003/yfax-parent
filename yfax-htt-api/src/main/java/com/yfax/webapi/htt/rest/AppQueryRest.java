package com.yfax.webapi.htt.rest;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yfax.utils.CreateID;
import com.yfax.utils.JsonResult;
import com.yfax.utils.ResultCode;
import com.yfax.utils.StrUtil;
import com.yfax.webapi.htt.service.AppConfigService;
import com.yfax.webapi.htt.service.AppUserService;
import com.yfax.webapi.htt.service.AppVersionService;
import com.yfax.webapi.htt.service.InitConfigService;
import com.yfax.webapi.htt.service.NoticeConfigService;
import com.yfax.webapi.htt.service.TraceHisService;
import com.yfax.webapi.htt.vo.AppConfigVo;
import com.yfax.webapi.htt.vo.AppVersionVo;
import com.yfax.webapi.htt.vo.InitConfigVo;
import com.yfax.webapi.htt.vo.NoticeConfigVo;

/**
 * @author Minbo.He 
 * 查询接口
 */
@RestController
@RequestMapping("/api/htt")
public class AppQueryRest {

	protected static Logger logger = LoggerFactory.getLogger(AppQueryRest.class);

	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private InitConfigService initConfigService;
	@Autowired
	private AppVersionService appVersionService;
	@Autowired
	private TraceHisService traceHisService;
	@Autowired
	private NoticeConfigService noticeConfigService;
	
	/**
	 * 个人信息接口
	 */
	@RequestMapping("/queryUserInfo")
	public JsonResult queryUserInfo(String phoneNum, Integer source, String versionCode,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		try {
			if(!StrUtil.null2Str(phoneNum).equals("")) {
				return this.appUserService.getUserInfo(phoneNum, source, versionCode, 
						mId, tId, sId, uId, request);
			}else {
				logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
		} catch (Exception e) {
			logger.error("phoneNum=" + phoneNum + "，个人信息接口查询异常：" + e.getMessage(), e);
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
	}
	
	
	/**
	 * 获得APP初始化配置数据（未登录情况下数据也要用）
	 */
	@RequestMapping("/queryInitConfig")
	public JsonResult queryInitConfig(String mId, String version, String channel, 
			HttpServletRequest request) {
		try {
			Map<String, Object> resultMap = new HashMap<>();
			//1. 初始配置数据
			InitConfigVo initConfigVo = this.initConfigService.selectInitConfig();
			resultMap.put("initConfigVo", initConfigVo);
			//2. APP渠道版本配置
			AppVersionVo appVersionVo = this.appVersionService.selectAppVersion();
			resultMap.put("appVersionVo", appVersionVo);
			//3. 参数配置信息
			AppConfigVo appConfigVo = this.appConfigService.selectAppConfig();
			resultMap.put("appConfigVo", appConfigVo);
			//4. 初始化跟踪id
			String tId = CreateID.getTraceId();
			resultMap.put("tId", tId);
			//记录历史
			this.traceHisService.initTraceHis(request, tId, "queryInitConfig", 0);
			return new JsonResult(ResultCode.SUCCESS, resultMap);
			
		} catch (Exception e) {
			logger.error("初始化信息异常：" + e.getMessage(), e);
			return new JsonResult(ResultCode.SUCCESS_UPGRADE_NEW_TO);
		}
	}
	
	public static Integer getValue(int type) {
		DecimalFormat myFormat = new DecimalFormat("#0");
		Integer result = 0;
		if(type == 1) {
			while(true) {
				result = Integer.valueOf(myFormat.format(new Random().nextFloat() * 100000 * new Random().nextFloat())) * 2;
				if(result>5000*10 && result<10000*10) {
					return result;
				}
			}
		}else if(type == 2) {
			while(true) {
				result = Integer.valueOf(myFormat.format(new Random().nextFloat() * 100000 * new Random().nextFloat()));
				if(result>2000*10 && result<5000*10) {
					return result;
				}
			}
		}else if(type == 3) {
			while(true) {
				result = Integer.valueOf(myFormat.format(new Random().nextFloat() * 100000 * new Random().nextFloat()));
				if(result>500*10 && result<2000*10) {
					return result;
				}
			}
		}
		return result;
	}
	
	/*
	 * 通知配置
	 */
	@GetMapping("/queryNoticeConfig")
	public JsonResult queryNoticeConfig() {
		List<NoticeConfigVo> noticeConfigs = this.noticeConfigService.selectNoticeConfig();
		return new JsonResult(ResultCode.SUCCESS,noticeConfigs);
	}
	
}
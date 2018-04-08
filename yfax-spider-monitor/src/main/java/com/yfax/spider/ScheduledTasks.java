package com.yfax.spider;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yfax.common.sms.SmsService;
import com.yfax.spider.service.EmailService;
import com.yfax.spider.service.SpiderConfigService;
import com.yfax.spider.service.SpiderEmailService;
import com.yfax.spider.service.SpiderListService;
import com.yfax.spider.service.SpiderSmsService;
import com.yfax.spider.toutiao.model.Tags;
import com.yfax.spider.toutiao.service.ToutiaoDetail;
import com.yfax.spider.vo.SpiderConfigVo;
import com.yfax.spider.vo.SpiderEmailVo;
import com.yfax.spider.vo.SpiderInfoVo;
import com.yfax.spider.vo.SpiderSmsVo;
import com.yfax.utils.DateUtil;

@Component
public class ScheduledTasks {

	protected static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private SpiderConfigService spiderConfigService;
	@Autowired
	private SpiderListService spiderListService;
	@Autowired
	private SpiderSmsService spiderSmsService;
	@Autowired
	private SpiderEmailService spiderEmailService;
	
	private static boolean flag = false;
	private static String alarmMsg = "";
	
	/**
	 * 消息监控，系统数据汇总
	 */
	@Scheduled(cron = "0 0 6,12,18,23 * * *")	//6小时跑一次
	public void newsMonitor() {
		flag = false;
		alarmMsg = "";
		logger.info("================(消息监控跑批，start)================");
		this.smsMonitor();
		logger.info("==============================================================================");
		logger.info("==============================================================================");
		this.emailMonitor();
		logger.info("================(消息监控跑批，end)================");
	}
	
	/**
	 * 移除无用消息记录
	 */
	@Scheduled(cron = "0 0 1-23 * * *")	//1小时跑一次
//	@Scheduled(initialDelay = 1000, fixedDelay = 900000)	//15分钟跑一次
	public void removeNotAvailableNews() {
		//获取开始时间
		long start=System.currentTimeMillis();   
		logger.info("=============移除无用消息记录 start=================");
		
		for (int i = 0; i < Tags.tagList.length; i++) {
			ToutiaoDetail tDetail = new ToutiaoDetail();
			try {
				tDetail.removeDetailInfo(Tags.tagList[i].toString());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		
		logger.info("=============移除无用消息记录 end=================");
		//获取结束时间
		long end=System.currentTimeMillis();
		logger.info("程序运行时间：" + (end-start)/1000 + " s");
	}
	
	/**
	 * 检测新闻数据是否有变化，间隔时间是否超过一小时，否则报警
	 */
	@Scheduled(cron = "0 0/50 * * * *")	//50分钟跑一次
//	@Scheduled(initialDelay = 1000, fixedDelay = 1200000)	//20分钟跑一次，启动时会立即执行一次
	public void checkNewsStatus() {
		//获取开始时间
		long start=System.currentTimeMillis(); 
		logger.info("=============================================");
		logger.info("==========检测新闻数据是否有变化 start===========");
		
		String date = DateUtil.getCurrentDate();
		logger.info("当前日期date=" + date);
		SpiderInfoVo spiderInfoVo = this.spiderListService.selectLastestSpiderInfo(date);
		if(spiderInfoVo != null) {
			logger.info(spiderInfoVo.toString());
			
			String updateDate = spiderInfoVo.getUpdateDate().substring(0, 19);
			String currentDate = DateUtil.getCurrentLongDateTime();
			
			logger.info("updateDate=" + updateDate);
			
			long[] times = DateUtil.getDistanceTimes(currentDate, updateDate);
			logger.info("day=" + times[0] + ", hour=" + times[1] + ", min=" + times[2] + ", sec=" + times[3]);
			//间隔超过一个小时，则告警
			if(times[1]>=1) {
				final String msg = "截止时间" + DateUtil.getCurrentLongDateTime() + "，新闻数据已经超过一个小时无更新了，请相关人员检查处理。";
				logger.info("告警内容msg=" + msg);
				
				logger.info("----------------");
				logger.info("----------------");
				logger.info("1. 短信告警");
				try {
					List<String> phoneNumList = new ArrayList<>();
					List<SpiderSmsVo> spiderSmsVoList = this.spiderSmsService.selectSpiderSms();
					for (SpiderSmsVo spiderSmsVo : spiderSmsVoList) {
						logger.info(spiderSmsVo.toString());
						phoneNumList.add(spiderSmsVo.getPhoneNum());
					}
					if(phoneNumList.size() <= 0) {
						logger.warn("无配置短信告警手机号码，请配置。");
					}else {
						for (String phoneNum : phoneNumList) {
							HashMap<String, Object> result = SmsService.sendSms(phoneNum, msg, "yfax-spider"
									, GlobalUtils.SMS_TEMPLATE_ID, GlobalUtils.SMS_APP_ID);
							if("000000".equals(result.get("statusCode"))){
								logger.info("发送成功。phoneNum=" + phoneNum);
							}else{
								logger.info("发送失败。phoneNum=" + phoneNum);
							}
						}
					}
				} catch (Exception e) {
					logger.error("短信告警异常: " + e.getMessage());
				}
				
				logger.info("----------------");
				logger.info("----------------");
				logger.info("2. 邮件告警");
				try {
					List<String> receiveMailAccount = new ArrayList<>();
					List<SpiderEmailVo> spiderEmailVoList = this.spiderEmailService.selectSpiderEmail();
					for (SpiderEmailVo spiderEmailVo : spiderEmailVoList) {
						logger.info(spiderEmailVo.toString());
						receiveMailAccount.add(spiderEmailVo.getEmail());
					}
					if(receiveMailAccount.size()>0) {
						EmailService.sendEmail(receiveMailAccount, msg);
					}else {
						logger.warn("没有配置邮件地址，请配置。");
					}
				} catch (Exception e) {
					logger.error("邮件告警异常: " + e.getMessage());
				}
				
			}
		}else {
			logger.info("检测新闻数据抓取跑批正常，无需告警。");
		}
		
		logger.info("==========检测新闻数据是否有变化 end===========");
		logger.info("=============================================");
		//获取结束时间
		long end=System.currentTimeMillis();
		logger.info("程序运行时间：" + (end-start)/1000 + " s");
	}
	
	
	/**
	 * 短信监控
	 */
	private void smsMonitor() {
		logger.info("==========短信监控 start===========");
		logger.info("=================================");
		
		List<String> phoneNumList = new ArrayList<>();
		List<SpiderSmsVo> spiderSmsVoList = this.spiderSmsService.selectSpiderSms();
		for (SpiderSmsVo spiderSmsVo : spiderSmsVoList) {
			logger.info(spiderSmsVo.toString());
			phoneNumList.add(spiderSmsVo.getPhoneNum());
		}
		
		String date = DateUtil.getCurrentDate();
		StringBuffer sb = new StringBuffer();
		sb.append("截止时间：" + DateUtil.getCurrentLongDateTime() + "，");
		sb.append("系统数据汇总统计，");
		flag = false;
		logger.info("当前日期date=" + date);
		for (int i = 0; i < Tags.tagList.length; i++) {
			SpiderInfoVo spiderInfoVo = new SpiderInfoVo();
			String tag = Tags.tagList[i].toString();
			spiderInfoVo.setDomain(tag);
			spiderInfoVo.setDate(date);
			//提醒阈值，新闻数量低于就报警
			SpiderConfigVo spiderConfigVo = this.spiderConfigService.selectSpiderConfig(tag);
			int alpha = spiderConfigVo.getAlpha();
			long num = this.spiderListService.selectDaysOfNewsNum(spiderInfoVo);
			logger.info("tag类型=" + tag + ", 告警阈值alpha=" + alpha + ", 今日已新增数量num=" + num);
			if(num<alpha) {
				logger.info("---------------------------");
				logger.info("tag=" + spiderInfoVo.getDomain() + ", num=" + num 
						+ "低于阈值alpha=" + alpha + "，短信报警，请注意");
				logger.info("---------------------------");
				sb.append("[" + spiderInfoVo.getDomain() + ", " 
						+ Tags.getTagName(spiderInfoVo.getDomain()) 
						+ "]仅[" + num + "].");
				flag = true;
			}
		}
		logger.info("flag=" + flag + "，短信告警内容=" + sb.toString());
		if(flag) {
			if(phoneNumList.size() <= 0) {
				logger.warn("无配置短信告警手机号码，请配置。");
			}else {
				for (String phoneNum : phoneNumList) {
					HashMap<String, Object> result = SmsService.sendSms(phoneNum, sb.toString(), "yfax-spider"
							, GlobalUtils.SMS_TEMPLATE_ID, GlobalUtils.SMS_APP_ID);
					if("000000".equals(result.get("statusCode"))){
						logger.info("发送成功。phoneNum=" + phoneNum);
					}else{
						logger.info("发送失败。phoneNum=" + phoneNum);
					}
				}
			}
		}else {
			logger.info("无需短信告警，数据符合要求。");
		}
		logger.info("=================================");
		logger.info("==========短信监控 end===========");
		logger.info("=================================");
		
		alarmMsg = sb.toString();
	}
	
	/**
	 * 邮件监控
	 */
	private void emailMonitor() {
		logger.info("==========邮件发送监控 start===========");
		logger.info("=================================");
		
		List<String> receiveMailAccount = new ArrayList<>();
		List<SpiderEmailVo> spiderEmailVoList = this.spiderEmailService.selectSpiderEmail();
		for (SpiderEmailVo spiderEmailVo : spiderEmailVoList) {
			logger.info(spiderEmailVo.toString());
			receiveMailAccount.add(spiderEmailVo.getEmail());
		}
		
		logger.info("flag=" + flag + "，邮件告警内容=" + alarmMsg);
		if(flag) {
			if(receiveMailAccount.size()>0) {
				EmailService.sendEmail(receiveMailAccount, alarmMsg);
			}else {
				logger.warn("没有配置邮件地址，请配置。");
			}
		}else {
			logger.info("无需邮件告警，数据符合要求。");
		}
		
		logger.info("=================================");
		logger.info("==========邮件发送监控 end===========");
		logger.info("=================================");
	}
}
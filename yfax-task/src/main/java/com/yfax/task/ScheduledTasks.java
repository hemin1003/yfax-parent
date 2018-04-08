package com.yfax.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.service.HttAlipayService;
import com.yfax.task.htt.service.WechatEpayService;

@Component
public class ScheduledTasks {

	protected static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private WechatEpayService wechatEpayService;
	@Autowired
	private HttAlipayService httAlipayService;
	
	// 每五分钟执行一次
	@Scheduled(initialDelay = 1000, fixedDelay = 300000)
	public void httTaskOfWechatPay() {
		logger.info("============悦头条新版-微信打款任务，start===================");
		this.wechatEpayService.wechatBatchPayHtt();
		logger.info("============悦头条新版-微信打款任务，end===================");
	}

	// 每五分钟执行一次
	@Scheduled(initialDelay = 1000, fixedDelay = 300000)
	public void httTaskOfAliPay() {
		logger.info("============悦头条新版-支付宝打款任务，start===================");
		this.httAlipayService.alipayBatchPayHtt();
		logger.info("============悦头条新版-支付宝打款任务，end===================");
	}

}
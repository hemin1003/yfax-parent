package com.yfax.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yfax.spider.service.SpiderConfigService;
import com.yfax.spider.service.SpiderListService;
import com.yfax.spider.toutiao.model.Tags;
import com.yfax.spider.toutiao.thread.ThreadDetailBatch;
import com.yfax.spider.toutiao.thread.ThreadMainBatch;

@Component
public class ScheduledTasks {

	protected static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private SpiderConfigService spiderConfigService;
	@Autowired
	private SpiderListService spiderListService;
	
	// 以指定时间间隔调度任务（以方法执行开始时间为准）
	// @Scheduled(fixedRate = 20000)
	// public void reportCurrentTime() {
	// logger.info("======================start===================");
	// logger.info("my task is running, The time is now " +
	// DateUtil.getCurrentLongDateTime());
	// this.batchResetDailyCheckIn();
	// this.batchAutoTransfer();
	// logger.info("=======================end====================");
	// }

	// 每隔一个小时执行一次
	//@Scheduled(initialDelay = 1000, fixedDelay = 3600000)
	@Scheduled(initialDelay = 5000, fixedDelay = 900000)	//启动延迟5秒执行，每隔15分钟一次
//	@Scheduled(cron = "0 0/15 * * * *")	//每15分钟一次
	public void mainList() {
		this.startChangeIp();
		
		logger.info("==================(一、获取列表数据，start)=================");
		logger.info("======================");
		logger.info("======================");
		logger.info("开始跑批...");
		logger.info("======================");
		logger.info("======================");
		// 获取开始时间
		long start = System.currentTimeMillis();
		logger.info("1. 跑前半部分标签...");
		for (int i = 0; i < Tags.tagList.length / 2; i++) {
			ThreadMainBatch mainClass = new ThreadMainBatch(Tags.tagList[i].toString(), 
					this.spiderListService, this.spiderConfigService);
			mainClass.start();
		}

		try {
			logger.info("休息三十秒");
			Thread.sleep(30 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("2. 跑后半部分标签...");
		for (int i = Tags.tagList.length / 2; i < Tags.tagList.length; i++) {
			ThreadMainBatch mainClass = new ThreadMainBatch(Tags.tagList[i].toString(), 
					this.spiderListService, this.spiderConfigService);
			mainClass.start();
		}

		// 获取结束时间
		long end = System.currentTimeMillis();
		logger.info("======================");
		logger.info("======================");
		logger.info("程序总运行时间： " + (end - start) + " ms");
		logger.info("======================");
		logger.info("======================");

		logger.info("==================(一、获取列表数据，end)=================");
	}

	// 指定延迟后开始调度任务
	// @Scheduled(initialDelay = 1000, fixedRate = 5000)
	// public void doSomething() {
	// // something that should execute periodically
	// }

	// cron表达式，second, minute, hour, day, month, weekday
	// @Scheduled(cron = "0 0 0 * * *") // 每日零点跑批
//	@Scheduled(cron = "0 0/9 * * * *")	//每9分钟一次
	@Scheduled(initialDelay = 180000, fixedDelay = 540000)	//启动延迟3分钟执行，每隔9分钟一次
	public void detailList() {
		this.startChangeIp();
		
		logger.info("============(补偿跑批，获取详情数据，start)===================");
		logger.info("======================");
		logger.info("======================");
		logger.info("2. 处理详情页面数据...");
		logger.info("======================");
		logger.info("======================");
		logger.info("跑前半部分标签...");
		for (int i = 0; i < Tags.tagList.length / 2; i++) {
			ThreadDetailBatch detail = new ThreadDetailBatch(Tags.tagList[i].toString(), 
					this.spiderListService, this.spiderConfigService);
			detail.start();
		}

		try {
			logger.info("休息十秒");
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("跑后半部分标签...");
		for (int i = Tags.tagList.length / 2; i < Tags.tagList.length; i++) {
			ThreadDetailBatch detail = new ThreadDetailBatch(Tags.tagList[i].toString(), 
					this.spiderListService, this.spiderConfigService);
			detail.start();
		}
		logger.info("======================");
		logger.info("======================");
		logger.info("============(补偿跑批，获取详情数据，end)===================");
	}
	
	/**
	 * 切换IP
	 */
	public void startChangeIp() {
		logger.info("====================================================");
		logger.info("===================IP切换程序 start===================");
		try {
			//先停止
			String command = "pppoe-stop";
			Runtime.getRuntime().exec(command);
			logger.info("1. 先执行pppoe-stop，停止运行.");
			logger.info("2. 休息5秒.");
			Thread.sleep(5000);
			//再启动
			command = "pppoe-start";
			Runtime.getRuntime().exec(command);
			logger.info("3. 先执行pppoe-start，开始运行.");
		} catch (Exception e1) {
			logger.error("IP切换程序异常：" + e1.getMessage());
		}
		logger.info("===================IP切换程序 end===================");
		logger.info("====================================================");
	    
		try {
			logger.info("正式跑批前，先休眠20秒...");
			Thread.sleep(20000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
}
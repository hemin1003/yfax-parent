package com.yfax.spider.toutiao.test;

import org.apache.log4j.Logger;

import com.yfax.spider.service.SpiderConfigService;
import com.yfax.spider.service.SpiderListService;
import com.yfax.spider.toutiao.model.Tags;
import com.yfax.spider.toutiao.thread.ThreadMainBatch;

/**
 * 抓取列表
 * 
 * @author Minbo
 */
public class MainTest {

	protected static final Logger logger = Logger.getLogger(MainTest.class);

	public static void main(String[] args) {
		
		SpiderListService spiderListService = new SpiderListService();
		SpiderConfigService spiderConfigService = new SpiderConfigService();

		logger.info("======================");
		logger.info("======================");
		logger.info("开始跑批...");
		logger.info("======================");
		logger.info("======================");
		// 获取开始时间
		long start = System.currentTimeMillis();
		logger.info("跑前半部分标签...");
//		for (int i = 0; i < Tags.tagList.length / 2; i++) {
//			ThreadMainBatch mainClass = new ThreadMainBatch(Tags.tagList[i].toString(), spiderListService, spiderConfigService);
//			mainClass.start();
//		}
		
		ThreadMainBatch mainClass = new ThreadMainBatch("news_hot", spiderListService, spiderConfigService);
		mainClass.start();

//		try {
//			logger.info("休息三十秒");
//			Thread.sleep(30 * 1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

//		logger.info("跑后半部分标签...");
//		for (int i = Tags.tagList.length / 2; i < Tags.tagList.length; i++) {
//			ThreadMainBatch mainClass = new ThreadMainBatch(Tags.tagList[i].toString(), spiderListService, spiderConfigService);
//			mainClass.start();
//		}

		// 获取结束时间
		long end = System.currentTimeMillis();
		logger.info("======================");
		logger.info("======================");
		logger.info("程序总运行时间： " + (end - start) + " ms");
		logger.info("======================");
		logger.info("======================");

	}
}

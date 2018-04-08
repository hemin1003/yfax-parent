package com.yfax.spider.toutiao.test;

import org.apache.log4j.Logger;

import com.yfax.spider.service.SpiderConfigService;
import com.yfax.spider.service.SpiderListService;
import com.yfax.spider.toutiao.model.Tags;
import com.yfax.spider.toutiao.thread.ThreadDetailBatch;

/**
 * 抓取详情 
 * @author Minbo
 */
public class DetailTest {
	
	protected static final Logger logger = Logger.getLogger(DetailTest.class);

	public static void main(String[] args) {
		SpiderListService spiderListService = new SpiderListService();
		SpiderConfigService spiderConfigService = new SpiderConfigService();
		
		System.out.println("------------------");
		System.out.println("------------------");
		// 2. 处理详情页面数据
		System.out.println();
		System.out.println("2. 处理详情页面数据...");

		logger.info("跑前半部分标签...");
		for (int i = 0; i < Tags.tagList.length / 2; i++) {
			ThreadDetailBatch detail = new ThreadDetailBatch(Tags.tagList[i].toString(), spiderListService, spiderConfigService);
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
			ThreadDetailBatch detail = new ThreadDetailBatch(Tags.tagList[i].toString(), spiderListService, spiderConfigService);
			detail.start();
		}
	}
}

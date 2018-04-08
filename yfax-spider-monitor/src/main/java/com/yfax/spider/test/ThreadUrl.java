package com.yfax.spider.test;

import org.apache.log4j.Logger;

public class ThreadUrl extends Thread{
	
	protected static final Logger logger = Logger.getLogger(ThreadUrl.class);

	@Override
	public void run() {
		try {
//			logger.info("线程信息：thread id=" + this.getId() + ", thread name=" + this.getName() + "，开始跑了...");
//			long start = System.currentTimeMillis(); // 获取开始时间
//			String cotent = new HttpRequestor().doGet("http://yc1616.cn/commons/_search?pretty");
			
			String cotent = new HttpRequestor().doPost("http://yc1616.cn/commons/_search?pretty", null);
			
//			String cotent = new HttpRequestor().doGet("http://yc1616.cn/commons/webpage/6475863172434625038");
//			logger.info(cotent);
//			long end = System.currentTimeMillis(); // 获取结束时间
//			logger.info("运行总耗费时间： " + (end - start) + " ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

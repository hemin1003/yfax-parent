package com.yfax.spider.test;

import org.apache.log4j.Logger;

public class Test {
	
	protected static final Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) throws Exception {
		logger.info("开始批量测试...");
        for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 50; j++) {
				new ThreadUrl().start();
			}
			logger.info("休眠一下...");
			Thread.sleep(5000);
		}
	}

}

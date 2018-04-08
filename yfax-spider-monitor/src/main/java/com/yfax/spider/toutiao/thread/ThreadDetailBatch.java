package com.yfax.spider.toutiao.thread;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.yfax.spider.service.SpiderConfigService;
import com.yfax.spider.service.SpiderListService;
import com.yfax.spider.toutiao.service.ToutiaoDetail;

public class ThreadDetailBatch extends Thread {

	protected static final Logger logger = Logger.getLogger(ThreadDetailBatch.class);

	private String myTag;
	private SpiderListService spiderListService;
	private SpiderConfigService spiderConfigService;

	public SpiderConfigService getSpiderConfigService() {
		return spiderConfigService;
	}

	public SpiderListService getSpiderListService() {
		return spiderListService;
	}

	public String getMyTag() {
		return myTag;
	}

	public ThreadDetailBatch(String myTag, SpiderListService spiderListService,
			SpiderConfigService spiderConfigService) {
		this.myTag = myTag;
		this.spiderListService = spiderListService;
		this.spiderConfigService = spiderConfigService;
	}

	@Override
	public void run() {
		ToutiaoDetail tDetail = new ToutiaoDetail();
		try {
			tDetail.processDetailInfo(this.myTag, this.spiderListService, spiderConfigService);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}

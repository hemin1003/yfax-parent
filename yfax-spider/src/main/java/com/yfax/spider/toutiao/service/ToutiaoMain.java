package com.yfax.spider.toutiao.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.yfax.spider.toutiao.model.Tags;
import com.yfax.spider.toutiao.model.Webpage;
import com.yfax.spider.toutiao.utils.Utils;
import com.yfax.utils.DateUtil;
import com.yfax.utils.StrUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

/**
 * 今日头条-文章列表数据抓取解析
 */
public class ToutiaoMain implements PageProcessor {

	protected static final Logger logger = Logger.getLogger(ToutiaoMain.class);
	
	private Site site = Site.me();;
	
	@Override
	public void process(Page page) {
		long start = System.currentTimeMillis();
		String json = page.getRawText();
		logger.info("======================================================");
		logger.info("==================解析列表数据 start=====================");
		logger.info("第一层解析...");
		logger.info("return_count=" + new JsonPathSelector("$.return_count").select(json));
		logger.info("has_more=" + new JsonPathSelector("$.has_more").select(json));
		logger.info("page_id=" + new JsonPathSelector("$.page_id").select(json));
		String pageTag = new JsonPathSelector("$.page_id").select(json);
		String myTag = pageTag.substring(1, pageTag.length()-1);
		logger.info("myTag=" + myTag);
		List<String> data = new JsonPathSelector("$.data").selectList(json);
		logger.info("data.size()=" + data.size());
		logger.info("============");
		logger.info("第二层解析...");
		for (int i = 0; i < data.size(); i++) {
			logger.info("===========================");
			logger.info("第" + (i + 1) + "条：" + data.get(i));
			String result = data.get(i);
			String article_tag = new JsonPathSelector("$.tag").select(result);
			String source = new JsonPathSelector("$.source").select(result);
			logger.info("article_tag=" + article_tag + ", source=" + source);
			// 跳过广告 和 专题
			if (!article_tag.equals("ad") && !StrUtil.null2Str(source).equals("专题")) {
				String id = new JsonPathSelector("$.item_id").select(result);	
				String item_id = new JsonPathSelector("$.item_id").select(result);
				long publish_time = Long.valueOf(new JsonPathSelector("$.publish_time").select(result));
				String title = new JsonPathSelector("$.title").select(result);
				String article_url = new JsonPathSelector("$.article_url").select(result);
				
				logger.info("article_url=" + article_url);
				List<String> image_list = new JsonPathSelector("$.image_list").selectList(result);
				StringBuffer imgsBuf = new StringBuffer();
				imgsBuf.append("[");  
				String imgs = "";
				for (int j = 0; j < image_list.size(); j++) {
					String result2 = image_list.get(j);
					String url = new JsonPathSelector("$.url").select(result2);
					imgs += url + ",";
				}
				if(!imgs.equals("")) {
					imgs = imgs.substring(0, imgs.length()-1);
				}
				imgsBuf.append(imgs);
				imgsBuf.append("]");
				
				Map<String, Object> dynamicFields = new HashMap<>();
				dynamicFields.put("imgs", imgsBuf.toString());
				long cost = System.currentTimeMillis() - start;
				
				Map<String, String> spiderMap = Utils.getSpirderInfo(myTag);
				
				Webpage webPage = new Webpage();
				webPage.setTitle(title);
				webPage.setUrl(article_url);
				webPage.setDomain(myTag);
				webPage.setSpiderUUID(spiderMap.get("spiderUUID"));
				webPage.setSpiderInfoId(spiderMap.get("spiderInfoId"));
				webPage.setCategory(source);
				webPage.setGathertime(DateUtil.dateToStamp2(DateUtil.getCurrentLongDateTime())*1000);
				webPage.setId(id);
				webPage.setItemId(item_id);
				webPage.setPublishTime(publish_time*1000);
				webPage.setDynamicFields(dynamicFields);
				webPage.setProcessTime(cost);
				
				webPage.setContent("");
				webPage.setKeywords(null);
				webPage.setSummary(null);
				webPage.setNamedEntity(null);
				
				logger.info("webPage=" + webPage.toString());
				
				logger.info("==================解析列表数据 end=====================");
				logger.info("======================================================");
				try {
					logger.info("===================================================");
					logger.info("==================开始列表数据入库====================");
					ProcessData pData = new ProcessData();
					pData.mainPage(webPage);
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

	@Override
	public Site getSite() {
		site.addHeader("Accept", "*/*");
		site.addHeader("Accept-Encoding", "gzip, deflate, br");
		site.addHeader("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2");
		site.addHeader("Connection", "keep-alive");
		site.addHeader("Cookie", Tags.cookieList[new Random().nextInt(Tags.cookieList.length) ]);
		site.addHeader("Host", "m.toutiao.com");
		site.addHeader("Referer", "https://m.toutiao.com/");
		site.addHeader("User-Agent", Tags.userAgentList[new Random().nextInt(Tags.userAgentList.length) ]);
		
		site.setTimeOut(60000);
		site.setCharset(Utils.CHARSET);
		site.setRetryTimes(2);
		return site;
	}
}

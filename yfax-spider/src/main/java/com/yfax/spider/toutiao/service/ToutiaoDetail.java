package com.yfax.spider.toutiao.service;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHits;

import com.yfax.spider.db.es.EsService;
import com.yfax.spider.service.SpiderConfigService;
import com.yfax.spider.service.SpiderListService;
import com.yfax.spider.toutiao.model.Tags;
import com.yfax.spider.toutiao.model.Webpage;
import com.yfax.spider.toutiao.utils.Utils;
import com.yfax.spider.vo.SpiderConfigVo;
import com.yfax.spider.vo.SpiderInfoVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.StrUtil;
import com.yfax.utils.UUID;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

/**
 * 今日头条-文章详情抓取解析
 */
public class ToutiaoDetail implements PageProcessor {
	
	protected static final Logger logger = Logger.getLogger(ToutiaoDetail.class);
	
	private Site site = Site.me();

    @Override
    public void process(Page page) {
    		try {
	    		String json = page.getRawText();
	    		logger.info("详情数据解析.......>>>");
	        	logger.info("第一层解析...");
	    		logger.info("success=" + new JsonPathSelector("$.success").select(json));
	    		List<String> data = new JsonPathSelector("$.data").selectList(json);
	    		logger.info("data.size()=" + data.size());
	    		logger.info("============");
	    		logger.info("第二层解析...");
	    		for (int i = 0; i < data.size(); i++) {
				logger.info("---------------");
	    			logger.info("第" + (i+1) + "条：" + data.get(i));
	    			String result = data.get(i);
	    			String content = new JsonPathSelector("$.content").select(result);
	    			String id = ToutiaoDetail.getId(page.getRequest().getUrl());
	    			logger.info("id=" + id);
	    			
	    			//处理详情内容图片，取出现的第一张，作为列表中显示
	    			String imgs = "";
	    			try {
		    			imgs = page.getHtml().xpath("//img/@src").get().toString();
		    			if(!StrUtil.null2Str(imgs).equals("")) {
		    				imgs = imgs.substring(2, imgs.length() - 2);
		    			}
				} catch (Exception e) {
					logger.warn("详情内容图片为空，继续处理，忽略.");
				}
	    			
				ProcessData pData = new ProcessData();
				pData.detailPage(id, content, EsService.getClient(), imgs);
	    		}
    		} catch (Exception e) {
			logger.error("详情数据解析异常：" + e.getMessage());
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
    
    public static String getId(String url) {
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(url);   
		return m.replaceAll("").trim();
    }
    
    @SuppressWarnings("unchecked")
	public Webpage getMainInfo(String id) throws UnknownHostException {
		//指定字段进行搜索
		QueryBuilder qb1 = termQuery("id", id);
	    int size = 1;
		int from = 0;
		SearchResponse response = null;
		try {
			response = EsService.getClient().prepareSearch("commons").setTypes("webpage")
			        .setQuery(qb1) 	// Query
			        .setFrom(from).setSize(size).setExplain(true)     
			        .execute()     
			        .actionGet();
		} catch (Exception e) {
			logger.error("搜索异常：id=" + id + ", msg=" + e.getMessage(), e);
		}
		Webpage webPage = new Webpage();
		if(response == null) {
			logger.info("------------------");
			logger.info("查询不到，新处理....id=" + id);
			return webPage;
		}
		SearchHits hits = response.getHits();
		if(hits.getTotalHits()<=0) {
			logger.info("------------------");
			logger.info("不存在，新处理....id=" + id);
			return webPage;
		}
		for (int i = 0; i < 1; i++) {
			
			webPage.setTitle((String) hits.getAt(i).getSource().get("title"));
			webPage.setUrl((String) hits.getAt(i).getSource().get("url"));
			webPage.setDomain((String) hits.getAt(i).getSource().get("domain"));
			webPage.setSpiderUUID((String) hits.getAt(i).getSource().get("spiderUUID"));
			webPage.setSpiderInfoId((String) hits.getAt(i).getSource().get("spiderInfoId"));
			webPage.setCategory((String) hits.getAt(i).getSource().get("category"));
			webPage.setGathertime(Long.valueOf(hits.getAt(i).getSource().get("gatherTime").toString()));
			webPage.setId((String) hits.getAt(i).getSource().get("id"));
			webPage.setItemId((String) hits.getAt(i).getSource().get("itemId"));
			webPage.setPublishTime(Long.valueOf(hits.getAt(i).getSource().get("publishTime").toString()));
			webPage.setDynamicFields((Map<String, Object>) hits.getAt(i).getSource().get("dynamicFields"));
			webPage.setProcessTime(Long.valueOf(hits.getAt(i).getSource().get("processTime").toString()));
			
			webPage.setContent((String) hits.getAt(i).getSource().get("content"));
			webPage.setKeywords((List<String>) hits.getAt(i).getSource().get("keywords"));
			webPage.setSummary((List<String>) hits.getAt(i).getSource().get("summary"));
			webPage.setNamedEntity((Map<String, Set<String>>) hits.getAt(i).getSource().get("namedEntity"));
			webPage.setFlag((int) hits.getAt(i).getSource().get("flag"));
		}
		return webPage;
	}
    
    public void processDetailInfo(String tag, SpiderListService spiderListService, SpiderConfigService spiderConfigService) throws UnknownHostException {
		long start=System.currentTimeMillis();   //获取开始时间
		logger.info("tag=" + tag + "，=============开始获取列表数据进行处理 start=================");
		
		//指定字段进行搜索
//		QueryBuilder qb1 = termQuery("domain", tag);
		
		//组合查询搜索
		QueryBuilder qb1 = boolQuery()
				.must(termQuery("domain", tag)) 
                .must(termQuery("flag", 0));
		
		//组合查询搜索
//		QueryBuilder qb2 = boolQuery()
//                .must(termQuery("onSale", "false"))     
//                .must(termQuery("content", "test4"))     
//                .mustNot(termQuery("content", "test2"))     
//                .should(termQuery("content", "test3"))
                ;  
		
        //过滤查询搜索
//        QueryBuilder qb3 = QueryBuilders.rangeQuery("price")
//        		.from(12).to(80);
//        
        int size = 3000;
		int from = 0;
//		//默认返回数据为10条
		SearchResponse response = null;
		try {
			response = EsService.getClient().prepareSearch("commons").setTypes("webpage")
			        .setQuery(qb1) 	// Query
			        .setFrom(from).setSize(size).setExplain(true)     
			        .execute()     
			        .actionGet();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		SearchHits hits = response.getHits();  
		logger.info("tag=" + tag + "，列表需处理数据总数：count=" + hits.getTotalHits());
		int count = 0;
		if(hits.getTotalHits() > 0) {
			String [] strArray = new String [(int) hits.getTotalHits()];
			for (int i = 0; i < hits.getTotalHits(); i++) {
				String id = hits.getAt(i).getSource().get("id").toString();
				String url = "https://m.toutiao.com/i" + hits.getAt(i).getSource().get("id") + "/info/";
				strArray[i] = url;
				logger.info("---------------");
				logger.info("id=" + id);
				logger.info("webPage=" + hits.getAt(i).getSource().toString());
				logger.info("不存在详情数据，添加抓取内容详情url=" + strArray[i]);
			}
			logger.info("================即将爬虫url详情内容的url列表==============");
			for (String string : strArray) {
				if(!StrUtil.null2Str(string).equals("")) {
					count++;
				}
			}
			String [] strArrayTmp = new String [count];
			int index = 0;
			for (String string : strArray) {
				if(!StrUtil.null2Str(string).equals("")) {
					strArrayTmp[index] = string;
					index++;
				}
			}
			logger.info("tag=" + tag + "，------------------>>>count=" + count);
			for (String string : strArrayTmp) {
				logger.info(string);
			}
			
			if(strArrayTmp.length > 0) {
				SpiderConfigVo spiderConfigVo = spiderConfigService.selectSpiderConfig(tag);
				logger.info("tag=" + tag + ", " + spiderConfigVo.toString());
				int finalReq = count>spiderConfigVo.getThreadCount() ? spiderConfigVo.getThreadCount():count;
				logger.info("并发请求数：finalReq=" + finalReq);
				Spider.create(new ToutiaoDetail()).addUrl(strArrayTmp).thread(finalReq).run();
			}
			logger.info("tag=" + tag + "，获得列表数据完成");
		}
		//记录抓取数据详情
		this.recordSpiderInfo(tag, count, spiderListService);
		long end=System.currentTimeMillis(); //获取结束时间
		logger.info("tag=" + tag + "，详情数据程序运行时间： "+(end-start)+" ms");
    }
    
    /**
     * 记录抓取数据详情
     */
    private void recordSpiderInfo(String tag, int count, SpiderListService spiderListService) {
    		try {
    			String currentTime = DateUtil.getCurrentDateTime();
    			String date = DateUtil.getCurrentDate();
    			SpiderInfoVo spiderInfoVo = new SpiderInfoVo();
    			spiderInfoVo.setDomain(tag);
    			spiderInfoVo.setDate(date);
    			spiderInfoVo = spiderListService.selectSpiderInfo(spiderInfoVo);
    			if(spiderInfoVo == null) {
    				spiderInfoVo = new SpiderInfoVo();
    				spiderInfoVo.setId(UUID.getUUID());
    				spiderInfoVo.setDomain(tag);
    				spiderInfoVo.setDomainName(Tags.getTagName(tag));
        			spiderInfoVo.setDate(date);
        			spiderInfoVo.setNewsNum(count);
        			spiderInfoVo.setCreateDate(currentTime);
        			spiderInfoVo.setUpdateDate(currentTime);
        			boolean flag = spiderListService.insertSpiderInfo(spiderInfoVo);
        			logger.info("tag=" + tag + "，tagName=" + Tags.getTagName(tag) + ", 新增统计记录结果：flag=" + flag 
        					+ "，新增新闻count数=" + count + " for insert");
    			}else {
    				spiderInfoVo.setNewsNum(spiderInfoVo.getNewsNum() + count);
    				spiderInfoVo.setUpdateDate(currentTime);
    				boolean flag = spiderListService.updateSpiderInfo(spiderInfoVo);
    				logger.info("tag=" + tag + "，tagName=" + Tags.getTagName(tag) + ", 新增统计记录结果：flag=" + flag 
        					+ "，新增新闻count数=" + count + " for update");
    			}
		} catch (Exception e) {
			logger.error("tag=" + tag + "记录失败：" + e.getMessage(), e);
		}
    }
    
}

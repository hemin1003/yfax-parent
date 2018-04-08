package com.yfax.spider.toutiao.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import com.yfax.spider.db.es.EsService;
import com.yfax.spider.nlp.HANLPExtractor;
import com.yfax.spider.nlp.NLPExtractor;
import com.yfax.spider.toutiao.model.Webpage;
import com.yfax.spider.toutiao.utils.Utils;
import com.yfax.utils.StrUtil;

/**
 * 存储进ES
 * @author Minbo
 */
public class ProcessData {
	
	protected static final Logger logger = Logger.getLogger(ProcessData.class);

	/**
	 * 列表数据
	 * @param webPage
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public boolean mainPage(Webpage webPage) throws IOException, ParseException {
		ToutiaoDetail tDetail = new ToutiaoDetail();
		Webpage webPageTmp = tDetail.getMainInfo(webPage.getId());
		if(webPageTmp != null && !StrUtil.null2Str(webPageTmp.getContent()).equals("")) {
			logger.info("已存在记录content，跳过处理。id = " + webPageTmp.getId());
			return false;
		}else {
			logger.info("==============添加列表数据 start ===============");
			webPage.setFlag(0);
			// 2. 添加索引数据
			XContentBuilder doc = XContentFactory.jsonBuilder()
					.startObject()
						.field("title", webPage.getTitle())
						.field("url", webPage.getUrl())
						.field("domain", webPage.getDomain())
						.field("spiderUUID", webPage.getSpiderUUID())
						.field("spiderInfoId", webPage.getSpiderInfoId())
						.field("category", webPage.getCategory())
						.field("gatherTime", webPage.getGathertime())
						.field("id", webPage.getId())
						.field("itemId", webPage.getItemId())
						.field("publishTime", webPage.getPublishTime())
						.field("dynamicFields", webPage.getDynamicFields())
						.field("processTime", webPage.getProcessTime())

						.field("content", webPage.getContent())
						.field("keywords", webPage.getKeywords())
						.field("summary", webPage.getSummary())
						.field("namedEntity", webPage.getNamedEntity())
						.field("flag", webPage.getFlag())
					.endObject();

			IndexResponse response = EsService.getClient().prepareIndex("commons", "webpage", webPage.getId()).setSource(doc).execute()
					.actionGet();
			logger.info("id = " + webPage.getId());
			logger.info("result = " + response.getResult());

			logger.info("列表数据 Done");
			logger.info("==============添加列表数据 end ===============");
			logger.info("=============================================");
			
			return true;
		}
	}

	/**
	 * 详情数据
	 * @param id
	 * @param content
	 * @param client
	 * @throws IOException
	 * @throws ParseException
	 */
	public void detailPage(String id, String content, Client client, String imgs) throws IOException, ParseException {
		ToutiaoDetail tDetail = new ToutiaoDetail();
		Webpage webPage = tDetail.getMainInfo(id);
		if(webPage.getFlag() == 1) {
			logger.info("id=" + webPage.getId() + ", 已存在详情数据了，重复记录，跳过处理。");
		}else {
			logger.info("id=" + webPage.getId() + ", 不存在详情数据，新处理。");
			
			String contentTmp = content;
			contentTmp = contentTmp.replace(Utils.HTML_PREFIX, "");
			contentTmp = contentTmp.replace(Utils.HTML_SUFFIX, "");
			if(StrUtil.null2Str(contentTmp).equals("")) {
				logger.warn("id=" + webPage.getId() + ", 文章无任何内容，跳过处理。contentTmp=" + contentTmp);
				
			}else {
				//当列表没有图时（长度为9，即为空），取详情内容第一张图
				if(webPage.getDynamicFields() == null || webPage.getDynamicFields().toString().length() <= 9) {
					if(!StrUtil.null2Str(imgs).equals("")) {
						Map<String, Object> dynamicFields = new HashMap<>();
						dynamicFields.put("imgs", "[" + imgs + "]");
						webPage.setDynamicFields(dynamicFields);
					}
				}
				
				String contentWithoutHtml = ProcessData.formatHtml(content);
				NLPExtractor extractor = new HANLPExtractor();
	//			NLPExtractor summaryExtractor = new HANLPExtractor();
	//			NLPExtractor namedEntitiesExtractor = new HANLPExtractor();
				
				List<String> keywords = new ArrayList<>();
				List<String> summary = new ArrayList<>();
				Map<String, Set<String>> namedEntity = new HashMap<>();
				if(contentWithoutHtml.equals("")) {
					// 抽取关键词（标题）, 10个词
					keywords = extractor.extractKeywords(formatHtml(webPage.getTitle()));
					webPage.setKeywords(keywords);
					webPage.setSummary(null);
					webPage.setNamedEntity(null);
					
				}else {
					// 抽取关键词（内容+标题）, 10个词
					keywords = extractor.extractKeywords(contentWithoutHtml + "。" + formatHtml(webPage.getTitle()));
					// 抽取摘要, 5句话
					summary = extractor.extractSummary(contentWithoutHtml);
					// 抽取命名实体
					try {
						namedEntity = extractor.extractNamedEntity(contentWithoutHtml);
					} catch (Exception e) {
						logger.error("抽取命名实体异常：" + e.getMessage(), e);
					}
					webPage.setKeywords(keywords);
					webPage.setSummary(summary);
					webPage.setNamedEntity(namedEntity);
				}
				
				webPage.setContent(content);
				webPage.setFlag(1);
			
//				logger.info("content=" + webPage.getContent());
//				logger.info("keywords=" + webPage.getKeywords());
//				logger.info("summary=" + webPage.getSummary());
//				logger.info("namedEntity=" + webPage.getNamedEntity());

				logger.info("===========================================");
				logger.info("==============详情数据处理 stat============");
				// 2. 添加索引数据
				XContentBuilder doc = XContentFactory.jsonBuilder()
						.startObject()
							.field("title", webPage.getTitle())
							.field("url", webPage.getUrl())
							.field("domain", webPage.getDomain())
							.field("spiderUUID", webPage.getSpiderUUID())
							.field("spiderInfoId", webPage.getSpiderInfoId())
							.field("category", webPage.getCategory())
							.field("gatherTime", webPage.getGathertime())
							.field("id", webPage.getId())
							.field("itemId", webPage.getItemId())
							.field("publishTime", webPage.getPublishTime())
							.field("dynamicFields", webPage.getDynamicFields())
							.field("processTime", webPage.getProcessTime())

							.field("content", Utils.HTML_PREFIX + webPage.getContent() + Utils.HTML_SUFFIX)
							.field("keywords", webPage.getKeywords())
							.field("summary", webPage.getSummary())
							.field("namedEntity", webPage.getNamedEntity())
							.field("flag", webPage.getFlag())
						.endObject();

				IndexResponse response = client.prepareIndex("commons", "webpage", webPage.getId()).setSource(doc).execute()
						.actionGet();
				logger.info("id=" + id + ", result=" + response.getResult());

				logger.info("详情数据 Done");
				logger.info("==============详情数据处理 end============");
				logger.info("===========================================");
			}
		}
	}

	/**
	 * 清除内容标签数据，只保留文本
	 * @param content
	 * @return
	 */
	public static String formatHtml(String content) {
		content = content.replace("</p>", "***");
		content = content.replace("<BR>", "***");
		content = content.replaceAll("<([\\s\\S]*?)>", "");
		content = content.replace("***", "<br/>");
		content = content.replace("\n", "<br/>");
		content = content.replaceAll("(\\<br/\\>\\s*){2,}", "<br/> ");
		content = content.replaceAll("(&nbsp;\\s*)+", " ");
		return content.replaceAll("<br/>", "");
	}
}
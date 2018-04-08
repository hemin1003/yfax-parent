package com.yfax.spider.nlp;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * NLPExtractor
 * 地址：https://github.com/hemin1003/HanLP
 * 
 * HanLP是由一系列模型与算法组成的Java工具包，目标是普及自然语言处理在生产环境中的应用。
 * HanLP具备功能完善、性能高效、架构清晰、语料时新、可自定义的特点。
 */
public interface NLPExtractor {
    /**
     * 抽取命名实体
     *
     * @param content 文章正文
     * @return map的key是一下三种nr, ns, nt  其value就是对应的词表
     */
    Map<String, Set<String>> extractNamedEntity(String content);

    /**
     * 抽取摘要
     *
     * @param content 文章正文
     * @return 摘要句子列表
     */
    List<String> extractSummary(String content);

    /**
     * 抽取关键词
     *
     * @param content 文章正文
     * @return 关键词列表
     */
    List<String> extractKeywords(String content);
}

package com.yfax.webapi.htt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.JsonUtils;
import com.yfax.webapi.htt.dao.NoticeConfigDao;
import com.yfax.webapi.htt.vo.NoticeConfigVo;

/**
 * 初始化配置数据
 * @author Minbo
 */
@Service
public class NoticeConfigService{
	
	protected static Logger logger = LoggerFactory.getLogger(NoticeConfigService.class);
	
	@Autowired
	private NoticeConfigDao dao ;
	
	public List<NoticeConfigVo> selectNoticeConfig() {
		List<NoticeConfigVo> noticeConfigVos = this.dao.selectNoticeConfig();
		for (NoticeConfigVo noticeConfigVo : noticeConfigVos) {
			noticeConfigVo.setAction(JsonUtils.JsonArrayToList(noticeConfigVo.getAction().toString()));
		}
		return noticeConfigVos;
	}

}

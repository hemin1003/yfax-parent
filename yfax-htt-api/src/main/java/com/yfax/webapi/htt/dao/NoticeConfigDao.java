package com.yfax.webapi.htt.dao;

import java.util.List;

import com.yfax.webapi.htt.vo.NoticeConfigVo;

public interface NoticeConfigDao {
		
	public List<NoticeConfigVo> selectNoticeConfig();
	
}

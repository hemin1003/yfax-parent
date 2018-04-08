package com.yfax.webapi.htt.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.NoticeConfigDao;
import com.yfax.webapi.htt.vo.NoticeConfigVo;


@Component
public class NoticeConfigDaoImpl implements NoticeConfigDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public List<NoticeConfigVo> selectNoticeConfig() {
		return this.sqlSessionTemplate.selectList("selectNoticeConfig");
	}

}

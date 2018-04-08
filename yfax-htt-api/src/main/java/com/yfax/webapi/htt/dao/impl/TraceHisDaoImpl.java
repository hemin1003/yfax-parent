package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yfax.webapi.htt.dao.TraceHisDao;
import com.yfax.webapi.htt.vo.TraceHisVo;

@Component
public class TraceHisDaoImpl implements TraceHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public boolean insertTraceHis(TraceHisVo traceHis) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertTraceHis", traceHis);
		return i > 0 ? true : false;
	}
	
	@Override
	public boolean updateTraceHis(TraceHisVo TraceHis) throws Exception {
		int i = this.sqlSessionTemplate.insert("updateTraceHis", TraceHis);
		return i > 0 ? true : false;
	}

	@Override
	public TraceHisVo selectTraceHisByUid(String uId) {
		return this.sqlSessionTemplate.selectOne("selectTraceHisByUid", uId);
	}

}

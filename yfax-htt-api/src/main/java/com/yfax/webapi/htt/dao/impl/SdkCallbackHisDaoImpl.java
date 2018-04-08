package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.SdkCallbackHisDao;
import com.yfax.webapi.htt.vo.SdkCallbackHisVo;

@Component
public class SdkCallbackHisDaoImpl implements SdkCallbackHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertSdkCallbackHis(SdkCallbackHisVo sdkCallbackHis) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertSdkCallbackHis", sdkCallbackHis);
		return i > 0 ? true : false;
	}
}

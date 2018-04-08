//package com.yfax.webapi.htt.dao.impl;
//
//import java.util.Map;
//
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.yfax.webapi.htt.dao.LoginHisDao;
//import com.yfax.webapi.htt.vo.LoginHisVo;
//
//
//@Component
//public class LoginHisDaoImpl implements LoginHisDao {
//
//	@Autowired
//	private SqlSessionTemplate sqlSessionTemplate;;
//	
//	@Override
//	@Transactional
//	public boolean insertLoginHis(LoginHisVo loginHis) throws Exception {
//		int i = this.sqlSessionTemplate.insert("insertLoginHis", loginHis);
//		return i > 0 ? true : false;
//	}
//
//	@Override
//	public LoginHisVo selectLoginHisDate(String phoneNum) {
//		return this.sqlSessionTemplate.selectOne("selectLoginHisDate", phoneNum);
//	}
//
//	@Override
//	public boolean updateLoginHis(LoginHisVo loginHis) throws Exception {
//		int i = this.sqlSessionTemplate.insert("updateLoginHis", loginHis);
//		return i > 0 ? true : false;
//	}
//
//	@Override
//	public Long selectCountOfLoginHisImei(Map<String, Object> params) {
//		return this.sqlSessionTemplate.selectOne("selectCountOfLoginHisImei", params);
//	}
//
//	@Override
//	public Long selectCountOfLoginHisPhoneNum(Map<String, Object> params) {
//		return this.sqlSessionTemplate.selectOne("selectCountOfLoginHisPhoneNum", params);
//	}
//
//}

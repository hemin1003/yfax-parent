package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.ImgCodeDao;
import com.yfax.webapi.htt.vo.ImgCodeVo;

@Component
public class ImgCodeDaoImpl implements ImgCodeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertImgCode(ImgCodeVo imgCodeVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertImgCode", imgCodeVo);
		return i > 0 ? true : false;
	}
	
	@Override
	public boolean updateImgCode(ImgCodeVo imgCodeVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("updateImgCode", imgCodeVo);
		return i > 0 ? true : false;
	}

	@Override
	public ImgCodeVo selectImgCodeByPhoneNum(ImgCodeVo imgCodeVo) {
		return this.sqlSessionTemplate.selectOne("selectImgCodeByPhoneNum", imgCodeVo);
	}

	@Override
	public ImgCodeVo selectLastestImgCodeByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectLastestImgCodeByPhoneNum", phoneNum);
	}
	
}
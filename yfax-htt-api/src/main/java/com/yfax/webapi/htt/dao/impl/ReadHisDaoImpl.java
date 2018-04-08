package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.ReadHisDao;
import com.yfax.webapi.htt.vo.ReadHisVo;


@Component
public class ReadHisDaoImpl implements ReadHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertReadHis(ReadHisVo readHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertReadHis", readHisVo);
		return i > 0 ? true : false;
	}

//	@Override
//	public List<ReadHisVo> selectReadHisByPhoneNum(String phoneNum) {
//		return this.sqlSessionTemplate.selectList("selectReadHisByPhoneNum", phoneNum);
//	}

	@Override
	public Long selectReadHisCountByPhoneNum(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectReadHisCountByPhoneNum", map);
	}

	@Override
	@Transactional
	public boolean updateReadHis(ReadHisVo readHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("updateReadHis", readHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public ReadHisVo selectReadHisById(String id) {
		return this.sqlSessionTemplate.selectOne("selectReadHisById", id);
	}

	@Override
	public ReadHisVo selectReadHisByPhoneNumAndPrimaryKey(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectReadHisByPhoneNumAndPrimaryKey", map);
	}

	@Override
	public boolean insertReadHisInfo(ReadHisVo readHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertReadHisInfo", readHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public Long selectReadHisByAwardCount(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectReadHisByAwardCount", map);
	}

	@Override
	public ReadHisVo selectReadInfoHisByPhoneNumAndPrimaryKey(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectReadInfoHisByPhoneNumAndPrimaryKey", map);
	}

}

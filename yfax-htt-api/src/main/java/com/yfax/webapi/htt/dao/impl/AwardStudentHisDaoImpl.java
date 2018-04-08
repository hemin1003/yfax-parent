package com.yfax.webapi.htt.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.AwardStudentHisDao;
import com.yfax.webapi.htt.vo.AwardStudentHisVo;

@Component
public class AwardStudentHisDaoImpl implements AwardStudentHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public List<AwardStudentHisVo> selectAwardStudentHisList(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectAwardStudentHisList", phoneNum);
	}
	
	@Override
	@Transactional
	public boolean insertAwardStudentHis(AwardStudentHisVo AwardStudentHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertAwardStudentHis", AwardStudentHisVo);
		return i > 0 ? true : false;
	}

}
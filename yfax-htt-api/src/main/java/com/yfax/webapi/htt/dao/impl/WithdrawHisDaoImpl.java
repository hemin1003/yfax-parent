package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.WithdrawHisDao;
import com.yfax.webapi.htt.vo.WithdrawHisVo;

@Component
public class WithdrawHisDaoImpl implements WithdrawHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public List<WithdrawHisVo> selectWithdrawHis(String phoneId) {
		return this.sqlSessionTemplate.selectList("selectWithdrawHis", phoneId);
	}

	@Override
	@Transactional
	public boolean insertWithdrawHis(WithdrawHisVo withdrawHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertWithdrawHis", withdrawHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public long selectCountByWithdrawHisType(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectCountByWithdrawHisType", map);
	}

	@Override
	public long selectWithdrawHisCountByPhoneNumAndTime(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectWithdrawHisCountByPhoneNumAndTime", map);
	}

}

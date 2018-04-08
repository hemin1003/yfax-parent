package com.yfax.webapi.htt.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.BalanceHisDao;
import com.yfax.webapi.htt.vo.BalanceHisVo;


@Component
public class BalanceHisDaoImpl implements BalanceHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertBalanceHis(BalanceHisVo balanceHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertBalanceHis", balanceHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public List<BalanceHisVo> selectBalanceHisByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectBalanceHisByPhoneNum", phoneNum);
	}

	@Override
	public long selectBalanceHisByType(BalanceHisVo balanceHisVo) {
		return this.sqlSessionTemplate.selectOne("selectBalanceHisByType", balanceHisVo);
	}

}

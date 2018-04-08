package com.yfax.task.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttWithdrawHisDao;
import com.yfax.task.htt.vo.HttWithdrawHisVo;

@Component
public class HttWithdrawHisDaoImpl implements HttWithdrawHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public List<HttWithdrawHisVo> selectHttWithdrawHis() {
		return this.sqlSessionTemplate.selectList("selectHttWithdrawHis");
	}

	@Override
	public boolean updateHttWithdrawHis(HttWithdrawHisVo httWithdrawHisVo) throws Exception {
		int i = this.sqlSessionTemplate.update("updateHttWithdrawHis", httWithdrawHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public List<HttWithdrawHisVo> selectHttWithdrawHisByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectHttWithdrawHisByPhoneNum", phoneNum);
	}

	@Override
	public boolean updateHttWithdrawHisForBlackList(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.update("updateHttWithdrawHisForBlackList", map);
		return i > 0 ? true : false;
	}

	@Override
	public List<HttWithdrawHisVo> selectHttWithdrawHisByProve(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("selectHttWithdrawHisByProve", map);
	}

	@Override
	public List<HttWithdrawHisVo> selectHttWithdrawHisForAlipay() {
		return this.sqlSessionTemplate.selectList("selectHttWithdrawHisForAlipay");
	}
}

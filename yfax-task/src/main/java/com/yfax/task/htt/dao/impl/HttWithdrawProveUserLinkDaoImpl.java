package com.yfax.task.htt.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttWithdrawProveUserLinkDao;
import com.yfax.task.htt.vo.HttWithdrawProveUserLinkVo;

@Component
public class HttWithdrawProveUserLinkDaoImpl implements HttWithdrawProveUserLinkDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public boolean insertHttWithdrawProveUserLink(HttWithdrawProveUserLinkVo httWithdrawProveUserLinkVo)
			throws Exception {
		int i = this.sqlSessionTemplate.insert("insertHttWithdrawProveUserLink", httWithdrawProveUserLinkVo);
		return i > 0 ? true : false;
	}

	@Override
	public boolean batchInsertHttWithdrawProveUserLink(List<HttWithdrawProveUserLinkVo> list) throws Exception {
		int i = this.sqlSessionTemplate.insert("batchInsertHttWithdrawProveUserLink", list);
		return i > 0 ? true : false;
	}

	@Override
	public boolean deleteByPid(String pid) throws Exception {
		int i = this.sqlSessionTemplate.delete("deleteByPid", pid);
		return i > 0 ? true : false;
	}

}

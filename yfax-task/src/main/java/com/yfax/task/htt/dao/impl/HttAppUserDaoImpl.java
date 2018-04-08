package com.yfax.task.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttAppUserDao;
import com.yfax.task.htt.vo.HttAppUserVo;
import com.yfax.task.htt.vo.HttTestVo;

@Component
public class HttAppUserDaoImpl implements HttAppUserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public boolean updateUser(HttAppUserVo appUserVo) {
		int i = this.sqlSessionTemplate.update("updateHttUser", appUserVo);
		return i > 0 ? true : false;
	}


	@Override
	public List<HttAppUserVo> selectAllUser() {
		return this.sqlSessionTemplate.selectList("selectHttAllUser");
	}


	@Override
	public HttAppUserVo selectHttAppUserVoByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectHttAppUserVoByPhoneNum", phoneNum);
	}


	@Override
	public Long selectByRestGold(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectByRestGold", phoneNum);
	}


	@Override
	public Long selectByTotalGold(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectByTotalGold", phoneNum);
	}


	@Override
	public Long selectByStudentTotalGold(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectByStudentTotalGold", phoneNum);
	}


	@Override
	public List<HttAppUserVo> selectHttAllUserNotBlackList() {
		return this.sqlSessionTemplate.selectList("selectHttAllUserNotBlackList");
	}


	@Override
	public List<HttAppUserVo> selectMasterByStudentNums(String currentTime) {
		return this.sqlSessionTemplate.selectList("selectMasterByStudentNums", currentTime);
	}


	@Override
	public boolean updateHttUserForBlackList(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.update("updateHttUserForBlackList", map);
		return i > 0 ? true : false;
	}


	@Override
	public List<HttAppUserVo> selectHttAllUserByDate(String currentTime) {
		return this.sqlSessionTemplate.selectList("selectHttAllUserByDate", currentTime);
	}


	@Override
	public List<HttAppUserVo> selectHttAllUserByCalIps(String currentTime) {
		return this.sqlSessionTemplate.selectList("selectHttAllUserByCalIps", currentTime);
	}


	@Override
	public List<HttAppUserVo> selectHttAllUserByUserPwds(String currentTime) {
		return this.sqlSessionTemplate.selectList("selectHttAllUserByUserPwds", currentTime);
	}


	@Override
	public List<HttAppUserVo> selectHttAllUserByLoginHisWifiMacs(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList("selectHttAllUserByLoginHisWifiMacs", params);
	}
	
	@Override
	public List<HttAppUserVo> selectHttAllUserByLoginHisLocations(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList("selectHttAllUserByLoginHisLocations", params);
	}
	
	@Override
	public List<HttAppUserVo> selectHttAllUserByLoginHisInstallListMd5(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList("selectHttAllUserByLoginHisInstallListMd5", params);
	}
	
	@Override
	public boolean batchUpdateHttAllUser(List<HttAppUserVo> list) {
		int i = this.sqlSessionTemplate.update("batchUpdateHttAllUser", list);
		return i > 0 ? true : false;
	}
	
	@Override
	public List<HttTestVo> selectByTestDataIds(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList("selectByTestDataIds", params);
	}
	
	@Override
	public List<HttTestVo> selectByTestData(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList("selectByTestData", params);
	}


	@Override
	public boolean deleteTestDataAwardById(String aid) {
		int i = this.sqlSessionTemplate.update("deleteTestDataAwardById", aid);
		return i > 0 ? true : false;
	}


	@Override
	public boolean updateTestDataStudentAwardHis(String bid) {
		int i = this.sqlSessionTemplate.update("updateTestDataStudentAwardHis", bid);
		return i > 0 ? true : false;
	}


	@Override
	public Long selecgCountByTestData(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectByRestGold", phoneNum);
	}


	@Override
	public boolean updateTestDataUser(String phoneNum) {
		int i = this.sqlSessionTemplate.update("updateTestDataUser", phoneNum);
		return i > 0 ? true : false;
	}


	@Override
	public List<HttTestVo> selectByTestDataCheckIns(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList("selectByTestDataCheckIns", params);
	}


	@Override
	public boolean deleteByTestDataCheckIns(Map<String, Object> params) {
		int i = this.sqlSessionTemplate.update("deleteByTestDataCheckIns", params);
		return i > 0 ? true : false;
	}
}

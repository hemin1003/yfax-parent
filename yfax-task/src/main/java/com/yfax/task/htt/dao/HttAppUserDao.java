package com.yfax.task.htt.dao;

import java.util.List;
import java.util.Map;
import com.yfax.task.htt.vo.HttAppUserVo;
import com.yfax.task.htt.vo.HttTestVo;

public interface HttAppUserDao {
	public boolean updateUser(HttAppUserVo appUserVo);
	public List<HttAppUserVo> selectAllUser();
	public HttAppUserVo selectHttAppUserVoByPhoneNum(String phoneNum);
	public Long selectByRestGold(String phoneNum);
	public Long selectByTotalGold(String phoneNum);
	public Long selectByStudentTotalGold(String phoneNum);
	public List<HttAppUserVo> selectHttAllUserNotBlackList();
	public List<HttAppUserVo> selectMasterByStudentNums(String currentTime);
	public boolean updateHttUserForBlackList(Map<String, Object> map);
	public List<HttAppUserVo> selectHttAllUserByDate(String currentTime);
	public List<HttAppUserVo> selectHttAllUserByCalIps(String currentTime);
	public List<HttAppUserVo> selectHttAllUserByUserPwds(String currentTime);
	public List<HttAppUserVo> selectHttAllUserByLoginHisWifiMacs(Map<String, Object> params);
	public List<HttAppUserVo> selectHttAllUserByLoginHisLocations(Map<String, Object> params);
	public List<HttAppUserVo> selectHttAllUserByLoginHisInstallListMd5(Map<String, Object> params);
	public boolean batchUpdateHttAllUser(List<HttAppUserVo> list);
	
	public List<HttTestVo> selectByTestDataIds(Map<String, Object> params);
	public List<HttTestVo> selectByTestData(Map<String, Object> params);
	public boolean deleteTestDataAwardById(String aid);
	public boolean updateTestDataStudentAwardHis(String bid);
	public Long selecgCountByTestData(String phoneNum);
	public boolean updateTestDataUser(String phoneNum);
	public List<HttTestVo> selectByTestDataCheckIns(Map<String, Object> params);
	public boolean deleteByTestDataCheckIns(Map<String, Object> params);
	
}

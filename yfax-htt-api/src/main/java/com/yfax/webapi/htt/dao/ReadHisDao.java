package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.ReadHisVo;

public interface ReadHisDao {
	public boolean insertReadHis(ReadHisVo readHisVo) throws Exception;
	public boolean insertReadHisInfo(ReadHisVo readHisVo) throws Exception;
	public boolean updateReadHis(ReadHisVo readHisVo) throws Exception;
//	public List<ReadHisVo> selectReadHisByPhoneNum(String phoneNum);
	public Long selectReadHisCountByPhoneNum(Map<String, Object> map);
	public ReadHisVo selectReadHisById(String id);
	public ReadHisVo selectReadHisByPhoneNumAndPrimaryKey(Map<String, Object> map);
	public Long selectReadHisByAwardCount(Map<String, Object> map);
	public ReadHisVo selectReadInfoHisByPhoneNumAndPrimaryKey(Map<String, Object> map);
}

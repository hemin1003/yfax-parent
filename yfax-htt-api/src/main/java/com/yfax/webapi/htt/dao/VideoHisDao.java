package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.VideoHisVo;

public interface VideoHisDao {
	public boolean insertVideoHis(VideoHisVo videoHisVo) throws Exception;
	public boolean insertVideoHisInfo(VideoHisVo videoHisVo) throws Exception;
	public boolean updateVideoHis(VideoHisVo videoHisVo) throws Exception;
	public List<VideoHisVo> selectVideoHisByPhoneNum(String phoneNum);
	public Long selectVideoHisCountByPhoneNum(Map<String, Object> map);
	public VideoHisVo selectVideoHisById(String id);
	public VideoHisVo selectVideoHisByPhoneNumAndPrimaryKey(Map<String, Object> map);
	public Long selectVideoHisByAwardCount(Map<String, Object> map);
	public VideoHisVo selectVideoInfoHisByPhoneNumAndPrimaryKey(Map<String, Object> map);
}

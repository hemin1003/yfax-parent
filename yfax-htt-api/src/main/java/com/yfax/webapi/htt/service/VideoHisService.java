package com.yfax.webapi.htt.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.VideoHisDao;
import com.yfax.webapi.htt.vo.VideoHisVo;

/**
 * 用户视频浏览历史
 * @author Minbo
 */
@Service
public class VideoHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(VideoHisService.class);
	
	@Autowired
	private VideoHisDao videoHisDao;
	
	@Transactional
	public boolean addVideoHis(VideoHisVo videoHisVo){
		try {
			return this.videoHisDao.insertVideoHis(videoHisVo);
		} catch (Exception e) {
			logger.error("新增用户视频历史异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	@Transactional
	public boolean addVideoHisInfo(VideoHisVo videoHisVo){
		try {
			return this.videoHisDao.insertVideoHisInfo(videoHisVo);
		} catch (Exception e) {
			logger.error("新增用户视频历史异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	@Transactional
	public boolean modifyVideoHis(VideoHisVo videoHisVo){
		try {
			return this.videoHisDao.updateVideoHis(videoHisVo);
		} catch (Exception e) {
			logger.error("修改用户视频历史异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public List<VideoHisVo> selectVideoHisByPhoneNum(String phoneNum) {
		return this.videoHisDao.selectVideoHisByPhoneNum(phoneNum);
	}
	
	public Long selectCountByPhoneNum(Map<String, Object> map) {
		return this.videoHisDao.selectVideoHisCountByPhoneNum(map);
	}
	
	public VideoHisVo selectVideoHisById(String id){
		return this.videoHisDao.selectVideoHisById(id);
	}
	
	public VideoHisVo selectVideoHisByPhoneNumAndPrimaryKey(Map<String, Object> map){
		return this.videoHisDao.selectVideoHisByPhoneNumAndPrimaryKey(map);
	}
	
	public Long getVideoHisByAwardCount(Map<String, Object> map) {
		return this.videoHisDao.selectVideoHisByAwardCount(map);
	}
	
	public VideoHisVo selectVideoInfoHisByPhoneNumAndPrimaryKey(Map<String, Object> map){
		return this.videoHisDao.selectVideoInfoHisByPhoneNumAndPrimaryKey(map);
	}
	
}

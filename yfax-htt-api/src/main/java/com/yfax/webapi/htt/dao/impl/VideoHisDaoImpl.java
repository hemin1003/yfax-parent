package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.VideoHisDao;
import com.yfax.webapi.htt.vo.VideoHisVo;


@Component
public class VideoHisDaoImpl implements VideoHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertVideoHis(VideoHisVo readHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertVideoHis", readHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public List<VideoHisVo> selectVideoHisByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectVideoHisByPhoneNum", phoneNum);
	}

	@Override
	public Long selectVideoHisCountByPhoneNum(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectVideoHisCountByPhoneNum", map);
	}

	@Override
	@Transactional
	public boolean updateVideoHis(VideoHisVo readHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("updateVideoHis", readHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public VideoHisVo selectVideoHisById(String id) {
		return this.sqlSessionTemplate.selectOne("selectVideoHisById", id);
	}

	@Override
	public VideoHisVo selectVideoHisByPhoneNumAndPrimaryKey(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectVideoHisByPhoneNumAndPrimaryKey", map);
	}

	@Override
	public boolean insertVideoHisInfo(VideoHisVo readHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertVideoHisInfo", readHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public Long selectVideoHisByAwardCount(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectVideoHisByAwardCount", map);
	}

	@Override
	public VideoHisVo selectVideoInfoHisByPhoneNumAndPrimaryKey(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectVideoInfoHisByPhoneNumAndPrimaryKey", map);
	}
}

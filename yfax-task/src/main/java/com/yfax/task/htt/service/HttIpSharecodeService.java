package com.yfax.task.htt.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttIpSharecodeDao;
import com.yfax.task.htt.vo.HttIpSharecodeVo;
import com.yfax.utils.AesUtil;
import com.yfax.utils.DateUtil;
import com.yfax.utils.HttpRequestUtil;
import com.yfax.utils.StrUtil;

/**
 * 处理失败天翼任务服务
 * 
 * @author Minbo
 */
@Service
public class HttIpSharecodeService {

	protected static Logger logger = LoggerFactory.getLogger(HttIpSharecodeService.class);

	@Autowired
	private HttIpSharecodeDao httIpSharecodeDao;
	
	public List<HttIpSharecodeVo> selectHttIpSharecodeUnFinished(){
		List<HttIpSharecodeVo> list = this.httIpSharecodeDao.selectHttIpSharecodeUnFinished();
		if(list.size() > 0) {
			return list;
		}else {
			return null ;
		}
	}
	
	public void processTianYiUnFinished(List<HttIpSharecodeVo> httIpSharecodeVoList) {
		 
	}
}
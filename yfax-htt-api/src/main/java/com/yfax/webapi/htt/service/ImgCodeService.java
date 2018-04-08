package com.yfax.webapi.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.htt.dao.ImgCodeDao;
import com.yfax.webapi.htt.vo.ImgCodeVo;

/**
 * 图形码记录
 * @author Minbo
 */
@Service
public class ImgCodeService{
	
	protected static Logger logger = LoggerFactory.getLogger(ImgCodeService.class);
	
	@Autowired
	private ImgCodeDao dao;
	
	public boolean addImgCodeVo(String phoneNum, String imgCode){
		try {
			String cTime = DateUtil.getCurrentLongDateTime();
			ImgCodeVo imgCodeVo = new ImgCodeVo();
			imgCodeVo.setId(UUID.getUUID());
			imgCodeVo.setPhoneNum(phoneNum);
			imgCodeVo.setImgCode(imgCode);
			imgCodeVo.setCreateDate(cTime);
			imgCodeVo.setUpdateDate(cTime);
			return this.dao.insertImgCode(imgCodeVo);
		} catch (Exception e) {
			logger.error("记录图形码异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public boolean modifyImgCode(String imgCodeVoId){
		try {
			String cTime = DateUtil.getCurrentLongDateTime();
			ImgCodeVo imgCodeVo = new ImgCodeVo();
			imgCodeVo.setId(imgCodeVoId);
			imgCodeVo.setIsUsed(1);
			imgCodeVo.setUpdateDate(cTime);
			return this.dao.updateImgCode(imgCodeVo);
		} catch (Exception e) {
			logger.error("更新图形码异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public ImgCodeVo getImgCodeByPhoneNum(ImgCodeVo imgCodeVo) {
		return this.dao.selectImgCodeByPhoneNum(imgCodeVo);
	}
	
	/**
	 * 查最新的一条，是否通过了验证
	 * @param phoneNum
	 * @return
	 */
	public ImgCodeVo getLastestImgCodeByPhoneNum(String phoneNum) {
		return this.dao.selectLastestImgCodeByPhoneNum(phoneNum);
	}
}

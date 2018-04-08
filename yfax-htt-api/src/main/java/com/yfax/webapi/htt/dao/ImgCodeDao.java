package com.yfax.webapi.htt.dao;

import com.yfax.webapi.htt.vo.ImgCodeVo;

public interface ImgCodeDao {
	public boolean insertImgCode(ImgCodeVo imgCodeVo) throws Exception;
	public boolean updateImgCode(ImgCodeVo imgCodeVo) throws Exception;
	public ImgCodeVo selectImgCodeByPhoneNum(ImgCodeVo imgCodeVo);
	public ImgCodeVo selectLastestImgCodeByPhoneNum(String phoneNum);
}

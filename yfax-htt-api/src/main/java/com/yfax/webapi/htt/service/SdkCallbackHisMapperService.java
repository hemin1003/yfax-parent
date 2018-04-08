package com.yfax.webapi.htt.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.DateUtil;
import com.yfax.utils.NetworkUtil;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.dao.SdkCallbackHisDao;
import com.yfax.webapi.htt.vo.SdkCallbackHisVo;

/**
 * 记录用户金币奖励记录
 * 
 * @author Minbo
 */
@Service
public class SdkCallbackHisMapperService {

	protected static Logger logger = LoggerFactory.getLogger(SdkCallbackHisMapperService.class);

	@Autowired
	private SdkCallbackHisDao sdkCallbackHisDao;
	@Autowired
	private AwardHisService awardHisService;

	/**
	 * 记录回调历史
	 * 
	 * @param orderid
	 * @param cid
	 * @param cuid
	 * @param devid
	 * @param adname
	 * @param time
	 * @param points
	 * @param atype
	 * @param sign
	 * @param request
	 * @return
	 */
	public boolean addSdkCallbackHis(Integer orderid, Integer cid, String cuid, String devid, String adname,
			String time, Integer points, Integer atype, String sign, String mySign, String sdkType,
			HttpServletRequest request, Integer result) {
		SdkCallbackHisVo sdkCallbackHisVo = new SdkCallbackHisVo();
		sdkCallbackHisVo.setOrderid(orderid);
		sdkCallbackHisVo.setCid(cid);
		sdkCallbackHisVo.setCuid(cuid);
		sdkCallbackHisVo.setDevid(devid);
		sdkCallbackHisVo.setAdname(adname);
		sdkCallbackHisVo.setTime(time);
		sdkCallbackHisVo.setTimeDate(DateUtil.stampToDate2(Long.valueOf(sdkCallbackHisVo.getTime())));
		sdkCallbackHisVo.setPoints(points);
		sdkCallbackHisVo.setAtype(atype);
		sdkCallbackHisVo.setSign(sign);
		sdkCallbackHisVo.setCreateDate(DateUtil.getCurrentLongDateTime());
		sdkCallbackHisVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
		sdkCallbackHisVo.setMySign(mySign);
		sdkCallbackHisVo.setSdkType(sdkType);
		sdkCallbackHisVo.setSip(NetworkUtil.getIpAddress(request));
		sdkCallbackHisVo.setResult(result);
		//获得奖励
		this.awardHisService.addAwardHis(sdkCallbackHisVo.getCuid(), sdkCallbackHisVo.getPoints()
				, GlobalUtils.AWARD_TYPE_GAME, null, null, null, null, null, null, null, null, null, 
				String.valueOf(sdkCallbackHisVo.getOrderid()), sdkCallbackHisVo.getAdname());
		try {
			return this.sdkCallbackHisDao.insertSdkCallbackHis(sdkCallbackHisVo);
		} catch (Exception e) {
			logger.error("记录回调历史异常：" + e.getMessage(), e);
			return false;
		}
	}
}
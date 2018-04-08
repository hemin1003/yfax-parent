package com.yfax.webapi.htt.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.DateUtil;
import com.yfax.utils.FilterStr;
import com.yfax.utils.MD5Util;
import com.yfax.utils.StrUtil;
import com.yfax.webapi.htt.dao.BlacklistSetDao;
import com.yfax.webapi.htt.vo.AppUserVo;
import com.yfax.webapi.htt.vo.BlacklistSetVo;
import com.yfax.webapi.htt.vo.LoginHisNewVo;

/**
 * 黑名单规则匹配
 * @author Minbo
 */
@Service
public class BlacklistSetService{
	
	protected static Logger logger = LoggerFactory.getLogger(BlacklistSetService.class);
	
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private BlacklistHisService blacklistHisService;
	
	@Autowired
	private BlacklistSetDao dao;
	
	public List<BlacklistSetVo> selectBlacklistSet() {
		return this.dao.selectBlacklistSet();
	}
	
	private List<BlacklistSetVo> listMap = new ArrayList<>();

	/**
	 * 
	 * @param loginHisNewVo
	 */
	public void processBlackList(LoginHisNewVo loginHisNewVo) {
		listMap = this.selectBlacklistSet();
		String strategy = null;
		boolean flag = false;	//是否拉黑处理
		for (BlacklistSetVo blacklistSetVo : listMap) {
			if(flag) {
				//如果满足其中之一，立即返回
				break;
			}
			switch (blacklistSetVo.getColumnName()) {
				case "deviceName":
					//设备名
					String deviceName = StrUtil.null2Str(loginHisNewVo.getDeviceName());
					if(!deviceName.equals("") && deviceName.equals(blacklistSetVo.getColumnValue())
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：设备名，deviceName=" + deviceName + "，拉黑处理。");
						flag = true;
						strategy = "命中[设备名]黑名单";
					}
					break;
					
				case "longitude/latitude":
					//经纬度
					String longitudeOflatitude = StrUtil.null2Str(loginHisNewVo.getLongitude() + "," + loginHisNewVo.getLatitude());
					if(!longitudeOflatitude.equals("") && longitudeOflatitude.equals(blacklistSetVo.getColumnValue())
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：经纬度，longitudeOflatitude=" + longitudeOflatitude + "，拉黑处理。");
						flag = true;
						strategy = "命中[经纬度]黑名单";
					}
					break;
					
				case "wifiMac":
					//Wifi mac地址
					String wifiMac = StrUtil.null2Str(loginHisNewVo.getWifiMac());
					if(!wifiMac.equals("") && wifiMac.equals(blacklistSetVo.getColumnValue())
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：Wifi mac地址，wifiMac=" + wifiMac + "，拉黑处理。");
						flag = true;
						strategy = "命中[Wifi mac地址]黑名单";
					}
					break;
					
				case "sIp":
					//公网登录IP
					String sIp = StrUtil.null2Str(loginHisNewVo.getsIp());
					if(!sIp.equals("") && sIp.equals(blacklistSetVo.getColumnValue())
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：sIp，sIp=" + sIp + "，拉黑处理。");
						strategy = "命中[公网Sip]黑名单";
						flag = true;
					}
					break;
					
				case "installedList":
					//安装列表md5
					String installedList	= StrUtil.null2Str(MD5Util.encodeByMD5(StrUtil.null2Str(loginHisNewVo.getInstalledList())));
					if(!installedList.equals("") && installedList.equals(blacklistSetVo.getColumnValue())
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：安装列表md5，installedList=" + installedList + "，拉黑处理。");
						strategy = "命中[安装列表md5]黑名单";
						flag = true;
					}
					break;
					
				case "wifi":
					//Wifi连接名
					String wifi = StrUtil.null2Str(loginHisNewVo.getWifi());
					if(!wifi.equals("") && wifi.equals(FilterStr.filterAll(blacklistSetVo.getColumnValue()))
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：Wifi连接名，wifi=" + wifi + "，拉黑处理。");
						flag = true;
						strategy = "命中[Wifi连接名]黑名单";
					}
					break;
					
				case "mac":
					//Mac地址
					String mac = StrUtil.null2Str(loginHisNewVo.getMac());
					if(!mac.equals("") && mac.equals(blacklistSetVo.getColumnValue())
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：Mac地址，mac=" + mac + "，拉黑处理。");
						flag = true;
						strategy = "命中[Mac地址]黑名单";
					}
					break;
					
				case "ip":
					//应用IP地址
					String ip = StrUtil.null2Str(loginHisNewVo.getIp());
					if(!ip.equals("") && ip.equals(blacklistSetVo.getColumnValue())
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：应用IP地址，ip=" + ip + "，拉黑处理。");
						flag = true;
						strategy = "命中[应用IP地址]黑名单";
					}
					break;
					
				case "imei":
					//IMEI码
					String imei = StrUtil.null2Str(loginHisNewVo.getImei());
					if(!imei.equals("") && imei.equals(blacklistSetVo.getColumnValue())
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：IMEI码，imei=" + imei + "，拉黑处理。");
						flag = true;
						strategy = "命中[IMEI码]黑名单";
					}
					break;
					
				case "position":
					//地理位置md5
					String position = StrUtil.null2Str(MD5Util.encodeByMD5(StrUtil.null2Str(loginHisNewVo.getPosition())));
					if(!position.equals("") && position.equals(blacklistSetVo.getColumnValue())
							&& blacklistSetVo.getIsBlack() == 1) {
						logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
							+ "，黑名单规则匹配：位置md5，position=" + position + "，拉黑处理。");
						flag = true;
						strategy = "命中[地理位置md5]黑名单";
					}
					break;
	
				default:
					break;
			}
		}
		//是否拉黑逻辑处理
		if (flag) {
			String cTime = DateUtil.getCurrentLongDateTime();
			AppUserVo appUserVo = new AppUserVo();
			appUserVo.setPhoneNum(loginHisNewVo.getPhoneNum());
			appUserVo.setBlackList(1);
			appUserVo.setUpdateDate(cTime);
			//1. 更新用户黑名单标志
			boolean result = this.appUserService.modifyUser(appUserVo);
			//2. 记录历史
			boolean result2 = this.blacklistHisService.addBlacklistHis(loginHisNewVo.getPhoneNum(), strategy, cTime);
			logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
				+ "，黑名单规则匹配：拉黑处理结果result=" + result 
				+ "，[phoneNum=" + loginHisNewVo.getPhoneNum() + "]，记录历史标识result2=" + result2);
			if(result && result2) {
				//3. 清除token
				boolean result3 = this.appUserService.doLoginOut(loginHisNewVo.getPhoneNum());
				logger.info("phoneNum=" + loginHisNewVo.getPhoneNum() 
					+ "，黑名单规则匹配成功，清除token标识result3=" + result3);
			}
		}
	}
}

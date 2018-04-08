package com.yfax.webapi.htt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.AppVersionDao;
import com.yfax.webapi.htt.vo.AppVersionVo;

import net.sf.json.JSONObject;

/**
 * APK渠道版本配置
 * @author Minbo
 */
@Service
public class AppVersionService{
	
	@Autowired
	private AppVersionDao dao;
	
	public AppVersionVo selectAppVersion(){
		return this.dao.selectAppVersion();
	}
	
	/**
	 * 根据版本和渠道号获得新闻源
	 * @param version
	 * @param channel
	 * @param jsonStr
	 * @return
	 */
	public Integer getSourceByVersionAndChannel(String version, String channel, String jsonStr) {
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		if(jsonObj != null && jsonObj.get(channel) != null) {
			JSONObject jsonObj2 = JSONObject.fromObject(jsonObj.get(channel));
			if(jsonObj2 != null && jsonObj2.get(version) != null) {
				JSONObject jsonObj3 = JSONObject.fromObject(jsonObj2.get(version));
				if(jsonObj3 != null && jsonObj3.get("source") != null) {
					return jsonObj3.getInt("source");
				}
			}
		}
		return -1;
	}
}

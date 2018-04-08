package com.yfax.spider.db.es;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.yfax.spider.toutiao.utils.Utils;

public class EsService {

	protected static final Logger logger = Logger.getLogger(EsService.class);

	private static Client instance = null;
	
	static {
		String ip = Utils.IP;
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
				.put("client.transport.sniff", true).build();
		try {
			instance = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), 9300));
		} catch (UnknownHostException e) {
			logger.error("获取es连接client异常：" + e.getMessage(), e);
		}
	}

	private EsService (){}

	public static Client getClient() {
		return instance;
	}
}

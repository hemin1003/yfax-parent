package com.yfax.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomEngine {
	/**
	 * 概率选择
	 * 
	 * @param keyChanceMap
	 *            key为唯一标识，value为该标识的概率，是去掉%的数字
	 * @return 被选中的key。未选中返回null
	 */
	public static String chanceSelect(Map<String, Integer> keyChanceMap) {
		if (keyChanceMap == null || keyChanceMap.size() == 0)
			return null;

		Integer sum = 0;
		for (Integer value : keyChanceMap.values()) {
			sum += value;
		}
		// 从1开始
		Integer rand = new Random().nextInt(sum) + 1;

		for (Map.Entry<String, Integer> entry : keyChanceMap.entrySet()) {
			rand -= entry.getValue();
			// 选中
			if (rand <= 0) {
				return entry.getKey();
			}
		}

		return null;
	}
	
	/**
	 * 获得值
	 * @param keyChanceMap
	 * @return
	 */
	public static String getRandomValue(Map<String, Integer> keyChanceMap) {
		Map<String, Integer> count = new HashMap<String, Integer>();
		String key = RandomEngine.chanceSelect(keyChanceMap);
		if (count.containsKey(key)) {
			count.put(key, count.get(key) + 1);
		} else {
			count.put(key, 1);
		}
		return key;
	}
}

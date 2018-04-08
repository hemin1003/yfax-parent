package com.yfax.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
	public static List<Map<String,Object>> JsonArrayToList(String jsonArrayData){
        JSONArray jsonArray = JSONArray.fromObject(jsonArrayData);
        List<Map<String,Object>> mapListJson = (List)jsonArray;
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < mapListJson.size(); i++) {
            Map<String,Object> obj=mapListJson.get(i);
            HashMap<String,Object> map = new HashMap<>();
            for(Entry<String,Object> entry : obj.entrySet()){
                String strkey1 = entry.getKey();
                Object strval1 = entry.getValue();
                map.put(strkey1, strval1);
            }
            list.add(map);
        }
        return list;
	}
	
	public static Map<String,Object> JsonObjectToMap(String jsonObjectData){
        JSONObject jsonObject = JSONObject.fromObject(jsonObjectData);
        Map<String,Object> map = new HashMap<>();
		Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
		for (Entry<String, Object> entry : mapJson.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
	
	public static void main(String[] args) {
		String jsonArrayData="[{\"videoDuration\":120,\"dailyVideoTime\":300,\"dailyReadTime\":300,\"tabArticle\":[{\"id\":\"1021\",\"name\":\"热点\",\"url\":\"https://cpu.baidu.com/1021/f457e481\",\"show\":0},{\"id\":\"1001\",\"name\":\"娱乐\",\"url\":\"https://cpu.baidu.com/1001/f457e481\",\"show\":0},{\"id\":\"1025\",\"name\":\"搞笑\",\"url\":\"https://cpu.baidu.com/1025/f457e481\",\"show\":0},{\"id\":\"1047\",\"name\":\"看点\",\"url\":\"https://cpu.baidu.com/1047/f457e481\",\"show\":0},{\"id\":\"1002\",\"name\":\"体育\",\"url\":\"https://cpu.baidu.com/1002/f457e481\",\"show\":0},{\"id\":\"1009\",\"name\":\"时尚\",\"url\":\"https://cpu.baidu.com/1009/f457e481\",\"show\":0},{\"id\":\"1040\",\"name\":\"游戏\",\"url\":\"https://cpu.baidu.com/1040/f457e481\",\"show\":0},{\"id\":\"1033\",\"name\":\"视频\",\"url\":\"https://cpu.baidu.com/1033/f457e481\",\"show\":0},{\"id\":\"1080\",\"name\":\"本地\",\"url\":\"https://cpu.baidu.com/1080/f457e481\",\"show\":0},{\"id\":\"1007\",\"name\":\"汽车\",\"url\":\"https://cpu.baidu.com/1007/f457e481\",\"show\":0},{\"id\":\"1012\",\"name\":\"军事\",\"url\":\"https://cpu.baidu.com/1012/f457e481\",\"show\":0},{\"id\":\"1013\",\"name\":\"科技\",\"url\":\"https://cpu.baidu.com/1013/f457e481\",\"show\":0},{\"id\":\"1035\",\"name\":\"生活\",\"url\":\"https://cpu.baidu.com/1035/f457e481\",\"show\":0},{\"id\":\"1024\",\"name\":\"美女\",\"url\":\"https://cpu.baidu.com/1024/f457e481\",\"show\":0},{\"id\":\"1035\",\"name\":\"女人\",\"url\":\"https://cpu.baidu.com/1035/f457e481\",\"show\":0},{\"id\":\"1006\",\"name\":\"财经\",\"url\":\"https://cpu.baidu.com/1006/f457e481\",\"show\":0},{\"id\":\"1036\",\"name\":\"文化\",\"url\":\"https://cpu.baidu.com/1036/f457e481\",\"show\":0},{\"id\":\"1008\",\"name\":\"房产\",\"url\":\"https://cpu.baidu.com/1008/f457e481\",\"show\":0},{\"id\":\"1003\",\"name\":\"图片\",\"url\":\"https://cpu.baidu.com/1003/f457e481\",\"show\":0},{\"id\":\"1005\",\"name\":\"手机\",\"url\":\"https://cpu.baidu.com/1005/f457e481\",\"show\":0}],\"tabVideo\":[{\"id\":\"0\",\"name\":\"推荐\",\"url\":\"http://k.360kan.com/pc/list?channel_id=0\",\"show\":0},{\"id\":\"2\",\"name\":\"搞笑\",\"url\":\"http://k.360kan.com/pc/list?channel_id=2\",\"show\":0},{\"id\":\"13\",\"name\":\"音乐\",\"url\":\"http://k.360kan.com/pc/list?channel_id=13\",\"show\":0},{\"id\":\"3\",\"name\":\"社会\",\"url\":\"http://k.360kan.com/pc/list?channel_id=3\",\"show\":0},{\"id\":\"8\",\"name\":\"影视\",\"url\":\"http://k.360kan.com/pc/list?channel_id=8\",\"show\":0},{\"id\":\"18\",\"name\":\"生活\",\"url\":\"http://k.360kan.com/pc/list?channel_id=18\",\"show\":0},{\"id\":\"19\",\"name\":\"军事\",\"url\":\"http://k.360kan.com/pc/list?channel_id=19\",\"show\":0},{\"id\":\"1\",\"name\":\"娱乐\",\"url\":\"http://k.360kan.com/pc/list?channel_id=1\",\"show\":0},{\"id\":\"7\",\"name\":\"科技\",\"url\":\"http://k.360kan.com/pc/list?channel_id=7\",\"show\":0},{\"id\":\"11\",\"name\":\"游戏\",\"url\":\"http://k.360kan.com/pc/list?channel_id=11\",\"show\":0},{\"id\":\"12\",\"name\":\"体育\",\"url\":\"http://k.360kan.com/pc/list?channel_id=12\",\"show\":0}]}]";
		List<Map<String,Object>> list = JsonArrayToList(jsonArrayData);
		for (Map<String, Object> map : list) {
			for(Entry<String,Object> entry : map.entrySet()){
                String strkey1 = entry.getKey();
                Object strval1 = entry.getValue();
                System.out.println(strkey1 + ", " + strval1);
            }
		}
	}
}

package jehc.xtmodules.xtcore.util.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * map转换工具
 */
public class MapUtil {
	/**
	 * 将List中的Key转换为小写
	 * @param list 返回新对象
	 * @return
	 */
	public static List<Map<String, Object>> convertKeyList2LowerCase(List<Map<String, Object>> list){
		if(null==list) {
			return null;
		}
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		Iterator<Map<String, Object>> iteratorL = list.iterator();
		while (iteratorL.hasNext()) {
			Map<String, Object> map = (Map<String, Object>) iteratorL.next();
			Map<String, Object> result = convertKey2LowerCase(map);
			if(null != result){
				resultList.add(result);
			}
		}
		//
		return resultList;
	}
	/**
	 * 转换单个map,将key转换为小写. 
	 * @param map 返回新对象
	 * @return
	 */
	public static Map<String, Object> convertKey2LowerCase(Map<String, Object> map){
		if(null==map) {
			return null;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		//
		Set<String> keys  = map.keySet();
		//
		Iterator<String> iteratorK = keys.iterator();
		while (iteratorK.hasNext()) {
			String key = (String) iteratorK.next();
			Object value = map.get(key);
			if(null == key){
				continue;
			}
			//
			String keyL = key.toLowerCase();
			result.put(keyL, value);
		}
		return result;
	}
	/**
	 * 将List中Map的Key转换为小写.
	 * @param list
	 * @return
	 */
	public static List<Map<String, Object>> trimListKeyValue(List<Map<String, Object>> list){
		if(null==list) {
			return null;
		}
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		//
		Iterator<Map<String, Object>> iteratorL = list.iterator();
		while (iteratorL.hasNext()) {
			Map<String, Object> map = (Map<String, Object>) iteratorL.next();
			//
			Map<String, Object> result = trimKeyValue(map);
			if(null != result){
				resultList.add(result);
			}
		}
		//
		return resultList;
	}
	/**
	 * 转换单个map,将key转换为小写. 
	 * @param map 返回新对象
	 * @return
	 */
	public static Map<String, Object> trimKeyValue(Map<String, Object> map){
		if(null==map) {
			return null;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		//
		Set<String> keys  = map.keySet();
		//
		Iterator<String> iteratorK = keys.iterator();
		while (iteratorK.hasNext()) {
			String key = (String) iteratorK.next();
			Object value = map.get(key);
			if(null == key){
				continue;
			}
			//
			String keyT = key.trim();
			if(value instanceof String){
				String valueT = String.valueOf(value).trim();
				result.put(keyT, valueT);
			} else {
				result.put(keyT, value);
			}
		}
		return result;
	}
}

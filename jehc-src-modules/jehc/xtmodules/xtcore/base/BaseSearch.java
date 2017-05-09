package jehc.xtmodules.xtcore.base;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.util.MapUtils;
import net.sf.json.JSONObject;

/**
 * 
 * @author 邓纯杰
 *
 */
public class BaseSearch {
	private String searchJson;

	public String getSearchJson() {
		return searchJson;
	}

	public void setSearchJson(String searchJson) {
		this.searchJson = searchJson;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> convert(){
		try {
			if(!StringUtil.isEmpty(searchJson)){
				Map<String, Object> map = JSONObject.fromObject(URLDecoder.decode(getSearchJson(), "UTF-8"));
				MapUtils.removeNullValue(map);
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			new HashMap<String, Object>();
		}
		return new HashMap<String, Object>();
	}
}

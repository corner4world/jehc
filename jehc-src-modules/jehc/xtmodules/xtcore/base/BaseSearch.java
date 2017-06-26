package jehc.xtmodules.xtcore.base;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.util.MapUtils;
import net.sf.json.JSONObject;

/**
 * 
 * @author 邓纯杰
 *
 */
public class BaseSearch {
	Logger log = LoggerFactory.getLogger(this.getClass());
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
				map = MapUtils.resetMap(map);
				return map;
			}
		} catch (Exception e) {
			log.error("查询条件参数转换出现异常："+e.getMessage());
			new HashMap<String, Object>();
		}
		return new HashMap<String, Object>();
	}
}

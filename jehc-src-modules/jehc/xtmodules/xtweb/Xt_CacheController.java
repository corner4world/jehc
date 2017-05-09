package jehc.xtmodules.xtweb;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.CacheManagerUtil;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
 * 缓存管理
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/xtCacheController")
public class Xt_CacheController  extends BaseAction{
	
	/**
	* 载入初始化页面
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtCache",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtAppkeyAppsecret(HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-cache/xt-cache-list");
	}
	
	/**
	* 加载初始化列表数据
	* @param request 
	*/
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value="/getXtCacheListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtCacheListByCondition(String cacheName,HttpServletRequest request){
		CacheManagerUtil CacheManagerUtil = new CacheManagerUtil();
		String[] cacheNames = CacheManagerUtil.getCacheNamesList();
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		for(int i = 0; i < cacheNames.length; i++){
			Cache cache = CacheManagerUtil.getCache(cacheNames[i]);
			model.put("CacheName", cacheNames[i]);
			model.put("CacheType", "ehcache");
			model.put("Hits", CacheManagerUtil.getCacheHits(cache));
			model.put("Misses", CacheManagerUtil.getCacheMisses(cache));
			model.put("CacheSize", CacheManagerUtil.getCacheSize(cache));
			model.put("DiskStoreSize", CacheManagerUtil.getDiskStoreSize(cache));
			model.put("MemoryStoreSize", CacheManagerUtil.getMemoryStoreSize(cache));
			jsonArray.add(model);
		}
		return outItemsStr(jsonArray);
	}
	/**
	* 读取缓存数据
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtCacheDataListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtCacheDataListByCondition(String cacheName,HttpServletRequest request){
		CacheManagerUtil CacheManagerUtil = new CacheManagerUtil();
		JSONArray jsonArray = CacheManagerUtil.getCacheDataList(cacheName);
		return outItemsStr(jsonArray);
	}
	
	/**
	 * 删除缓存
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delCache",method={RequestMethod.POST,RequestMethod.GET})
	public String delCache(String cacheName){
		if(CacheManagerUtil.deleteCache(cacheName)){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 导出
	* @param excleData 
	* @param excleHeader 
	* @param excleText 
	* @param request 
	* @param request 
	*/
	@RequestMapping(value="/exportCache",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCache(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

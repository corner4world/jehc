package jehc.xtmodules.xtcore.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.json.JSONArray;

import org.springframework.cache.ehcache.EhCacheCacheManager;

import jehc.xtmodules.xtcore.util.springutil.SpringUtil;

/**
 * 缓存统一处理
 * @author 邓纯杰
 *
 */
public class CacheManagerUtil {
//	@Resource
//	EhCacheCacheManager cacheManager;
	/**
	 * 通过URL创建缓存管理器
	 * @return
	 */
	public CacheManager getCacheManager(){
//		URL url = getClass().getResource("/xtCore/sources/ehcache/ehcache.xml");
//		CacheManager cacheManager = CacheManager.create(url);
////	CacheManager cacheManager = CacheManager.create();
		EhCacheCacheManager cacheManager = (EhCacheCacheManager)SpringUtil.getBean("ehcacheManager");
		return cacheManager.getCacheManager();
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	public static Cache getCache(String key){
		CacheManagerUtil cacheManagerUtil = new CacheManagerUtil();
		return cacheManagerUtil.getCacheManager().getCache(key);
	}
	
	/**
	 * 卸载CacheManager ,关闭Cache
	 * @param cacheManager
	 */
	public static void shutdown(CacheManager cacheManager){
		cacheManager.shutdown();
	}
	
	/**
	 * 删除指定缓存中指定元素
	 * @param cacheManager
	 * @param cacheKey
	 * @param elementKey
	 */
	public static boolean remove(CacheManager cacheManager,String cacheKey,String elementKey){
		try {
			Cache cache = cacheManager.getCache(cacheKey);
			cache.remove(elementKey);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 获取所有缓存名称
	 * @param cacheManager
	 * @return
	 */
	public String[] getCacheNamesList(){
		return getCacheManager().getCacheNames();
	}
	
	/**
	 * 得到缓存中的对象数
	 * @param cache
	 * @return
	 */
	public int getCacheSize(Cache cache){
		return cache.getSize();
	}
	
	/**
	 * 得到缓存对象占用内存的大小
	 * @param cache
	 * @return
	 */
	public long getMemoryStoreSize(Cache cache){
		return cache.getMemoryStoreSize();
	}
	
	/**
	 * 得到缓存读取的命中次数
	 * @param cache
	 * @return
	 */
	public long getCacheHits(Cache cache){
		return cache.getStatistics().getCacheHits();
	}
	
	/**
	 * 得到缓存读取的错失次数
	 * @param cache
	 * @return
	 */
	public long getCacheMisses(Cache cache){
		return cache.getStatistics().getCacheMisses();
	}
	
	/**
	 * 获得当前DiskStore中element数量
	 * @param cache
	 * @return
	 */
	public long getDiskStoreSize(Cache cache){
		return cache.getDiskStoreSize();
	}
	
	/**
	 * 清空指定换乘中全部数据
	 * @param cacheName
	 */
	public static boolean deleteCache(String cacheName){
		try {
			Cache ehCache = CacheManagerUtil.getCache(cacheName);
			ehCache.removeAll();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 返回所有数据
	 * @param cacheName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONArray getCacheDataList(String cacheName){
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		Cache ehCache = CacheManagerUtil.getCache(cacheName);
		List<String> keys = ehCache.getKeys();
		for(String key:keys){
			Element element = ehCache.get(key);
			model.put("key", key);
			model.put("value", element.getObjectValue());
			jsonArray.add(model);
		}
		return jsonArray;
	}
}

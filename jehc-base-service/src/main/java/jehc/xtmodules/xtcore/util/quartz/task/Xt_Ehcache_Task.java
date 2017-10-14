package jehc.xtmodules.xtcore.util.quartz.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.solrmodules.solrdao.SolrCoreDao;
import jehc.solrmodules.solrmodel.SolrCore;
import jehc.xtmodules.xtcore.util.CacheManagerUtil;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtdao.XtConstantDao;
import jehc.xtmodules.xtdao.XtDataDictionaryDao;
import jehc.xtmodules.xtdao.XtFunctioninfoCommonDao;
import jehc.xtmodules.xtdao.XtFunctioninfoDao;
import jehc.xtmodules.xtdao.XtIpFrozenDao;
import jehc.xtmodules.xtdao.XtPathDao;
import jehc.xtmodules.xtmodel.XtConstant;
import jehc.xtmodules.xtmodel.XtDataDictionary;
import jehc.xtmodules.xtmodel.XtFunctioninfo;
import jehc.xtmodules.xtmodel.XtFunctioninfoCommon;
import jehc.xtmodules.xtmodel.XtIpFrozen;
import jehc.xtmodules.xtmodel.XtPath;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
public class Xt_Ehcache_Task extends Thread{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 业务逻辑处理
	 */
	public void service() {
		new Xt_Ehcache_Task().start();
	}
	public void run(){
		try {
			changeEhcache();
			loadXtFunctioninfoCommon();
			loadSolrCore();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
     * 加载数据字典及平台路径到缓存中
     * @param ehCache
     */
    private void changeEhcache(){
    	CacheManagerUtil cacheManagerUtil = new CacheManagerUtil();
		CacheManager cacheManager = cacheManagerUtil.getCacheManager();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info(sdf.format(new Date())+"--->开始删除数据字典缓存"); 
		boolean flag = CacheManagerUtil.remove(cacheManager, "XtDataDictionaryCache", "XtDataDictionaryCache");
		logger.info(sdf.format(new Date())+"--->删除数据字典缓存结束，状态:"+flag);
		logger.info(sdf.format(new Date())+"--->开始删除平台路径缓存"); 
		flag = CacheManagerUtil.remove(cacheManager, "XtPathCache", "XtPathCache");
		logger.info(sdf.format(new Date())+"--->删除平台路径缓存结束，状态:"+flag);
		logger.info(sdf.format(new Date())+"--->开始删除IP黑户缓存"); 
		flag = CacheManagerUtil.remove(cacheManager, "XtIpFrozenCache", "XtIpFrozenCache");
		logger.info(sdf.format(new Date())+"--->删除IP黑户结束，状态:"+flag);
    	long millis1 = System.currentTimeMillis();
    	XtDataDictionaryDao xtDataDictionaryDao = (XtDataDictionaryDao)SpringUtil.getBean("xtDataDictionaryDao");
    	Map<String, Object> condition = new HashMap<String, Object>();
    	List<XtDataDictionary> Xt_Data_DictionaryList = xtDataDictionaryDao.getXtDataDictionaryListAllByCondition(condition);
		Cache XtDataDictionaryCache = CacheManagerUtil.getCache("XtDataDictionaryCache");
		Element XtDataDictionaryEle=new Element("XtDataDictionaryCache", Xt_Data_DictionaryList); 
		XtDataDictionaryCache.put(XtDataDictionaryEle);
		long millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取数据字典耗时:"+(millis2-millis1)+"毫秒"); 
		logger.info(sdf.format(new Date())+"--->加载缓存配置开始");
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+Xt_Data_DictionaryList.size()+"条数据字典数据");
		logger.info(sdf.format(new Date())+"--->将数据存入缓存耗时:"+(millis2-millis1)+"毫秒");
		logger.info(sdf.format(new Date())+"--->加载缓存配置结束");
		
		
		millis1 = System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取平台路径开始");
		condition = new HashMap<String, Object>();
		XtPathDao xtPathDao = (XtPathDao)SpringUtil.getBean("xtPathDao");
		List<XtPath> xt_Path_List = xtPathDao.getXtPathListAllByCondition(condition);
		Element XtPathEle=new Element("XtPathCache", xt_Path_List); 
		Cache XtPathCache = CacheManagerUtil.getCache("XtPathCache");
		XtPathCache.put(XtPathEle);
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+xt_Path_List.size()+"条平台路径数据");
		logger.info(sdf.format(new Date())+"--->读取平台路径缓存耗时:"+(millis2-millis1)+"毫秒");
		
		
		millis1 = System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取IP黑户开始");
		condition = new HashMap<String, Object>();
		condition.put("xt_ip_frozen_status", 2);
		XtIpFrozenDao xt_Ip_FrozenDao = (XtIpFrozenDao)SpringUtil.getBean("xtIpFrozenDao");
		List<XtIpFrozen> xt_Ip_FrozenList = xt_Ip_FrozenDao.getXtIpFrozenListAllByCondition(condition);
		Element XtIpFrozenEle=new Element("XtIpFrozenCache", xt_Ip_FrozenList); 
		Cache XtIpFrozenCache = CacheManagerUtil.getCache("XtIpFrozenCache");
		XtIpFrozenCache.put(XtIpFrozenEle);
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+xt_Ip_FrozenList.size()+"条平IP黑户数据");
		logger.info(sdf.format(new Date())+"--->读取IP黑户缓存耗时:"+(millis2-millis1)+"毫秒");
		
		
		millis1 = System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取平台常量开始");
		condition = new HashMap<String, Object>();
		XtConstantDao xt_ConstantDao = (XtConstantDao)SpringUtil.getBean("xtConstantDao");
		List<XtConstant> xt_ConstantList = xt_ConstantDao.getXtConstantListAllByCondition(condition);
		Element XtConstantEle=new Element("XtConstantCache", xt_ConstantList); 
		//取得配置文件中预先，定义的XtConstantEle设置，生成一个Cache 该XtConstantEle为ehcache.xml定义好的名称
		Cache XtConstantCache = CacheManagerUtil.getCache("XtConstantCache");
		//在缓存中放元素
		XtConstantCache.put(XtConstantEle);
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+xt_ConstantList.size()+"条平台常量数据");
		logger.info(sdf.format(new Date())+"--->读取平台常量缓存耗时:"+(millis2-millis1)+"毫秒");
    }
    
    /**
     * 加载公共功能到内存中
     */
    public void loadXtFunctioninfoCommon(){
    	XtFunctioninfoCommonDao xt_Functioninfo_CommonDao = (XtFunctioninfoCommonDao)SpringUtil.getBean("xtFunctioninfoCommonDao");
    	XtFunctioninfoDao xt_FunctioninfoDao = (XtFunctioninfoDao)SpringUtil.getBean("xtFunctioninfoDao");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	condition.put("xt_functioninfo_common_status", "0");
    	List<XtFunctioninfoCommon> xt_Functioninfo_CommonList = xt_Functioninfo_CommonDao.getXtFunctioninfoCommonListByCondition(condition);
    	long millis2 =  System.currentTimeMillis();
    	logger.info(sdf.format(new Date())+"--->读取公共功能数量:"+xt_Functioninfo_CommonList.size()+"个");
		logger.info(sdf.format(new Date())+"--->读取公共功能耗时:"+(millis2-millis1)+"毫秒");
		logger.info(sdf.format(new Date())+"--->加载公共功能缓存开始");
		StringBuffer sbf = new StringBuffer();
		for(XtFunctioninfoCommon xt_Functioninfo_Common:xt_Functioninfo_CommonList){
			if(null != sbf && !"".equals(sbf.toString()) && null != (sbf.toString())){
				sbf.append(xt_Functioninfo_Common.getXt_functioninfo_common_url()+",");
			}else{
				sbf.append(","+xt_Functioninfo_Common.getXt_functioninfo_common_url()+",");
			}
		}
		condition = new HashMap<String, Object>();
		condition.put("xt_functioninfoType", "0");
		List<XtFunctioninfo> xtFunctioninfoList = xt_FunctioninfoDao.getXtFunctioninfoList(condition);
		logger.info(sdf.format(new Date())+"--->读取非公共功能无需拦截数量:"+xtFunctioninfoList.size()+"个");
		for(XtFunctioninfo xtFunctioninfo:xtFunctioninfoList){
			sbf.append(xtFunctioninfo.getXt_functioninfoURL()+",");
		}
		Element XtFunctioninfoCommonEle=new Element("XtFunctioninfoCommonCache", sbf.toString()); 
		//取得配置文件中预先，定义的XtFunctioninfoCommonCache设置，生成一个Cache 该XtFunctioninfoCommonCache为ehcache.xml定义好的名称
		Cache XtFunctioninfoCommonCache = CacheManagerUtil.getCache("XtFunctioninfoCommonCache");
		//在缓存中放元素
		XtFunctioninfoCommonCache.put(XtFunctioninfoCommonEle);
		logger.info(sdf.format(new Date())+"--->加载公共功能缓存结束");
		millis2 =  System.currentTimeMillis();
    }
    
    /**
     * 加载Solr实例到缓存中
     */
    public void loadSolrCore(){
    	SolrCoreDao solr_CoreDao = (SolrCoreDao)SpringUtil.getBean("solrCoreDao");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	List<SolrCore> solr_CoreList = solr_CoreDao.getSolrCoreListByCondition(condition);
    	long millis2 =  System.currentTimeMillis();
    	logger.info(sdf.format(new Date())+"--->读取SOLR实例数量:"+solr_CoreList.size()+"个");
		logger.info(sdf.format(new Date())+"--->读取SOLR实例耗时:"+(millis2-millis1)+"毫秒");
		logger.info(sdf.format(new Date())+"--->加载SOLR实例缓存开始");
		Element SolrCoreCacheEle=new Element("SolrCoreCache", solr_CoreList); 
		Cache SolrCoreCache = CacheManagerUtil.getCache("SolrCoreCache");
		//在缓存中放元素
		SolrCoreCache.put(SolrCoreCacheEle);
		logger.info(sdf.format(new Date())+"--->加载SOLR实例缓存结束");
		millis2 =  System.currentTimeMillis();
    }
}

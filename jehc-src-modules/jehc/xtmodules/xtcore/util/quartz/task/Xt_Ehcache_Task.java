package jehc.xtmodules.xtcore.util.quartz.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.solrmodules.solrmodel.SolrCore;
import jehc.solrmodules.solrservice.SolrCoreService;
import jehc.xtmodules.xtcore.util.CacheManagerUtil;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtmodel.XtConstant;
import jehc.xtmodules.xtmodel.XtDataDictionary;
import jehc.xtmodules.xtmodel.XtFunctioninfo;
import jehc.xtmodules.xtmodel.XtFunctioninfoCommon;
import jehc.xtmodules.xtmodel.XtIpFrozen;
import jehc.xtmodules.xtmodel.XtPath;
import jehc.xtmodules.xtservice.XtConstantService;
import jehc.xtmodules.xtservice.XtDataDictionaryService;
import jehc.xtmodules.xtservice.XtFunctioninfoService;
import jehc.xtmodules.xtservice.XtFunctioninfoCommonService;
import jehc.xtmodules.xtservice.XtIpFrozenService;
import jehc.xtmodules.xtservice.XtPathService;
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
    	XtDataDictionaryService xtDataDictionaryService = (XtDataDictionaryService)SpringUtil.getBean("xtDataDictionaryService");
    	Map<String, Object> condition = new HashMap<String, Object>();
    	List<XtDataDictionary> Xt_Data_DictionaryList = xtDataDictionaryService.getXtDataDictionaryListAllByCondition(condition);
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
		XtPathService xtPathService = (XtPathService)SpringUtil.getBean("xtPathService");
		List<XtPath> xt_Path_List = xtPathService.getXtPathListAllByCondition(condition);
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
		XtIpFrozenService xt_Ip_FrozenService = (XtIpFrozenService)SpringUtil.getBean("xtIpFrozenService");
		List<XtIpFrozen> xt_Ip_FrozenList = xt_Ip_FrozenService.getXtIpFrozenListAllByCondition(condition);
		Element XtIpFrozenEle=new Element("XtIpFrozenCache", xt_Ip_FrozenList); 
		Cache XtIpFrozenCache = CacheManagerUtil.getCache("XtIpFrozenCache");
		XtIpFrozenCache.put(XtIpFrozenEle);
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+xt_Ip_FrozenList.size()+"条平IP黑户数据");
		logger.info(sdf.format(new Date())+"--->读取IP黑户缓存耗时:"+(millis2-millis1)+"毫秒");
		
		
		millis1 = System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取平台常量开始");
		condition = new HashMap<String, Object>();
		XtConstantService xt_ConstantService = (XtConstantService)SpringUtil.getBean("xtConstantService");
		List<XtConstant> xt_ConstantList = xt_ConstantService.getXtConstantListAllByCondition(condition);
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
    	XtFunctioninfoCommonService xt_Functioninfo_CommonService = (XtFunctioninfoCommonService)SpringUtil.getBean("xtFunctioninfoCommonService");
    	XtFunctioninfoService xt_FunctioninfoService = (XtFunctioninfoService)SpringUtil.getBean("xtFunctioninfoService");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	condition.put("xt_functioninfo_common_status", "0");
    	List<XtFunctioninfoCommon> xt_Functioninfo_CommonList = xt_Functioninfo_CommonService.getXtFunctioninfoCommonListByCondition(condition);
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
		List<XtFunctioninfo> xtFunctioninfoList = xt_FunctioninfoService.getXtFunctioninfoList(condition);
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
    	SolrCoreService solr_CoreService = (SolrCoreService)SpringUtil.getBean("solrCoreService");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	List<SolrCore> solr_CoreList = solr_CoreService.getSolrCoreListByCondition(condition);
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

package jehc.xtmodules.xtcore.init;

/**
import java.io.File;
**/
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.solrmodules.solrmodel.SolrCore;
import jehc.solrmodules.solrservice.SolrCoreService;
import jehc.solrmodules.solrservice.SolrIndexAttributeService;
import jehc.solrmodules.solrservice.SolrSortService;
import jehc.xtmodules.xtcore.util.CacheManagerUtil;
import jehc.xtmodules.xtcore.util.MapUtils;
import jehc.xtmodules.xtcore.util.ReadProperties;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.constant.CacheConstant;
import jehc.xtmodules.xtcore.util.quartz.QuartzInit;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtmodel.XtAreaRegion;
import jehc.xtmodules.xtmodel.XtConstant;
import jehc.xtmodules.xtmodel.XtDataDictionary;
import jehc.xtmodules.xtmodel.XtFunctioninfo;
import jehc.xtmodules.xtmodel.XtFunctioninfoCommon;
import jehc.xtmodules.xtmodel.XtIpFrozen;
import jehc.xtmodules.xtmodel.XtPath;
import jehc.xtmodules.xtmodel.XtQuartz;
import jehc.xtmodules.xtmodel.XtStartStopLog;
import jehc.xtmodules.xtservice.XtAreaRegionService;
import jehc.xtmodules.xtservice.XtConstantService;
import jehc.xtmodules.xtservice.XtDataDictionaryService;
import jehc.xtmodules.xtservice.XtFunctioninfoCommonService;
import jehc.xtmodules.xtservice.XtFunctioninfoService;
import jehc.xtmodules.xtservice.XtIpFrozenService;
import jehc.xtmodules.xtservice.XtPathService;
import jehc.xtmodules.xtservice.XtQuartzService;
import jehc.xtmodules.xtservice.XtStartStopLogService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * 让服务器启动或停止时自动执行代码
 * @author 邓纯杰
 *
 */
public class InitExcuteClass implements ServletContextListener{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 停止时执行的方法
	 */
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
	    sc.removeAttribute("syspath");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info(sdf.format(new Date())+"--->服务器容器销毁成功");
		XtStartStopLog xt_Start_Stop_Log = new XtStartStopLog();
		xt_Start_Stop_Log.setXt_start_stop_log_stoptime(sdf.format(new Date()));
		addOrUpdateXtStartStopLog(xt_Start_Stop_Log,1);
	}

	/**
	 * 启动时执行方法
	 */
	public void contextInitialized(ServletContextEvent event) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ServletContext sc = event.getServletContext();
		XtStartStopLog xt_Start_Stop_Log = new XtStartStopLog();
		try {
	        sc.setAttribute("syspath", getContextPath(sc));
	        logger.info(""+sdf.format(new Date())+"--->业务平台路径:"+getContextPath(sc));
	        cacheXtDataDictionary();
	    	cacheXtFunctioninfoCommon();
	    	cacheSolrCore();
	    	cacheXtAreaRegion();
	        logger.info(sdf.format(new Date())+"--->进入类加载");
	        logger.info(sdf.format(new Date())+"--->装载配置文件"); 
			Map<String, Object> map = ReadProperties.readProperties(event);
			MapUtils.setKvToServletContext(map, sc);		
			
			map = ReadProperties.readMessageProperties(event);
			MapUtils.setKvToServletContext(map, sc);
			logger.info(sdf.format(new Date())+"--->装载配置结束"); 
			
			logger.info(sdf.format(new Date())+"--->装载Config配置开始"); 
			map = ReadProperties.readConfigProperties(event);
			MapUtils.setKvToServletContext(map, sc);
			logger.info(sdf.format(new Date())+"--->装载Config配置结束"); 
			logger.info(sdf.format(new Date())+"--->开始初始化调度任务"); 
			cacheQuarzInit();
			logger.info(sdf.format(new Date())+"--->结束初始化调度任务"); 
			xt_Start_Stop_Log.setXt_start_stop_log_iserror("0");
		} catch (Exception e) {
			xt_Start_Stop_Log.setXt_start_stop_log_iserror("1");
		}
		xt_Start_Stop_Log.setXt_start_stop_log_starttime(sdf.format(new Date()));
		xt_Start_Stop_Log.setXt_start_stop_log_content("1.业务平台路径 2加载工厂耗 3.读取数据字典 4.加载缓存配置5.装载config配置");
		addOrUpdateXtStartStopLog(xt_Start_Stop_Log,0);
		logger.info(sdf.format(new Date())+"--->结束类加载"); 
		
	}
	private String getContextPath(ServletContext sc) {
        return sc.getContextPath();
    }
	
	/**
	 * 添加或修改启动日志
	 * @param xt_Start_Stop_Log
	 */
	public void addOrUpdateXtStartStopLog(XtStartStopLog xtStartStopLog,int status){
		XtStartStopLogService xtStartStopLogService = (XtStartStopLogService)SpringUtil.getBean("xtStartStopLogService");;
		if(1==status){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("offset", "0");
			condition.put("pageSize", "1");
			List<XtStartStopLog> list = xtStartStopLogService.getXtStartStopLogListByCondition(condition);
			if(!list.isEmpty()){
				xtStartStopLogService.updateXtStartStopLog(list.get(0));
			}
		}else{
			xtStartStopLog.setXt_start_stop_log_id(UUID.toUUID());
			xtStartStopLogService.addXtStartStopLog(xtStartStopLog);
		}
	}
	/**
     * 加载调度器设置
     */
    private void cacheQuarzInit() {
        Timer timer = new Timer("loadQuarzInit", true);
        timer.schedule(new TimerTask() {
            public void run() {
            	XtQuartzService xtQuartzService = (XtQuartzService)SpringUtil.getBean("xtQuartzService");
            	Scheduler scheduler = (Scheduler) SpringUtil.getBean("schedulerFactoryBean");
            	Map<String, Object> condition = new HashMap<String, Object>();
            	condition.put("jobStatus", "NORMAL");
        		List<XtQuartz> xtQuartzList = xtQuartzService.getXtQuartzListAllByCondition(condition);
        		for(int i = 0; i < xtQuartzList.size(); i++){
        			XtQuartz xtQuartz = xtQuartzList.get(i);
        			new QuartzInit(scheduler,xtQuartz.getId(),xtQuartz.getJobName(),xtQuartz.getJobGroup(),xtQuartz.getCronExpression(),xtQuartz.getDesc(),xtQuartz.getTargetMethod(),xtQuartz.getTargetClass()).run();
        		}
            }
        }, 1 * 10);
    }
    
    /**
     * 加载数据字典，平台常量及平台路径到缓存中
     * @param ehCache
     */
	private void cacheXtDataDictionary(){
    	XtDataDictionaryService xtDataDictionaryService = (XtDataDictionaryService)SpringUtil.getBean("xtDataDictionaryService");
    	XtPathService xtPathService = (XtPathService)SpringUtil.getBean("xtPathService");
    	XtIpFrozenService xtIpFrozenService = (XtIpFrozenService)SpringUtil.getBean("xtIpFrozenService");
    	XtConstantService xtConstantService = (XtConstantService)SpringUtil.getBean("xtConstantService");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	List<XtDataDictionary> XtDataDictionaryList = xtDataDictionaryService.getXtDataDictionaryListAllByCondition(condition);
		//2取得配置文件中预先，定义的XtDataDictionaryCache设置，生成一个Cache 该XtDataDictionaryCache为ehcache.xml定义好的名称
		Cache XtDataDictionaryCache = CacheManagerUtil.getCache(CacheConstant.XTDATADICTIONARYCACHE);
		//3在缓存中放元素
		Element XtDataDictionaryEle=new Element(CacheConstant.XTDATADICTIONARYCACHE, XtDataDictionaryList); 
		XtDataDictionaryCache.put(XtDataDictionaryEle);
		long millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取数据字典耗时:"+(millis2-millis1)+"毫秒"); 
		logger.info(sdf.format(new Date())+"--->加载缓存配置开始");
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+XtDataDictionaryList.size()+"条数据字典数据");
		logger.info(sdf.format(new Date())+"--->将数据存入缓存耗时:"+(millis2-millis1)+"毫秒");
		logger.info(sdf.format(new Date())+"--->加载缓存配置结束");
		
		
		millis1 = System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取平台路径开始");
		condition = new HashMap<String, Object>();
		List<XtPath> xt_Path_List = xtPathService.getXtPathListAllByCondition(condition);
		Element XtPathEle=new Element(CacheConstant.XTPATHCACHE, xt_Path_List); 
		//取得配置文件中预先，定义的XtPathCache设置，生成一个Cache 该XtPathCache为ehcache.xml定义好的名称
		Cache XtPathCache = CacheManagerUtil.getCache(CacheConstant.XTPATHCACHE);
		//在缓存中放元素
		XtPathCache.put(XtPathEle);
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+xt_Path_List.size()+"条平台路径数据");
		logger.info(sdf.format(new Date())+"--->读取平台路径缓存耗时:"+(millis2-millis1)+"毫秒");
		
		
		millis1 = System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取IP黑户开始");
		condition = new HashMap<String, Object>();
		condition.put("xt_ip_frozen_status", 2);
		List<XtIpFrozen> xt_Ip_FrozenList = xtIpFrozenService.getXtIpFrozenListAllByCondition(condition);
		if(!xt_Ip_FrozenList.isEmpty() && xt_Ip_FrozenList.size() > 0){
			Element XtIpFrozenEle=new Element(CacheConstant.XTIPFROZENCACHE, xt_Ip_FrozenList); 
			//取得配置文件中预先，定义的XtIpFrozenCache设置，生成一个Cache 该XtIpFrozenCache为ehcache.xml定义好的名称
			Cache XtIpFrozenCache = CacheManagerUtil.getCache(CacheConstant.XTIPFROZENCACHE);
			//在缓存中放元素
			XtIpFrozenCache.put(XtIpFrozenEle);
			millis2 =  System.currentTimeMillis();
			logger.info(sdf.format(new Date())+"--->一共加载了:"+xt_Ip_FrozenList.size()+"条平IP黑户数据");
			logger.info(sdf.format(new Date())+"--->读取IP黑户缓存耗时:"+(millis2-millis1)+"毫秒");
		}
		
		millis1 = System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取平台常量开始");
		condition = new HashMap<String, Object>();
		List<XtConstant> xtConstantList = xtConstantService.getXtConstantListAllByCondition(condition);
		Element XtConstantEle=new Element(CacheConstant.XTCONSTANTCACHE, xtConstantList); 
		//取得配置文件中预先，定义的XtConstantEle设置，生成一个Cache 该XtConstantEle为ehcache.xml定义好的名称
		Cache XtConstantCache = CacheManagerUtil.getCache(CacheConstant.XTCONSTANTCACHE);
		//在缓存中放元素
		XtConstantCache.put(XtConstantEle);
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+xtConstantList.size()+"条平台常量数据");
		logger.info(sdf.format(new Date())+"--->读取平台常量缓存耗时:"+(millis2-millis1)+"毫秒");
    }
    
    /**
     * 加载公共功能到内存中
     */
    public void cacheXtFunctioninfoCommon(){
    	XtFunctioninfoCommonService xtFunctioninfoCommonService = (XtFunctioninfoCommonService)SpringUtil.getBean("xtFunctioninfoCommonService");
    	XtFunctioninfoService xtFunctioninfoService = (XtFunctioninfoService)SpringUtil.getBean("xtFunctioninfoService");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	condition.put("xt_functioninfo_common_status", "0");
    	List<XtFunctioninfoCommon> xt_Functioninfo_CommonList = xtFunctioninfoCommonService.getXtFunctioninfoCommonListByCondition(condition);
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
		List<XtFunctioninfo> xtFunctioninfoList = xtFunctioninfoService.getXtFunctioninfoList(condition);
		logger.info(sdf.format(new Date())+"--->读取非公共功能无需拦截数量:"+xtFunctioninfoList.size()+"个");
		for(XtFunctioninfo xtFunctioninfo:xtFunctioninfoList){
			sbf.append(xtFunctioninfo.getXt_functioninfoURL()+",");
		}
		Element XtFunctioninfoCommonEle=new Element(CacheConstant.XTFUNCTIONINFOCOMMONCACHE, sbf.toString()); 
		//取得配置文件中预先，定义的XtFunctioninfoCommonCache设置，生成一个Cache 该XtFunctioninfoCommonCache为ehcache.xml定义好的名称
		Cache XtFunctioninfoCommonCache = CacheManagerUtil.getCache(CacheConstant.XTFUNCTIONINFOCOMMONCACHE);
		//在缓存中放元素
		XtFunctioninfoCommonCache.put(XtFunctioninfoCommonEle);
		logger.info(sdf.format(new Date())+"--->加载公共功能缓存结束");
		millis2 =  System.currentTimeMillis();
    }
    
    /**
     * 加载Solr实例到缓存中
     */
    public void cacheSolrCore(){
    	SolrIndexAttributeService solrIndexAttributeService = (SolrIndexAttributeService)SpringUtil.getBean("solrIndexAttributeService");
    	SolrCoreService solrCoreService = (SolrCoreService)SpringUtil.getBean("solrCoreService");
    	SolrSortService solrSortService = (SolrSortService)SpringUtil.getBean("solrSortService");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	List<SolrCore> solrCoreList = solrCoreService.getSolrCoreListByCondition(condition);
    	for(int i = 0; i < solrCoreList.size(); i++){
    		condition = new HashMap<String, Object>();
    		SolrCore solrCore = solrCoreList.get(i);
    		condition.put("solr_core_id", solrCore.getSolr_core_id());
    		solrCoreList.get(i).setSolr_index_attribute_list(solrIndexAttributeService.getSolrIndexAttributeList(condition));
    		solrCoreList.get(i).setSolr_sort_list(solrSortService.getSolrSortList(condition));
    	}
    	long millis2 =  System.currentTimeMillis();
    	logger.info(sdf.format(new Date())+"--->读取SOLR实例数量:"+solrCoreList.size()+"个");
		logger.info(sdf.format(new Date())+"--->读取SOLR实例耗时:"+(millis2-millis1)+"毫秒");
		logger.info(sdf.format(new Date())+"--->加载SOLR实例缓存开始");
		Element SolrCoreCacheEle=new Element(CacheConstant.SOLRCORECACHE, solrCoreList); 
		Cache SolrCoreCache = CacheManagerUtil.getCache(CacheConstant.SOLRCORECACHE);
		//在缓存中放元素
		SolrCoreCache.put(SolrCoreCacheEle);
		logger.info(sdf.format(new Date())+"--->加载SOLR实例缓存结束");
		millis2 =  System.currentTimeMillis();
    }
    
    /**
     * 初始化区域至缓存中
     */
    public void cacheXtAreaRegion(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String,Object>();
    	XtAreaRegionService xtAreaRegionService = (XtAreaRegionService)SpringUtil.getBean("xtAreaRegionService");
    	List<XtAreaRegion> list = xtAreaRegionService.getXtAreaRegionListByCondition(condition);
    	long millis2 =  System.currentTimeMillis();
    	logger.info(sdf.format(new Date())+"--->读取行政区域实例数量:"+list.size()+"个");
		logger.info(sdf.format(new Date())+"--->读取行政区域实例耗时:"+(millis2-millis1)+"毫秒");
		logger.info(sdf.format(new Date())+"--->加载行政区域实例缓存开始");
		Element XtAreaRegionCacheEle=new Element(CacheConstant.XTAREAREGIONCACHE, list); 
		Cache XtAreaRegionCache = CacheManagerUtil.getCache(CacheConstant.XTAREAREGIONCACHE);
		//在缓存中放元素
		XtAreaRegionCache.put(XtAreaRegionCacheEle);
		logger.info(sdf.format(new Date())+"--->加载行政区域实例缓存结束");
		millis2 =  System.currentTimeMillis();
    }
}

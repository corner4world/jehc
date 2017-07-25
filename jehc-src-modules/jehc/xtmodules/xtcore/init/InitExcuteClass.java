package jehc.xtmodules.xtcore.init;

/**
import java.io.File;
**/
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.solrmodules.solrmodel.Solr_Core;
import jehc.solrmodules.solrservice.Solr_CoreService;
import jehc.solrmodules.solrservice.Solr_Index_AttributeService;
import jehc.solrmodules.solrservice.Solr_SortService;
import jehc.xtmodules.xtcore.allutils.AllUtils;
import jehc.xtmodules.xtcore.allutils.file.FileUtil;
import jehc.xtmodules.xtcore.util.CacheManagerUtil;
import jehc.xtmodules.xtcore.util.ReadProperties;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.quartz.QuartzInit;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtmodel.Xt_Constant;
import jehc.xtmodules.xtmodel.Xt_Data_Dictionary;
import jehc.xtmodules.xtmodel.Xt_Functioninfo;
import jehc.xtmodules.xtmodel.Xt_Functioninfo_Common;
import jehc.xtmodules.xtmodel.Xt_Ip_Frozen;
import jehc.xtmodules.xtmodel.Xt_Path;
import jehc.xtmodules.xtmodel.Xt_Quartz;
import jehc.xtmodules.xtmodel.Xt_Start_Stop_Log;
import jehc.xtmodules.xtservice.Xt_ConstantService;
import jehc.xtmodules.xtservice.Xt_Data_DictionaryService;
import jehc.xtmodules.xtservice.Xt_FunctioninfoService;
import jehc.xtmodules.xtservice.Xt_Functioninfo_CommonService;
import jehc.xtmodules.xtservice.Xt_Ip_FrozenService;
import jehc.xtmodules.xtservice.Xt_PathService;
import jehc.xtmodules.xtservice.Xt_QuartzService;
import jehc.xtmodules.xtservice.Xt_Start_Stop_LogService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * 让服务器启动或停止时自动执行代码
 * @author 邓纯杰
 *
 */
public class InitExcuteClass implements ServletContextListener{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	static final String extjs = "/deng/source/plugins/e6/ext-all.js";
	/**
	 * 停止时执行的方法
	 */
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
	    sc.removeAttribute("syspath");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("------------------------"+sdf.format(new Date())+"--->服务器容器已销毁------------------------");
		Xt_Start_Stop_Log xt_Start_Stop_Log = new Xt_Start_Stop_Log();
		xt_Start_Stop_Log.setXt_start_stop_log_stoptime(sdf.format(new Date()));
		addOrUpdateXtStartStopLog(xt_Start_Stop_Log,1);
	}

	/**
	 * 启动时执行方法
	 */
	public void contextInitialized(ServletContextEvent event) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ServletContext sc = event.getServletContext();
		/*
        String contextConfigLocationpath = event.getServletContext().getInitParameter("contextConfigLocation");
		ApplicationContext ac = (ApplicationContext) new ClassPathXmlApplicationContext(contextConfigLocationpath);
		*/
		Xt_Start_Stop_Log xt_Start_Stop_Log = new Xt_Start_Stop_Log();
		try {
	        sc.setAttribute("syspath", getContextPath(sc));
	        logger.info(""+sdf.format(new Date())+"--->业务平台路径:"+getContextPath(sc));
			/**
			String realPath = event.getServletContext().getRealPath(File.separator);
			String contextConfigLocationpath = event.getServletContext().getRealPath(File.separator)+event.getServletContext().getInitParameter("contextConfigLocation");
			System.out.println("------------------------"+sdf.format(new Date())+"--->业务平台服务器路径:"+realPath+"------------------------");
			System.out.println("------------------------"+sdf.format(new Date())+"--->业务平台配置路径:"+contextConfigLocationpath+"------------------------");
			**/
			
			/**
			ApplicationContext ac = (ApplicationContext) new ClassPathXmlApplicationContext(new String[]{"/WEB-INF/context/spring.xml","/WEB-INF/context/springmvc.xml"});
			ApplicationContext ac = (ApplicationContext) new FileSystemXmlApplicationContext(new String[]{event.getServletContext().getRealPath(File.separator)+"/WEB-INF/context/spring.xml",event.getServletContext().getRealPath(File.separator)+"/WEB-INF/context/springmvc.xml"});
			ApplicationContext ac = (ApplicationContext) new FileSystemXmlApplicationContext(contextConfigLocationpath);
			**/
	        
	        
	    	loadXtDataDictionary();
	    	loadXtFunctioninfoCommon();
	    	loadSolrCore();
	        logger.info(sdf.format(new Date())+"--->进入类加载");
	        logger.info(sdf.format(new Date())+"--->装载配置文件"); 
			Map<String, Object> map = ReadProperties.readProperties(event);
			sc.setAttribute("sys_pt_login", map.get("sys_pt_login"));
			sc.setAttribute("sys_pt_index", map.get("sys_pt_index"));
			sc.setAttribute("sys_pt_index_foot", map.get("sys_pt_index_foot"));
			sc.setAttribute("sys_pt_index_top", map.get("sys_pt_index_top"));
			sc.setAttribute("sys_pt_session", map.get("sys_pt_session"));
			map = ReadProperties.readMessageProperties(event);
			sc.setAttribute("sys_message", map);
			logger.info(sdf.format(new Date())+"--->装载配置结束"); 
			
			logger.info(sdf.format(new Date())+"--->装载Config配置开始"); 
			map = ReadProperties.readConfigProperties(event);
			sc.setAttribute("grid_toolbar_text_is_view", map.get("grid_toolbar_text_is_view"));
			sc.setAttribute("moretext", map.get("moretext"));
			sc.setAttribute("moretexttooltip", map.get("moretexttooltip"));
			sc.setAttribute("grid_toolbar_gaps", map.get("grid_toolbar_gaps"));
			sc.setAttribute("grid_toolbar_moretext_gaps", map.get("grid_toolbar_moretext_gaps"));
			sc.setAttribute("solr_home_path", map.get("solr_home_path"));
			sc.setAttribute("lc_design_displaywin_for_edit", map.get("lc_design_displaywin_for_edit"));
			logger.info(sdf.format(new Date())+"--->装载Config配置结束"); 
			logger.info(sdf.format(new Date())+"--->开始初始化调度任务"); 
			loadQuarzInit();
			logger.info(sdf.format(new Date())+"--->结束初始化调度任务"); 
			
			//加载ext-all.js文件内容只缓存中
//			sc.setAttribute("ext_all",FileUtil.compress(FileUtil.readFile(AllUtils.getWebRootAbsolutePath()+extjs)));
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
	public void addOrUpdateXtStartStopLog(Xt_Start_Stop_Log xt_Start_Stop_Log,int status){
		Xt_Start_Stop_LogService xt_Start_Stop_LogService = (Xt_Start_Stop_LogService)SpringUtil.getBean("xt_Start_Stop_LogService");;
		if(1==status){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("offset", "0");
			condition.put("pageSize", "1");
			List<Xt_Start_Stop_Log> list = xt_Start_Stop_LogService.getXtStartStopLogListByCondition(condition);
			if(!list.isEmpty()){
				xt_Start_Stop_LogService.updateXtStartStopLog(list.get(0));
			}
		}else{
			xt_Start_Stop_Log.setXt_start_stop_log_id(UUID.toUUID());
			xt_Start_Stop_LogService.addXtStartStopLog(xt_Start_Stop_Log);
		}
	}
	/**
     * 加载调度器设置
     */
    private void loadQuarzInit() {
        Timer timer = new Timer("loadQuarzInit", true);
        timer.schedule(new TimerTask() {
            public void run() {
            	Xt_QuartzService xt_QuartzService = (Xt_QuartzService)SpringUtil.getBean("xt_QuartzService");
            	Scheduler scheduler = (Scheduler) SpringUtil.getBean("schedulerFactoryBean");
            	Map<String, Object> condition = new HashMap<String, Object>();
            	condition.put("jobStatus", "NORMAL");
        		List<Xt_Quartz> xtQuartzList = xt_QuartzService.getXtQuartzListAllByCondition(condition);
        		for(int i = 0; i < xtQuartzList.size(); i++){
        			Xt_Quartz xtQuartz = xtQuartzList.get(i);
        			new QuartzInit(scheduler,xtQuartz.getId(),xtQuartz.getJobName(),xtQuartz.getJobGroup(),xtQuartz.getCronExpression(),xtQuartz.getDesc(),xtQuartz.getTargetMethod(),xtQuartz.getTargetClass()).run();
        		}
            }
        }, 1 * 10);
    }
    
    /**
     * 加载数据字典，平台常量及平台路径到缓存中
     * @param ehCache
     */
	private void loadXtDataDictionary(){
    	Xt_Data_DictionaryService xt_Data_DictionaryService = (Xt_Data_DictionaryService)SpringUtil.getBean("xt_Data_DictionaryService");
    	Xt_PathService xt_PathService = (Xt_PathService)SpringUtil.getBean("xt_PathService");
    	Xt_Ip_FrozenService xt_Ip_FrozenService = (Xt_Ip_FrozenService)SpringUtil.getBean("xt_Ip_FrozenService");
    	Xt_ConstantService xt_ConstantService = (Xt_ConstantService)SpringUtil.getBean("xt_ConstantService");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	List<Xt_Data_Dictionary> Xt_Data_DictionaryList = xt_Data_DictionaryService.getXtDataDictionaryListAllByCondition(condition);
		//2取得配置文件中预先，定义的XtDataDictionaryCache设置，生成一个Cache 该XtDataDictionaryCache为ehcache.xml定义好的名称
		Cache XtDataDictionaryCache = CacheManagerUtil.getCache("XtDataDictionaryCache");
		//3在缓存中放元素
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
		List<Xt_Path> xt_Path_List = xt_PathService.getXtPathListAllByCondition(condition);
		Element XtPathEle=new Element("XtPathCache", xt_Path_List); 
		//取得配置文件中预先，定义的XtPathCache设置，生成一个Cache 该XtPathCache为ehcache.xml定义好的名称
		Cache XtPathCache = CacheManagerUtil.getCache("XtPathCache");
		//在缓存中放元素
		XtPathCache.put(XtPathEle);
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+xt_Path_List.size()+"条平台路径数据");
		logger.info(sdf.format(new Date())+"--->读取平台路径缓存耗时:"+(millis2-millis1)+"毫秒");
		
		
		millis1 = System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取IP黑户开始");
		condition = new HashMap<String, Object>();
		condition.put("xt_ip_frozen_status", 2);
		List<Xt_Ip_Frozen> xt_Ip_FrozenList = xt_Ip_FrozenService.getXtIpFrozenListAllByCondition(condition);
		if(!xt_Ip_FrozenList.isEmpty() && xt_Ip_FrozenList.size() > 0){
			Element XtIpFrozenEle=new Element("XtIpFrozenCache", xt_Ip_FrozenList); 
			//取得配置文件中预先，定义的XtIpFrozenCache设置，生成一个Cache 该XtIpFrozenCache为ehcache.xml定义好的名称
			Cache XtIpFrozenCache = CacheManagerUtil.getCache("XtIpFrozenCache");
			//在缓存中放元素
			XtIpFrozenCache.put(XtIpFrozenEle);
			millis2 =  System.currentTimeMillis();
			logger.info(sdf.format(new Date())+"--->一共加载了:"+xt_Ip_FrozenList.size()+"条平IP黑户数据");
			logger.info(sdf.format(new Date())+"--->读取IP黑户缓存耗时:"+(millis2-millis1)+"毫秒");
		}
		
		millis1 = System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->读取平台常量开始");
		condition = new HashMap<String, Object>();
		List<Xt_Constant> xt_ConstantList = xt_ConstantService.getXtConstantListAllByCondition(condition);
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
    	Xt_Functioninfo_CommonService xt_Functioninfo_CommonService = (Xt_Functioninfo_CommonService)SpringUtil.getBean("xt_Functioninfo_CommonService");
    	Xt_FunctioninfoService xt_FunctioninfoService = (Xt_FunctioninfoService)SpringUtil.getBean("xt_FunctioninfoService");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	condition.put("xt_functioninfo_common_status", "0");
    	List<Xt_Functioninfo_Common> xt_Functioninfo_CommonList = xt_Functioninfo_CommonService.getXtFunctioninfoCommonListByCondition(condition);
    	long millis2 =  System.currentTimeMillis();
    	logger.info(sdf.format(new Date())+"--->读取公共功能数量:"+xt_Functioninfo_CommonList.size()+"个");
		logger.info(sdf.format(new Date())+"--->读取公共功能耗时:"+(millis2-millis1)+"毫秒");
		logger.info(sdf.format(new Date())+"--->加载公共功能缓存开始");
		StringBuffer sbf = new StringBuffer();
		for(Xt_Functioninfo_Common xt_Functioninfo_Common:xt_Functioninfo_CommonList){
			if(null != sbf && !"".equals(sbf.toString()) && null != (sbf.toString())){
				sbf.append(xt_Functioninfo_Common.getXt_functioninfo_common_url()+",");
			}else{
				sbf.append(","+xt_Functioninfo_Common.getXt_functioninfo_common_url()+",");
			}
		}
		condition = new HashMap<String, Object>();
		condition.put("xt_functioninfoType", "0");
		List<Xt_Functioninfo> xtFunctioninfoList = xt_FunctioninfoService.getXtFunctioninfoList(condition);
		logger.info(sdf.format(new Date())+"--->读取非公共功能无需拦截数量:"+xtFunctioninfoList.size()+"个");
		for(Xt_Functioninfo xtFunctioninfo:xtFunctioninfoList){
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
    	Solr_Index_AttributeService solr_Index_AttributeService = (Solr_Index_AttributeService)SpringUtil.getBean("solr_Index_AttributeService");
    	Solr_CoreService solr_CoreService = (Solr_CoreService)SpringUtil.getBean("solr_CoreService");
    	Solr_SortService solr_SortService = (Solr_SortService)SpringUtil.getBean("solr_SortService");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	List<Solr_Core> solr_CoreList = solr_CoreService.getSolrCoreListByCondition(condition);
    	for(int i = 0; i < solr_CoreList.size(); i++){
    		condition = new HashMap<String, Object>();
    		Solr_Core solr_Core = solr_CoreList.get(i);
    		condition.put("solr_core_id", solr_Core.getSolr_core_id());
    		solr_CoreList.get(i).setSolr_index_attribute_list(solr_Index_AttributeService.getSolrIndexAttributeList(condition));
    		solr_CoreList.get(i).setSolr_sort_list(solr_SortService.getSolrSortList(condition));
    	}
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

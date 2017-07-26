package jehc.xtmodules.xtcore.init;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import jehc.xtmodules.xtcore.util.CacheManagerUtil;
import jehc.xtmodules.xtmodel.XtConstant;
import jehc.xtmodules.xtmodel.XtDataDictionary;
import jehc.xtmodules.xtmodel.XtIpFrozen;
import jehc.xtmodules.xtmodel.XtPath;
import jehc.xtmodules.xtmodel.XtQuartz;
import jehc.xtmodules.xtservice.XtConstantService;
import jehc.xtmodules.xtservice.XtDataDictionaryService;
import jehc.xtmodules.xtservice.XtIpFrozenService;
import jehc.xtmodules.xtservice.XtPathService;
import jehc.xtmodules.xtservice.XtQuartzService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
/**
 * 启动工程解决注解未取到问题
 * @author 邓纯杰
 *
 */
@Service
public class InitListener implements ApplicationContextAware, ServletContextAware,InitializingBean,ApplicationListener<ContextRefreshedEvent>{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@Autowired
	private XtQuartzService xtQuartzService;
	@Autowired
	private XtDataDictionaryService xtDataDictionaryService;
	@Autowired
	private XtPathService xtPathService;
	@Autowired
	private XtIpFrozenService xtIpFrozenService;
	@Autowired
	private XtConstantService xtConstantService;
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        logger.info("1 => StartupListener.setApplicationContext");
    }
 
    public void setServletContext(ServletContext context) {
        logger.info("2 => StartupListener.setServletContext");
    }
    public void afterPropertiesSet() throws Exception {
    	/**
    	loadQuarzInit();
    	logger.info("--------------------------->初始化启动调度任务成功------------------------------------------");
    	loadXtDataDictionary();
        logger.info("3 => StartupListener.afterPropertiesSet");
        **/
    }
    public void onApplicationEvent(final ContextRefreshedEvent evt) {
    	logger.info("4.1 => MyApplicationListener.onApplicationEvent");
        if (evt.getApplicationContext().getParent() == null) {
        	logger.info("4.2 => MyApplicationListener.onApplicationEvent");
        	/**
        	loadQuarzInit();
        	logger.info("--------------------------->初始化启动调度任务成功------------------------------------------");
        	loadXtDataDictionary();
        	**/
        }
    }

    /**
     * 加载调度器设置
     */
    private void loadQuarzInit() {
        Timer timer = new Timer("loadQuarzInit", true);
        timer.schedule(new TimerTask() {
            public void run() {
            	Map<String, Object> condition = new HashMap<String, Object>();
            	condition.put("jobStatus", "NORMAL");
        		List<XtQuartz> xtQuartzList = xtQuartzService.getXtQuartzListAllByCondition(condition);
        		for(int i = 0; i < xtQuartzList.size(); i++){
        			XtQuartz xtQuartz = xtQuartzList.get(i);
//        			new QuartzInit(schedulerFactoryBean,xtQuartz.getId(),xtQuartz.getJobName(),xtQuartz.getJobGroup(),xtQuartz.getCronExpression(),xtQuartz.getDesc(),xtQuartz.getTargetMethod(),xtQuartz.getTargetClass()).run();
        		}
            }
        }, 1 * 10);
    }
    
    /**
     * 加载数据字典，平台常量及平台路径到缓存中
     * @param ehCache
     */
    @SuppressWarnings("unused")
	private void loadXtDataDictionary(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millis1 = System.currentTimeMillis();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	List<XtDataDictionary> Xt_Data_DictionaryList = xtDataDictionaryService.getXtDataDictionaryListAllByCondition(condition);
//    	//1创建缓存管理器
//    	URL url = getClass().getResource("/xtCore/sources/ehcache/ehcache.xml");
//		CacheManager cacheManager = CacheManager.create(url);
		/**
		 *该方法为手动设置：设置缓存并加入到缓存管理器中
		Cache ehCache = new Cache("AllEhCache", 1, true, false, 5, 2);
		cacheManager.addCache(ehCache);
		**/
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
		List<XtPath> xt_Path_List = xtPathService.getXtPathListAllByCondition(condition);
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
		List<XtIpFrozen> xt_Ip_FrozenList = xtIpFrozenService.getXtIpFrozenListAllByCondition(condition);
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
		List<XtConstant> xt_ConstantList = xtConstantService.getXtConstantListAllByCondition(condition);
		Element XtConstantEle=new Element("XtConstantCache", xt_ConstantList); 
		//取得配置文件中预先，定义的XtConstantEle设置，生成一个Cache 该XtConstantEle为ehcache.xml定义好的名称
		Cache XtConstantCache = CacheManagerUtil.getCache("XtConstantCache");
		//在缓存中放元素
		XtConstantCache.put(XtConstantEle);
		millis2 =  System.currentTimeMillis();
		logger.info(sdf.format(new Date())+"--->一共加载了:"+xt_ConstantList.size()+"条平台常量数据");
		logger.info(sdf.format(new Date())+"--->读取平台常量缓存耗时:"+(millis2-millis1)+"毫秒");
    }
}

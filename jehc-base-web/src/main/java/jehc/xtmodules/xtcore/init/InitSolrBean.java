package jehc.xtmodules.xtcore.init;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import jehc.solrmodules.solrmodel.SolrCore;
import jehc.solrmodules.solrservice.SolrCoreService;
import jehc.solrmodules.solrservice.SolrIndexAttributeService;
import jehc.solrmodules.solrservice.SolrSortService;
import jehc.xtmodules.xtcore.cache.ehcache.CacheManagerUtil;
import jehc.xtmodules.xtcore.util.constant.CacheConstant;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
/**
 * 初始化检索实例至缓存中
 * @author 邓纯杰
 *
 */
public class InitSolrBean implements ApplicationListener<ContextRefreshedEvent>{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SolrIndexAttributeService solrIndexAttributeService;
	@Autowired
    SolrCoreService solrCoreService;
	@Autowired
    SolrSortService solrSortService;
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			initdata();
		}
	}
	
	public void initdata(){
		cacheSolrCore();
	}
	
	/**
     * 加载Solr实例到缓存中
     */
    public void cacheSolrCore(){
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
}

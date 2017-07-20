package jehc.xtmodules.xtcore.util.quartz.task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.solrmodules.solrmodel.Solr_Core;
import jehc.solrmodules.solrmodel.Solr_Url;
import jehc.solrmodules.solrservice.Solr_CoreService;
import jehc.solrmodules.solrservice.Solr_UrlService;
import jehc.xtmodules.xtcore.solr.utils.SolrUtil;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
/**
 * 定时增量更新索引
 * @author Administrator
 *
 */
public class Solr_dataimportTask extends Thread{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 业务逻辑处理
	 */
	public void service() {
		new Solr_dataimportTask().start();
	}
	
	public void run(){
		try {
			excute();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void excute(){
		logger.info("----------开始进行增量索引--------------");
		Solr_CoreService solr_CoreService = (Solr_CoreService)SpringUtil.getBean("solr_CoreService");
		Solr_UrlService solr_UrlService = (Solr_UrlService)SpringUtil.getBean("solr_UrlService");
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Solr_Core> solr_CoreList = solr_CoreService.getSolrCoreListByCondition(condition);
		for(int i = 0; i < solr_CoreList.size(); i++){
			Solr_Url solr_url = solr_UrlService.getSolrUrlById(solr_CoreList.get(i).getSolr_url_id());
			SolrUtil.dataimport(solr_url.getSolr_url_url(), solr_CoreList.get(i).getSolr_core_name());
		}
	}
}

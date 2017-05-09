package jehc.solrmodules.solrweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;

import jehc.solrmodules.solrmodel.Solr_Data_Config;
import jehc.solrmodules.solrservice.Solr_Data_ConfigService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* solr_data_config模板配置 
* 2016-07-02 10:09:05  邓纯杰
*/
@Controller
@RequestMapping("/solrDataConfigController")
public class Solr_Data_ConfigController extends BaseAction{
	@Autowired
	private Solr_Data_ConfigService solr_Data_ConfigService;
	/**
	* 载入初始化页面
	* @param solr_data_config 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrDataConfig",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrDataConfig(Solr_Data_Config solr_Data_Config,HttpServletRequest request){
		return new ModelAndView("pc/solr-view/solr-data-config/solr-data-config-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_data_config 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrDataConfigListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrDataConfigListByCondition(Solr_Data_Config solr_Data_Config,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		condition.put("solr_data_config_title",request.getParameter("solr_data_config_title"));
		condition.put("solr_data_config_status",request.getParameter("solr_data_config_status"));
		List<Solr_Data_Config> solr_Data_ConfigList = solr_Data_ConfigService.getSolrDataConfigListByCondition(condition);
		PageInfo<Solr_Data_Config> page = new PageInfo<Solr_Data_Config>(solr_Data_ConfigList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param solr_data_config_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrDataConfigById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrDataConfigById(String solr_data_config_id,HttpServletRequest request){
		Solr_Data_Config solr_Data_Config = solr_Data_ConfigService.getSolrDataConfigById(solr_data_config_id);
		return outDataStr(solr_Data_Config);
	}
	/**
	* 添加
	* @param solr_data_config 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrDataConfig",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrDataConfig(Solr_Data_Config solr_Data_Config,HttpServletRequest request){
		int i = 0;
		if(null != solr_Data_Config && !"".equals(solr_Data_Config)){
			solr_Data_Config.setSolr_data_config_id(UUID.toUUID());
			solr_Data_Config.setSolr_data_config_ctime(getSimpleDateFormat());
			solr_Data_Config.setXt_userinfo_id(getXtUid());
			solr_Data_Config.setSolr_data_config_datasource(request.getParameter("solr_data_config_datasource"));
			solr_Data_Config.setSolr_data_config_content(request.getParameter("solr_data_config_content"));
			i=solr_Data_ConfigService.addSolrDataConfig(solr_Data_Config);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_data_config 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrDataConfig",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrDataConfig(Solr_Data_Config solr_Data_Config,HttpServletRequest request){
		int i = 0;
		if(null != solr_Data_Config && !"".equals(solr_Data_Config)){
			solr_Data_Config.setSolr_data_config_mtime(getSimpleDateFormat());
			solr_Data_Config.setXt_userinfo_id(getXtUid());
			solr_Data_Config.setSolr_data_config_datasource(request.getParameter("solr_data_config_datasource"));
			solr_Data_Config.setSolr_data_config_content(request.getParameter("solr_data_config_content"));
			i=solr_Data_ConfigService.updateSolrDataConfig(solr_Data_Config);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_data_config_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrDataConfig",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrDataConfig(String solr_data_config_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_data_config_id && !"".equals(solr_data_config_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_data_config_id",solr_data_config_id.split(","));
			i=solr_Data_ConfigService.delSolrDataConfig(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_data_config_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrDataConfig",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrDataConfig(String solr_data_config_id,HttpServletRequest request){
		int i = 0;
		Solr_Data_Config solr_Data_Config = solr_Data_ConfigService.getSolrDataConfigById(solr_data_config_id);
		if(null != solr_Data_Config && !"".equals(solr_Data_Config)){
			solr_Data_Config.setSolr_data_config_id(UUID.toUUID());
			i=solr_Data_ConfigService.addSolrDataConfig(solr_Data_Config);
		}
		if(i>0){
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
	@RequestMapping(value="/exportSolrDataConfig",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrDataConfig(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	* 加载所有数据
	* @param solr_data_config 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrDataConfigList",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrDataConfigList(Solr_Data_Config solr_Data_Config,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("solr_data_config_status", 0);
		List<Solr_Data_Config> solr_Data_ConfigList = solr_Data_ConfigService.getSolrDataConfigListByCondition(condition);
		return outComboDataStr(solr_Data_ConfigList);
	}
}

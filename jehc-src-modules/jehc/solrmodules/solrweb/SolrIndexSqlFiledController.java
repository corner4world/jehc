package jehc.solrmodules.solrweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.solrmodules.solrmodel.SolrIndexSqlFiled;
import jehc.solrmodules.solrservice.SolrIndexSqlFiledService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* SQL查询结果返回字段 
* 2015-12-23 09:44:02  邓纯杰
*/
@Controller
@RequestMapping("/solrIndexSqlFiledController")
@Scope("prototype")
public class SolrIndexSqlFiledController extends BaseAction{
	@Autowired
	private SolrIndexSqlFiledService solrIndexSqlFiledService;
	/**
	* 载入初始化页面
	* @param solr_index_sql_filed 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrIndexSqlFiled",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrIndexSqlFiled(SolrIndexSqlFiled solr_Index_Sql_Filed,HttpServletRequest request){
		return new ModelAndView("pc/solr-view/solr-index-sql-filed/solr-index-sql-filed-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_index_sql_filed 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrIndexSqlFiledListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexSqlFiledListByCondition(SolrIndexSqlFiled solr_Index_Sql_Filed,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		if(null != solr_Index_Sql_Filed.getSolr_entity_id() && !"".equals(solr_Index_Sql_Filed.getSolr_entity_id())){
			condition.put("solr_entity_id", solr_Index_Sql_Filed.getSolr_entity_id());
		}else{
			condition.put("solr_entity_id", 0);
		}
		List<SolrIndexSqlFiled> solr_Index_Sql_FiledList = solrIndexSqlFiledService.getSolrIndexSqlFiledListByCondition(condition);
		return outItemsStr(solr_Index_Sql_FiledList);
	}
	/**
	* 获取对象
	* @param solr_index_sql_filed_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrIndexSqlFiledById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexSqlFiledById(String solr_index_sql_filed_id,HttpServletRequest request){
		SolrIndexSqlFiled solr_Index_Sql_Filed = solrIndexSqlFiledService.getSolrIndexSqlFiledById(solr_index_sql_filed_id);
		return outDataStr(solr_Index_Sql_Filed);
	}
	/**
	* 添加
	* @param solr_index_sql_filed 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrIndexSqlFiled",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrIndexSqlFiled(SolrIndexSqlFiled solr_Index_Sql_Filed,HttpServletRequest request){
		int i = 0;
		if(null != solr_Index_Sql_Filed && !"".equals(solr_Index_Sql_Filed)){
			solr_Index_Sql_Filed.setSolr_index_sql_filed_id(UUID.toUUID());
			i=solrIndexSqlFiledService.addSolrIndexSqlFiled(solr_Index_Sql_Filed);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_index_sql_filed 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrIndexSqlFiled",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrIndexSqlFiled(SolrIndexSqlFiled solr_Index_Sql_Filed,HttpServletRequest request){
		int i = 0;
		if(null != solr_Index_Sql_Filed && !"".equals(solr_Index_Sql_Filed)){
			i=solrIndexSqlFiledService.updateSolrIndexSqlFiled(solr_Index_Sql_Filed);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_index_sql_filed_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrIndexSqlFiled",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrIndexSqlFiled(String solr_index_sql_filed_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_index_sql_filed_id && !"".equals(solr_index_sql_filed_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_index_sql_filed_id",solr_index_sql_filed_id.split(","));
			i=solrIndexSqlFiledService.delSolrIndexSqlFiled(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_index_sql_filed_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrIndexSqlFiled",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrIndexSqlFiled(String solr_index_sql_filed_id,HttpServletRequest request){
		int i = 0;
		SolrIndexSqlFiled solr_Index_Sql_Filed = solrIndexSqlFiledService.getSolrIndexSqlFiledById(solr_index_sql_filed_id);
		if(null != solr_Index_Sql_Filed && !"".equals(solr_Index_Sql_Filed)){
			solr_Index_Sql_Filed.setSolr_index_sql_filed_id(UUID.toUUID());
			i=solrIndexSqlFiledService.addSolrIndexSqlFiled(solr_Index_Sql_Filed);
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
	@RequestMapping(value="/exportSolrIndexSqlFiled",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrIndexSqlFiled(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

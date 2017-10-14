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

import jehc.solrmodules.solrmodel.SolrIndexSql;
import jehc.solrmodules.solrservice.SolrIndexSqlService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 索引SQL查询 
* 2015-12-23 09:42:26  邓纯杰
*/
@Controller
@RequestMapping("/solrIndexSqlController")
@Scope("prototype")
public class SolrIndexSqlController extends BaseAction{
	@Autowired
	private SolrIndexSqlService solrIndexSqlService;
	/**
	* 载入初始化页面
	* @param solr_index_sql 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrIndexSql",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrIndexSql(SolrIndexSql solr_Index_Sql,HttpServletRequest request){
		return new ModelAndView("pc/solr-view/solr-index-sql/solr-index-sql-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_index_sql 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrIndexSqlListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexSqlListByCondition(SolrIndexSql solr_Index_Sql,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		if(null != solr_Index_Sql.getSolr_entity_id() && !"".equals(solr_Index_Sql.getSolr_entity_id())){
			condition.put("solr_entity_id", solr_Index_Sql.getSolr_entity_id());
		}else{
			condition.put("solr_entity_id", 0);
		}
		List<SolrIndexSql> solr_Index_SqlList = solrIndexSqlService.getSolrIndexSqlListByCondition(condition);
		return outItemsStr(solr_Index_SqlList);
	}
	/**
	* 获取对象
	* @param solr_index_sql_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrIndexSqlById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexSqlById(String solr_index_sql_id,HttpServletRequest request){
		SolrIndexSql solr_Index_Sql = solrIndexSqlService.getSolrIndexSqlById(solr_index_sql_id);
		return outDataStr(solr_Index_Sql);
	}
	/**
	* 添加
	* @param solr_index_sql 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrIndexSql",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrIndexSql(SolrIndexSql solr_Index_Sql,HttpServletRequest request){
		int i = 0;
		if(null != solr_Index_Sql && !"".equals(solr_Index_Sql)){
			solr_Index_Sql.setSolr_index_sql_id(UUID.toUUID());
			i=solrIndexSqlService.addSolrIndexSql(solr_Index_Sql);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_index_sql 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrIndexSql",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrIndexSql(SolrIndexSql solr_Index_Sql,HttpServletRequest request){
		int i = 0;
		if(null != solr_Index_Sql && !"".equals(solr_Index_Sql)){
			i=solrIndexSqlService.updateSolrIndexSql(solr_Index_Sql);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_index_sql_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrIndexSql",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrIndexSql(String solr_index_sql_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_index_sql_id && !"".equals(solr_index_sql_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_index_sql_id",solr_index_sql_id.split(","));
			i=solrIndexSqlService.delSolrIndexSql(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_index_sql_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrIndexSql",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrIndexSql(String solr_index_sql_id,HttpServletRequest request){
		int i = 0;
		SolrIndexSql solr_Index_Sql = solrIndexSqlService.getSolrIndexSqlById(solr_index_sql_id);
		if(null != solr_Index_Sql && !"".equals(solr_Index_Sql)){
			solr_Index_Sql.setSolr_index_sql_id(UUID.toUUID());
			i=solrIndexSqlService.addSolrIndexSql(solr_Index_Sql);
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
	@RequestMapping(value="/exportSolrIndexSql",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrIndexSql(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

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

import com.github.pagehelper.PageInfo;

import jehc.solrmodules.solrmodel.Solr_Index;
import jehc.solrmodules.solrservice.Solr_IndexService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 索引字段表 
* 2015-12-23 09:32:01  邓纯杰
*/
@Controller
@RequestMapping("/solrIndexController")
@Scope("prototype")
public class Solr_IndexController extends BaseAction{
	@Autowired
	private Solr_IndexService solr_IndexService;
	/**
	* 载入初始化页面
	* @param solr_index 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrIndex",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrIndex(Solr_Index solr_Index,HttpServletRequest request){
		return new ModelAndView("pc/solr-view/solr-index/solr-index-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_index 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrIndexListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexListByCondition(Solr_Index solr_Index,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		condition.put("solr_core_id", solr_Index.getSolr_core_id());
		List<Solr_Index> solr_IndexList = solr_IndexService.getSolrIndexListByCondition(condition);
		PageInfo<Solr_Index> page = new PageInfo<Solr_Index>(solr_IndexList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param solr_index_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrIndexById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexById(String solr_index_id,HttpServletRequest request){
		Solr_Index solr_Index = solr_IndexService.getSolrIndexById(solr_index_id);
		return outDataStr(solr_Index);
	}
	/**
	* 添加
	* @param solr_index 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrIndex",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrIndex(Solr_Index solr_Index,HttpServletRequest request){
		int i = 0;
		if(null != solr_Index && !"".equals(solr_Index)){
			solr_Index.setSolr_index_id(UUID.toUUID());
			i=solr_IndexService.addSolrIndex(solr_Index);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_index 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrIndex",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrIndex(Solr_Index solr_Index,HttpServletRequest request){
		int i = 0;
		if(null != solr_Index && !"".equals(solr_Index)){
			i=solr_IndexService.updateSolrIndex(solr_Index);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_index_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrIndex",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrIndex(String solr_index_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_index_id && !"".equals(solr_index_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_index_id",solr_index_id.split(","));
			i=solr_IndexService.delSolrIndex(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_index_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrIndex",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrIndex(String solr_index_id,HttpServletRequest request){
		int i = 0;
		Solr_Index solr_Index = solr_IndexService.getSolrIndexById(solr_index_id);
		if(null != solr_Index && !"".equals(solr_Index)){
			solr_Index.setSolr_index_id(UUID.toUUID());
			i=solr_IndexService.addSolrIndex(solr_Index);
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
	@RequestMapping(value="/exportSolrIndex",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrIndex(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	* 读取索引
	* @param solr_index 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrIndexList",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexList(String solr_core_id,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("solr_core_id", solr_core_id);
		List<Solr_Index> solr_IndexList = solr_IndexService.getSolrIndexListByCondition(condition);
		return outItemsStr(solr_IndexList);
	}
}

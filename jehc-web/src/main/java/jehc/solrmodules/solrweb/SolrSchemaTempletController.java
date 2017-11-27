package jehc.solrmodules.solrweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.solrmodules.solrmodel.SolrSchemaTemplet;
import jehc.solrmodules.solrservice.SolrSchemaTempletService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* solr schema 模板 
* 2016-07-01 13:14:46  邓纯杰
*/
@Controller
@RequestMapping("/solrSchemaTempletController")
public class SolrSchemaTempletController extends BaseAction{
	@Autowired
	private SolrSchemaTempletService solrSchemaTempletService;
	/**
	* 载入初始化页面
	* @param solr_schema_templet 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrSchemaTemplet",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrSchemaTemplet(SolrSchemaTemplet solr_Schema_Templet,HttpServletRequest request){
		return new ModelAndView("pc/solr-view/solr-schema-templet/solr-schema-templet-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_schema_templet 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrSchemaTempletListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrSchemaTempletListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<SolrSchemaTemplet> solrSchemaTempletList = solrSchemaTempletService.getSolrSchemaTempletListByCondition(condition);
		PageInfo<SolrSchemaTemplet> page = new PageInfo<SolrSchemaTemplet>(solrSchemaTempletList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param solr_schema_templet_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrSchemaTempletById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrSchemaTempletById(String solr_schema_templet_id,HttpServletRequest request){
		SolrSchemaTemplet solr_Schema_Templet = solrSchemaTempletService.getSolrSchemaTempletById(solr_schema_templet_id);
		return outDataStr(solr_Schema_Templet);
	}
	/**
	* 添加
	* @param solr_schema_templet 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrSchemaTemplet",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrSchemaTemplet(SolrSchemaTemplet solr_Schema_Templet,HttpServletRequest request){
		int i = 0;
		if(null != solr_Schema_Templet && !"".equals(solr_Schema_Templet)){
			solr_Schema_Templet.setXt_userinfo_id(getXtUid());
			solr_Schema_Templet.setSolr_schema_templet_content(request.getParameter("solr_schema_templet_content"));
			solr_Schema_Templet.setSolr_schema_templet_ctime(getSimpleDateFormat());
			solr_Schema_Templet.setSolr_schema_templet_id(UUID.toUUID());
			i=solrSchemaTempletService.addSolrSchemaTemplet(solr_Schema_Templet);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_schema_templet 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrSchemaTemplet",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrSchemaTemplet(SolrSchemaTemplet solr_Schema_Templet,HttpServletRequest request){
		int i = 0;
		if(null != solr_Schema_Templet && !"".equals(solr_Schema_Templet)){
			solr_Schema_Templet.setXt_userinfo_id(getXtUid());
			solr_Schema_Templet.setSolr_schema_templet_content(request.getParameter("solr_schema_templet_content"));
			solr_Schema_Templet.setSolr_schema_templet_mtime(getSimpleDateFormat());
			i=solrSchemaTempletService.updateSolrSchemaTemplet(solr_Schema_Templet);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_schema_templet_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrSchemaTemplet",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrSchemaTemplet(String solr_schema_templet_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_schema_templet_id && !"".equals(solr_schema_templet_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_schema_templet_id",solr_schema_templet_id.split(","));
			i=solrSchemaTempletService.delSolrSchemaTemplet(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_schema_templet_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrSchemaTemplet",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrSchemaTemplet(String solr_schema_templet_id,HttpServletRequest request){
		int i = 0;
		SolrSchemaTemplet solr_Schema_Templet = solrSchemaTempletService.getSolrSchemaTempletById(solr_schema_templet_id);
		if(null != solr_Schema_Templet && !"".equals(solr_Schema_Templet)){
			solr_Schema_Templet.setSolr_schema_templet_id(UUID.toUUID());
			i=solrSchemaTempletService.addSolrSchemaTemplet(solr_Schema_Templet);
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
	@RequestMapping(value="/exportSolrSchemaTemplet",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrSchemaTemplet(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 集合
	* @param solr_schema_templet 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrSchemaTempletList",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrSchemaTempletList(SolrSchemaTemplet solr_Schema_Templet,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("solr_schema_templet_status", 0);
		List<SolrSchemaTemplet> solr_Schema_TempletList = solrSchemaTempletService.getSolrSchemaTempletListByCondition(condition);
		return outComboDataStr(solr_Schema_TempletList);
	}
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toSolrSchemaTempletAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toSolrSchemaTempletAdd(SolrSchemaTemplet solrSchemaTemplet,HttpServletRequest request){
		return new ModelAndView("pc/solr-view/solr-schema-templet/solr-schema-templet-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toSolrSchemaTempletUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toSolrSchemaTempletUpdate(String solr_schema_templet_id,HttpServletRequest request, Model model){
		SolrSchemaTemplet solrSchemaTemplet = solrSchemaTempletService.getSolrSchemaTempletById(solr_schema_templet_id);
		model.addAttribute("solrSchemaTemplet", solrSchemaTemplet);
		return new ModelAndView("pc/solr-view/solr-schema-templet/solr-schema-templet-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toSolrSchemaTempletDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toSolrSchemaTempletDetail(String solr_schema_templet_id,HttpServletRequest request, Model model){
		SolrSchemaTemplet solrSchemaTemplet = solrSchemaTempletService.getSolrSchemaTempletById(solr_schema_templet_id);
		model.addAttribute("solrSchemaTemplet", solrSchemaTemplet);
		return new ModelAndView("pc/solr-view/solr-schema-templet/solr-schema-templet-detail");
	}
}

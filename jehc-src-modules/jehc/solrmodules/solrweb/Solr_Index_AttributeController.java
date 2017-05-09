package jehc.solrmodules.solrweb;
import java.util.List;
import java.util.HashMap;
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

import jehc.solrmodules.solrmodel.Solr_Index_Attribute;
import jehc.solrmodules.solrservice.Solr_Index_AttributeService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 索引更多属性 
* 2016-07-10 22:19:49  邓纯杰
*/
@Controller
@RequestMapping("/solrIndexAttributeController")
public class Solr_Index_AttributeController extends BaseAction{
	@Autowired
	private Solr_Index_AttributeService solr_Index_AttributeService;
	/**
	* 载入初始化页面
	* @param solr_index_attribute 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrIndexAttribute",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrIndexAttribute(Solr_Index_Attribute solr_Index_Attribute,HttpServletRequest request,Model model){
		String solr_index_id = request.getParameter("solr_index_id");
		model.addAttribute("solr_index_id", solr_index_id);
		return new ModelAndView("pc/solr-view/solr-index-attribute/solr-index-attribute-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_index_attribute 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrIndexAttributeListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexAttributeListByCondition(Solr_Index_Attribute solr_Index_Attribute,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		List<Solr_Index_Attribute> solr_Index_AttributeList = solr_Index_AttributeService.getSolrIndexAttributeListByCondition(condition);
		PageInfo<Solr_Index_Attribute> page = new PageInfo<Solr_Index_Attribute>(solr_Index_AttributeList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param solr_index_attribute_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrIndexAttributeById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexAttributeById(String solr_index_attribute_id,HttpServletRequest request){
		Solr_Index_Attribute solr_Index_Attribute = solr_Index_AttributeService.getSolrIndexAttributeById(solr_index_attribute_id);
		return outDataStr(solr_Index_Attribute);
	}
	/**
	* 添加
	* @param solr_index_attribute 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrIndexAttribute",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrIndexAttribute(Solr_Index_Attribute solr_Index_Attribute,HttpServletRequest request){
		int i = 0;
		if(null != solr_Index_Attribute && !"".equals(solr_Index_Attribute)){
			solr_Index_Attribute.setSolr_index_attribute_id(UUID.toUUID());
			i=solr_Index_AttributeService.addSolrIndexAttribute(solr_Index_Attribute);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_index_attribute 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrIndexAttribute",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrIndexAttribute(Solr_Index_Attribute solr_Index_Attribute,HttpServletRequest request){
		int i = 0;
		if(null != solr_Index_Attribute && !"".equals(solr_Index_Attribute)){
			i=solr_Index_AttributeService.updateSolrIndexAttribute(solr_Index_Attribute);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_index_attribute_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrIndexAttribute",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrIndexAttribute(String solr_index_attribute_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_index_attribute_id && !"".equals(solr_index_attribute_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_index_attribute_id",solr_index_attribute_id.split(","));
			i=solr_Index_AttributeService.delSolrIndexAttribute(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_index_attribute_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrIndexAttribute",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrIndexAttribute(String solr_index_attribute_id,HttpServletRequest request){
		int i = 0;
		Solr_Index_Attribute solr_Index_Attribute = solr_Index_AttributeService.getSolrIndexAttributeById(solr_index_attribute_id);
		if(null != solr_Index_Attribute && !"".equals(solr_Index_Attribute)){
			solr_Index_Attribute.setSolr_index_attribute_id(UUID.toUUID());
			i=solr_Index_AttributeService.addSolrIndexAttribute(solr_Index_Attribute);
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
	@RequestMapping(value="/exportSolrIndexAttribute",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrIndexAttribute(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

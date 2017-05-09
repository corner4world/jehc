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

import jehc.solrmodules.solrmodel.Solr_Filed_Copy;
import jehc.solrmodules.solrservice.Solr_Filed_CopyService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* Solr字段拷贝配置 
* 2016-11-15 12:49:27  邓纯杰
*/
@Controller
@RequestMapping("/solrFiledCopyController")
public class Solr_Filed_CopyController extends BaseAction{
	@Autowired
	private Solr_Filed_CopyService solr_Filed_CopyService;
	/**
	* 载入初始化页面
	* @param solr_filed_copy 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrFiledCopy",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrFiledCopy(Solr_Filed_Copy solr_Filed_Copy,HttpServletRequest request){
		request.setAttribute("solr_core_id", solr_Filed_Copy.getSolr_core_id());
		return new ModelAndView("pc/solr-view/solr-filed-copy/solr-filed-copy-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_filed_copy 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrFiledCopyListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrFiledCopyListByCondition(Solr_Filed_Copy solr_Filed_Copy,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("solr_core_id", solr_Filed_Copy.getSolr_core_id());
		condition.put("solr_filed_copy_source_id", solr_Filed_Copy.getSolr_filed_copy_source_id());
		condition.put("solr_filed_copy_dest_id", solr_Filed_Copy.getSolr_filed_copy_dest_id());
		condition.put("solr_filed_copy_remark", solr_Filed_Copy.getSolr_filed_copy_remark());
		commonHPager(condition,request);
		List<Solr_Filed_Copy> solr_Filed_CopyList = solr_Filed_CopyService.getSolrFiledCopyListByCondition(condition);
		PageInfo<Solr_Filed_Copy> page = new PageInfo<Solr_Filed_Copy>(solr_Filed_CopyList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param solr_filed_copy_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrFiledCopyById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrFiledCopyById(String solr_filed_copy_id,HttpServletRequest request){
		Solr_Filed_Copy solr_Filed_Copy = solr_Filed_CopyService.getSolrFiledCopyById(solr_filed_copy_id);
		return outDataStr(solr_Filed_Copy);
	}
	/**
	* 添加
	* @param solr_filed_copy 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrFiledCopy",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrFiledCopy(Solr_Filed_Copy solr_Filed_Copy,HttpServletRequest request){
		int i = 0;
		if(null != solr_Filed_Copy && !"".equals(solr_Filed_Copy)){
			solr_Filed_Copy.setSolr_filed_copy_id(UUID.toUUID());
			solr_Filed_Copy.setXt_userinfo_id(getXtUid());
			solr_Filed_Copy.setSolr_filed_copy_ctime(getSimpleDateFormat());
			i=solr_Filed_CopyService.addSolrFiledCopy(solr_Filed_Copy);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_filed_copy 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrFiledCopy",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrFiledCopy(Solr_Filed_Copy solr_Filed_Copy,HttpServletRequest request){
		int i = 0;
		if(null != solr_Filed_Copy && !"".equals(solr_Filed_Copy)){
			solr_Filed_Copy.setXt_userinfo_id(getXtUid());
			solr_Filed_Copy.setSolr_filed_copy_mtime(getSimpleDateFormat());
			i=solr_Filed_CopyService.updateSolrFiledCopy(solr_Filed_Copy);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_filed_copy_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrFiledCopy",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrFiledCopy(String solr_filed_copy_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_filed_copy_id && !"".equals(solr_filed_copy_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_filed_copy_id",solr_filed_copy_id.split(","));
			i=solr_Filed_CopyService.delSolrFiledCopy(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_filed_copy_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrFiledCopy",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrFiledCopy(String solr_filed_copy_id,HttpServletRequest request){
		int i = 0;
		Solr_Filed_Copy solr_Filed_Copy = solr_Filed_CopyService.getSolrFiledCopyById(solr_filed_copy_id);
		if(null != solr_Filed_Copy && !"".equals(solr_Filed_Copy)){
			solr_Filed_Copy.setSolr_filed_copy_id(UUID.toUUID());
			i=solr_Filed_CopyService.addSolrFiledCopy(solr_Filed_Copy);
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
	@RequestMapping(value="/exportSolrFiledCopy",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrFiledCopy(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

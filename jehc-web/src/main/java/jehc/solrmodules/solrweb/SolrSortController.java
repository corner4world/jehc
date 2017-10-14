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

import jehc.solrmodules.solrmodel.SolrSort;
import jehc.solrmodules.solrservice.SolrSortService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* solr排序 
* 2016-07-08 23:49:58  邓纯杰
*/
@Controller
@RequestMapping("/solrSortController")
public class SolrSortController extends BaseAction{
	@Autowired
	private SolrSortService solrSortService;
	/**
	* 载入初始化页面
	* @param solr_sort 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrSort",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrSort(SolrSort solr_Sort,HttpServletRequest request,Model model){
		String solr_index_id = request.getParameter("solr_index_id");
		model.addAttribute("solr_index_id", solr_index_id);
		return new ModelAndView("pc/solr-view/solr-sort/solr-sort-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_sort 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrSortListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrSortListByCondition(SolrSort solr_Sort,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		condition.put("solr_index_id", solr_Sort.getSolr_index_id());
		condition.put("solr_sort_name",request.getParameter("solr_sort_name"));
		List<SolrSort> solr_SortList = solrSortService.getSolrSortListByCondition(condition);
		PageInfo<SolrSort> page = new PageInfo<SolrSort>(solr_SortList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param solr_sort_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrSortById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrSortById(String solr_sort_id,HttpServletRequest request){
		SolrSort solr_Sort = solrSortService.getSolrSortById(solr_sort_id);
		return outDataStr(solr_Sort);
	}
	/**
	* 添加
	* @param solr_sort 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrSort",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrSort(SolrSort solr_Sort,HttpServletRequest request){
		int i = 0;
		if(null != solr_Sort && !"".equals(solr_Sort)){
			solr_Sort.setSolr_sort_id(UUID.toUUID());
			solr_Sort.setSolr_sort_ctime(getSimpleDateFormat());
			solr_Sort.setXt_userinfo_id(getXtUid());
			i=solrSortService.addSolrSort(solr_Sort);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_sort 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrSort",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrSort(SolrSort solr_Sort,HttpServletRequest request){
		int i = 0;
		if(null != solr_Sort && !"".equals(solr_Sort)){
			solr_Sort.setSolr_sort_mtime(getSimpleDateFormat());
			solr_Sort.setXt_userinfo_id(getXtUid());
			i=solrSortService.updateSolrSort(solr_Sort);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_sort_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrSort",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrSort(String solr_sort_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_sort_id && !"".equals(solr_sort_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_sort_id",solr_sort_id.split(","));
			i=solrSortService.delSolrSort(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_sort_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrSort",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrSort(String solr_sort_id,HttpServletRequest request){
		int i = 0;
		SolrSort solr_Sort = solrSortService.getSolrSortById(solr_sort_id);
		if(null != solr_Sort && !"".equals(solr_Sort)){
			solr_Sort.setSolr_sort_id(UUID.toUUID());
			i=solrSortService.addSolrSort(solr_Sort);
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
	@RequestMapping(value="/exportSolrSort",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrSort(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

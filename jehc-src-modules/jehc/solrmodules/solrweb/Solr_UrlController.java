package jehc.solrmodules.solrweb;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import jehc.solrmodules.solrmodel.Solr_Url;
import jehc.solrmodules.solrservice.Solr_CoreService;
import jehc.solrmodules.solrservice.Solr_UrlService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* solr多实例URL配置 
* 2015-12-15 11:24:06  邓纯杰
*/
@Controller
@RequestMapping("/solrUrlController")
@Scope("prototype")
public class Solr_UrlController extends BaseAction{
	@Autowired
	private Solr_UrlService solr_UrlService;
	@Autowired
	private Solr_CoreService solr_CoreService;
	/**
	* 载入初始化页面
	* @param solr_url 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrUrl",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrUrl(Solr_Url solr_Url,HttpServletRequest request){
		return new ModelAndView("pc/solr-view/solr-url/solr-url-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_url 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrUrlListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrUrlListByCondition(Solr_Url solr_Url,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		List<Solr_Url> solr_UrlList = solr_UrlService.getSolrUrlListByCondition(condition); 
		PageInfo<Solr_Url> page = new PageInfo<Solr_Url>(solr_UrlList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param solr_url_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrUrlById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrUrlById(String solr_url_id,HttpServletRequest request){
		Solr_Url solr_Url = solr_UrlService.getSolrUrlById(solr_url_id);
		return outDataStr(solr_Url);
	}
	/**
	* 添加
	* @param solr_url 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrUrl",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrUrl(Solr_Url solr_Url,HttpServletRequest request){
		int i = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null != solr_Url && !"".equals(solr_Url)){
			solr_Url.setSolr_url_id(UUID.toUUID());
			solr_Url.setSolr_url_ctime(sdf.format(new Date()));
			solr_Url.setXt_userinfo_id(CommonUtils.getXtUid());
			i=solr_UrlService.addSolrUrl(solr_Url);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_url 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrUrl",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrUrl(Solr_Url solr_Url,HttpServletRequest request){
		int i = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		if(null != solr_Url && !"".equals(solr_Url)){
			solr_Url.setSolr_url_mtime(sdf.format(new Date()));
			solr_Url.setXt_userinfo_id(CommonUtils.getXtUid());
			i=solr_UrlService.updateSolrUrl(solr_Url);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_url_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrUrl",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrUrl(String solr_url_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_url_id && !"".equals(solr_url_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			String[] solr_url_idList = solr_url_id.split(",");
			condition.put("solr_url_id",solr_url_idList);
			//验证其下面是否存在多实例
			for(String solr_url_ids:solr_url_idList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("solr_url_id", solr_url_ids);
				if(!solr_CoreService.getSolrCoreListByCondition(map).isEmpty()){
					return outAudStr(false, "该URL下存在多实例，不能删除！");
				}
			}
			i=solr_UrlService.delSolrUrl(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_url_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrUrl",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrUrl(String solr_url_id,HttpServletRequest request){
		int i = 0;
		Solr_Url solr_Url = solr_UrlService.getSolrUrlById(solr_url_id);
		if(null != solr_Url && !"".equals(solr_Url)){
			solr_Url.setSolr_url_id(UUID.toUUID());
			i=solr_UrlService.addSolrUrl(solr_Url);
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
	@RequestMapping(value="/exportSolrUrl",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrUrl(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

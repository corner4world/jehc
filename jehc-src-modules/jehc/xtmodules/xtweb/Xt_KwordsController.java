package jehc.xtmodules.xtweb;
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

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.Xt_Kwords;
import jehc.xtmodules.xtservice.Xt_KwordsService;

/**
* 关键词（敏感词） 
* 2016-10-08 15:03:41  邓纯杰
*/
@Controller
@RequestMapping("/xtKwordsController")
public class Xt_KwordsController extends BaseAction{
	@Autowired
	private Xt_KwordsService xt_KwordsService;
	/**
	* 载入初始化页面
	* @param xt_kwords 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtKwords",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtKwords(Xt_Kwords xt_Kwords,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-kwords/xt-kwords-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_kwords 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtKwordsListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtKwordsListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Kwords> xt_KwordsList = xt_KwordsService.getXtKwordsListByCondition(condition);
		PageInfo<Xt_Kwords> page = new PageInfo<Xt_Kwords>(xt_KwordsList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_kwords_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtKwordsById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtKwordsById(String xt_kwords_id,HttpServletRequest request){
		Xt_Kwords xt_Kwords = xt_KwordsService.getXtKwordsById(xt_kwords_id);
		return outDataStr(xt_Kwords);
	}
	/**
	* 添加
	* @param xt_kwords 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtKwords",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtKwords(Xt_Kwords xt_Kwords,HttpServletRequest request){
		int i = 0;
		if(null != xt_Kwords && !"".equals(xt_Kwords)){
			xt_Kwords.setXt_kwords_id(UUID.toUUID());
			xt_Kwords.setXt_kwords_ctime(getSimpleDateFormat());
			xt_Kwords.setXt_userinfo_id(getXtUid());
			i=xt_KwordsService.addXtKwords(xt_Kwords);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_kwords 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtKwords",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtKwords(Xt_Kwords xt_Kwords,HttpServletRequest request){
		int i = 0;
		if(null != xt_Kwords && !"".equals(xt_Kwords)){
			xt_Kwords.setXt_kwords_mtime(getSimpleDateFormat());
			xt_Kwords.setXt_userinfo_id(getXtUid());
			i=xt_KwordsService.updateXtKwords(xt_Kwords);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_kwords_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtKwords",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtKwords(String xt_kwords_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_kwords_id && !"".equals(xt_kwords_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_kwords_id",xt_kwords_id.split(","));
			i=xt_KwordsService.delXtKwords(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_kwords_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtKwords",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtKwords(String xt_kwords_id,HttpServletRequest request){
		int i = 0;
		Xt_Kwords xt_Kwords = xt_KwordsService.getXtKwordsById(xt_kwords_id);
		if(null != xt_Kwords && !"".equals(xt_Kwords)){
			xt_Kwords.setXt_kwords_id(UUID.toUUID());
			i=xt_KwordsService.addXtKwords(xt_Kwords);
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
	@RequestMapping(value="/exportXtKwords",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtKwords(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

package jehc.oamodules.oaweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
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
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.oamodules.oamodel.OaMplanClassify;
import jehc.oamodules.oaservice.OaMplanClassifyService;

/**
* 个人计划分类 
* 2018-07-09 20:18:35  邓纯杰
*/
@Controller
@RequestMapping("/oaMplanClassifyController")
public class OaMplanClassifyController extends BaseAction{
	@Autowired
	private OaMplanClassifyService oaMplanClassifyService;
	/**
	* 载入初始化页面
	* @param oa_mplan_classify 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadOaMplanClassify",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadOaMplanClassify(OaMplanClassify oaMplanClassify,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-mplan-classify/oa-mplan-classify-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param oa_mplan_classify 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaMplanClassifyListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaMplanClassifyListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<OaMplanClassify> oaMplanClassifyList = oaMplanClassifyService.getOaMplanClassifyListByCondition(condition);
		PageInfo<OaMplanClassify> page = new PageInfo<OaMplanClassify>(oaMplanClassifyList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param oa_mplan_classify_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaMplanClassifyById",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaMplanClassifyById(String oa_mplan_classify_id,HttpServletRequest request){
		OaMplanClassify oaMplanClassify = oaMplanClassifyService.getOaMplanClassifyById(oa_mplan_classify_id);
		return outDataStr(oaMplanClassify);
	}
	/**
	* 添加
	* @param oa_mplan_classify 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addOaMplanClassify",method={RequestMethod.POST,RequestMethod.GET})
	public String addOaMplanClassify(OaMplanClassify oaMplanClassify,HttpServletRequest request){
		int i = 0;
		if(null != oaMplanClassify && !"".equals(oaMplanClassify)){
			oaMplanClassify.setCtime(getDate());
			oaMplanClassify.setXt_userinfo_id(getXtUid());
			oaMplanClassify.setOa_mplan_classify_id(UUID.toUUID());
			i=oaMplanClassifyService.addOaMplanClassify(oaMplanClassify);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param oa_mplan_classify 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateOaMplanClassify",method={RequestMethod.POST,RequestMethod.GET})
	public String updateOaMplanClassify(OaMplanClassify oaMplanClassify,HttpServletRequest request){
		int i = 0;
		if(null != oaMplanClassify && !"".equals(oaMplanClassify)){
			oaMplanClassify.setMtime(getDate());
			i=oaMplanClassifyService.updateOaMplanClassify(oaMplanClassify);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param oa_mplan_classify_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delOaMplanClassify",method={RequestMethod.POST,RequestMethod.GET})
	public String delOaMplanClassify(String oa_mplan_classify_id,HttpServletRequest request){
		int i = 0;
		if(null != oa_mplan_classify_id && !"".equals(oa_mplan_classify_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("oa_mplan_classify_id",oa_mplan_classify_id.split(","));
			i=oaMplanClassifyService.delOaMplanClassify(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param oa_mplan_classify_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyOaMplanClassify",method={RequestMethod.POST,RequestMethod.GET})
	public String copyOaMplanClassify(String oa_mplan_classify_id,HttpServletRequest request){
		int i = 0;
		OaMplanClassify oaMplanClassify = oaMplanClassifyService.getOaMplanClassifyById(oa_mplan_classify_id);
		if(null != oaMplanClassify && !"".equals(oaMplanClassify)){
			oaMplanClassify.setOa_mplan_classify_id(UUID.toUUID());
			i=oaMplanClassifyService.addOaMplanClassify(oaMplanClassify);
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
	@RequestMapping(value="/exportOaMplanClassify",method={RequestMethod.POST,RequestMethod.GET})
	public void exportOaMplanClassify(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toOaMplanClassifyAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaMplanClassifyAdd(OaMplanClassify oaMplanClassify,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-mplan-classify/oa-mplan-classify-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toOaMplanClassifyUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaMplanClassifyUpdate(String oa_mplan_classify_id,HttpServletRequest request, Model model){
		OaMplanClassify oaMplanClassify = oaMplanClassifyService.getOaMplanClassifyById(oa_mplan_classify_id);
		model.addAttribute("oaMplanClassify", oaMplanClassify);
		return new ModelAndView("pc/oa-view/oa-mplan-classify/oa-mplan-classify-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toOaMplanClassifyDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaMplanClassifyDetail(String oa_mplan_classify_id,HttpServletRequest request, Model model){
		OaMplanClassify oaMplanClassify = oaMplanClassifyService.getOaMplanClassifyById(oa_mplan_classify_id);
		model.addAttribute("oaMplanClassify", oaMplanClassify);
		return new ModelAndView("pc/oa-view/oa-mplan-classify/oa-mplan-classify-detail");
	}
}

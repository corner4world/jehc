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
import jehc.xtmodules.xtmodel.XtGeneratorForbidtable;
import jehc.xtmodules.xtservice.XtGeneratorForbidtableService;

/**
* 禁止使用代码生成器生成的表信息 
* 2016-09-26 10:55:48  邓纯杰
*/
@Controller
@RequestMapping("/xtGeneratorForbidtableController")
public class XtGeneratorForbidtableController extends BaseAction{
	@Autowired
	private XtGeneratorForbidtableService xtGeneratorForbidtableService;
	/**
	* 载入初始化页面
	* @param xt_generator_forbidtable 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtGeneratorForbidtable",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtGeneratorForbidtable(XtGeneratorForbidtable xt_Generator_Forbidtable,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-generator-forbidtable/xt-generator-forbidtable-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_generator_forbidtable 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtGeneratorForbidtableListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtGeneratorForbidtableListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtGeneratorForbidtable> xt_Generator_ForbidtableList = xtGeneratorForbidtableService.getXtGeneratorForbidtableListByCondition(condition);
		PageInfo<XtGeneratorForbidtable> page = new PageInfo<XtGeneratorForbidtable>(xt_Generator_ForbidtableList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_generator_forbidtable_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtGeneratorForbidtableById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtGeneratorForbidtableById(String xt_generator_forbidtable_id,HttpServletRequest request){
		XtGeneratorForbidtable xt_Generator_Forbidtable = xtGeneratorForbidtableService.getXtGeneratorForbidtableById(xt_generator_forbidtable_id);
		return outDataStr(xt_Generator_Forbidtable);
	}
	/**
	* 添加
	* @param xt_generator_forbidtable 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtGeneratorForbidtable",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtGeneratorForbidtable(XtGeneratorForbidtable xt_Generator_Forbidtable,HttpServletRequest request){
		int i = 0;
		if(null != xt_Generator_Forbidtable && !"".equals(xt_Generator_Forbidtable)){
			xt_Generator_Forbidtable.setXt_generator_forbidtable_id(UUID.toUUID());
			xt_Generator_Forbidtable.setXt_generator_forbidtable_ctime(getSimpleDateFormat());
			xt_Generator_Forbidtable.setXt_userinfo_id(getXtUid());
			i=xtGeneratorForbidtableService.addXtGeneratorForbidtable(xt_Generator_Forbidtable);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_generator_forbidtable 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtGeneratorForbidtable",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtGeneratorForbidtable(XtGeneratorForbidtable xt_Generator_Forbidtable,HttpServletRequest request){
		int i = 0;
		if(null != xt_Generator_Forbidtable && !"".equals(xt_Generator_Forbidtable)){
			xt_Generator_Forbidtable.setXt_generator_forbidtable_mtime(getSimpleDateFormat());
			xt_Generator_Forbidtable.setXt_userinfo_id(getXtUid());
			i=xtGeneratorForbidtableService.updateXtGeneratorForbidtable(xt_Generator_Forbidtable);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_generator_forbidtable_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtGeneratorForbidtable",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtGeneratorForbidtable(String xt_generator_forbidtable_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_generator_forbidtable_id && !"".equals(xt_generator_forbidtable_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_generator_forbidtable_id",xt_generator_forbidtable_id.split(","));
			i=xtGeneratorForbidtableService.delXtGeneratorForbidtable(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_generator_forbidtable_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtGeneratorForbidtable",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtGeneratorForbidtable(String xt_generator_forbidtable_id,HttpServletRequest request){
		int i = 0;
		XtGeneratorForbidtable xt_Generator_Forbidtable = xtGeneratorForbidtableService.getXtGeneratorForbidtableById(xt_generator_forbidtable_id);
		if(null != xt_Generator_Forbidtable && !"".equals(xt_Generator_Forbidtable)){
			xt_Generator_Forbidtable.setXt_generator_forbidtable_id(UUID.toUUID());
			i=xtGeneratorForbidtableService.addXtGeneratorForbidtable(xt_Generator_Forbidtable);
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
	@RequestMapping(value="/exportXtGeneratorForbidtable",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtGeneratorForbidtable(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

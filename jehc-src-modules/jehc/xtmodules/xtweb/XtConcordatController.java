package jehc.xtmodules.xtweb;
import java.util.HashMap;
import java.util.List;
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
import jehc.xtmodules.xtmodel.XtConcordat;
import jehc.xtmodules.xtservice.XtConcordatService;

/**
* 合同管理 
* 2015-05-24 08:39:49  邓纯杰
*/
@Controller
@RequestMapping("/xtConcordatController")
public class XtConcordatController extends BaseAction{
	@Autowired
	private XtConcordatService xtConcordatService;
	/**
	* 载入初始化页面
	* @param xt_concordat 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtConcordat",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtConcordat(XtConcordat xt_Concordat,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-concordat/xt-concordat-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_concordat 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtConcordatListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtConcordatListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtConcordat>XtConcordatList = xtConcordatService.getXtConcordatListByCondition(condition);
		PageInfo<XtConcordat> page = new PageInfo<XtConcordat>(XtConcordatList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_concordat_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtConcordatById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtConcordatById(String xt_concordat_id,HttpServletRequest request){
		XtConcordat xt_Concordat = xtConcordatService.getXtConcordatById(xt_concordat_id);
		return outDataStr(xt_Concordat);
	}
	/**
	* 添加
	* @param xt_concordat 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtConcordat",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtConcordat(XtConcordat xt_Concordat,HttpServletRequest request){
		int i = 0;
		if(null != xt_Concordat && !"".equals(xt_Concordat)){
			xt_Concordat.setXt_concordat_id(UUID.toUUID());
			i=xtConcordatService.addXtConcordat(xt_Concordat);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_concordat 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtConcordat",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtConcordat(XtConcordat xt_Concordat,HttpServletRequest request){
		int i = 0;
		if(null != xt_Concordat && !"".equals(xt_Concordat)){
			i=xtConcordatService.updateXtConcordat(xt_Concordat);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_concordat_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtConcordat",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtConcordat(String xt_concordat_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_concordat_id && !"".equals(xt_concordat_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_concordat_id",xt_concordat_id.split(","));
			i=xtConcordatService.delXtConcordat(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_concordat_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtConcordat",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtConcordat(String xt_concordat_id,HttpServletRequest request){
		int i = 0;
		XtConcordat xt_Concordat = xtConcordatService.getXtConcordatById(xt_concordat_id);
		if(null != xt_Concordat && !"".equals(xt_Concordat)){
			xt_Concordat.setXt_concordat_id(UUID.toUUID());
			i=xtConcordatService.addXtConcordat(xt_Concordat);
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
	@RequestMapping(value="/exportXtConcordat",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtConcordat(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

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
import jehc.xtmodules.xtmodel.Xt_Constant;
import jehc.xtmodules.xtservice.Xt_ConstantService;

/**
* 台平常量; InnoDB free: 4096 kB 
* 2015-09-30 14:36:13  邓纯杰
*/
@Controller
@RequestMapping("/xtConstantController")
public class Xt_ConstantController extends BaseAction{
	@Autowired
	private Xt_ConstantService xt_ConstantService;
	/**
	* 载入初始化页面
	* @param xt_constant 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtConstant",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtConstant(Xt_Constant xt_Constant,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-constant/xt-constant-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_constant 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtConstantListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtConstantListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Constant> xt_ConstantList = xt_ConstantService.getXtConstantListByCondition(condition);
		PageInfo<Xt_Constant> page = new PageInfo<Xt_Constant>(xt_ConstantList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_constant_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtConstantById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtConstantById(String xt_constant_id,HttpServletRequest request){
		Xt_Constant xt_Constant = xt_ConstantService.getXtConstantById(xt_constant_id);
		return outDataStr(xt_Constant);
	}
	/**
	* 添加
	* @param xt_constant 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtConstant",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtConstant(Xt_Constant xt_Constant,HttpServletRequest request){
		int i = 0;
		if(null != xt_Constant && !"".equals(xt_Constant)){
			xt_Constant.setXt_constant_id(UUID.toUUID());
			i=xt_ConstantService.addXtConstant(xt_Constant);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_constant 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtConstant",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtConstant(Xt_Constant xt_Constant,HttpServletRequest request){
		int i = 0;
		if(null != xt_Constant && !"".equals(xt_Constant)){
			i=xt_ConstantService.updateXtConstant(xt_Constant);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_constant_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtConstant",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtConstant(String xt_constant_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_constant_id && !"".equals(xt_constant_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_constant_id",xt_constant_id.split(","));
			i=xt_ConstantService.delXtConstant(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_constant_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtConstant",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtConstant(String xt_constant_id,HttpServletRequest request){
		int i = 0;
		Xt_Constant xt_Constant = xt_ConstantService.getXtConstantById(xt_constant_id);
		if(null != xt_Constant && !"".equals(xt_Constant)){
			xt_Constant.setXt_constant_id(UUID.toUUID());
			i=xt_ConstantService.addXtConstant(xt_Constant);
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
	@RequestMapping(value="/exportXtConstant",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtConstant(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 根据类型查找常量集合
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtConstantList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtConstantList(String xt_constantType){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("xt_constantType", xt_constantType);
		List<Xt_Constant> xt_ConstantList = xt_ConstantService.getXtConstantListByCondition(condition);
		return outItemsStr(xt_ConstantList);
	}
}

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
import jehc.xtmodules.xtmodel.Xt_Unit;
import jehc.xtmodules.xtservice.Xt_UnitService;

/**
* 商品(产品)单位; InnoDB free: 4096 kB 
* 2015-09-30 14:16:39  邓纯杰
*/
@Controller
@RequestMapping("/xtUnitController")
public class Xt_UnitController extends BaseAction{
	@Autowired
	private Xt_UnitService xt_UnitService;
	/**
	* 载入初始化页面
	* @param xt_unit 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtUnit",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtUnit(Xt_Unit xt_Unit,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-unit/xt-unit-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_unit 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtUnitListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUnitListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Unit> xt_UnitList = xt_UnitService.getXtUnitListByCondition(condition);
		PageInfo<Xt_Unit> page = new PageInfo<Xt_Unit>(xt_UnitList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_unit_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtUnitById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUnitById(String xt_unit_id,HttpServletRequest request){
		Xt_Unit xt_Unit = xt_UnitService.getXtUnitById(xt_unit_id);
		return outDataStr(xt_Unit);
	}
	/**
	* 添加
	* @param xt_unit 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtUnit",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtUnit(Xt_Unit xt_Unit,HttpServletRequest request){
		int i = 0;
		if(null != xt_Unit && !"".equals(xt_Unit)){
			xt_Unit.setXt_unit_id(UUID.toUUID());
			i=xt_UnitService.addXtUnit(xt_Unit);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_unit 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtUnit",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtUnit(Xt_Unit xt_Unit,HttpServletRequest request){
		int i = 0;
		if(null != xt_Unit && !"".equals(xt_Unit)){
			i=xt_UnitService.updateXtUnit(xt_Unit);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_unit_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtUnit",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtUnit(String xt_unit_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_unit_id && !"".equals(xt_unit_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_unit_id",xt_unit_id.split(","));
			i=xt_UnitService.delXtUnit(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_unit_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtUnit",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtUnit(String xt_unit_id,HttpServletRequest request){
		int i = 0;
		Xt_Unit xt_Unit = xt_UnitService.getXtUnitById(xt_unit_id);
		if(null != xt_Unit && !"".equals(xt_Unit)){
			xt_Unit.setXt_unit_id(UUID.toUUID());
			i=xt_UnitService.addXtUnit(xt_Unit);
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
	@RequestMapping(value="/exportXtUnit",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtUnit(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

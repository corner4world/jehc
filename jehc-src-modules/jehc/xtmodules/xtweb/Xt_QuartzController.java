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
import jehc.xtmodules.xtmodel.Xt_Quartz;
import jehc.xtmodules.xtservice.Xt_QuartzService;

/**
* 任务调度配置信息表 
* 2015-10-29 16:50:03  邓纯杰
*/
@Controller
@RequestMapping("/xtQuartzController")
public class Xt_QuartzController extends BaseAction{
	@Autowired
	private Xt_QuartzService xt_QuartzService;
	/**
	* 载入初始化页面
	* @param xt_quartz 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtQuartz",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtQuartz(Xt_Quartz xt_Quartz,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-quartz/xt-quartz-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_quartz 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtQuartzListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtQuartzListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Quartz> xt_QuartzList = xt_QuartzService.getXtQuartzListByCondition(condition);
		PageInfo<Xt_Quartz> page = new PageInfo<Xt_Quartz>(xt_QuartzList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtQuartzById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtQuartzById(String id,HttpServletRequest request){
		Xt_Quartz xt_Quartz = xt_QuartzService.getXtQuartzById(id);
		return outDataStr(xt_Quartz);
	}
	/**
	* 添加
	* @param xt_quartz 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtQuartz",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtQuartz(Xt_Quartz xt_Quartz,HttpServletRequest request){
		int i = 0;
		if(null != xt_Quartz && !"".equals(xt_Quartz)){
			xt_Quartz.setId(UUID.toUUID());
			i=xt_QuartzService.addXtQuartz(xt_Quartz);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_quartz 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtQuartz",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtQuartz(Xt_Quartz xt_Quartz,HttpServletRequest request){
		int i = 0;
		if(null != xt_Quartz && !"".equals(xt_Quartz)){
			i=xt_QuartzService.updateXtQuartz(xt_Quartz);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtQuartz",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtQuartz(String id,HttpServletRequest request){
		int i = 0;
		if(null != id && !"".equals(id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id",id.split(","));
			i=xt_QuartzService.delXtQuartz(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtQuartz",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtQuartz(String id,HttpServletRequest request){
		int i = 0;
		Xt_Quartz xt_Quartz = xt_QuartzService.getXtQuartzById(id);
		if(null != xt_Quartz && !"".equals(xt_Quartz)){
			xt_Quartz.setId(UUID.toUUID());
			i=xt_QuartzService.addXtQuartz(xt_Quartz);
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
	@RequestMapping(value="/exportXtQuartz",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtQuartz(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

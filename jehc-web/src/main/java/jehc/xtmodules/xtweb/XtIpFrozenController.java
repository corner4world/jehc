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
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtIpFrozen;
import jehc.xtmodules.xtservice.XtIpFrozenService;

/**
* 平台IP冻结账户 
* 2016-02-29 10:41:23  邓纯杰
*/
@Controller
@RequestMapping("/xtIpFrozenController")
public class XtIpFrozenController extends BaseAction{
	@Autowired
	private XtIpFrozenService xtIpFrozenService;
	/**
	* 载入初始化页面
	* @param xt_ip_frozen 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtIpFrozen",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtIpFrozen(XtIpFrozen xt_Ip_Frozen,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-ip-frozen/xt-ip-frozen-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_ip_frozen 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtIpFrozenListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtIpFrozenListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtIpFrozen> xt_Ip_FrozenList = xtIpFrozenService.getXtIpFrozenListByCondition(condition);
		PageInfo<XtIpFrozen> page = new PageInfo<XtIpFrozen>(xt_Ip_FrozenList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_ip_frozen_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtIpFrozenById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtIpFrozenById(String xt_ip_frozen_id,HttpServletRequest request){
		XtIpFrozen xt_Ip_Frozen = xtIpFrozenService.getXtIpFrozenById(xt_ip_frozen_id);
		return outDataStr(xt_Ip_Frozen);
	}
	/**
	* 添加
	* @param xt_ip_frozen 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtIpFrozen",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtIpFrozen(XtIpFrozen xt_Ip_Frozen,HttpServletRequest request){
		int i = 0;
		if(null != xt_Ip_Frozen && !"".equals(xt_Ip_Frozen)){
			xt_Ip_Frozen.setXt_ip_frozen_id(UUID.toUUID());
			xt_Ip_Frozen.setXt_userinfo_id(CommonUtils.getXtUid());
			xt_Ip_Frozen.setXt_ip_frozen_ctime(CommonUtils.getSimpleDateFormat());
			i=xtIpFrozenService.addXtIpFrozen(xt_Ip_Frozen);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_ip_frozen 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtIpFrozen",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtIpFrozen(XtIpFrozen xt_Ip_Frozen,HttpServletRequest request){
		int i = 0;
		if(null != xt_Ip_Frozen && !"".equals(xt_Ip_Frozen)){
			xt_Ip_Frozen.setXt_userinfo_id(CommonUtils.getXtUid());
			xt_Ip_Frozen.setXt_ip_frozen_mtime(CommonUtils.getSimpleDateFormat());
			i=xtIpFrozenService.updateXtIpFrozen(xt_Ip_Frozen);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_ip_frozen_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtIpFrozen",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtIpFrozen(String xt_ip_frozen_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_ip_frozen_id && !"".equals(xt_ip_frozen_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_ip_frozen_id",xt_ip_frozen_id.split(","));
			i=xtIpFrozenService.delXtIpFrozen(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_ip_frozen_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtIpFrozen",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtIpFrozen(String xt_ip_frozen_id,HttpServletRequest request){
		int i = 0;
		XtIpFrozen xt_Ip_Frozen = xtIpFrozenService.getXtIpFrozenById(xt_ip_frozen_id);
		if(null != xt_Ip_Frozen && !"".equals(xt_Ip_Frozen)){
			xt_Ip_Frozen.setXt_ip_frozen_id(UUID.toUUID());
			i=xtIpFrozenService.addXtIpFrozen(xt_Ip_Frozen);
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
	@RequestMapping(value="/exportXtIpFrozen",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtIpFrozen(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

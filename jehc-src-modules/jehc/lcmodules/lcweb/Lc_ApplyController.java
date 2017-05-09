package jehc.lcmodules.lcweb;
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

import jehc.lcmodules.lcmodel.Lc_Apply;
import jehc.lcmodules.lcservice.Lc_ApplyService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 流程申请 
* 2017-01-08 14:55:11  邓纯杰
*/
@Controller
@RequestMapping("/lcApplyController")
public class Lc_ApplyController extends BaseAction{
	@Autowired
	private Lc_ApplyService lc_ApplyService;
	/**
	* 载入初始化页面
	* @param lc_apply 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadLcApply",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLcApply(Lc_Apply lc_Apply,HttpServletRequest request){
		return new ModelAndView("pc/lc-view/lc-apply/lc-apply-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param lc_apply 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcApplyListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcApplyListByCondition(Lc_Apply lc_Apply,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		condition.put("lc_apply_title",request.getParameter("lc_apply_title"));
		condition.put("xt_userinfo_id",getXtUid());
		List<Lc_Apply> lc_ApplyList = lc_ApplyService.getLcApplyListByCondition(condition);
		PageInfo<Lc_Apply> page = new PageInfo<Lc_Apply>(lc_ApplyList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param lc_apply_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcApplyById",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcApplyById(String lc_apply_id,HttpServletRequest request){
		Lc_Apply lc_Apply = lc_ApplyService.getLcApplyById(lc_apply_id);
		return outDataStr(lc_Apply);
	}
	/**
	* 添加
	* @param lc_apply 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addLcApply",method={RequestMethod.POST,RequestMethod.GET})
	public String addLcApply(Lc_Apply lc_Apply,HttpServletRequest request){
		int i = 0;
		if(null != lc_Apply && !"".equals(lc_Apply)){
			lc_Apply.setLc_apply_id(UUID.toUUID());
			i=lc_ApplyService.addLcApply(lc_Apply);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param lc_apply 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateLcApply",method={RequestMethod.POST,RequestMethod.GET})
	public String updateLcApply(Lc_Apply lc_Apply,HttpServletRequest request){
		int i = 0;
		if(null != lc_Apply && !"".equals(lc_Apply)){
			i=lc_ApplyService.updateLcApply(lc_Apply);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param lc_apply_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delLcApply",method={RequestMethod.POST,RequestMethod.GET})
	public String delLcApply(String lc_apply_id,HttpServletRequest request){
		int i = 0;
		if(null != lc_apply_id && !"".equals(lc_apply_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("lc_apply_id",lc_apply_id.split(","));
			i=lc_ApplyService.delLcApply(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param lc_apply_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyLcApply",method={RequestMethod.POST,RequestMethod.GET})
	public String copyLcApply(String lc_apply_id,HttpServletRequest request){
		int i = 0;
		Lc_Apply lc_Apply = lc_ApplyService.getLcApplyById(lc_apply_id);
		if(null != lc_Apply && !"".equals(lc_Apply)){
			lc_Apply.setLc_apply_id(UUID.toUUID());
			i=lc_ApplyService.addLcApply(lc_Apply);
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
	@RequestMapping(value="/exportLcApply",method={RequestMethod.POST,RequestMethod.GET})
	public void exportLcApply(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

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
import jehc.lcmodules.lcmodel.Lc_Approval;
import jehc.lcmodules.lcservice.Lc_ApplyService;
import jehc.lcmodules.lcservice.Lc_ApprovalService;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 工作流批审表 
* 2017-01-08 17:12:17  邓纯杰
*/
@Controller
@RequestMapping("/lcApprovalController")
public class Lc_ApprovalController extends BaseAction{
	@Autowired
	private Lc_ApprovalService lc_ApprovalService;
	@Autowired
	private Lc_ApplyService lc_ApplyService;
	/**
	* 载入初始化页面
	* @param lc_approval 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadLcApproval",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLcApproval(Lc_Approval lc_Approval,HttpServletRequest request){
		return new ModelAndView("pc/lc-view/lc-approval/lc-approval-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param lc_approval 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcApprovalListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcApprovalListByCondition(Lc_Approval lc_Approval,String instanceId,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(instanceId)){
			condition.put("processInstance_id", instanceId);
			List<Lc_Apply> list = lc_ApplyService.getLcApplyListByCondition(condition);
			if(!list.isEmpty()){
				lc_Approval.setLc_apply_id(list.get(0).getLc_apply_id());
			}
		}else{
			condition = new HashMap<String, Object>();
		}
		commonHPager(condition,request);
		condition.put("lc_status_id",request.getParameter("lc_status_id"));
		condition.put("lc_apply_id", lc_Approval.getLc_apply_id());
		List<Lc_Approval> lc_ApprovalList = lc_ApprovalService.getLcApprovalListByCondition(condition);
		PageInfo<Lc_Approval> page = new PageInfo<Lc_Approval>(lc_ApprovalList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param lc_approval_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcApprovalById",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcApprovalById(String lc_approval_id,HttpServletRequest request){
		Lc_Approval lc_Approval = lc_ApprovalService.getLcApprovalById(lc_approval_id);
		return outDataStr(lc_Approval);
	}
	/**
	* 添加
	* @param lc_approval 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addLcApproval",method={RequestMethod.POST,RequestMethod.GET})
	public String addLcApproval(Lc_Approval lc_Approval,HttpServletRequest request){
		int i = 0;
		if(null != lc_Approval && !"".equals(lc_Approval)){
			lc_Approval.setLc_approval_id(UUID.toUUID());
			i=lc_ApprovalService.addLcApproval(lc_Approval);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param lc_approval 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateLcApproval",method={RequestMethod.POST,RequestMethod.GET})
	public String updateLcApproval(Lc_Approval lc_Approval,HttpServletRequest request){
		int i = 0;
		if(null != lc_Approval && !"".equals(lc_Approval)){
			i=lc_ApprovalService.updateLcApproval(lc_Approval);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param lc_approval_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delLcApproval",method={RequestMethod.POST,RequestMethod.GET})
	public String delLcApproval(String lc_approval_id,HttpServletRequest request){
		int i = 0;
		if(null != lc_approval_id && !"".equals(lc_approval_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("lc_approval_id",lc_approval_id.split(","));
			i=lc_ApprovalService.delLcApproval(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param lc_approval_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyLcApproval",method={RequestMethod.POST,RequestMethod.GET})
	public String copyLcApproval(String lc_approval_id,HttpServletRequest request){
		int i = 0;
		Lc_Approval lc_Approval = lc_ApprovalService.getLcApprovalById(lc_approval_id);
		if(null != lc_Approval && !"".equals(lc_Approval)){
			lc_Approval.setLc_approval_id(UUID.toUUID());
			i=lc_ApprovalService.addLcApproval(lc_Approval);
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
	@RequestMapping(value="/exportLcApproval",method={RequestMethod.POST,RequestMethod.GET})
	public void exportLcApproval(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

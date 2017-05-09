package jehc.lcmodules.lcweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;

import jehc.lcmodules.lcmodel.Lc_Deployment_His;
import jehc.lcmodules.lcservice.Lc_Deployment_HisService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 流程部署历史记录 
* 2016-12-22 13:02:01  邓纯杰
*/
@Controller
@RequestMapping("/lcDeploymentHisController")
public class Lc_Deployment_HisController extends BaseAction{
	@Autowired
	private Lc_Deployment_HisService lc_Deployment_HisService;
	/**
	* 载入初始化页面
	* @param lc_deployment_his 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadLcDeploymentHis",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLcDeploymentHis(Lc_Deployment_His lc_Deployment_His,HttpServletRequest request,Model model){
		model.addAttribute("lc_Deployment_His",lc_Deployment_His);
		return new ModelAndView("pc/lc-view/lc-deployment-his/lc-deployment-his-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param lc_deployment_his 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcDeploymentHisListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcDeploymentHisListByCondition(Lc_Deployment_His lc_Deployment_His,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		condition.put("lc_process_id", lc_Deployment_His.getLc_process_id());
		List<Lc_Deployment_His> lc_Deployment_HisList = lc_Deployment_HisService.getLcDeploymentHisListByCondition(condition);
		PageInfo<Lc_Deployment_His> page = new PageInfo<Lc_Deployment_His>(lc_Deployment_HisList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcDeploymentHisById",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcDeploymentHisById(String id,HttpServletRequest request){
		Lc_Deployment_His lc_Deployment_His = lc_Deployment_HisService.getLcDeploymentHisById(id);
		return outDataStr(lc_Deployment_His);
	}
	/**
	* 添加
	* @param lc_deployment_his 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addLcDeploymentHis",method={RequestMethod.POST,RequestMethod.GET})
	public String addLcDeploymentHis(Lc_Deployment_His lc_Deployment_His,HttpServletRequest request){
		int i = 0;
		if(null != lc_Deployment_His && !"".equals(lc_Deployment_His)){
			lc_Deployment_His.setId(UUID.toUUID());
			i=lc_Deployment_HisService.addLcDeploymentHis(lc_Deployment_His);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param lc_deployment_his 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateLcDeploymentHis",method={RequestMethod.POST,RequestMethod.GET})
	public String updateLcDeploymentHis(Lc_Deployment_His lc_Deployment_His,HttpServletRequest request){
		int i = 0;
		if(null != lc_Deployment_His && !"".equals(lc_Deployment_His)){
			i=lc_Deployment_HisService.updateLcDeploymentHis(lc_Deployment_His);
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
	@RequestMapping(value="/delLcDeploymentHis",method={RequestMethod.POST,RequestMethod.GET})
	public String delLcDeploymentHis(String id,HttpServletRequest request){
		int i = 0;
		if(null != id && !"".equals(id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id",id.split(","));
			i=lc_Deployment_HisService.delLcDeploymentHis(condition);
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
	@RequestMapping(value="/copyLcDeploymentHis",method={RequestMethod.POST,RequestMethod.GET})
	public String copyLcDeploymentHis(String id,HttpServletRequest request){
		int i = 0;
		Lc_Deployment_His lc_Deployment_His = lc_Deployment_HisService.getLcDeploymentHisById(id);
		if(null != lc_Deployment_His && !"".equals(lc_Deployment_His)){
			lc_Deployment_His.setId(UUID.toUUID());
			i=lc_Deployment_HisService.addLcDeploymentHis(lc_Deployment_His);
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
	@RequestMapping(value="/exportLcDeploymentHis",method={RequestMethod.POST,RequestMethod.GET})
	public void exportLcDeploymentHis(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

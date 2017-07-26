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

import jehc.lcmodules.lcmodel.LcStatus;
import jehc.lcmodules.lcservice.LcStatusService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 流程状态 
* 2016-05-04 14:13:34  邓纯杰
*/
@Controller
@RequestMapping("/lcStatusController")
public class LcStatusController extends BaseAction{
	@Autowired
	private LcStatusService lcStatusService;
	/**
	* 载入初始化页面
	* @param lc_status 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadLcStatus",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLcStatus(LcStatus lc_Status,HttpServletRequest request){
		return new ModelAndView("pc/lc-view/lc-status/lc-status-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param lc_status 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcStatusListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcStatusListByCondition(LcStatus lc_Status,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		List<LcStatus> lc_StatusList = lcStatusService.getLcStatusListByCondition(condition);
		PageInfo<LcStatus> page = new PageInfo<LcStatus>(lc_StatusList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param lc_status_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcStatusById",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcStatusById(String lc_status_id,HttpServletRequest request){
		LcStatus lc_Status = lcStatusService.getLcStatusById(lc_status_id);
		return outDataStr(lc_Status);
	}
	/**
	* 添加
	* @param lc_status 
	* @param request 
	*/
	@RequestMapping(value="/addLcStatus",method={RequestMethod.POST,RequestMethod.GET})
	public String addLcStatus(LcStatus lc_Status,HttpServletRequest request){
		int i = 0;
		if(null != lc_Status && !"".equals(lc_Status)){
			lc_Status.setXt_userinfo_id(CommonUtils.getXtUid());
			lc_Status.setLc_status_ctime(CommonUtils.getSimpleDateFormat());
			lc_Status.setLc_status_id(UUID.toUUID());
			i=lcStatusService.addLcStatus(lc_Status);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param lc_status 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateLcStatus",method={RequestMethod.POST,RequestMethod.GET})
	public String updateLcStatus(LcStatus lc_Status,HttpServletRequest request){
		int i = 0;
		if(null != lc_Status && !"".equals(lc_Status)){
			lc_Status.setXt_userinfo_id(getXtUid());
			lc_Status.setLc_status_mtime(CommonUtils.getSimpleDateFormat());
			i=lcStatusService.updateLcStatus(lc_Status);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param lc_status_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delLcStatus",method={RequestMethod.POST,RequestMethod.GET})
	public String delLcStatus(String lc_status_id,HttpServletRequest request){
		int i = 0;
		if(null != lc_status_id && !"".equals(lc_status_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("lc_status_id",lc_status_id.split(","));
			i=lcStatusService.delLcStatus(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param lc_status_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyLcStatus",method={RequestMethod.POST,RequestMethod.GET})
	public String copyLcStatus(String lc_status_id,HttpServletRequest request){
		int i = 0;
		LcStatus lc_Status = lcStatusService.getLcStatusById(lc_status_id);
		if(null != lc_Status && !"".equals(lc_Status)){
			lc_Status.setLc_status_id(UUID.toUUID());
			i=lcStatusService.addLcStatus(lc_Status);
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
	@RequestMapping(value="/exportLcStatus",method={RequestMethod.POST,RequestMethod.GET})
	public void exportLcStatus(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

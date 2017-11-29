package jehc.xtmodules.xtweb;
import java.util.HashMap;
import java.util.List;
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

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtModifyRecord;
import jehc.xtmodules.xtservice.XtModifyRecordService;

/**
* 修改记录日志 
* 2017-04-13 12:50:49  邓纯杰
*/
@Controller
@RequestMapping("/xtModifyRecordController")
public class XtModifyRecordController extends BaseAction{
	@Autowired
	private XtModifyRecordService xtModifyRecordService;
	/**
	* 载入初始化页面
	* @param xt_modify_record 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtModifyRecord",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtModifyRecord(XtModifyRecord xt_Modify_Record,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-modify-record/xt-modify-record-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_modify_record 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtModifyRecordListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtModifyRecordListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtModifyRecord> xt_Modify_RecordList = xtModifyRecordService.getXtModifyRecordListByCondition(condition);
		PageInfo<XtModifyRecord> page = new PageInfo<XtModifyRecord>(xt_Modify_RecordList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_modify_record_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtModifyRecordById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtModifyRecordById(String xt_modify_record_id,HttpServletRequest request){
		XtModifyRecord xt_Modify_Record = xtModifyRecordService.getXtModifyRecordById(xt_modify_record_id);
		return outDataStr(xt_Modify_Record);
	}
	/**
	* 添加
	* @param xt_modify_record 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtModifyRecord",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtModifyRecord(XtModifyRecord xt_Modify_Record,HttpServletRequest request){
		int i = 0;
		if(null != xt_Modify_Record && !"".equals(xt_Modify_Record)){
			xt_Modify_Record.setXt_modify_record_id(UUID.toUUID());
			i=xtModifyRecordService.addXtModifyRecord(xt_Modify_Record);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_modify_record 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtModifyRecord",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtModifyRecord(XtModifyRecord xt_Modify_Record,HttpServletRequest request){
		int i = 0;
		if(null != xt_Modify_Record && !"".equals(xt_Modify_Record)){
			i=xtModifyRecordService.updateXtModifyRecord(xt_Modify_Record);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_modify_record_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtModifyRecord",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtModifyRecord(String xt_modify_record_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_modify_record_id && !"".equals(xt_modify_record_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_modify_record_id",xt_modify_record_id.split(","));
			i=xtModifyRecordService.delXtModifyRecord(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_modify_record_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtModifyRecord",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtModifyRecord(String xt_modify_record_id,HttpServletRequest request){
		int i = 0;
		XtModifyRecord xt_Modify_Record = xtModifyRecordService.getXtModifyRecordById(xt_modify_record_id);
		if(null != xt_Modify_Record && !"".equals(xt_Modify_Record)){
			xt_Modify_Record.setXt_modify_record_id(UUID.toUUID());
			i=xtModifyRecordService.addXtModifyRecord(xt_Modify_Record);
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
	@RequestMapping(value="/exportXtModifyRecord",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtModifyRecord(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtModifyRecordDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtModifyRecordDetail(String xt_modify_record_id,HttpServletRequest request, Model model){
		XtModifyRecord xtModifyRecord = xtModifyRecordService.getXtModifyRecordById(xt_modify_record_id);
		model.addAttribute("xtModifyRecord", xtModifyRecord);
		return new ModelAndView("pc/xt-view/xt-modify-record/xt-modify-record-detail");
	}
}

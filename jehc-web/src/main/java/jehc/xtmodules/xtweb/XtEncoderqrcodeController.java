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
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtEncoderqrcode;
import jehc.xtmodules.xtservice.XtEncoderqrcodeService;

/**
* 平台二维码 
* 2016-04-05 21:06:53  邓纯杰
*/
@Controller
@RequestMapping("/xtEncoderqrcodeController")
public class XtEncoderqrcodeController extends BaseAction{
	@Autowired
	private XtEncoderqrcodeService xtEncoderqrcodeService;
	/**
	* 载入初始化页面
	* @param xt_encoderqrcode 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtEncoderqrcode",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtEncoderqrcode(XtEncoderqrcode xt_Encoderqrcode,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-encoderqrcode/xt-encoderqrcode-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_encoderqrcode 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtEncoderqrcodeListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtEncoderqrcodeListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		String jehcsources_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		List<XtEncoderqrcode> xt_EncoderqrcodeList = xtEncoderqrcodeService.getXtEncoderqrcodeListByCondition(condition);
		for(int i = 0; i < xt_EncoderqrcodeList.size(); i++){
			xt_EncoderqrcodeList.get(i).setJehcsources_base_url(jehcsources_base_url);
		}
		PageInfo<XtEncoderqrcode> page = new PageInfo<XtEncoderqrcode>(xt_EncoderqrcodeList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_encoderqrcode_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtEncoderqrcodeById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtEncoderqrcodeById(String xt_encoderqrcode_id,HttpServletRequest request){
		XtEncoderqrcode xt_Encoderqrcode = xtEncoderqrcodeService.getXtEncoderqrcodeById(xt_encoderqrcode_id);
		return outDataStr(xt_Encoderqrcode);
	}
	/**
	* 添加
	* @param xt_encoderqrcode 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtEncoderqrcode",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtEncoderqrcode(XtEncoderqrcode xt_Encoderqrcode,HttpServletRequest request){
		int i = 0;
		if(null != xt_Encoderqrcode && !"".equals(xt_Encoderqrcode)){
			xt_Encoderqrcode.setXt_encoderqrcode_id(UUID.toUUID());
			xt_Encoderqrcode.setXt_encoderqrcode_ctime(CommonUtils.getSimpleDateFormat());
			xt_Encoderqrcode.setXt_userinfo_id(CommonUtils.getXtUid());
			i=xtEncoderqrcodeService.addXtEncoderqrcode(xt_Encoderqrcode);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_encoderqrcode 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtEncoderqrcode",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtEncoderqrcode(XtEncoderqrcode xt_Encoderqrcode,HttpServletRequest request){
		int i = 0;
		if(null != xt_Encoderqrcode && !"".equals(xt_Encoderqrcode)){
			xt_Encoderqrcode.setXt_userinfo_id(CommonUtils.getXtUid());
			xt_Encoderqrcode.setXt_encoderqrcode_mtime(CommonUtils.getSimpleDateFormat());
			i=xtEncoderqrcodeService.updateXtEncoderqrcode(xt_Encoderqrcode);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_encoderqrcode_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtEncoderqrcode",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtEncoderqrcode(String xt_encoderqrcode_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_encoderqrcode_id && !"".equals(xt_encoderqrcode_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_encoderqrcode_id",xt_encoderqrcode_id.split(","));
			i=xtEncoderqrcodeService.delXtEncoderqrcode(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_encoderqrcode_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtEncoderqrcode",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtEncoderqrcode(String xt_encoderqrcode_id,HttpServletRequest request){
		int i = 0;
		XtEncoderqrcode xt_Encoderqrcode = xtEncoderqrcodeService.getXtEncoderqrcodeById(xt_encoderqrcode_id);
		if(null != xt_Encoderqrcode && !"".equals(xt_Encoderqrcode)){
			xt_Encoderqrcode.setXt_encoderqrcode_id(UUID.toUUID());
			i=xtEncoderqrcodeService.addXtEncoderqrcode(xt_Encoderqrcode);
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
	@RequestMapping(value="/exportXtEncoderqrcode",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtEncoderqrcode(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtEncoderqrcodeAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtEncoderqrcodeAdd(XtEncoderqrcode xtEncoderqrcode,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-encoderqrcode/xt-encoderqrcode-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toXtEncoderqrcodeUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtEncoderqrcodeUpdate(String xt_encoderqrcode_id,HttpServletRequest request, Model model){
		XtEncoderqrcode xtEncoderqrcode = xtEncoderqrcodeService.getXtEncoderqrcodeById(xt_encoderqrcode_id);
		model.addAttribute("xtEncoderqrcode", xtEncoderqrcode);
		return new ModelAndView("pc/xt-view/xt-encoderqrcode/xt-encoderqrcode-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtEncoderqrcodeDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtEncoderqrcodeDetail(String xt_encoderqrcode_id,HttpServletRequest request, Model model){
		XtEncoderqrcode xtEncoderqrcode = xtEncoderqrcodeService.getXtEncoderqrcodeById(xt_encoderqrcode_id);
		model.addAttribute("xtEncoderqrcode", xtEncoderqrcode);
		return new ModelAndView("pc/xt-view/xt-encoderqrcode/xt-encoderqrcode-detail");
	}
}

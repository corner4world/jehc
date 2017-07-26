package jehc.xtmodules.xtweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import jehc.xtmodules.xtmodel.XtAttachment;
import jehc.xtmodules.xtservice.XtAttachmentService;

/**
* 附件管理 
* 2015-05-24 08:36:53  邓纯杰
*/
@Controller
@RequestMapping("/xtAttachmentController")
public class XtAttachmentController extends BaseAction{
	@Autowired
	private XtAttachmentService xtAttachmentService;
	/**
	* 载入初始化页面
	* @param xt_attachment 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtAttachment",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtAttachment(XtAttachment xt_Attachment,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-attachment/xt-attachment-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_attachment 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtAttachmentListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtAttachmentListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtAttachment>XtAttachmentList = xtAttachmentService.getXtAttachmentListByCondition(condition);
		PageInfo<XtAttachment> page = new PageInfo<XtAttachment>(XtAttachmentList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_attachment_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtAttachmentById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtAttachmentById(String xt_attachment_id,HttpServletRequest request){
		XtAttachment xt_Attachment = xtAttachmentService.getXtAttachmentById(xt_attachment_id);
		return outDataStr(xt_Attachment);
	}
	/**
	* 添加
	* @param xt_attachment 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtAttachment",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtAttachment(XtAttachment xt_Attachment,HttpServletRequest request){
		int i = 0;
		if(null != xt_Attachment && !"".equals(xt_Attachment)){
			xt_Attachment.setXt_attachment_id(UUID.toUUID());
			i=xtAttachmentService.addXtAttachment(xt_Attachment);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_attachment 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtAttachment",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtAttachment(XtAttachment xt_Attachment,HttpServletRequest request){
		int i = 0;
		if(null != xt_Attachment && !"".equals(xt_Attachment)){
			i=xtAttachmentService.updateXtAttachment(xt_Attachment);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_attachment_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtAttachment",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtAttachment(String xt_attachment_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_attachment_id && !"".equals(xt_attachment_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_attachment_id",xt_attachment_id.split(","));
			i=xtAttachmentService.delXtAttachment(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}

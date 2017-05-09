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
import jehc.xtmodules.xtmodel.Xt_Attachment;
import jehc.xtmodules.xtservice.Xt_AttachmentService;

/**
* 附件管理 
* 2015-05-24 08:36:53  邓纯杰
*/
@Controller
@RequestMapping("/xtAttachmentController")
public class Xt_AttachmentController extends BaseAction{
	@Autowired
	private Xt_AttachmentService xt_AttachmentService;
	/**
	* 载入初始化页面
	* @param xt_attachment 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtAttachment",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtAttachment(Xt_Attachment xt_Attachment,HttpServletRequest request){
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
		List<Xt_Attachment>XtAttachmentList = xt_AttachmentService.getXtAttachmentListByCondition(condition);
		PageInfo<Xt_Attachment> page = new PageInfo<Xt_Attachment>(XtAttachmentList);
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
		Xt_Attachment xt_Attachment = xt_AttachmentService.getXtAttachmentById(xt_attachment_id);
		return outDataStr(xt_Attachment);
	}
	/**
	* 添加
	* @param xt_attachment 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtAttachment",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtAttachment(Xt_Attachment xt_Attachment,HttpServletRequest request){
		int i = 0;
		if(null != xt_Attachment && !"".equals(xt_Attachment)){
			xt_Attachment.setXt_attachment_id(UUID.toUUID());
			i=xt_AttachmentService.addXtAttachment(xt_Attachment);
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
	public String updateXtAttachment(Xt_Attachment xt_Attachment,HttpServletRequest request){
		int i = 0;
		if(null != xt_Attachment && !"".equals(xt_Attachment)){
			i=xt_AttachmentService.updateXtAttachment(xt_Attachment);
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
			i=xt_AttachmentService.delXtAttachment(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}

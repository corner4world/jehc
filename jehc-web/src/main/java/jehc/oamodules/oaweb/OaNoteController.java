package jehc.oamodules.oaweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.annotation.NeedLoginUnAuth;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.oamodules.oamodel.OaNote;
import jehc.oamodules.oamodel.OaNoteClassify;
import jehc.oamodules.oaservice.OaNoteClassifyService;
import jehc.oamodules.oaservice.OaNoteService;

/**
* 记事本 
* 2018-07-05 19:35:07  邓纯杰
*/
@Controller
@RequestMapping("/oaNoteController")
public class OaNoteController extends BaseAction{
	@Autowired
	private OaNoteService oaNoteService;
	@Autowired
	private OaNoteClassifyService oaNoteClassifyService;
	/**
	* 载入初始化页面
	* @param oa_note 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadOaNote",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadOaNote(OaNote oaNote,HttpServletRequest request){
		request.setAttribute("OaNoteClassifyList", oaNoteClassifyService.getOaNoteClassifyListByCondition(null));
		return new ModelAndView("pc/oa-view/oa-note/oa-note-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param oa_note 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaNoteListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaNoteListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<OaNote> oaNoteList = oaNoteService.getOaNoteListByCondition(condition);
		PageInfo<OaNote> page = new PageInfo<OaNote>(oaNoteList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param oa_note_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaNoteById",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaNoteById(String oa_note_id,HttpServletRequest request){
		OaNote oaNote = oaNoteService.getOaNoteById(oa_note_id);
		return outDataStr(oaNote);
	}
	/**
	* 添加
	* @param oa_note 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addOaNote",method={RequestMethod.POST,RequestMethod.GET})
	public String addOaNote(OaNote oaNote,HttpServletRequest request){
		int i = 0;
		if(null != oaNote && !"".equals(oaNote)){
			oaNote.setOa_note_id(UUID.toUUID());
			oaNote.setCtime(getDate());
			oaNote.setXt_userinfo_id(getXtUid());
			i=oaNoteService.addOaNote(oaNote);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param oa_note 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateOaNote",method={RequestMethod.POST,RequestMethod.GET})
	public String updateOaNote(OaNote oaNote,HttpServletRequest request){
		int i = 0;
		if(null != oaNote && !"".equals(oaNote)){
			oaNote.setMtime(getDate());
			i=oaNoteService.updateOaNote(oaNote);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param oa_note_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delOaNote",method={RequestMethod.POST,RequestMethod.GET})
	public String delOaNote(String oa_note_id,HttpServletRequest request){
		int i = 0;
		if(null != oa_note_id && !"".equals(oa_note_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("oa_note_id",oa_note_id.split(","));
			i=oaNoteService.delOaNote(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param oa_note_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyOaNote",method={RequestMethod.POST,RequestMethod.GET})
	public String copyOaNote(String oa_note_id,HttpServletRequest request){
		int i = 0;
		OaNote oaNote = oaNoteService.getOaNoteById(oa_note_id);
		if(null != oaNote && !"".equals(oaNote)){
			oaNote.setOa_note_id(UUID.toUUID());
			i=oaNoteService.addOaNote(oaNote);
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
	@RequestMapping(value="/exportOaNote",method={RequestMethod.POST,RequestMethod.GET})
	public void exportOaNote(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toOaNoteAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaNoteAdd(OaNote oaNote,HttpServletRequest request){
		request.setAttribute("OaNoteClassifyList", oaNoteClassifyService.getOaNoteClassifyListByCondition(null));
		return new ModelAndView("pc/oa-view/oa-note/oa-note-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toOaNoteUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaNoteUpdate(String oa_note_id,HttpServletRequest request, Model model){
		OaNote oaNote = oaNoteService.getOaNoteById(oa_note_id);
		model.addAttribute("oaNote", oaNote);
		request.setAttribute("OaNoteClassifyList", oaNoteClassifyService.getOaNoteClassifyListByCondition(null));
		return new ModelAndView("pc/oa-view/oa-note/oa-note-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toOaNoteDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaNoteDetail(String oa_note_id,HttpServletRequest request, Model model){
		OaNote oaNote = oaNoteService.getOaNoteById(oa_note_id);
		model.addAttribute("oaNote", oaNote);
		return new ModelAndView("pc/oa-view/oa-note/oa-note-detail");
	}
}

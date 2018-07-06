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
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.oamodules.oamodel.OaSuggestionbox;
import jehc.oamodules.oaservice.OaSuggestionboxService;

/**
* 意见申诉 
* 2018-07-06 20:11:52  邓纯杰
*/
@Controller
@RequestMapping("/oaSuggestionboxController")
public class OaSuggestionboxController extends BaseAction{
	@Autowired
	private OaSuggestionboxService oaSuggestionboxService;
	/**
	* 载入初始化页面
	* @param oa_suggestionbox 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadOaSuggestionbox",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadOaSuggestionbox(OaSuggestionbox oaSuggestionbox,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-suggestionbox/oa-suggestionbox-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param oa_suggestionbox 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaSuggestionboxListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaSuggestionboxListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<OaSuggestionbox> oaSuggestionboxList = oaSuggestionboxService.getOaSuggestionboxListByCondition(condition);
		PageInfo<OaSuggestionbox> page = new PageInfo<OaSuggestionbox>(oaSuggestionboxList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param oa_suggestionbox_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaSuggestionboxById",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaSuggestionboxById(String oa_suggestionbox_id,HttpServletRequest request){
		OaSuggestionbox oaSuggestionbox = oaSuggestionboxService.getOaSuggestionboxById(oa_suggestionbox_id);
		return outDataStr(oaSuggestionbox);
	}
	/**
	* 添加
	* @param oa_suggestionbox 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addOaSuggestionbox",method={RequestMethod.POST,RequestMethod.GET})
	public String addOaSuggestionbox(OaSuggestionbox oaSuggestionbox,HttpServletRequest request){
		int i = 0;
		if(null != oaSuggestionbox && !"".equals(oaSuggestionbox)){
			oaSuggestionbox.setOa_suggestionbox_id(UUID.toUUID());
			oaSuggestionbox.setCreateTime(getDate());
			oaSuggestionbox.setXt_userinfo_id(getXtUid());
			i=oaSuggestionboxService.addOaSuggestionbox(oaSuggestionbox);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param oa_suggestionbox 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateOaSuggestionbox",method={RequestMethod.POST,RequestMethod.GET})
	public String updateOaSuggestionbox(OaSuggestionbox oaSuggestionbox,HttpServletRequest request){
		int i = 0;
		if(null != oaSuggestionbox && !"".equals(oaSuggestionbox)){
			i=oaSuggestionboxService.updateOaSuggestionbox(oaSuggestionbox);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param oa_suggestionbox_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delOaSuggestionbox",method={RequestMethod.POST,RequestMethod.GET})
	public String delOaSuggestionbox(String oa_suggestionbox_id,HttpServletRequest request){
		int i = 0;
		if(null != oa_suggestionbox_id && !"".equals(oa_suggestionbox_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("oa_suggestionbox_id",oa_suggestionbox_id.split(","));
			i=oaSuggestionboxService.delOaSuggestionbox(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param oa_suggestionbox_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyOaSuggestionbox",method={RequestMethod.POST,RequestMethod.GET})
	public String copyOaSuggestionbox(String oa_suggestionbox_id,HttpServletRequest request){
		int i = 0;
		OaSuggestionbox oaSuggestionbox = oaSuggestionboxService.getOaSuggestionboxById(oa_suggestionbox_id);
		if(null != oaSuggestionbox && !"".equals(oaSuggestionbox)){
			oaSuggestionbox.setOa_suggestionbox_id(UUID.toUUID());
			i=oaSuggestionboxService.addOaSuggestionbox(oaSuggestionbox);
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
	@RequestMapping(value="/exportOaSuggestionbox",method={RequestMethod.POST,RequestMethod.GET})
	public void exportOaSuggestionbox(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toOaSuggestionboxAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaSuggestionboxAdd(OaSuggestionbox oaSuggestionbox,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-suggestionbox/oa-suggestionbox-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toOaSuggestionboxUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaSuggestionboxUpdate(String oa_suggestionbox_id,HttpServletRequest request, Model model){
		OaSuggestionbox oaSuggestionbox = oaSuggestionboxService.getOaSuggestionboxById(oa_suggestionbox_id);
		model.addAttribute("oaSuggestionbox", oaSuggestionbox);
		return new ModelAndView("pc/oa-view/oa-suggestionbox/oa-suggestionbox-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toOaSuggestionboxDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaSuggestionboxDetail(String oa_suggestionbox_id,HttpServletRequest request, Model model){
		OaSuggestionbox oaSuggestionbox = oaSuggestionboxService.getOaSuggestionboxById(oa_suggestionbox_id);
		model.addAttribute("oaSuggestionbox", oaSuggestionbox);
		return new ModelAndView("pc/oa-view/oa-suggestionbox/oa-suggestionbox-detail");
	}
}

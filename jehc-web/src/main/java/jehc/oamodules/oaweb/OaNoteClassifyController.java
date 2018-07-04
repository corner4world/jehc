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
import jehc.oamodules.oamodel.OaNoteClassify;
import jehc.oamodules.oaservice.OaNoteClassifyService;

/**
* 记事本分类 
* 2018-07-04 21:06:37  邓纯杰
*/
@Controller
@RequestMapping("/oaNoteClassifyController")
public class OaNoteClassifyController extends BaseAction{
	@Autowired
	private OaNoteClassifyService oaNoteClassifyService;
	/**
	* 载入初始化页面
	* @param oa_note_classify 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadOaNoteClassify",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadOaNoteClassify(OaNoteClassify oaNoteClassify,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-note-classify/oa-note-classify-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param oa_note_classify 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaNoteClassifyListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaNoteClassifyListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<OaNoteClassify> oaNoteClassifyList = oaNoteClassifyService.getOaNoteClassifyListByCondition(condition);
		PageInfo<OaNoteClassify> page = new PageInfo<OaNoteClassify>(oaNoteClassifyList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param oa_note_classify_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getOaNoteClassifyById",method={RequestMethod.POST,RequestMethod.GET})
	public String getOaNoteClassifyById(String oa_note_classify_id,HttpServletRequest request){
		OaNoteClassify oaNoteClassify = oaNoteClassifyService.getOaNoteClassifyById(oa_note_classify_id);
		return outDataStr(oaNoteClassify);
	}
	/**
	* 添加
	* @param oa_note_classify 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addOaNoteClassify",method={RequestMethod.POST,RequestMethod.GET})
	public String addOaNoteClassify(OaNoteClassify oaNoteClassify,HttpServletRequest request){
		int i = 0;
		if(null != oaNoteClassify && !"".equals(oaNoteClassify)){
			oaNoteClassify.setOa_note_classify_id(UUID.toUUID());
			oaNoteClassify.setCtime(getDate());
			oaNoteClassify.setXt_userinfo_id(getXtUid());
			i=oaNoteClassifyService.addOaNoteClassify(oaNoteClassify);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param oa_note_classify 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateOaNoteClassify",method={RequestMethod.POST,RequestMethod.GET})
	public String updateOaNoteClassify(OaNoteClassify oaNoteClassify,HttpServletRequest request){
		int i = 0;
		if(null != oaNoteClassify && !"".equals(oaNoteClassify)){
			oaNoteClassify.setMtime(getDate());
			i=oaNoteClassifyService.updateOaNoteClassify(oaNoteClassify);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param oa_note_classify_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delOaNoteClassify",method={RequestMethod.POST,RequestMethod.GET})
	public String delOaNoteClassify(String oa_note_classify_id,HttpServletRequest request){
		int i = 0;
		if(null != oa_note_classify_id && !"".equals(oa_note_classify_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("oa_note_classify_id",oa_note_classify_id.split(","));
			i=oaNoteClassifyService.delOaNoteClassify(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param oa_note_classify_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyOaNoteClassify",method={RequestMethod.POST,RequestMethod.GET})
	public String copyOaNoteClassify(String oa_note_classify_id,HttpServletRequest request){
		int i = 0;
		OaNoteClassify oaNoteClassify = oaNoteClassifyService.getOaNoteClassifyById(oa_note_classify_id);
		if(null != oaNoteClassify && !"".equals(oaNoteClassify)){
			oaNoteClassify.setOa_note_classify_id(UUID.toUUID());
			i=oaNoteClassifyService.addOaNoteClassify(oaNoteClassify);
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
	@RequestMapping(value="/exportOaNoteClassify",method={RequestMethod.POST,RequestMethod.GET})
	public void exportOaNoteClassify(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toOaNoteClassifyAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaNoteClassifyAdd(OaNoteClassify oaNoteClassify,HttpServletRequest request){
		return new ModelAndView("pc/oa-view/oa-note-classify/oa-note-classify-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toOaNoteClassifyUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaNoteClassifyUpdate(String oa_note_classify_id,HttpServletRequest request, Model model){
		OaNoteClassify oaNoteClassify = oaNoteClassifyService.getOaNoteClassifyById(oa_note_classify_id);
		model.addAttribute("oaNoteClassify", oaNoteClassify);
		return new ModelAndView("pc/oa-view/oa-note-classify/oa-note-classify-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toOaNoteClassifyDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toOaNoteClassifyDetail(String oa_note_classify_id,HttpServletRequest request, Model model){
		OaNoteClassify oaNoteClassify = oaNoteClassifyService.getOaNoteClassifyById(oa_note_classify_id);
		model.addAttribute("oaNoteClassify", oaNoteClassify);
		return new ModelAndView("pc/oa-view/oa-note-classify/oa-note-classify-detail");
	}
}

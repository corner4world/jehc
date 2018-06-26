package jehc.cmsmodules.cmsweb;
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
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.cmsmodules.cmsmodel.CmsVideo;
import jehc.cmsmodules.cmsservice.CmsVideoService;

/**
* 内容发布平台视频 
* 2018-06-25 21:50:52  邓纯杰
*/
@Controller
@RequestMapping("/cmsVideoController")
public class CmsVideoController extends BaseAction{
	@Autowired
	private CmsVideoService cmsVideoService;
	/**
	* 载入初始化页面
	* @param cms_video 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadCmsVideo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsVideo(CmsVideo cmsVideo,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-video/cms-video-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param cms_video 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsVideoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsVideoListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsVideo> cmsVideoList = cmsVideoService.getCmsVideoListByCondition(condition);
		String jehcimg_base_url = callBaseFileUrl();
		for(int i = 0; i < cmsVideoList.size(); i++){
			cmsVideoList.get(i).setXt_attachmentPath(jehcimg_base_url+cmsVideoList.get(i).getXt_attachmentPath());
		}
		PageInfo<CmsVideo> page = new PageInfo<CmsVideo>(cmsVideoList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param cms_video_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getCmsVideoById",method={RequestMethod.POST,RequestMethod.GET})
	public String getCmsVideoById(String cms_video_id,HttpServletRequest request){
		CmsVideo cmsVideo = cmsVideoService.getCmsVideoById(cms_video_id);
		return outDataStr(cmsVideo);
	}
	/**
	* 添加
	* @param cms_video 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addCmsVideo",method={RequestMethod.POST,RequestMethod.GET})
	public String addCmsVideo(CmsVideo cmsVideo,HttpServletRequest request){
		int i = 0;
		if(null != cmsVideo && !"".equals(cmsVideo)){
			cmsVideo.setCms_video_id(UUID.toUUID());
			cmsVideo.setCtime(getDate());
			cmsVideo.setXt_userinfo_id(getXtUid());
			i=cmsVideoService.addCmsVideo(cmsVideo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param cms_video 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateCmsVideo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateCmsVideo(CmsVideo cmsVideo,HttpServletRequest request){
		int i = 0;
		if(null != cmsVideo && !"".equals(cmsVideo)){
			cmsVideo.setMtime(getDate());
			i=cmsVideoService.updateCmsVideo(cmsVideo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param cms_video_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delCmsVideo",method={RequestMethod.POST,RequestMethod.GET})
	public String delCmsVideo(String cms_video_id,HttpServletRequest request){
		int i = 0;
		if(null != cms_video_id && !"".equals(cms_video_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("cms_video_id",cms_video_id.split(","));
			i=cmsVideoService.delCmsVideo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param cms_video_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyCmsVideo",method={RequestMethod.POST,RequestMethod.GET})
	public String copyCmsVideo(String cms_video_id,HttpServletRequest request){
		int i = 0;
		CmsVideo cmsVideo = cmsVideoService.getCmsVideoById(cms_video_id);
		if(null != cmsVideo && !"".equals(cmsVideo)){
			cmsVideo.setCms_video_id(UUID.toUUID());
			i=cmsVideoService.addCmsVideo(cmsVideo);
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
	@RequestMapping(value="/exportCmsVideo",method={RequestMethod.POST,RequestMethod.GET})
	public void exportCmsVideo(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsVideoAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsVideoAdd(CmsVideo cmsVideo,HttpServletRequest request){
		return new ModelAndView("pc/cms-view/cms-video/cms-video-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsVideoUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsVideoUpdate(String cms_video_id,HttpServletRequest request, Model model){
		CmsVideo cmsVideo = cmsVideoService.getCmsVideoById(cms_video_id);
		model.addAttribute("cmsVideo", cmsVideo);
		return new ModelAndView("pc/cms-view/cms-video/cms-video-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsVideoDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsVideoDetail(String cms_video_id,HttpServletRequest request, Model model){
		CmsVideo cmsVideo = cmsVideoService.getCmsVideoById(cms_video_id);
		model.addAttribute("cmsVideo", cmsVideo);
		return new ModelAndView("pc/cms-view/cms-video/cms-video-detail");
	}
}

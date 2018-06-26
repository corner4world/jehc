package jehc.cmsmodules.cmsweb;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.cmsmodules.cmsmodel.CmsVideo;
import jehc.cmsmodules.cmsservice.CmsVideoService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.BrowserUtil;
import jehc.xtmodules.xtcore.util.CommonUtils;

/**
* 视频
* 2018-06-10 17:56:24  邓纯杰
*/
@Controller
@RequestMapping("/cmsvideoController")
public class CmsVideoController extends BaseAction{
	@Autowired
	private CmsVideoService cmsVideoService;
	/**
	* 载入初始化页面
	* @param cms_about 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/video.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadCmsAbout(BaseSearch baseSearch,HttpServletRequest request, Model model){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<CmsVideo> cmsVideoList = cmsVideoService.getCmsVideoListByCondition(condition);
		PageInfo<CmsVideo> page = new PageInfo<CmsVideo>(cmsVideoList);
		model.addAttribute("page", page);
		model.addAttribute("title", "视频展示 ");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-video/cms-video-list");
		}else{
			return new ModelAndView("pc/cms-view/cms-video/cms-video-list");
		}
	}
	
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toCmsVideoDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toCmsVideoDetail(String cms_video_id,HttpServletRequest request, Model model){
		CmsVideo cmsVideo = cmsVideoService.getCmsVideoById(cms_video_id);
		model.addAttribute("cmsVideo", cmsVideo);
		model.addAttribute("title", "视频展示 ");
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcsources_base_url").get(0).getXt_path();
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		if(BrowserUtil.isPhone(request)){
			return new ModelAndView("phone/cms-view/cms-video/cms-video-detail");
		}else{
			return new ModelAndView("pc/cms-view/cms-video/cms-video-detail");
		}
	}
}

package jehc.bmodules.bweb;
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

import jehc.bmodules.bmodel.BRecommend;
import jehc.bmodules.bservice.BRecommendService;
import jehc.xtmodules.xtcore.allutils.file.FileUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseJson;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtAttachment;
import jehc.xtmodules.xtservice.XtAttachmentService;

/**
* 基础推荐 
* 2016-01-10 11:24:05  邓纯杰
*/
@Controller
@RequestMapping("/bRecommendController")
public class BRecommendController extends BaseAction{
	@Autowired
	private BRecommendService bRecommendService;
	@Autowired
	private XtAttachmentService xtAttachmentService;
	/**
	* 载入初始化页面
	* @param b_recommend 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBRecommend",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBRecommend(BRecommend b_Recommend,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-recommend/b-recommend-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_recommend 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBRecommendListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBRecommendListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BRecommend> b_RecommendList = bRecommendService.getBRecommendListByCondition(condition);
		PageInfo<BRecommend> page = new PageInfo<BRecommend>(b_RecommendList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param b_recommend_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBRecommendById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBRecommendById(String b_recommend_id,HttpServletRequest request){
		BRecommend b_Recommend = bRecommendService.getBRecommendById(b_recommend_id);
		return outDataStr(b_Recommend);
	}
	/**
	* 添加
	* @param b_recommend 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBRecommend",method={RequestMethod.POST,RequestMethod.GET})
	public String addBRecommend(BRecommend b_Recommend,HttpServletRequest request){
		int i = 0;
		if(null != b_Recommend && !"".equals(b_Recommend)){
			b_Recommend.setB_recommend_ctime(getDate());
			b_Recommend.setXt_userinfo_id(getXtUid());
			b_Recommend.setB_recommend_id(UUID.toUUID());
			i=bRecommendService.addBRecommend(b_Recommend);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_recommend 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBRecommend",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBRecommend(BRecommend b_Recommend,HttpServletRequest request){
		int i = 0;
		if(null != b_Recommend && !"".equals(b_Recommend)){
			b_Recommend.setXt_userinfo_id(getXtUid());
			b_Recommend.setB_recommend_mtime(getDate());
			i=bRecommendService.updateBRecommend(b_Recommend);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_recommend_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBRecommend",method={RequestMethod.POST,RequestMethod.GET})
	public String delBRecommend(String b_recommend_id,HttpServletRequest request){
		int i = 0;
		if(null != b_recommend_id && !"".equals(b_recommend_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_recommend_id",b_recommend_id.split(","));
			i=bRecommendService.delBRecommend(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_recommend_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBRecommend",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBRecommend(String b_recommend_id,HttpServletRequest request){
		int i = 0;
		BRecommend b_Recommend = bRecommendService.getBRecommendById(b_recommend_id);
		if(null != b_Recommend && !"".equals(b_Recommend)){
			b_Recommend.setB_recommend_id(UUID.toUUID());
			i=bRecommendService.addBRecommend(b_Recommend);
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
	@RequestMapping(value="/exportBRecommend",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBRecommend(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 上传推荐滚动图片
	 * @param file
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/uploadRecommendImages",method={RequestMethod.POST,RequestMethod.GET})
	public String uploadRecommendImages(HttpServletRequest request,HttpServletResponse response){
		BaseJson baseJson = new BaseJson();
		try {
			String path = CommonUtils.getXtPathCache("b_recommend_path").get(0).getXt_path();
			String relative_path = CommonUtils.getXtPathCache("b_recommend_relative_path").get(0).getXt_path();
			FileUtil.initPath(request, path);
			List<XtAttachment> xtAttachmentList = CommonUtils.upLoad(request, path,relative_path,null,null,null,null,null);
			int i = xtAttachmentService.addXtAttachment(xtAttachmentList.get(0));
			if(i > 0){
				baseJson.setJsonID(xtAttachmentList.get(0).getXt_attachment_id());
				baseJson.setMsg("上传成功");
				baseJson.setJsonValue(CommonUtils.getXtPathCache("jehcimg_base_url").get(0).getXt_path()+ xtAttachmentList.get(0).getXt_attachmentPath());
			}else{
				baseJson.setMsg("上传失败");
			}
			return outDataStr(baseJson);
        } catch (Exception e) {
        	baseJson.setMsg("上传失败");
        	return outDataStr(baseJson);
        }
	}
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toBRecommendAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBRecommendAdd(BRecommend bRecommend,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-recommend/b-recommend-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toBRecommendUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBRecommendUpdate(String b_recommend_id,HttpServletRequest request, Model model){
		BRecommend bRecommend = bRecommendService.getBRecommendById(b_recommend_id);
		model.addAttribute("bRecommend", bRecommend);
		return new ModelAndView("pc/b-view/b-recommend/b-recommend-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toBRecommendDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBRecommendDetail(String b_recommend_id,HttpServletRequest request, Model model){
		BRecommend bRecommend = bRecommendService.getBRecommendById(b_recommend_id);
		model.addAttribute("bRecommend", bRecommend);
		return new ModelAndView("pc/b-view/b-recommend/b-recommend-detail");
	}
}

package jehc.xtmodules.xtweb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtPost;
import jehc.xtmodules.xtservice.XtPostService;

/**
* 用户岗位表(xt_post) 
* 2015-05-13 16:48:11  邓纯杰
*/
@Controller
@RequestMapping("/xtPostController")
public class XtPostController extends BaseAction{
	@Autowired
	private XtPostService xtPostService;
	/**
	* 载入初始化页面
	* @param xt_post 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtPost",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtPost(XtPost xt_Post,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-post/xt-post-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_post 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtPostListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPostListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtPost>XtPostList = xtPostService.getXtPostListByCondition(condition);
		PageInfo<XtPost> page = new PageInfo<XtPost>(XtPostList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_post_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtPostById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPostById(String xt_post_id,HttpServletRequest request){
		XtPost xt_Post = xtPostService.getXtPostById(xt_post_id);
		return outDataStr(xt_Post);
	}
	/**
	* 添加
	* @param xt_post 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtPost",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtPost(XtPost xt_Post,HttpServletRequest request){
		int i = 0;
		if(null != xt_Post && !"".equals(xt_Post)){
			xt_Post.setXt_post_id(UUID.toUUID());
			if(StringUtil.isEmpty(xt_Post.getXt_post_parentId())){
				xt_Post.setXt_post_parentId("0");
			}
			i=xtPostService.addXtPost(xt_Post);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_post 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtPost",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtPost(XtPost xt_Post,HttpServletRequest request){
		int i = 0;
		if(null != xt_Post && !"".equals(xt_Post)){
			if(StringUtil.isEmpty(xt_Post.getXt_post_parentId())){
				xt_Post.setXt_post_parentId("0");
			}
			i=xtPostService.updateXtPost(xt_Post);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_post_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtPost",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtPost(String xt_post_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_post_id && !"".equals(xt_post_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_post_id",xt_post_id.split(","));
			i=xtPostService.delXtPost(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	 * 获取岗位树
	 * @param id
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/getXtPostTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPostTree(String id,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<XtPost> xtPostList = xtPostService.getXtPostListAll(condition);
		for(int i = 0; i < xtPostList.size(); i++){
			XtPost xtPost = xtPostList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(xtPost.getXt_post_id());
			BaseTreeGridEntity.setPid(xtPost.getXt_post_parentId());
			BaseTreeGridEntity.setText(xtPost.getXt_post_name());
			BaseTreeGridEntity.setExpanded(true);
			BaseTreeGridEntity.setSingleClickExpand(true);
			BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
			list.add(BaseTreeGridEntity);
		}
		return outStr(BaseTreeGridEntity.buildTree(list,false));
	}
	/**
	* 复制一行并生成记录
	* @param xt_post_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtPost",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtPost(String xt_post_id,HttpServletRequest request){
		int i = 0;
		XtPost xt_Post = xtPostService.getXtPostById(xt_post_id);
		if(null != xt_Post && !"".equals(xt_Post)){
			xt_Post.setXt_post_id(UUID.toUUID());
			i=xtPostService.addXtPost(xt_Post);
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
	@RequestMapping(value="/exportXtPost",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtPost(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 根据各种情况查找集合不分页（流程设计器中处理组 发起组等使用）
	 * @param xt_post_id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtPostList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPostList(String xt_post_id,HttpServletRequest request){
		List<XtPost> list = new ArrayList<XtPost>();
		if(null != xt_post_id && !"".equals(xt_post_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_post_id", xt_post_id.split(","));
			list = xtPostService.getXtPostList(condition);
		}
		return  outItemsStr(list);
	}
}

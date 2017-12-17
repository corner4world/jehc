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

import jehc.bmodules.bmodel.BFriendshipLink;
import jehc.bmodules.bservice.BFriendshipLinkService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础友情链接 
* 2016-01-10 12:35:06  邓纯杰
*/
@Controller
@RequestMapping("/bFriendshipLinkController")
public class BFriendshipLinkController extends BaseAction{
	@Autowired
	private BFriendshipLinkService bFriendshipLinkService;
	/**
	* 载入初始化页面
	* @param b_friendship_link 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBFriendshipLink",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBFriendshipLink(BFriendshipLink b_Friendship_Link,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-friendship-link/b-friendship-link-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_friendship_link 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBFriendshipLinkListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBFriendshipLinkListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BFriendshipLink> b_Friendship_LinkList = bFriendshipLinkService.getBFriendshipLinkListByCondition(condition);
		PageInfo<BFriendshipLink> page = new PageInfo<BFriendshipLink>(b_Friendship_LinkList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param b_friendship_link_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBFriendshipLinkById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBFriendshipLinkById(String b_friendship_link_id,HttpServletRequest request){
		BFriendshipLink b_Friendship_Link = bFriendshipLinkService.getBFriendshipLinkById(b_friendship_link_id);
		return outDataStr(b_Friendship_Link);
	}
	/**
	* 添加
	* @param b_friendship_link 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBFriendshipLink",method={RequestMethod.POST,RequestMethod.GET})
	public String addBFriendshipLink(BFriendshipLink b_Friendship_Link,HttpServletRequest request){
		int i = 0;
		if(null != b_Friendship_Link && !"".equals(b_Friendship_Link)){
			b_Friendship_Link.setB_friendship_link_id(UUID.toUUID());
			b_Friendship_Link.setB_friendship_link_ctime(getDate());
			b_Friendship_Link.setXt_userinfo_id(getXtUid());
			i=bFriendshipLinkService.addBFriendshipLink(b_Friendship_Link);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_friendship_link 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBFriendshipLink",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBFriendshipLink(BFriendshipLink b_Friendship_Link,HttpServletRequest request){
		int i = 0;
		if(null != b_Friendship_Link && !"".equals(b_Friendship_Link)){
			b_Friendship_Link.setXt_userinfo_id(getXtUid());
			b_Friendship_Link.setB_friendship_link_mtime(getDate());
			i=bFriendshipLinkService.updateBFriendshipLink(b_Friendship_Link);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_friendship_link_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBFriendshipLink",method={RequestMethod.POST,RequestMethod.GET})
	public String delBFriendshipLink(String b_friendship_link_id,HttpServletRequest request){
		int i = 0;
		if(null != b_friendship_link_id && !"".equals(b_friendship_link_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_friendship_link_id",b_friendship_link_id.split(","));
			i=bFriendshipLinkService.delBFriendshipLink(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_friendship_link_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBFriendshipLink",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBFriendshipLink(String b_friendship_link_id,HttpServletRequest request){
		int i = 0;
		BFriendshipLink b_Friendship_Link = bFriendshipLinkService.getBFriendshipLinkById(b_friendship_link_id);
		if(null != b_Friendship_Link && !"".equals(b_Friendship_Link)){
			b_Friendship_Link.setB_friendship_link_id(UUID.toUUID());
			i=bFriendshipLinkService.addBFriendshipLink(b_Friendship_Link);
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
	@RequestMapping(value="/exportBFriendshipLink",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBFriendshipLink(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toBFriendshipLinkAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBFriendshipLinkAdd(BFriendshipLink bFriendshipLink,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-friendship-link/b-friendship-link-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toBFriendshipLinkUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBFriendshipLinkUpdate(String b_friendship_link_id,HttpServletRequest request, Model model){
		BFriendshipLink bFriendshipLink = bFriendshipLinkService.getBFriendshipLinkById(b_friendship_link_id);
		model.addAttribute("bFriendshipLink", bFriendshipLink);
		return new ModelAndView("pc/b-view/b-friendship-link/b-friendship-link-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toBFriendshipLinkDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBFriendshipLinkDetail(String b_friendship_link_id,HttpServletRequest request, Model model){
		BFriendshipLink bFriendshipLink = bFriendshipLinkService.getBFriendshipLinkById(b_friendship_link_id);
		model.addAttribute("bFriendshipLink", bFriendshipLink);
		return new ModelAndView("pc/b-view/b-friendship-link/b-friendship-link-detail");
	}
}

package jehc.bmodules.bweb;
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

import jehc.bmodules.bmodel.BMember;
import jehc.bmodules.bservice.BMemberService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础会员 
* 2016-01-08 22:35:34  邓纯杰
*/
@Controller
@RequestMapping("/bMemberController")
public class BMemberController extends BaseAction{
	@Autowired
	private BMemberService bMemberService;
	/**
	* 载入初始化页面
	* @param b_member 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBMember",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBMember(BMember b_Member,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-member/b-member-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_member 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BMember> b_MemberList = bMemberService.getBMemberListByCondition(condition);
		PageInfo<BMember> page = new PageInfo<BMember>(b_MemberList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_member_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberById(String b_member_id,HttpServletRequest request){
		BMember b_Member = bMemberService.getBMemberById(b_member_id);
		return outDataStr(b_Member);
	}
	/**
	* 添加
	* @param b_member 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBMember",method={RequestMethod.POST,RequestMethod.GET})
	public String addBMember(BMember b_Member,HttpServletRequest request){
		int i = 0;
		if(null != b_Member && !"".equals(b_Member)){
			b_Member.setB_member_id(UUID.toUUID());
			b_Member.setB_member_ctime(CommonUtils.getSimpleDateFormat());
			i=bMemberService.addBMember(b_Member);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_member 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBMember",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBMember(BMember b_Member,HttpServletRequest request){
		int i = 0;
		if(null != b_Member && !"".equals(b_Member)){
			b_Member.setB_member_mtime(CommonUtils.getSimpleDateFormat());
			i=bMemberService.updateBMember(b_Member);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_member_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBMember",method={RequestMethod.POST,RequestMethod.GET})
	public String delBMember(String b_member_id,HttpServletRequest request){
		int i = 0;
		if(null != b_member_id && !"".equals(b_member_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_member_id",b_member_id.split(","));
			i=bMemberService.delBMember(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_member_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBMember",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBMember(String b_member_id,HttpServletRequest request){
		int i = 0;
		BMember b_Member = bMemberService.getBMemberById(b_member_id);
		if(null != b_Member && !"".equals(b_Member)){
			b_Member.setB_member_id(UUID.toUUID());
			i=bMemberService.addBMember(b_Member);
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
	@RequestMapping(value="/exportBMember",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBMember(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

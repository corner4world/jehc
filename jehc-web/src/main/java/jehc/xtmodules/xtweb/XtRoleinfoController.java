package jehc.xtmodules.xtweb;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtFunctioninfo;
import jehc.xtmodules.xtmodel.XtFunctioninfoRight;
import jehc.xtmodules.xtmodel.XtMR;
import jehc.xtmodules.xtmodel.XtMenuinfo;
import jehc.xtmodules.xtmodel.XtRoleinfo;
import jehc.xtmodules.xtmodel.XtUR;
import jehc.xtmodules.xtmodel.XtUserinfo;
import jehc.xtmodules.xtservice.XtFunctioninfoRightService;
import jehc.xtmodules.xtservice.XtFunctioninfoService;
import jehc.xtmodules.xtservice.XtMRService;
import jehc.xtmodules.xtservice.XtMenuinfoService;
import jehc.xtmodules.xtservice.XtRoleinfoService;
import jehc.xtmodules.xtservice.XtURService;

/**
* 用户角色表 
* 2015-05-29 11:08:55  邓纯杰
*/
@Controller
@RequestMapping("/xtRoleinfoController")
@Scope("prototype")
public class XtRoleinfoController extends BaseAction{
	@Autowired
	private XtRoleinfoService xtRoleinfoService;
	
	@Autowired
	private XtMenuinfoService xtMenuinfoService;
	
	@Autowired
	private XtURService xtURService;
	
	@Autowired
	private XtMRService xtMRService;
	
	@Autowired
	private XtFunctioninfoService xtFunctioninfoService;
	
	@Autowired
	private XtFunctioninfoRightService xtFunctioninfoRightService;
	/**
	* 载入初始化页面
	* @param xt_roleinfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtRoleinfo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtRoleinfo(XtRoleinfo xt_Roleinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-roleinfo/xt-roleinfo-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_roleinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtRoleinfoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtRoleinfoListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		String xt_role_isdelete = request.getParameter("xt_role_isdelete");
		condition.put("xt_role_isdelete", xt_role_isdelete);
		commonHPager(condition,request);
		List<XtRoleinfo>XtRoleinfoList = xtRoleinfoService.getXtRoleinfoListByCondition(condition);
		PageInfo<XtRoleinfo> page = new PageInfo<XtRoleinfo>(XtRoleinfoList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_role_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtRoleinfoById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtRoleinfoById(String xt_role_id,HttpServletRequest request){
		XtRoleinfo xt_Roleinfo = xtRoleinfoService.getXtRoleinfoById(xt_role_id);
		return outDataStr(xt_Roleinfo);
	}
	/**
	* 添加
	* @param xt_roleinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtRoleinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtRoleinfo(XtRoleinfo xt_Roleinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Roleinfo && !"".equals(xt_Roleinfo)){
			xt_Roleinfo.setXt_role_id(UUID.toUUID());
			xt_Roleinfo.setXt_role_createTime(getSimpleDateFormat());
			i=xtRoleinfoService.addXtRoleinfo(xt_Roleinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_roleinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtRoleinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtRoleinfo(XtRoleinfo xt_Roleinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Roleinfo && !"".equals(xt_Roleinfo)){
			xt_Roleinfo.setXt_role_updateTime(getSimpleDateFormat());
			i=xtRoleinfoService.updateXtRoleinfo(xt_Roleinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_role_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtRoleinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtRoleinfo(String xt_role_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_role_id && !"".equals(xt_role_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_role_id",xt_role_id.split(","));
			i=xtRoleinfoService.delXtRoleinfo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	 * 恢复
	 * @param xt_role_id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/recoverXtRoleinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String recoverXtRoleinfo(String xt_role_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_role_id && !"".equals(xt_role_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_role_id",xt_role_id.split(","));
			i=xtRoleinfoService.recoverXtRoleinfo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 复制一行并生成记录
	* @param xt_role_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtRoleinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtRoleinfo(String xt_role_id,HttpServletRequest request){
		int i = 0;
		XtRoleinfo xt_Roleinfo = xtRoleinfoService.getXtRoleinfoById(xt_role_id);
		if(null != xt_Roleinfo && !"".equals(xt_Roleinfo)){
			xt_Roleinfo.setXt_role_id(UUID.toUUID());
			i=xtRoleinfoService.addXtRoleinfo(xt_Roleinfo);
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
	@RequestMapping(value="/exportXtRoleinfo",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtRoleinfo(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 读取用户角色列表【用户对角色】
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value="/getUserinfoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getUserinfoListByCondition(BaseSearch baseSearch,String type,String id,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		if(null != type && !"".equals(type)){
			type = URLDecoder.decode(type, "UTF-8");
			condition.put("id", id);
			condition.put("type", type);
		}
		List<XtUserinfo> xtUserinfoList = xtURService.getXtURListByCondition(condition);
		PageInfo<XtUserinfo> page = new PageInfo<XtUserinfo>(xtUserinfoList);
		return outPageBootStr(page,request);
	}
	
	/**
	 * 读取所有菜单列表
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtMenuinfoListAll",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMenuinfoListAll(HttpServletRequest request, HttpServletResponse response){
		//1获取所有菜单
		Map<String, Object> condition = new HashMap<String, Object>();
		String xt_role_id = request.getParameter("xt_role_id");
		String expanded = request.getParameter("expanded");
		String singleClickExpand = request.getParameter("singleClickExpand");
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		condition.put("xt_menuinfo_sys", "0");
		List<XtMenuinfo> xtMenuinfoList = xtMenuinfoService.getXtMenuinfoListAll(condition);
		condition = new HashMap<String, Object>();
		condition.put("xt_functioninfoType", "1");
		List<XtFunctioninfo> xtFunctioninfoList = xtFunctioninfoService.getXtFunctioninfoList(condition);
		//2获取所有已权限菜单
		condition.put("xt_role_id", xt_role_id);
		List<XtMenuinfo> xtMenuinfoRoleList = xtMenuinfoService.getXtMenuinfoListAll(condition);
		String xt_menuinfo_id = resultXtMenuInfoID(xtMenuinfoRoleList);
		for(int i = 0; i < xtMenuinfoList.size(); i++){
			XtMenuinfo xtMenuinfo = xtMenuinfoList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(xtMenuinfo.getXt_menuinfo_id());
			BaseTreeGridEntity.setPid(xtMenuinfo.getXt_menuinfo_parentId());
			BaseTreeGridEntity.setText(xtMenuinfo.getXt_menuinfo_title());
			if(xt_menuinfo_id.indexOf(xtMenuinfo.getXt_menuinfo_id())<0){
				BaseTreeGridEntity.setChecked(false);
			}else{
				BaseTreeGridEntity.setChecked(true);
			}
			if("0".equals(xtMenuinfo.getXt_menuinfo_leaf())){
				BaseTreeGridEntity.setLeaf(false);
			}else{
				BaseTreeGridEntity.setLeaf(true);
				//当菜单为末级时判断是否存在功能
				if(hasLeaf(xtFunctioninfoList, xtMenuinfo.getXt_menuinfo_id())){
					BaseTreeGridEntity.setLeaf(false);
				}else{
					BaseTreeGridEntity.setLeaf(true);
				}
			}
			BaseTreeGridEntity.setIcon("../deng/images/icons/"+xtMenuinfo.getXt_menuinfo_images());
			BaseTreeGridEntity.setTempObject("菜单");
			if(("true").equals(expanded)){
				BaseTreeGridEntity.setExpanded(true);
			}else{
				BaseTreeGridEntity.setExpanded(false);
			}
			if("true".equals(singleClickExpand)){
				BaseTreeGridEntity.setSingleClickExpand(true);
			}else{
				BaseTreeGridEntity.setSingleClickExpand(false);
			}
			list.add(BaseTreeGridEntity);
		}
		condition = new HashMap<String, Object>();
		condition.put("xt_role_id", xt_role_id.split(","));
		List<XtFunctioninfoRight> xt_Functioninfo_RightList = xtFunctioninfoRightService.getXtFunctioninfoRightListByCondition(condition);
		for(int i = 0; i < xtFunctioninfoList.size(); i++){
			XtFunctioninfo xtFunctioninfo = xtFunctioninfoList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(xtFunctioninfo.getXt_functioninfo_id()+"@"+xtFunctioninfo.getXt_menuinfo_id()+"@2");
			BaseTreeGridEntity.setPid(xtFunctioninfo.getXt_menuinfo_id());
			BaseTreeGridEntity.setText("<font color=red>"+xtFunctioninfo.getXt_functioninfoTitle()+"</font>");
			BaseTreeGridEntity.setIcon("../deng/images/icons/target_point.png");
			BaseTreeGridEntity.setTempObject("功能");
			BaseTreeGridEntity.setContent(""+xtFunctioninfo.getXt_functioninfoTitle());
			BaseTreeGridEntity.setIntegerappend(xtFunctioninfo.getXt_functioninfoIsAuthority()+","+xtFunctioninfo.getXt_functioninfoType());
			if(("true").equals(expanded)){
				BaseTreeGridEntity.setExpanded(true);
			}else{
				BaseTreeGridEntity.setExpanded(false);
			}
			if("true".equals(singleClickExpand)){
				BaseTreeGridEntity.setSingleClickExpand(true);
			}else{
				BaseTreeGridEntity.setSingleClickExpand(false);
			}
			for(XtFunctioninfoRight xt_Functioninfo_Right:xt_Functioninfo_RightList){
				if(xt_Functioninfo_Right.getXt_functioninfo_id().equals(xtFunctioninfo.getXt_functioninfo_id())){
					BaseTreeGridEntity.setChecked(true);
					break;
				}
			}
			BaseTreeGridEntity.setLeaf(true);
			list.add(BaseTreeGridEntity);
		}
		return outStr(BaseTreeGridEntity.buildTree(list,true));
	}
	/**
	 * 判断菜单下面是否有功能
	 * @param xtFunctioninfoList
	 * @param xt_menuinfo_id
	 * @return
	 */
	public boolean hasLeaf(List<XtFunctioninfo> xtFunctioninfoList,String xt_menuinfo_id){
		boolean flag = true;
		for(int i = 0; i < xtFunctioninfoList.size(); i++){
			if(xtFunctioninfoList.get(i).getXt_menuinfo_id().equals(xt_menuinfo_id)){
				return true;
			}
		}
		flag = false;
		return flag;
	}
	/**
	 * 解析已有权限菜单ID编号
	 * @param xtMenuinfoRoleList
	 * @return
	 */
	public String resultXtMenuInfoID(List<XtMenuinfo> xtMenuinfoRoleList){
		StringBuffer xt_menuinfo_id = new StringBuffer();
		for(int i = 0; i < xtMenuinfoRoleList.size(); i++){
			XtMenuinfo xt_Menuinfo = xtMenuinfoRoleList.get(i);
			if(null != xt_menuinfo_id){
				xt_menuinfo_id.append(","+xt_Menuinfo.getXt_menuinfo_id());
			}else{
				xt_menuinfo_id.append(xt_Menuinfo.getXt_menuinfo_id());
			}
		}
		return xt_menuinfo_id.toString();
	}
	/**
	* 导入用户信息
	* @param xt_u_r 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtUR",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtUR(String xt_userinfo_id, String xt_role_id,HttpServletRequest request){
		List<XtUR> xt_U_RList = new ArrayList<XtUR>();
		if(null != xt_userinfo_id && !"".equals(xt_userinfo_id)){
			String[] idList = xt_userinfo_id.split(",");
			for(int j = 0; j < idList.length; j++){
				String uuid = UUID.toUUID();
				XtUR xt_U_R = new XtUR();
				xt_U_R.setXt_userinfo_id(idList[j]);
				xt_U_R.setXt_u_r_id(uuid);
				xt_U_R.setXt_roleinfo_id(xt_role_id);
				xt_U_RList.add(xt_U_R);
			}
		}
		int i=xtURService.addXtUR(xt_U_RList);
		if(i>0){
			return outAudStr(true, CommonUtils.getCacheStr("sys_import_user_sucess"));
		}else{
			return outAudStr(false, CommonUtils.getCacheStr("sys_import_user_error"));
		}
	}
	
	/**
	 * 移除用户信息
	 * @param xt_userinfo_id
	 * @param xt_role_id
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/delXtUR",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtUR(String xt_userinfo_id, String xt_role_id,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("xt_userinfo_id", xt_userinfo_id.split(","));
		condition.put("xt_roleinfo_id", xt_role_id);
		int i=xtURService.delXtUR(condition);
		if(i>0){
			return outAudStr(true, CommonUtils.getCacheStr("sys_remove_user_sucess"));
		}else{
			return outAudStr(false, CommonUtils.getCacheStr("sys_remove_user_error"));
		}
	}
	
	/**
	* 添加
	* @param xt_m_r 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtMR",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtMR(String id,String xt_role_id,HttpServletRequest request){
		int i = 0;
		List<XtMR> xt_M_RList = new ArrayList<XtMR>();
		List<XtFunctioninfoRight> xt_Functioninfo_RightList = new ArrayList<XtFunctioninfoRight>();
		if(null != id && !"".equals(id)){
			String[] idList = id.split(",");
			for(int j = 0; j < idList.length; j++){
				if(idList[j].split("@").length == 1){
					//菜单
					String uuid = UUID.toUUID();
					XtMR xt_M_R = new XtMR();
					xt_M_R.setXt_menuinfo_id(idList[j]);
					xt_M_R.setXt_m_r_id(uuid);
					xt_M_R.setXt_role_id(xt_role_id);
					xt_M_RList.add(xt_M_R);
				}else{
					//功能
					String uuid = UUID.toUUID();
					XtFunctioninfoRight xt_Functioninfo_Right = new XtFunctioninfoRight();
					xt_Functioninfo_Right.setXt_menuinfo_id(idList[j].split("@")[1]);
					xt_Functioninfo_Right.setXt_functioninfo_right_id(uuid);
					xt_Functioninfo_Right.setXt_role_id(xt_role_id);
					xt_Functioninfo_Right.setXt_functioninfo_id(idList[j].split("@")[0]);
					xt_Functioninfo_RightList.add(xt_Functioninfo_Right);
				}
			}
		}
		i=xtMRService.addXtMR(xt_M_RList,xt_Functioninfo_RightList,xt_role_id);
		if(i>0){
			return outAudStr(true, CommonUtils.getCacheStr("sys_import_sources_sucess"));
		}else{
			return outAudStr(false, CommonUtils.getCacheStr("sys_improt_sourcess_error"));
		}
	}
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toXtRoleinfoAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtRoleinfoAdd(XtRoleinfo xtRoleinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-roleinfo/xt-roleinfo-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toXtRoleinfoUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtRoleinfoUpdate(String xt_role_id,HttpServletRequest request, Model model){
		XtRoleinfo xtRoleinfo = xtRoleinfoService.getXtRoleinfoById(xt_role_id);
		model.addAttribute("xtRoleinfo", xtRoleinfo);
		return new ModelAndView("pc/xt-view/xt-roleinfo/xt-roleinfo-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toXtRoleinfoDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toXtRoleinfoDetail(String xt_role_id,HttpServletRequest request, Model model){
		XtRoleinfo xtRoleinfo = xtRoleinfoService.getXtRoleinfoById(xt_role_id);
		model.addAttribute("xtRoleinfo", xtRoleinfo);
		return new ModelAndView("pc/xt-view/xt-roleinfo/xt-roleinfo-detail");
	}
}

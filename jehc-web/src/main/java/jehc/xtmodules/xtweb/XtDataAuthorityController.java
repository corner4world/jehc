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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtDataAuthority;
import jehc.xtmodules.xtmodel.XtDataAuthorityDefault;
import jehc.xtmodules.xtmodel.XtDataAuthorityDepart;
import jehc.xtmodules.xtmodel.XtDataAuthorityPost;
import jehc.xtmodules.xtmodel.XtDepartinfo;
import jehc.xtmodules.xtmodel.XtFunctioninfo;
import jehc.xtmodules.xtmodel.XtPost;
import jehc.xtmodules.xtmodel.XtUserinfo;
import jehc.xtmodules.xtservice.XtDataAuthorityService;
import jehc.xtmodules.xtservice.XtDataAuthorityDefaultService;
import jehc.xtmodules.xtservice.XtDataAuthorityDepartService;
import jehc.xtmodules.xtservice.XtDataAuthorityPostService;
import jehc.xtmodules.xtservice.XtDepartinfoService;
import jehc.xtmodules.xtservice.XtFunctioninfoService;
import jehc.xtmodules.xtservice.XtPostService;
import jehc.xtmodules.xtservice.XtUserinfoService;

/**
* 数据权限; InnoDB free: 10240 kB 
* 2015-10-04 14:42:34  邓纯杰
*/
@Controller
@RequestMapping("/xtDataAuthorityController")
public class XtDataAuthorityController extends BaseAction{
	@Autowired
	private XtDataAuthorityService xtDataAuthorityService;
	@Autowired
	private XtFunctioninfoService xtFunctioninfoService;
	@Autowired
	private XtUserinfoService xtUserinfoService;
	@Autowired
	private XtDepartinfoService xtDepartinfoService;
	@Autowired
	private XtPostService xtPostService;
	@Autowired
	private XtDataAuthorityDepartService xtDataAuthorityDepartService;
	@Autowired
	private XtDataAuthorityPostService xtDataAuthorityPostService;
	@Autowired
	private XtDataAuthorityDefaultService xtDataAuthorityDefaultService;
	/**
	* 载入初始化页面
	* @param xt_data_authority 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDataAuthority",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDataAuthority(XtDataAuthority xt_Data_Authority,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-data-authority/xt-data-authority-list");
	}
	
	/**
	 * 初始化所有可用数据权限模块
	 */
	@ResponseBody
	@RequestMapping(value="/getXtFunctioninfoListForData",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFunctioninfoListForData(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("xt_functioninfoIsAuthority", 0);
		List<XtFunctioninfo> xt_FunctioninfoList = xtFunctioninfoService.getXtFunctioninfoListForData(condition);
		return outItemsStr(xt_FunctioninfoList);
	}
	/**
	* 按人员添加
	* @param xt_data_authority 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtDataAuthorityByUser",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDataAuthorityByUser(HttpServletRequest request,String xt_userinfo_id,String id,String xt_menuinfo_id,String delID){
//		Map<String, Object> condition = new HashMap<String, Object>();
		int i = 0;
//		StringBuffer sbf = new StringBuffer();
//		String[] delIDList = new String[]{};
//		StringBuffer delSbf = new StringBuffer();
//		if(null != delID && !"".equals(delID)){
//			delIDList = delID.split(",");
//			for(int j = 0; j < delIDList.length; j++){
//				delSbf.append(xt_userinfo_id+xt_menuinfo_id+delIDList[j].split("@")[0]+"1"+delIDList[j].split("@")[1]);
//			}
//		}
//		condition.put("xt_menuinfo_id", xt_menuinfo_id);
//		condition.put("Xt_data_authorityType", 1);
//		condition.put("xt_userinfo_id", xt_userinfo_id);
//		List<Xt_Data_Authority> xt_Data_Authority_OldList = xtDataAuthorityService.getXtDataAuthorityListAllByCondition(condition);
//		for(int j = 0; j < xt_Data_Authority_OldList.size(); j++){
//			Xt_Data_Authority xt_Data_Authority_Old = xt_Data_Authority_OldList.get(j);
//			sbf.append(xt_Data_Authority_Old.getXt_userinfo_id()+xt_Data_Authority_Old.getXt_menuinfo_id()+xt_Data_Authority_Old.getXt_functioninfo_id()+xt_Data_Authority_Old.getXt_data_authorityType()+xt_Data_Authority_Old.getXtUID());
//		}
//		
//		//将xt_Data_Authority_OldList需要删除的元素去除
//		for(int j = 0; j < xt_Data_Authority_OldList.size(); j++){
//			Xt_Data_Authority xt_Data_Authority_Old = xt_Data_Authority_OldList.get(j);
//			String str = xt_Data_Authority_Old.getXt_userinfo_id()+xt_Data_Authority_Old.getXt_menuinfo_id()+xt_Data_Authority_Old.getXt_functioninfo_id()+xt_Data_Authority_Old.getXt_data_authorityType()+xt_Data_Authority_Old.getXtUID();
//			if(delSbf.toString().indexOf(str)>=0){
//				xt_Data_Authority_OldList.remove(j);
//				j--;
//			}
//		}
		List<XtDataAuthority> xt_Data_Authority_List = new ArrayList<XtDataAuthority>();
		if(null != id && !"".equals(id)){
			String[] idList = id.split(",");
			for(int j = 0; j < idList.length; j++){
				String[] idValue = idList[j].split("@");
				if(!idValue[0].equals("0")){
					XtDataAuthority xt_Data_Authority = new XtDataAuthority();
					xt_Data_Authority.setXt_data_authority_id(UUID.toUUID());
					xt_Data_Authority.setXt_data_authorityType("1");
					xt_Data_Authority.setXt_functioninfo_id(idValue[0]);
					xt_Data_Authority.setXt_menuinfo_id(xt_menuinfo_id);
					xt_Data_Authority.setXt_userinfo_id(xt_userinfo_id);
					xt_Data_Authority.setXtUID(idValue[1]);
//					String str = xt_Data_Authority.getXt_userinfo_id()+xt_Data_Authority.getXt_menuinfo_id()+xt_Data_Authority.getXt_functioninfo_id()+xt_Data_Authority.getXt_data_authorityType()+xt_Data_Authority.getXtUID();
					//将新的数据添加进去
//					if(sbf.toString().indexOf(str)<0){
					xt_Data_Authority_List.add(xt_Data_Authority);
//					}
				}
			}
		}
		if(null != xt_Data_Authority_List && !"".equals(xt_Data_Authority_List)){
			i=xtDataAuthorityService.addXtDataAuthority(xt_Data_Authority_List,xt_userinfo_id,id,xt_menuinfo_id,"1");
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 根据部门进行添加
	 * @param request
	 * @param xt_departinfo_id拥有者
	 * @param id被拥有者
	 * @param xt_menuinfo_id
	 * @param delID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addXtDataAuthorityByDepart",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDataAuthorityByDepart(HttpServletRequest request,String xt_departinfo_id,String id,String xt_menuinfo_id,String delID){
		Map<String, Object> condition = new HashMap<String, Object>();
		int i = 0;
//		StringBuffer sbf = new StringBuffer();
//		String[] delIDList = new String[]{};
//		StringBuffer delSbf = new StringBuffer();
//		if(null != delID && !"".equals(delID)){
//			delIDList = delID.split(",");
//			for(int j = 0; j < delIDList.length; j++){
//				delSbf.append(xt_departinfo_id+xt_menuinfo_id+delIDList[j].split("@")[0]+delIDList[j].split("@")[1]);
//			}
//		}
//		condition.put("xt_menuinfo_id", xt_menuinfo_id);
//		condition.put("xt_departinfo_id", xt_departinfo_id);
//		
//		List<Xt_Data_Authority_Depart> xt_Data_Authority_Depart_OldList = xtDataAuthorityDepartService.getXtDataAuthorityDepartListByCondition(condition);
//		for(int j = 0; j < xt_Data_Authority_Depart_OldList.size(); j++){
//			Xt_Data_Authority_Depart xt_Data_Authority_Depart_Old = xt_Data_Authority_Depart_OldList.get(j);
//			sbf.append(xt_Data_Authority_Depart_Old.getXt_departinfo_id()+xt_Data_Authority_Depart_Old.getXt_menuinfo_id()+xt_Data_Authority_Depart_Old.getXt_functioninfo_id()+xt_Data_Authority_Depart_Old.getXtDID());
//		}
		List<XtDataAuthorityDepart> xt_Data_Authority_Depart_OldList = new ArrayList<XtDataAuthorityDepart>();
		if(null != id && !"".equals(id)){
			String[] idList = id.split(",");
			for(int j = 0; j < idList.length; j++){
				String[] idValue = idList[j].split("@");
				if(!idValue[0].equals("0")){
					XtDataAuthorityDepart xt_Data_Authority_Depart = new XtDataAuthorityDepart();
					xt_Data_Authority_Depart.setXt_data_authority_depart_id(UUID.toUUID());
					xt_Data_Authority_Depart.setXt_functioninfo_id(idValue[0]);
					xt_Data_Authority_Depart.setXt_menuinfo_id(xt_menuinfo_id);
					xt_Data_Authority_Depart.setXt_departinfo_id(xt_departinfo_id);
					xt_Data_Authority_Depart.setXtDID(idValue[1]);
//					String str = xt_Data_Authority_Depart.getXt_departinfo_id()+xt_Data_Authority_Depart.getXt_menuinfo_id()+xt_Data_Authority_Depart.getXt_functioninfo_id()+xt_Data_Authority_Depart.getXtDID();
					//将新的数据添加进去
//					if(sbf.toString().indexOf(str)<0){
						xt_Data_Authority_Depart_OldList.add(xt_Data_Authority_Depart);
//					}
				}
			}
		}
		//将xt_Data_Authority_OldList需要删除的元素去除
//		for(int j = 0; j < xt_Data_Authority_Depart_OldList.size(); j++){
//			Xt_Data_Authority_Depart xt_Data_Authority_Depart_Old = xt_Data_Authority_Depart_OldList.get(j);
//			String str = xt_Data_Authority_Depart_Old.getXt_departinfo_id()+xt_Data_Authority_Depart_Old.getXt_menuinfo_id()+xt_Data_Authority_Depart_Old.getXt_functioninfo_id()+xt_Data_Authority_Depart_Old.getXtDID();
//			if(delSbf.toString().indexOf(str)>=0){
//				xt_Data_Authority_Depart_OldList.remove(j);
//				j--;
//			}
//		}
		if(null != xt_Data_Authority_Depart_OldList && !"".equals(xt_Data_Authority_Depart_OldList)){
			i=xtDataAuthorityDepartService.addBatchXtDataAuthorityDepart(xt_Data_Authority_Depart_OldList,xt_departinfo_id,id,xt_menuinfo_id);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 根据岗位添加
	 * @param request
	 * @param xt_post_id
	 * @param id
	 * @param xt_menuinfo_id
	 * @param delID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addXtDataAuthorityByPost",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDataAuthorityByPost(HttpServletRequest request,String xt_post_id,String id,String xt_menuinfo_id,String delID){
		Map<String, Object> condition = new HashMap<String, Object>();
		int i = 0;
//		StringBuffer sbf = new StringBuffer();
//		String[] delIDList = new String[]{};
//		StringBuffer delSbf = new StringBuffer();
//		if(null != delID && !"".equals(delID)){
//			delIDList = delID.split(",");
//			for(int j = 0; j < delIDList.length; j++){
//				delSbf.append(xt_post_id+xt_menuinfo_id+delIDList[j].split("@")[0]+delIDList[j].split("@")[1]);
//			}
//		}
//		condition.put("xt_menuinfo_id", xt_menuinfo_id);
//		condition.put("xt_post_id", xt_post_id);
//		
//		List<Xt_Data_Authority_Post> xt_Data_Authority_Post_OldList = xtDataAuthorityPostService.getXtDataAuthorityPostListByCondition(condition);
//		for(int j = 0; j < xt_Data_Authority_Post_OldList.size(); j++){
//			Xt_Data_Authority_Post xt_Data_Authority_Post_Old = xt_Data_Authority_Post_OldList.get(j);
//			sbf.append(xt_Data_Authority_Post_Old.getXt_post_id()+xt_Data_Authority_Post_Old.getXt_menuinfo_id()+xt_Data_Authority_Post_Old.getXt_functioninfo_id()+xt_Data_Authority_Post_Old.getXtPID());
//		}
		List<XtDataAuthorityPost> xt_Data_Authority_Post_OldList = new ArrayList<XtDataAuthorityPost>();
		if(null != id && !"".equals(id)){
			String[] idList = id.split(",");
			for(int j = 0; j < idList.length; j++){
				String[] idValue = idList[j].split("@");
				if(!idValue[0].equals("0")){
					XtDataAuthorityPost xt_Data_Authority_Post = new XtDataAuthorityPost();
					xt_Data_Authority_Post.setXt_data_authority_post_id(UUID.toUUID());
					xt_Data_Authority_Post.setXt_functioninfo_id(idValue[0]);
					xt_Data_Authority_Post.setXt_menuinfo_id(xt_menuinfo_id);
					xt_Data_Authority_Post.setXt_post_id(xt_post_id);
					xt_Data_Authority_Post.setXtPID(idValue[1]);
//					String str = xt_Data_Authority_Post.getXt_post_id()+xt_Data_Authority_Post.getXt_menuinfo_id()+xt_Data_Authority_Post.getXt_functioninfo_id()+xt_Data_Authority_Post.getXtPID();
					//将新的数据添加进去
//					if(sbf.toString().indexOf(str)<0){
						xt_Data_Authority_Post_OldList.add(xt_Data_Authority_Post);
//					}
				}
			}
		}
		//将xt_Data_Authority_OldList需要删除的元素去除
//		for(int j = 0; j < xt_Data_Authority_Post_OldList.size(); j++){
//			Xt_Data_Authority_Post xt_Data_Authority_Post_Old = xt_Data_Authority_Post_OldList.get(j);
//			String str = xt_Data_Authority_Post_Old.getXt_post_id()+xt_Data_Authority_Post_Old.getXt_menuinfo_id()+xt_Data_Authority_Post_Old.getXt_functioninfo_id()+xt_Data_Authority_Post_Old.getXtPID();
//			if(delSbf.toString().indexOf(str)>=0){
//				xt_Data_Authority_Post_OldList.remove(j);
//				j--;
//			}
//		}
		if(null != xt_Data_Authority_Post_OldList && !"".equals(xt_Data_Authority_Post_OldList)){
			i=xtDataAuthorityPostService.addBatchXtDataAuthorityPost(xt_Data_Authority_Post_OldList,xt_post_id,id,xt_menuinfo_id);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 添加按默认初始化设置
	 * @param request
	 * @param xt_menuinfo_id
	 * @param xt_functioninfo_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addXtDataAuthorityByDefault",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDataAuthorityByDefault(HttpServletRequest request,String xt_menuinfo_id,String xt_functioninfo_id){
		if(StringUtil.isEmpty(xt_menuinfo_id)){
			throw new ExceptionUtil("未能获取到菜单编号---xt_menuinfo_id");
		}
		int i = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("xt_menuinfo_id", xt_menuinfo_id);
		List<XtDataAuthorityDefault> xt_Data_Authority_DefaultList = new ArrayList<XtDataAuthorityDefault>(); 
		if(!StringUtil.isEmpty(xt_functioninfo_id)){
			String[] xt_functioninfo_idList=xt_functioninfo_id.split(",");
			for(int j = 0; j < xt_functioninfo_idList.length; j++){
				XtDataAuthorityDefault Xt_Data_Authority_Default = new XtDataAuthorityDefault();
				Xt_Data_Authority_Default.setXt_menuinfo_id(xt_menuinfo_id);
				Xt_Data_Authority_Default.setXt_data_authority_default_id(UUID.toUUID());
				Xt_Data_Authority_Default.setXt_functioninfo_id(xt_functioninfo_idList[j]);
				Xt_Data_Authority_Default.setXt_data_authority_d_value("1");
				xt_Data_Authority_DefaultList.add(Xt_Data_Authority_Default);
			}
		}
		i = xtDataAuthorityDefaultService.addBatchXtDataAuthorityDefault(xt_Data_Authority_DefaultList,xt_menuinfo_id);
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
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
		List<XtUserinfo>XtUserinfoList = xtUserinfoService.getXtUserinfoListByCondition(condition);
		PageInfo<XtUserinfo> page = new PageInfo<XtUserinfo>(XtUserinfoList);
		return outPageStr(page,request);
	}
	
	/**
	 * 读取被拥有者及功能及已被设置的功能树列表
	 * @param request
	 * @param xt_menuinfo_id
	 * @param xt_userinfo_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getUserinfoAndFunctionInfoAndDataAuthorityTreeGrid",method={RequestMethod.POST,RequestMethod.GET})
	public String getUserinfoAndFunctionInfoAndDataAuthorityTreeGrid(HttpServletRequest request,String xt_menuinfo_id,String xt_userinfo_id){
		Map<String, Object> condition = new HashMap<String, Object>();
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<XtUserinfo>XtUserinfoList = xtUserinfoService.getXtUserinfoListAllByCondition(condition);
		condition.put("xt_menuinfo_id", xt_menuinfo_id);
		condition.put("xt_functioninfoIsAuthority", 0);
		condition.put("xt_data_authorityType", 1);
		condition.put("xt_userinfo_id", xt_userinfo_id);
		List<XtDataAuthority> xt_Data_AuthorityList = xtDataAuthorityService.getXtDataAuthorityListAllByCondition(condition);
		//定义一个拥有者编号+被拥有者编号+功能编号组合字段用来判断选择
		StringBuffer uf= new StringBuffer();
		for(int j = 0; j < xt_Data_AuthorityList.size();j++){
			XtDataAuthority xt_Data_Authority = xt_Data_AuthorityList.get(j);
			uf.append("|"+xt_Data_Authority.getXtUID()+"|"+xt_Data_Authority.getXt_functioninfo_id()+"|");
		}
		for(int i = 0; i < XtUserinfoList.size(); i++){
			XtUserinfo XtUserinfo = XtUserinfoList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId("0@"+XtUserinfo.getXt_userinfo_id());
			BaseTreeGridEntity.setPid("0");
			BaseTreeGridEntity.setText(XtUserinfo.getXt_userinfo_name());
			BaseTreeGridEntity.setExpanded(true);
			BaseTreeGridEntity.setSingleClickExpand(true);
			BaseTreeGridEntity.setTempObject("用户");
			BaseTreeGridEntity.setContent("被选择的用户");
			BaseTreeGridEntity.setIcon("../deng/images/icons/user.png");
			if((uf.toString()).indexOf(XtUserinfo.getXt_userinfo_id())<0){
				BaseTreeGridEntity.setChecked(false);
			}else{
				BaseTreeGridEntity.setChecked(true);
			}
			list.add(BaseTreeGridEntity);
		}
		List<XtFunctioninfo> xtFunctioninfoList = xtFunctioninfoService.getXtFunctioninfoAllForData(condition);
		for(int j = 0; j < XtUserinfoList.size(); j++){
			XtUserinfo XtUserinfo = XtUserinfoList.get(j);
			for(int i = 0; i < xtFunctioninfoList.size(); i++){
				XtFunctioninfo xtFunctioninfo = xtFunctioninfoList.get(i);
				BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
				BaseTreeGridEntity.setId(xtFunctioninfo.getXt_functioninfo_id()+"@"+XtUserinfo.getXt_userinfo_id());
				BaseTreeGridEntity.setPid("0@"+XtUserinfo.getXt_userinfo_id());
				BaseTreeGridEntity.setText(xtFunctioninfo.getXt_functioninfoName());
				if((uf.toString()).indexOf("|"+XtUserinfo.getXt_userinfo_id()+"|"+xtFunctioninfo.getXt_functioninfo_id()+"|")<0){
					BaseTreeGridEntity.setChecked(false);
				}else{
					BaseTreeGridEntity.setChecked(true);
				}
				BaseTreeGridEntity.setExpanded(true);
				BaseTreeGridEntity.setSingleClickExpand(true);
				BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
				BaseTreeGridEntity.setTempObject("功能");
				BaseTreeGridEntity.setContent("被选择的功能");
				list.add(BaseTreeGridEntity);
			}
		}
		return outStr(BaseTreeGridEntity.buildTree(list,true));
	}
	
	/**
	 * 读取被拥有者及功能及已被设置的功能树列表
	 * @param request
	 * @param xt_menuinfo_id
	 * @param xt_userinfo_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getDataAuthorityDefaultGrid",method={RequestMethod.POST,RequestMethod.GET})
	public String getDataAuthorityDefaultGrid(HttpServletRequest request,String xt_menuinfo_id){
		//获取数据权限功能
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("xt_menuinfo_id", xt_menuinfo_id);
		condition.put("xt_functioninfoIsAuthority", 0);
		List<XtDataAuthorityDefault> xt_Data_Authority_DefaultList= xtDataAuthorityDefaultService.getXtDataAuthorityDefaultListByCondition(condition);
		List<XtFunctioninfo> xtFunctioninfoList = xtFunctioninfoService.getXtFunctioninfoAllForData(condition);
		for(int i = 0; i < xtFunctioninfoList.size(); i++){
			XtFunctioninfo xt_Functioninfo =xtFunctioninfoList.get(i);
			for(int j = 0; j < xt_Data_Authority_DefaultList.size(); j++){
				XtDataAuthorityDefault xt_Data_Authority_Default = xt_Data_Authority_DefaultList.get(j);
				if(xt_Functioninfo.getXt_functioninfo_id().equals(xt_Data_Authority_Default.getXt_functioninfo_id())){
					xtFunctioninfoList.get(i).setItem(1);
					break;
				}
			}
		}
		
		return outItemsStr(xtFunctioninfoList);
	}
	/**
	 * 读取部门树
	 * @param id
	 * @param type
	 * @param request
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value="/getXtDepartTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDepartTree(String id,String type,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> condition = new HashMap<String, Object>();
		String expanded = request.getParameter("expanded");
		String singleClickExpand = request.getParameter("singleClickExpand");
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<XtDepartinfo> xtDepartinfoList = xtDepartinfoService.getXtDepartinfoListAll(condition);
		for(int i = 0; i < xtDepartinfoList.size(); i++){
			XtDepartinfo xtDepartinfo = xtDepartinfoList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(xtDepartinfo.getXt_departinfo_id());
			BaseTreeGridEntity.setPid(xtDepartinfo.getXt_departinfo_parentId());
			BaseTreeGridEntity.setText(xtDepartinfo.getXt_departinfo_name());
			BaseTreeGridEntity.setQtip(xtDepartinfo.getXt_departinfo_name());
			BaseTreeGridEntity.setTempObject("部门");
			BaseTreeGridEntity.setContent(""+xtDepartinfo.getXt_departinfo_desc());
			BaseTreeGridEntity.setIcon("../deng/images/icons/cursor_drag_arrow_2.png");
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
		return outStr(BaseTreeGridEntity.buildTree(list,false));
	}
	
	/**
	 * 读取被拥有者及功能及已被设置的功能树列表（部门）
	 * @param request
	 * @param xt_menuinfo_id
	 * @param xt_post_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtDepartAndFunctionInfoAndDataAuthorityTreeGrid",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDepartAndFunctionInfoAndDataAuthorityTreeGrid(HttpServletRequest request,String xt_menuinfo_id,String xt_departinfo_id){
		Map<String, Object> condition = new HashMap<String, Object>();
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<XtDepartinfo> xt_DepartinfoList = xtDepartinfoService.getXtDepartinfoListAll(condition);
		condition.put("xt_menuinfo_id", xt_menuinfo_id);
		condition.put("xt_departinfo_id", xt_departinfo_id);
		List<XtDataAuthorityDepart> xt_Data_Authority_DepartList = xtDataAuthorityDepartService.getXtDataAuthorityDepartListByCondition(condition);
		//定义一个拥有者编号+被拥有者编号+功能编号组合字段用来判断选择
		StringBuffer uf= new StringBuffer();
		for(int j = 0; j < xt_Data_Authority_DepartList.size();j++){
			XtDataAuthorityDepart xt_Data_Authority_Depart = xt_Data_Authority_DepartList.get(j);
			uf.append("|"+xt_Data_Authority_Depart.getXtDID()+"|"+xt_Data_Authority_Depart.getXt_functioninfo_id()+"|");
		}
		for(int i = 0; i < xt_DepartinfoList.size(); i++){
			XtDepartinfo xt_Departinfo = xt_DepartinfoList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId("0@"+xt_Departinfo.getXt_departinfo_id());
			BaseTreeGridEntity.setPid("0");
			BaseTreeGridEntity.setText(xt_Departinfo.getXt_departinfo_name());
			BaseTreeGridEntity.setExpanded(true);
			BaseTreeGridEntity.setSingleClickExpand(true);
			BaseTreeGridEntity.setTempObject("部门");
			BaseTreeGridEntity.setContent("被选部门");
			BaseTreeGridEntity.setIcon("../deng/images/icons/user.png");
			if((uf.toString()).indexOf(xt_Departinfo.getXt_departinfo_id())<0){
				BaseTreeGridEntity.setChecked(false);
			}else{
				BaseTreeGridEntity.setChecked(true);
			}
			list.add(BaseTreeGridEntity);
		}
		condition.put("xt_functioninfoIsAuthority", 0);
		List<XtFunctioninfo> xtFunctioninfoList = xtFunctioninfoService.getXtFunctioninfoAllForData(condition);
		for(int j = 0; j < xt_DepartinfoList.size(); j++){
			XtDepartinfo xt_Departinfo = xt_DepartinfoList.get(j);
			for(int i = 0; i < xtFunctioninfoList.size(); i++){
				XtFunctioninfo xtFunctioninfo = xtFunctioninfoList.get(i);
				BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
				BaseTreeGridEntity.setId(xtFunctioninfo.getXt_functioninfo_id()+"@"+xt_Departinfo.getXt_departinfo_id());
				BaseTreeGridEntity.setPid("0@"+xt_Departinfo.getXt_departinfo_id());
				BaseTreeGridEntity.setText(xtFunctioninfo.getXt_functioninfoName());
				if((uf.toString()).indexOf("|"+xt_Departinfo.getXt_departinfo_id()+"|"+xtFunctioninfo.getXt_functioninfo_id()+"|")<0){
					BaseTreeGridEntity.setChecked(false);
				}else{
					BaseTreeGridEntity.setChecked(true);
				}
				BaseTreeGridEntity.setExpanded(true);
				BaseTreeGridEntity.setSingleClickExpand(true);
				BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
				BaseTreeGridEntity.setTempObject("功能");
				list.add(BaseTreeGridEntity);
			}
		}
		return outStr(BaseTreeGridEntity.buildTree(list,true));
	}
	
	/**
	 * 读取岗位树
	 * @param id
	 * @param type
	 * @param request
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value="/getXtPostTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPostTree(String id,String type,HttpServletRequest request) throws UnsupportedEncodingException{
		id = request.getParameter("node");
		type = URLDecoder.decode(type, "UTF-8");
		StringBuffer jsonStr = new StringBuffer("");  
		if(null != id && !"".equals(id) && "0".equals(id)){
			jsonStr.append("[");
			List<XtDepartinfo> xtDepartinfoList = xtDepartinfoService.getXtDepartinfoList();
			for(int i = 0; i < xtDepartinfoList.size(); i++){
				XtDepartinfo xtDepartinfo = xtDepartinfoList.get(i);
				String leaf = "false";
				if(xtDepartinfo.getXt_departinfo_leaf() == 0){
					leaf = "false";
				}else{
					leaf = "true";
				}
				if(i==(xtDepartinfoList.size()-1)){
					jsonStr.append("{id:'"+xtDepartinfo.getXt_departinfo_id()+"',name:'"+xtDepartinfo.getXt_departinfo_name()+"',icon:'../deng/images/icons/depart.png',xt_departinfo_parentId:'"+xtDepartinfo.getXt_departinfo_parentId()+"',type:'部门',leaf:"+leaf+",remark:'"+xtDepartinfo.getXt_departinfo_desc()+"'}");  
				}else{
					jsonStr.append("{id:'"+xtDepartinfo.getXt_departinfo_id()+"',name:'"+xtDepartinfo.getXt_departinfo_name()+"',icon:'../deng/images/icons/depart.png',xt_departinfo_parentId:'"+xtDepartinfo.getXt_departinfo_parentId()+"',type:'部门',leaf:"+leaf+",remark:'"+xtDepartinfo.getXt_departinfo_desc()+"'},");
				}
			}
			jsonStr.append("]");
		}else{
			//1查找部门
			jsonStr.append("[");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_departinfo_parentId", id);
			List<XtDepartinfo> xtDepartinfoList = xtDepartinfoService.getXtDepartinfoListChild(condition);
			for(int i = 0; i < xtDepartinfoList.size(); i++){
				XtDepartinfo xtDepartinfo = xtDepartinfoList.get(i);
				String leaf = "false";
				if(i==(xtDepartinfoList.size()-1)){
					if(xtDepartinfo.getXt_departinfo_leaf() == 0){
						leaf = "false";
					}else{
						leaf = "true";
					}
					jsonStr.append("{id:'"+xtDepartinfo.getXt_departinfo_id()+"',name:'"+xtDepartinfo.getXt_departinfo_name()+"',leaf:"+leaf+",icon:'../deng/images/icons/depart.png',xt_departinfo_parentId:'"+xtDepartinfo.getXt_departinfo_parentId()+"',type:'部门',remark:'"+xtDepartinfo.getXt_departinfo_desc()+"'}");  
				}else{
					if(xtDepartinfo.getXt_departinfo_leaf() == 0){
						leaf = "false";
					}else{
						leaf = "true";
					}
					jsonStr.append("{id:'"+xtDepartinfo.getXt_departinfo_id()+"',name:'"+xtDepartinfo.getXt_departinfo_name()+"',leaf:"+leaf+",icon:'../deng/images/icons/depart.png',xt_departinfo_parentId:'"+xtDepartinfo.getXt_departinfo_parentId()+"',type:'部门',remark:'"+xtDepartinfo.getXt_departinfo_desc()+"'},");  
				}
			}
			//2查找部门下面一级岗位
			String xtPostStr = xtPostStr(id,type);
			if(null != xtPostStr && !"".equals(xtPostStr) && !"[".equals(jsonStr.toString())){
				jsonStr.append(","+xtPostStr);
			}else{
				jsonStr.append(xtPostStr);
			}
			jsonStr.append("]");
		}
		return outStr(jsonStr.toString());
	}
	
	/**
	 * 返回岗位字符串
	 * @param id
	 * @return
	 */
	public String xtPostStr(String id,String type){
		//如果类型:部门 则查出所有部门下的一级岗位
		StringBuffer jsonStr = new StringBuffer(); 
		Map<String, Object> condition = new HashMap<String, Object>();
		List<XtPost> xtPostList = new ArrayList<XtPost>();
		if(null != type && !"".equals(type) && "部门".equals(type)){
			condition.put("xt_departinfo_id", id);
			xtPostList = xtPostService.getXtPostinfoList(condition);
		}else{
			condition.put("xt_post_parentId", id);
			xtPostList = xtPostService.getXtPostListChild(condition);
		}
		//拼接字符串
		for(int i = 0; i < xtPostList.size(); i++){
			XtPost xtPost = xtPostList.get(i);
			String leaf = "false";
			if(xtPost.getXt_post_isLeaf() == 0){
				leaf = "false";
			}else{
				leaf = "true";
			}
			if(i==(xtPostList.size()-1)){
				jsonStr.append("{id:'"+xtPost.getXt_post_id()+"',name:'"+xtPost.getXt_post_name()+"',icon:'../deng/images/icons/users.png',xt_post_parentId:'"+xtPost.getXt_post_parentId()+"',type:'岗位',leaf:"+leaf+",remark:'"+xtPost.getXt_post_desc()+"',xt_departinfo_id:'"+xtPost.getXt_departinfo_id()+"',xt_departinfo_name:'"+xtPost.getXt_departinfo_name()+"'}");  
			}else{
				jsonStr.append("{id:'"+xtPost.getXt_post_id()+"',name:'"+xtPost.getXt_post_name()+"',icon:'../deng/images/icons/users.png',xt_post_parentId:'"+xtPost.getXt_post_parentId()+"',type:'岗位',leaf:"+leaf+",remark:'"+xtPost.getXt_post_desc()+"',xt_departinfo_id:'"+xtPost.getXt_departinfo_id()+"',xt_departinfo_name:'"+xtPost.getXt_departinfo_name()+"'},");
			}
		}
		return jsonStr.toString();
	}
	
	/**
	 * 读取被拥有者及功能及已被设置的功能树列表（岗位）
	 * @param request
	 * @param xt_menuinfo_id
	 * @param xt_post_id
	 */
	@ResponseBody
	@RequestMapping(value="/getXtPostAndFunctionInfoAndDataAuthorityTreeGrid",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtPostAndFunctionInfoAndDataAuthorityTreeGrid(HttpServletRequest request,String xt_menuinfo_id,String xt_post_id){
		Map<String, Object> condition = new HashMap<String, Object>();
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<XtPost> xtPostList = xtPostService.getXtPostListAll(condition);
		condition.put("xt_menuinfo_id", xt_menuinfo_id);
		condition.put("xt_post_id", xt_post_id);
		List<XtDataAuthorityPost> xt_Data_Authority_PostList = xtDataAuthorityPostService.getXtDataAuthorityPostListByCondition(condition);
		//定义一个拥有者编号+被拥有者编号+功能编号组合字段用来判断选择
		StringBuffer uf= new StringBuffer();
		for(int j = 0; j < xt_Data_Authority_PostList.size();j++){
			XtDataAuthorityPost xt_Data_Authority_Post = xt_Data_Authority_PostList.get(j);
			uf.append("|"+xt_Data_Authority_Post.getXtPID()+"|"+xt_Data_Authority_Post.getXt_functioninfo_id()+"|");
		}
		for(int i = 0; i < xtPostList.size(); i++){
			XtPost xtPost = xtPostList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId("0@"+xtPost.getXt_post_id());
			BaseTreeGridEntity.setPid("0");
			BaseTreeGridEntity.setText(xtPost.getXt_post_name());
			BaseTreeGridEntity.setExpanded(true);
			BaseTreeGridEntity.setSingleClickExpand(true);
			BaseTreeGridEntity.setTempObject("岗位");
			BaseTreeGridEntity.setContent(xtPost.getXt_departinfo_name());
			BaseTreeGridEntity.setIcon("../deng/images/icons/user.png");
			if((uf.toString()).indexOf(xtPost.getXt_post_id())<0){
				BaseTreeGridEntity.setChecked(false);
			}else{
				BaseTreeGridEntity.setChecked(true);
			}
			list.add(BaseTreeGridEntity);
		}
		condition.put("xt_functioninfoIsAuthority", 0);
		List<XtFunctioninfo> xtFunctioninfoList = xtFunctioninfoService.getXtFunctioninfoAllForData(condition);
		for(int j = 0; j < xtPostList.size(); j++){
			XtPost xtPost = xtPostList.get(j);
			for(int i = 0; i < xtFunctioninfoList.size(); i++){
				XtFunctioninfo xtFunctioninfo = xtFunctioninfoList.get(i);
				BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
				BaseTreeGridEntity.setId(xtFunctioninfo.getXt_functioninfo_id()+"@"+xtPost.getXt_post_id());
				BaseTreeGridEntity.setPid("0@"+xtPost.getXt_post_id());
				BaseTreeGridEntity.setText(xtFunctioninfo.getXt_functioninfoName());
				if((uf.toString()).indexOf("|"+xtPost.getXt_post_id()+"|"+xtFunctioninfo.getXt_functioninfo_id()+"|")<0){
					BaseTreeGridEntity.setChecked(false);
				}else{
					BaseTreeGridEntity.setChecked(true);
				}
				BaseTreeGridEntity.setExpanded(true);
				BaseTreeGridEntity.setSingleClickExpand(true);
				BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
				BaseTreeGridEntity.setTempObject("功能");
				list.add(BaseTreeGridEntity);
			}
		}
		return outStr(BaseTreeGridEntity.buildTree(list,true));
	}
	
	
	
	/**
	* 导出
	* @param excleData 
	* @param excleHeader 
	* @param excleText 
	* @param request 
	* @param request 
	*/
	@RequestMapping(value="/exportXtDataAuthority",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtDataAuthority(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	* 1清空按人员设置数据权限
	* @param xt_constant_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtDataAuthorityUserAll",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDataAuthorityUserAll(String xt_menuinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_menuinfo_id && !"".equals(xt_menuinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			condition.put("xt_data_authorityType", 1);
			i = xtDataAuthorityService.delXtDataAuthorityByCondition(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 2清空按部门设置数据权限
	* @param xt_constant_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtDataAuthorityDepartAll",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDataAuthorityDepartAll(String xt_menuinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_menuinfo_id && !"".equals(xt_menuinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			condition.put("xt_data_authorityType", 2);
			i = xtDataAuthorityDepartService.delXtDataAuthorityDepartAllByCondition(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 3清空按岗位设置数据权限
	* @param xt_constant_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtDataAuthorityPostAll",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDataAuthorityPostAll(String xt_menuinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_menuinfo_id && !"".equals(xt_menuinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			condition.put("xt_data_authorityType", 3);
			i = xtDataAuthorityPostService.delXtDataAuthorityPostListByCondition(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 4清空按初始化数据权限设置
	* @param xt_constant_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtDataAuthorityDefaultAll",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDataAuthorityDefaultAll(String xt_menuinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_menuinfo_id && !"".equals(xt_menuinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			condition.put("xt_data_authorityType", 4);
			i = xtDataAuthorityDefaultService.delXtDataAuthorityDefaultAllByCondition(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}

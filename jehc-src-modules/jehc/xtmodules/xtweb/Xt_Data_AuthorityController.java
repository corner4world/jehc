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

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.Xt_Data_Authority;
import jehc.xtmodules.xtmodel.Xt_Departinfo;
import jehc.xtmodules.xtmodel.Xt_Functioninfo;
import jehc.xtmodules.xtmodel.Xt_Post;
import jehc.xtmodules.xtmodel.Xt_Userinfo;
import jehc.xtmodules.xtservice.Xt_Data_AuthorityService;
import jehc.xtmodules.xtservice.Xt_DepartinfoService;
import jehc.xtmodules.xtservice.Xt_FunctioninfoService;
import jehc.xtmodules.xtservice.Xt_PostService;
import jehc.xtmodules.xtservice.Xt_UserinfoService;

/**
* 数据权限; InnoDB free: 10240 kB 
* 2015-10-04 14:42:34  邓纯杰
*/
@Controller
@RequestMapping("/xtDataAuthorityController")
public class Xt_Data_AuthorityController extends BaseAction{
	@Autowired
	private Xt_Data_AuthorityService xt_Data_AuthorityService;
	@Autowired
	private Xt_FunctioninfoService xt_FunctioninfoService;
	@Autowired
	private Xt_UserinfoService xt_UserinfoService;
	@Autowired
	private Xt_DepartinfoService xt_DepartinfoService;
	@Autowired
	private Xt_PostService xt_PostService;
	/**
	* 载入初始化页面
	* @param xt_data_authority 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDataAuthority",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDataAuthority(Xt_Data_Authority xt_Data_Authority,HttpServletRequest request){
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
		commonHPager(condition,request);
		List<Xt_Functioninfo> xt_FunctioninfoList = xt_FunctioninfoService.getXtFunctioninfoListForData(condition);
		PageInfo<Xt_Functioninfo> page = new PageInfo<Xt_Functioninfo>(xt_FunctioninfoList);
		return outPageStr(page,request);
	}
	/**
	* 按人员添加
	* @param xt_data_authority 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtDataAuthorityByUser",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDataAuthorityByUser(HttpServletRequest request,String xt_userinfo_id,String id,String xt_menuinfo_id,String delID){
		Map<String, Object> condition = new HashMap<String, Object>();
		int i = 0;
		StringBuffer sbf = new StringBuffer();
		String[] delIDList = new String[]{};
		StringBuffer delSbf = new StringBuffer();
		if(null != delID && !"".equals(delID)){
			delIDList = delID.split(",");
			for(int j = 0; j < delIDList.length; j++){
				delSbf.append(xt_userinfo_id+xt_menuinfo_id+delIDList[j].split("@")[0]+"1"+delIDList[j].split("@")[1]);
			}
		}
		condition.put("xt_menuinfo_id", xt_menuinfo_id);
		condition.put("Xt_data_authorityType", 1);
		condition.put("xt_userinfo_id", xt_userinfo_id);
		List<Xt_Data_Authority> xt_Data_Authority_OldList = xt_Data_AuthorityService.getXtDataAuthorityListAllByCondition(condition);
		for(int j = 0; j < xt_Data_Authority_OldList.size(); j++){
			Xt_Data_Authority xt_Data_Authority_Old = xt_Data_Authority_OldList.get(j);
			sbf.append(xt_Data_Authority_Old.getXt_userinfo_id()+xt_Data_Authority_Old.getXt_menuinfo_id()+xt_Data_Authority_Old.getXt_functioninfo_id()+xt_Data_Authority_Old.getXt_data_authorityType()+xt_Data_Authority_Old.getXtUID());
		}
		if(null != id && !"".equals(id)){
			String[] idList = id.split(",");
			for(int j = 0; j < idList.length; j++){
				String[] idValue = idList[j].split("@");
				if(!idValue[0].equals("0")){
					Xt_Data_Authority xt_Data_Authority = new Xt_Data_Authority();
					xt_Data_Authority.setXt_data_authority_id(UUID.toUUID());
					xt_Data_Authority.setXt_data_authorityType("1");
					xt_Data_Authority.setXt_functioninfo_id(idValue[0]);
					xt_Data_Authority.setXt_menuinfo_id(xt_menuinfo_id);
					xt_Data_Authority.setXt_userinfo_id(xt_userinfo_id);
					xt_Data_Authority.setXtUID(idValue[1]);
					String str = xt_Data_Authority.getXt_userinfo_id()+xt_Data_Authority.getXt_menuinfo_id()+xt_Data_Authority.getXt_functioninfo_id()+xt_Data_Authority.getXt_data_authorityType()+xt_Data_Authority.getXtUID();
					//将新的数据添加进去
					if(sbf.toString().indexOf(str)<0){
						xt_Data_Authority_OldList.add(xt_Data_Authority);
					}
				}
			}
		}
		//将xt_Data_Authority_OldList需要删除的元素去除
		for(int j = 0; j < xt_Data_Authority_OldList.size(); j++){
			Xt_Data_Authority xt_Data_Authority_Old = xt_Data_Authority_OldList.get(j);
			String str = xt_Data_Authority_Old.getXt_userinfo_id()+xt_Data_Authority_Old.getXt_menuinfo_id()+xt_Data_Authority_Old.getXt_functioninfo_id()+xt_Data_Authority_Old.getXt_data_authorityType()+xt_Data_Authority_Old.getXtUID();
			if(delSbf.toString().indexOf(str)>=0){
				xt_Data_Authority_OldList.remove(j);
			}
		}
		if(null != xt_Data_Authority_OldList && !"".equals(xt_Data_Authority_OldList)){
			i=xt_Data_AuthorityService.addXtDataAuthority(xt_Data_Authority_OldList,xt_userinfo_id,id,xt_menuinfo_id,"1");
		}
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
	public String getUserinfoListByCondition(String type,String id,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		if(null != type && !"".equals(type)){
			type = URLDecoder.decode(type, "UTF-8");
			condition.put("id", id);
			condition.put("type", type);
		}
		List<Xt_Userinfo>XtUserinfoList = xt_UserinfoService.getXtUserinfoListByCondition(condition);
		PageInfo<Xt_Userinfo> page = new PageInfo<Xt_Userinfo>(XtUserinfoList);
		return outPageStr(page,request);
	}
	
	/**
	 * 读取被拥有者及功能及已被设置的功能树列表
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/getUserinfoAndFunctionInfoAndDataAuthorityTreeGrid",method={RequestMethod.POST,RequestMethod.GET})
	public String getUserinfoAndFunctionInfoAndDataAuthorityTreeGrid(HttpServletRequest request,String xt_menuinfo_id,String xt_userinfo_id){
		Map<String, Object> condition = new HashMap<String, Object>();
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<Xt_Userinfo>XtUserinfoList = xt_UserinfoService.getXtUserinfoListAllByCondition(condition);
		condition.put("xt_menuinfo_id", xt_menuinfo_id);
		condition.put("xt_functioninfoIsAuthority", 1);
		condition.put("xt_userinfo_id", xt_userinfo_id);
		List<Xt_Data_Authority> xt_Data_AuthorityList = xt_Data_AuthorityService.getXtDataAuthorityListAllByCondition(condition);
		//定义一个拥有者编号+被拥有者编号+功能编号组合字段用来判断选择
		StringBuffer uf= new StringBuffer();
		for(int j = 0; j < xt_Data_AuthorityList.size();j++){
			Xt_Data_Authority xt_Data_Authority = xt_Data_AuthorityList.get(j);
			uf.append("|"+xt_Data_Authority.getXtUID()+"|"+xt_Data_Authority.getXt_functioninfo_id()+"|");
		}
		for(int i = 0; i < XtUserinfoList.size(); i++){
			Xt_Userinfo XtUserinfo = XtUserinfoList.get(i);
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
		List<Xt_Functioninfo> xtFunctioninfoList = xt_FunctioninfoService.getXtFunctioninfoAllForData(condition);
		for(int j = 0; j < XtUserinfoList.size(); j++){
			Xt_Userinfo XtUserinfo = XtUserinfoList.get(j);
			for(int i = 0; i < xtFunctioninfoList.size(); i++){
				Xt_Functioninfo xtFunctioninfo = xtFunctioninfoList.get(i);
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
		List<Xt_Departinfo> xtDepartinfoList = xt_DepartinfoService.getXtDepartinfoListAll(condition);
		for(int i = 0; i < xtDepartinfoList.size(); i++){
			Xt_Departinfo xtDepartinfo = xtDepartinfoList.get(i);
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
			List<Xt_Departinfo> xtDepartinfoList = xt_DepartinfoService.getXtDepartinfoList();
			for(int i = 0; i < xtDepartinfoList.size(); i++){
				Xt_Departinfo xtDepartinfo = xtDepartinfoList.get(i);
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
			List<Xt_Departinfo> xtDepartinfoList = xt_DepartinfoService.getXtDepartinfoListChild(condition);
			for(int i = 0; i < xtDepartinfoList.size(); i++){
				Xt_Departinfo xtDepartinfo = xtDepartinfoList.get(i);
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
		if(null != type && !"".equals(type) && "部门".equals(type)){
			condition.put("xt_departinfo_id", id);
			List<Xt_Post> xtPostList = xt_PostService.getXtPostinfoList(condition);
			//拼接字符串
			for(int i = 0; i < xtPostList.size(); i++){
				Xt_Post xtPost = xtPostList.get(i);
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
		}
		return jsonStr.toString();
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
}

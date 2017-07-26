package jehc.xtmodules.xtweb;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtmodel.XtDepartinfo;
import jehc.xtmodules.xtmodel.XtPost;
import jehc.xtmodules.xtservice.XtDepartinfoService;
import jehc.xtmodules.xtservice.XtPostService;

/**
 * 平台组织机构
 * @author邓纯杰
 *
 */
@Controller
@RequestMapping("/xtOrgController")
@Scope("prototype")
public class XtOrgController extends BaseAction{
	@Autowired
	private XtDepartinfoService xtDepartinfoService;
	@Autowired
	private XtPostService xtPostService;
	
	/**
	 * 载入组织机构页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtOrg",method={RequestMethod.POST,RequestMethod.GET})
	public String loadXtOrg(HttpServletRequest request){
		return "pc/xt-view/xt-org/xt-org-list";
	}
	
	
	/**
	 * 组织机构树列表
	 * @param id
	 * @param type
	 * @param request
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value="/getXtOrgTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtOrgTree(String id,String type,HttpServletRequest request) throws UnsupportedEncodingException{
		id = request.getParameter("node");
		type = URLDecoder.decode(type, "UTF-8");
		StringBuffer jsonStr = new StringBuffer("");  
		if(null != id && !"".equals(id) && "0".equals(id)){
			jsonStr.append("[");
			List<XtDepartinfo> xtDepartinfoList = xtDepartinfoService.getXtDepartinfoList();
			for(int i = 0; i < xtDepartinfoList.size(); i++){
				XtDepartinfo xtDepartinfo = xtDepartinfoList.get(i);
				if(i==(xtDepartinfoList.size()-1)){
					jsonStr.append("{id:'"+xtDepartinfo.getXt_departinfo_id()+"',name:'"+xtDepartinfo.getXt_departinfo_name()+"',icon:'../deng/images/icons/depart.png',xt_departinfo_parentId:'"+xtDepartinfo.getXt_departinfo_parentId()+"',type:'部门',remark:'"+xtDepartinfo.getXt_departinfo_desc()+"'}");  
				}else{
					jsonStr.append("{id:'"+xtDepartinfo.getXt_departinfo_id()+"',name:'"+xtDepartinfo.getXt_departinfo_name()+"',icon:'../deng/images/icons/depart.png',xt_departinfo_parentId:'"+xtDepartinfo.getXt_departinfo_parentId()+"',type:'部门',remark:'"+xtDepartinfo.getXt_departinfo_desc()+"'},");
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
		if(null != type && !"".equals(type) && "部门".equals(type)){
			condition = new HashMap<String, Object>();
			condition.put("xt_departinfo_id", id);
			List<XtPost> xtPostList = xtPostService.getXtPostinfoList(condition);
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
					jsonStr.append("{id:'"+xtPost.getXt_post_id()+"',leaf:"+leaf+",name:'"+xtPost.getXt_post_name()+"',icon:'../deng/images/icons/users.png',xt_post_parentId:'"+xtPost.getXt_post_parentId()+"',type:'岗位',remark:'"+xtPost.getXt_post_desc()+"',xt_departinfo_id:'"+xtPost.getXt_departinfo_id()+"',xt_departinfo_name:'"+xtPost.getXt_departinfo_name()+"'}");  
				}else{
					jsonStr.append("{id:'"+xtPost.getXt_post_id()+"',leaf:"+leaf+",name:'"+xtPost.getXt_post_name()+"',icon:'../deng/images/icons/users.png',xt_post_parentId:'"+xtPost.getXt_post_parentId()+"',type:'岗位',remark:'"+xtPost.getXt_post_desc()+"',xt_departinfo_id:'"+xtPost.getXt_departinfo_id()+"',xt_departinfo_name:'"+xtPost.getXt_departinfo_name()+"'},");
				}
			}
		}else if(null != type && !"".equals(type) && "岗位".equals(type)){
			condition = new HashMap<String, Object>();
			condition.put("xt_post_parentId", id);
			List<XtPost> xtPostList = xtPostService.getXtPostListChild(condition);
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
					jsonStr.append("{id:'"+xtPost.getXt_post_id()+"',leaf:"+leaf+",name:'"+xtPost.getXt_post_name()+"',icon:'../deng/images/icons/users.png',xt_post_parentId:'"+xtPost.getXt_post_parentId()+"',type:'岗位',remark:'"+xtPost.getXt_post_desc()+"',xt_departinfo_id:'"+xtPost.getXt_departinfo_id()+"',xt_departinfo_name:'"+xtPost.getXt_departinfo_name()+"'}");  
				}else{
					jsonStr.append("{id:'"+xtPost.getXt_post_id()+"',leaf:"+leaf+",name:'"+xtPost.getXt_post_name()+"',icon:'../deng/images/icons/users.png',xt_post_parentId:'"+xtPost.getXt_post_parentId()+"',type:'岗位',remark:'"+xtPost.getXt_post_desc()+"',xt_departinfo_id:'"+xtPost.getXt_departinfo_id()+"',xt_departinfo_name:'"+xtPost.getXt_departinfo_name()+"'},");
				}
			}
		}
		return jsonStr.toString();
	}
	
	/**
	 * 获取静态部门及岗位组成的树
	 * @param id
	 * @param type
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/getStaticDepartinfoAndPostTreeGrid",method={RequestMethod.POST,RequestMethod.GET})
	public String getStaticDepartinfoAndPostTreeGrid(String id,String type,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		List<XtDepartinfo> xtDepartinfoList = xtDepartinfoService.getXtDepartinfoListAll(condition);
		List<XtPost> xtPostList = xtPostService.getXtPostListAll(condition);
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		for(int i = 0; i < xtDepartinfoList.size(); i++){
			XtDepartinfo xtDepartinfo = xtDepartinfoList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(xtDepartinfo.getXt_departinfo_id());
			BaseTreeGridEntity.setPid(xtDepartinfo.getXt_departinfo_parentId());
			BaseTreeGridEntity.setText(xtDepartinfo.getXt_departinfo_name());
			BaseTreeGridEntity.setExpanded(false);
			BaseTreeGridEntity.setSingleClickExpand(true);
			BaseTreeGridEntity.setTempObject("部门");
			BaseTreeGridEntity.setContent("");
			BaseTreeGridEntity.setIntegerappend(xtDepartinfo.getXt_departinfo_id()+"@"+xtDepartinfo.getXt_departinfo_name());
			BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
			list.add(BaseTreeGridEntity);
			for(int j = 0; j < xtPostList.size(); j++){
				XtPost xtPost = xtPostList.get(j);
				BaseTreeGridEntity = new BaseTreeGridEntity();
				BaseTreeGridEntity.setId(xtPost.getXt_post_id());
				BaseTreeGridEntity.setText(xtPost.getXt_post_name());
				BaseTreeGridEntity.setExpanded(false);
				BaseTreeGridEntity.setSingleClickExpand(true);
				BaseTreeGridEntity.setTempObject("岗位");
				BaseTreeGridEntity.setIcon("../deng/images/icons/target_point.png");
				BaseTreeGridEntity.setContent("");
				BaseTreeGridEntity.setIntegerappend(xtPost.getXt_departinfo_id()+"@"+xtPost.getXt_departinfo_name());
				if(xtPost.getXt_departinfo_id().equals(xtDepartinfo.getXt_departinfo_id()) && xtPost.getXt_post_parentId().equals("0")){
					BaseTreeGridEntity.setPid(xtPost.getXt_departinfo_id());
				}else if(xtPost.getXt_departinfo_id().equals(xtDepartinfo.getXt_departinfo_id()) && !xtPost.getXt_post_parentId().equals("0")){
					BaseTreeGridEntity.setPid(xtPost.getXt_post_parentId());
				}
				list.add(BaseTreeGridEntity);
			}
		}
		return outStr(BaseTreeGridEntity.buildTree(list,false));
	}
}

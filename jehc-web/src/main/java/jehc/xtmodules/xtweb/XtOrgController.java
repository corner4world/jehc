package jehc.xtmodules.xtweb;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.base.BaseZTreeEntity;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;
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
		Map<String, Object> condition = new HashMap<String, Object>();
		List<BaseZTreeEntity> list = new ArrayList<BaseZTreeEntity>();
		List<XtDepartinfo> xtDepartinfoList = xtDepartinfoService.getXtDepartinfoListAll(condition);
		for(int i = 0; i < xtDepartinfoList.size(); i++){
			XtDepartinfo xtDepartinfo = xtDepartinfoList.get(i);
			BaseZTreeEntity BaseZTreeEntity = new BaseZTreeEntity();
			BaseZTreeEntity.setId(xtDepartinfo.getXt_departinfo_id());
			BaseZTreeEntity.setPid(xtDepartinfo.getXt_departinfo_parentId());
			BaseZTreeEntity.setText(xtDepartinfo.getXt_departinfo_name());
			BaseZTreeEntity.setExpanded(true);
			BaseZTreeEntity.setSingleClickExpand(true);
			BaseZTreeEntity.setTempObject("DEPART");
			list.add(BaseZTreeEntity);
		}
		
		//根岗位遍历
		List<XtPost> xtPostList = xtPostService.getXtPostinfoList(condition);
		for(XtPost xtPost:xtPostList){
			BaseZTreeEntity BaseZTreeEntity = new BaseZTreeEntity();
			BaseZTreeEntity.setId(xtPost.getXt_post_id());
			BaseZTreeEntity.setPid(xtPost.getXt_departinfo_id());
			BaseZTreeEntity.setText(xtPost.getXt_post_name());
			BaseZTreeEntity.setExpanded(true);
			BaseZTreeEntity.setSingleClickExpand(true);
			BaseZTreeEntity.setTempObject("POST");
			list.add(BaseZTreeEntity);
		}
		
		//非根岗位
		xtPostList = xtPostService.getXtPostinfoUnRootList(condition);
		for(XtPost xtPost:xtPostList){
			BaseZTreeEntity BaseZTreeEntity = new BaseZTreeEntity();
			BaseZTreeEntity.setId(xtPost.getXt_post_id());
			BaseZTreeEntity.setPid(xtPost.getXt_post_parentId());
			BaseZTreeEntity.setText(xtPost.getXt_post_name());
			BaseZTreeEntity.setExpanded(true);
			BaseZTreeEntity.setSingleClickExpand(true);
			BaseZTreeEntity.setTempObject("POST");
			list.add(BaseZTreeEntity);
		}		
		return outStr(BaseZTreeEntity.buildTree(list,false));
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
	
	/**
	* 添加或编辑
	* @param xt_departinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateXtDepartinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String saveOrUpdateXtDepartinfo(XtDepartinfo xt_Departinfo,HttpServletRequest request){
		int i = 0;
		if(StringUtil.isEmpty(xt_Departinfo.getXt_departinfo_parentId())){
			xt_Departinfo.setXt_departinfo_parentId("0");
		}
		if(StringUtils.isEmpty(xt_Departinfo.getXt_departinfo_id())){
			xt_Departinfo.setXt_departinfo_id(UUID.toUUID());
			i=xtDepartinfoService.addXtDepartinfo(xt_Departinfo);
		}else{
			//编辑
			i=xtDepartinfoService.updateXtDepartinfo(xt_Departinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param id 
	* @param tempObject 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtOrg",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtOrg(String id,String tempObject,HttpServletRequest request){
		int i = 0;
		if(StringUtils.isEmpty(tempObject)){
			throw new ExceptionUtil("未能获取到tempObject");
		}
		if(StringUtils.isEmpty(id)){
			throw new ExceptionUtil("未能获取到id");
		}
		if(tempObject.equals("DEPART")){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_departinfo_id",id.split(","));
			i=xtDepartinfoService.delXtDepartinfo(condition);
		}else if(tempObject.equals("POST")){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_post_id",id.split(","));
			i=xtPostService.delXtPost(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 添加
	* @param xt_post 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateXtPost",method={RequestMethod.POST,RequestMethod.GET})
	public String saveOrUpdateXtPost(XtPost xt_Post,HttpServletRequest request){
		int i = 0;
		if(StringUtil.isEmpty(xt_Post.getXt_post_parentId())){
			xt_Post.setXt_post_parentId("0");
		}
		if(StringUtils.isEmpty(xt_Post.getXt_post_id())){
			//新增
			xt_Post.setXt_post_id(UUID.toUUID());
			i=xtPostService.addXtPost(xt_Post);
		}else{
			//编辑
			i=xtPostService.updateXtPost(xt_Post);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}

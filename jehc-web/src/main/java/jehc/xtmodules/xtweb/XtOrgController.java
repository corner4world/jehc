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
import jehc.xtmodules.xtcore.base.BaseZTreeEntity;
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
}

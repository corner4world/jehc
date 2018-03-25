package jehc.xtmodules.xtweb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseBTreeGridEntity;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.base.BaseTreeEntity;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.base.BaseZTreeEntity;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.XtFunctioninfo;
import jehc.xtmodules.xtmodel.XtMenuinfo;
import jehc.xtmodules.xtservice.XtFunctioninfoService;
import jehc.xtmodules.xtservice.XtMenuinfoService;
/**
 * 系统菜单表
 * 
 * @author邓纯杰
 * 
 */
@Controller
@RequestMapping("/xtMenuinfoController")
public class XtMenuinfoController extends BaseAction {
	@Autowired
	private XtFunctioninfoService xtFunctioninfoService;
	@Autowired
	private XtMenuinfoService xtMenuinfoService;
	
	/**
	 * 载入初始化页面
	 * @param xt_Menuinfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtMenuinfo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtMenuinfo(XtMenuinfo xt_Menuinfo,HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-menuinfo/xt-menuinfo-list");
	}
	
	/**
	 * 加载初始化列表数据并分页
	 * @param xt_Menuinfo
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtMenuinfoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMenuinfoListByCondition(BaseSearch baseSearch,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtMenuinfo> xt_MenuinfoList = xtMenuinfoService.getXtMenuinfoListByCondition(condition);
		PageInfo<XtMenuinfo> page = new PageInfo<XtMenuinfo>(xt_MenuinfoList);
		return outPageStr(page,request);
	}
	
	/**
	 * 读取所有菜单列表
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtMenuinfoList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMenuinfoList(HttpServletRequest request, HttpServletResponse response){
		//废弃Extjs操作模式
		//1获取所有菜单
//		Map<String, Object> condition = new HashMap<String, Object>();
//		String expanded = request.getParameter("expanded");
//		String singleClickExpand = request.getParameter("singleClickExpand");
//		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
//		List<XtFunctioninfo> xtFunctioninfoList = xtFunctioninfoService.getXtFunctioninfoList(condition);
//		List<XtMenuinfo> xtMenuinfoList = xtMenuinfoService.getXtMenuinfoListAll(condition);
//		for(int j = 0; j < xtMenuinfoList.size(); j++){
//			XtMenuinfo xtMenuinfo = xtMenuinfoList.get(j);
//			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
//			BaseTreeGridEntity.setId(xtMenuinfo.getXt_menuinfo_id());
//			BaseTreeGridEntity.setPid(xtMenuinfo.getXt_menuinfo_parentId());
//			BaseTreeGridEntity.setText(xtMenuinfo.getXt_menuinfo_title());
//			BaseTreeGridEntity.setContent("");
//			if("0".equals(xtMenuinfo.getXt_menuinfo_leaf())){
//				BaseTreeGridEntity.setLeaf(false);
//			}else{
//				BaseTreeGridEntity.setLeaf(true);
//				//当菜单为末级时判断是否存在功能
//				if(hasLeaf(xtFunctioninfoList, xtMenuinfo.getXt_menuinfo_id())){
//					BaseTreeGridEntity.setLeaf(false);
//				}else{
//					BaseTreeGridEntity.setLeaf(true);
//				}
//			}
//			BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
//			BaseTreeGridEntity.setTempObject("菜单");
//			if(("true").equals(expanded)){
//				BaseTreeGridEntity.setExpanded(true);
//			}else{
//				BaseTreeGridEntity.setExpanded(false);
//			}
//			if("true".equals(singleClickExpand)){
//				BaseTreeGridEntity.setSingleClickExpand(true);
//			}else{
//				BaseTreeGridEntity.setSingleClickExpand(false);
//			}
//			list.add(BaseTreeGridEntity);
//		}
//		for(int i = 0; i < xtFunctioninfoList.size(); i++){
//			XtFunctioninfo xtFunctioninfo = xtFunctioninfoList.get(i);
//			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
//			BaseTreeGridEntity.setId(xtFunctioninfo.getXt_functioninfo_id());
//			BaseTreeGridEntity.setPid(xtFunctioninfo.getXt_menuinfo_id());
//			BaseTreeGridEntity.setText(xtFunctioninfo.getXt_functioninfoTitle());
//			BaseTreeGridEntity.setIcon("../deng/images/icons/target_point.png");
//			BaseTreeGridEntity.setTempObject("功能");
//			BaseTreeGridEntity.setContent(""+xtFunctioninfo.getXt_functioninfoTitle());
//			BaseTreeGridEntity.setIntegerappend(xtFunctioninfo.getXt_functioninfoIsAuthority()+","+xtFunctioninfo.getXt_functioninfoType());
//			if(("true").equals(expanded)){
//				BaseTreeGridEntity.setExpanded(true);
//			}else{
//				BaseTreeGridEntity.setExpanded(false);
//			}
//			if("true".equals(singleClickExpand)){
//				BaseTreeGridEntity.setSingleClickExpand(true);
//			}else{
//				BaseTreeGridEntity.setSingleClickExpand(false);
//			}
//			BaseTreeGridEntity.setLeaf(true);
//			list.add(BaseTreeGridEntity);
//		}
//		return outStr(BaseTreeGridEntity.buildTree(list,false));
		
		
		//采用BTree操作模式
		Map<String, Object> condition = new HashMap<String, Object>();
		List<BaseBTreeGridEntity> list = new ArrayList<BaseBTreeGridEntity>();
		List<XtFunctioninfo> xtFunctioninfoList = xtFunctioninfoService.getXtFunctioninfoList(condition);
		List<XtMenuinfo> xtMenuinfoList = xtMenuinfoService.getXtMenuinfoListAll(condition);
		for(int j = 0; j < xtMenuinfoList.size(); j++){
			XtMenuinfo xtMenuinfo = xtMenuinfoList.get(j);
			BaseBTreeGridEntity BaseBTreeGridEntity = new BaseBTreeGridEntity();
			BaseBTreeGridEntity.setId(xtMenuinfo.getXt_menuinfo_id());
			BaseBTreeGridEntity.setPid(xtMenuinfo.getXt_menuinfo_parentId());
			BaseBTreeGridEntity.setText(xtMenuinfo.getXt_menuinfo_title());
			BaseBTreeGridEntity.setTempObject("菜单");
			list.add(BaseBTreeGridEntity);
		}
		for(int i = 0; i < xtFunctioninfoList.size(); i++){
			XtFunctioninfo xtFunctioninfo = xtFunctioninfoList.get(i);
			BaseBTreeGridEntity BaseBTreeGridEntity = new BaseBTreeGridEntity();
			BaseBTreeGridEntity.setId(xtFunctioninfo.getXt_functioninfo_id());
			BaseBTreeGridEntity.setPid(xtFunctioninfo.getXt_menuinfo_id());
			BaseBTreeGridEntity.setText(xtFunctioninfo.getXt_functioninfoTitle());
			BaseBTreeGridEntity.setIcon("../deng/images/icons/target_point.png");
			BaseBTreeGridEntity.setTempObject("功能");
			BaseBTreeGridEntity.setContent(""+xtFunctioninfo.getXt_functioninfoTitle());
			BaseBTreeGridEntity.setIntegerappend(xtFunctioninfo.getXt_functioninfoIsAuthority()+","+xtFunctioninfo.getXt_functioninfoType());
			list.add(BaseBTreeGridEntity);
		}
		return outStr(BaseBTreeGridEntity.buildTreeF(list));
	}
	
	/**
	 * 添加菜单
	 * @param xt_Menuinfo
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/addXtMenuinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtMenuinfo(XtMenuinfo xt_Menuinfo,HttpServletRequest request, HttpServletResponse response){
		int i = 0;
		if(null != xt_Menuinfo){
			xt_Menuinfo.setXt_menuinfo_id(UUID.toUUID());
			i = xtMenuinfoService.addXtMenuinfo(xt_Menuinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 修改菜单
	 * @param xt_Menuinfo
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/updateXtMenuinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtMenuinfo(XtMenuinfo xt_Menuinfo,HttpServletRequest request, HttpServletResponse response){
		int i = 0;
		if(null != xt_Menuinfo){
			i = xtMenuinfoService.updateXtMenuinfo(xt_Menuinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 删除
	 * @param xt_menuinfo_id
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/delXtMenuinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtMenuinfo(String xt_menuinfo_id,HttpServletRequest request, HttpServletResponse response){
		int i = 0;
		if(null != xt_menuinfo_id){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			i = xtMenuinfoService.delXtMenuinfo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	 * 获取对象
	 * @param xt_menuinfo_id
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtMenuinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMenuinfo(String xt_menuinfo_id,HttpServletRequest request, HttpServletResponse response){
		XtMenuinfo xt_Menuinfo = xtMenuinfoService.getXtMenuinfo(xt_menuinfo_id);
		return outDataStr(xt_Menuinfo);
	}
	
	/**
	 * 读取所有菜单列表
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtMenuinfoTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMenuinfoTree(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> condition = new HashMap<String, Object>();
		String expanded = request.getParameter("expanded");
		String singleClickExpand = request.getParameter("singleClickExpand");
		List<BaseTreeEntity> list = new ArrayList<BaseTreeEntity>();
		List<XtMenuinfo> xtMenuinfoList = xtMenuinfoService.getXtMenuinfoListAll(condition);
		for(int i = 0; i < xtMenuinfoList.size(); i++){
			XtMenuinfo xtMenuinfo = xtMenuinfoList.get(i);
			BaseTreeEntity baseTreeEntity = new BaseTreeEntity();
			baseTreeEntity.setId(xtMenuinfo.getXt_menuinfo_id());
			baseTreeEntity.setPid(xtMenuinfo.getXt_menuinfo_parentId());
			baseTreeEntity.setText(xtMenuinfo.getXt_menuinfo_title());
			baseTreeEntity.setIcon("../deng/images/icons/"+xtMenuinfo.getXt_menuinfo_images());
			if("0".equals(xtMenuinfo.getXt_menuinfo_leaf())){
				baseTreeEntity.setLeaf(false);
			}else{
				baseTreeEntity.setLeaf(true);
			}
			if(("true").equals(expanded)){
				baseTreeEntity.setExpanded(true);
			}else{
				baseTreeEntity.setExpanded(false);
			}
			if("true".equals(singleClickExpand)){
				baseTreeEntity.setSingleClickExpand(true);
			}else{
				baseTreeEntity.setSingleClickExpand(false);
			}
			list.add(baseTreeEntity);
		}
		return outStr(BaseTreeEntity.buildTree(list));
	}
	
	/**
	 * 读取所有菜单列表（Bztree风格）
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtMenuinfoBZTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtMenuinfoBZTree(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> condition = new HashMap<String, Object>();
		String expanded = request.getParameter("expanded");
		String singleClickExpand = request.getParameter("singleClickExpand");
		List<BaseZTreeEntity> list = new ArrayList<BaseZTreeEntity>();
		List<XtMenuinfo> xtMenuinfoList = xtMenuinfoService.getXtMenuinfoListAll(condition);
		for(int i = 0; i < xtMenuinfoList.size(); i++){
			XtMenuinfo xtMenuinfo = xtMenuinfoList.get(i);
			BaseZTreeEntity BaseZTreeEntity = new BaseZTreeEntity();
			BaseZTreeEntity.setId(xtMenuinfo.getXt_menuinfo_id());
			BaseZTreeEntity.setPid(xtMenuinfo.getXt_menuinfo_parentId());
			BaseZTreeEntity.setText(xtMenuinfo.getXt_menuinfo_title());
			BaseZTreeEntity.setIcon("../deng/images/icons/target_point.png");
			if("0".equals(xtMenuinfo.getXt_menuinfo_leaf())){
				BaseZTreeEntity.setLeaf(false);
			}else{
				BaseZTreeEntity.setLeaf(true);
			}
			if(("true").equals(expanded)){
				BaseZTreeEntity.setExpanded(true);
			}else{
				BaseZTreeEntity.setExpanded(false);
			}
			if("true".equals(singleClickExpand)){
				BaseZTreeEntity.setSingleClickExpand(true);
			}else{
				BaseZTreeEntity.setSingleClickExpand(false);
			}
			list.add(BaseZTreeEntity);
		}
		return outStr(BaseZTreeEntity.buildTree(list,false));
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
	* 复制一行并生成记录
	* @param xt_menuinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtMenuinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtMenuinfo(String xt_menuinfo_id,HttpServletRequest request){
		int i = 0;
		XtMenuinfo xt_Menuinfo = xtMenuinfoService.getXtMenuinfo(xt_menuinfo_id);
		if(null != xt_Menuinfo && !"".equals(xt_Menuinfo)){
			xt_Menuinfo.setXt_menuinfo_id(UUID.toUUID());
			i=xtMenuinfoService.addXtMenuinfo(xt_Menuinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 获取平台菜单图片
	* @param request 
	*/
	@RequestMapping(value="/getImgList",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getImgList(HttpServletRequest request,String xt_menuinfo_images){
		List<String> list = new ArrayList<String>();
		String path =  request.getSession().getServletContext().getRealPath("/")+"deng/images/icons";
		File file = new File(path);   
        File[] array = file.listFiles();   
        for(int i=0;i<array.length;i++){   
	        if(array[i].isFile()){  
	        	list.add(array[i].getName());
	        } 
        }
        request.setAttribute("imgList", list);
        request.setAttribute("xt_menuinfo_images", xt_menuinfo_images);
        return new ModelAndView("pc/xt-view/xt-menuinfo/xt-menuinfo-img-select");
	}
}

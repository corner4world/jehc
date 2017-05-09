package jehc.xtmodules.xtweb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.Xt_Functioninfo;
import jehc.xtmodules.xtmodel.Xt_Menuinfo;
import jehc.xtmodules.xtservice.Xt_FunctioninfoService;
import jehc.xtmodules.xtservice.Xt_MenuinfoService;

/**
* 功能表 
* 2015-06-01 20:41:56  邓纯杰
*/
@Controller
@RequestMapping("/xtFunctioninfoController")
public class Xt_FunctioninfoController extends BaseAction{
	@Autowired
	private Xt_FunctioninfoService xt_FunctioninfoService;
	
	@Autowired
	private Xt_MenuinfoService xt_MenuinfoService;
	/**
	* 载入初始化页面
	* @param xt_functioninfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtFunctioninfo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtFunctioninfo(Xt_Functioninfo xt_Functioninfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-functioninfo/xt-functioninfo-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_functioninfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtFunctioninfoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFunctioninfoListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Functioninfo>XtFunctioninfoList = xt_FunctioninfoService.getXtFunctioninfoListByCondition(condition);
		PageInfo<Xt_Functioninfo> page = new PageInfo<Xt_Functioninfo>(XtFunctioninfoList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_functioninfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtFunctioninfoById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFunctioninfoById(String xt_functioninfo_id,HttpServletRequest request){
		Xt_Functioninfo xt_Functioninfo = xt_FunctioninfoService.getXtFunctioninfoById(xt_functioninfo_id);
		return outDataStr(xt_Functioninfo);
	}
	/**
	* 添加
	* @param xt_functioninfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtFunctioninfo",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtFunctioninfo(Xt_Functioninfo xt_Functioninfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Functioninfo && !"".equals(xt_Functioninfo)){
			xt_Functioninfo.setXt_functioninfo_id(UUID.toUUID());
			i=xt_FunctioninfoService.addXtFunctioninfo(xt_Functioninfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_functioninfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtFunctioninfo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtFunctioninfo(Xt_Functioninfo xt_Functioninfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Functioninfo && !"".equals(xt_Functioninfo)){
			i=xt_FunctioninfoService.updateXtFunctioninfo(xt_Functioninfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_functioninfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtFunctioninfo",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtFunctioninfo(String xt_functioninfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_functioninfo_id && !"".equals(xt_functioninfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_functioninfo_id",xt_functioninfo_id);
			i=xt_FunctioninfoService.delXtFunctioninfo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 加载所有
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtFunctioninfoList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtFunctioninfoList(HttpServletRequest request){
		//1获取所有菜单
		Map<String, Object> condition = new HashMap<String, Object>();
		String expanded = request.getParameter("expanded");
		String singleClickExpand = request.getParameter("singleClickExpand");
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<Xt_Functioninfo> xtFunctioninfoList = xt_FunctioninfoService.getXtFunctioninfoList(condition);
		List<Xt_Menuinfo> xtMenuinfoList = xt_MenuinfoService.getXtMenuinfoListAll(condition);
		for(int j = 0; j < xtMenuinfoList.size(); j++){
			Xt_Menuinfo xtMenuinfo = xtMenuinfoList.get(j);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(xtMenuinfo.getXt_menuinfo_id());
			BaseTreeGridEntity.setPid(xtMenuinfo.getXt_menuinfo_parentId());
			BaseTreeGridEntity.setText(xtMenuinfo.getXt_menuinfo_title());
			BaseTreeGridEntity.setContent("");
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
			BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
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
		for(int i = 0; i < xtFunctioninfoList.size(); i++){
			Xt_Functioninfo xtFunctioninfo = xtFunctioninfoList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(xtFunctioninfo.getXt_functioninfo_id());
			BaseTreeGridEntity.setPid(xtFunctioninfo.getXt_menuinfo_id());
			BaseTreeGridEntity.setText(xtFunctioninfo.getXt_functioninfoTitle());
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
			BaseTreeGridEntity.setLeaf(true);
			list.add(BaseTreeGridEntity);
		}
		return outStr(BaseTreeGridEntity.buildTree(list,false));
	}
	
	/**
	 * 判断菜单下面是否有功能
	 * @param xtFunctioninfoList
	 * @param xt_menuinfo_id
	 * @return
	 */
	public boolean hasLeaf(List<Xt_Functioninfo> xtFunctioninfoList,String xt_menuinfo_id){
		boolean flag = true;
		for(int i = 0; i < xtFunctioninfoList.size(); i++){
			if(xtFunctioninfoList.get(i).getXt_menuinfo_id().equals(xt_menuinfo_id)){
				return true;
			}
		}
		flag = false;
		return flag;
	}
}

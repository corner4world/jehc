package jehc.xtmodules.xtweb;
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
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtDepartinfo;
import jehc.xtmodules.xtservice.XtDepartinfoService;

/**
* 部门信息表(departInfo) 
* 2015-05-13 15:46:38  邓纯杰
*/
@Controller
@RequestMapping("/xtDepartinfoController")
public class XtDepartinfoController extends BaseAction{
	@Autowired
	private XtDepartinfoService xtDepartinfoService;
	/**
	* 载入初始化页面
	* @param xt_departinfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDepartinfo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDepartinfo(XtDepartinfo xt_Departinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-departinfo/xt-departinfo-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_departinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDepartinfoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDepartinfoListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<XtDepartinfo>XtDepartinfoList = xtDepartinfoService.getXtDepartinfoListByCondition(condition);
		PageInfo<XtDepartinfo> page = new PageInfo<XtDepartinfo>(XtDepartinfoList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_departinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDepartinfoById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDepartinfoById(String xt_departinfo_id,HttpServletRequest request){
		XtDepartinfo xt_Departinfo = xtDepartinfoService.getXtDepartinfoById(xt_departinfo_id);
		return outDataStr(xt_Departinfo);
	}
	/**
	* 添加
	* @param xt_departinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtDepartinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDepartinfo(XtDepartinfo xt_Departinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Departinfo && !"".equals(xt_Departinfo)){
			xt_Departinfo.setXt_departinfo_id(UUID.toUUID());
			i=xtDepartinfoService.addXtDepartinfo(xt_Departinfo);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_departinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtDepartinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtDepartinfo(XtDepartinfo xt_Departinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Departinfo && !"".equals(xt_Departinfo)){
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
	* @param xt_departinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtDepartinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDepartinfo(String xt_departinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_departinfo_id && !"".equals(xt_departinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_departinfo_id",xt_departinfo_id.split(","));
			i=xtDepartinfoService.delXtDepartinfo(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	
	/**
	 * 读取部门树
	 * @param xt_departinfo_id
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/getXtDepartinfoTree",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDepartinfoTree(String id,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<XtDepartinfo> xtDepartinfoList = xtDepartinfoService.getXtDepartinfoListAll(condition);
		for(int i = 0; i < xtDepartinfoList.size(); i++){
			XtDepartinfo xtDepartinfo = xtDepartinfoList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(xtDepartinfo.getXt_departinfo_id());
			BaseTreeGridEntity.setPid(xtDepartinfo.getXt_departinfo_parentId());
			BaseTreeGridEntity.setText(xtDepartinfo.getXt_departinfo_name());
			BaseTreeGridEntity.setExpanded(true);
			BaseTreeGridEntity.setSingleClickExpand(true);
			BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
			list.add(BaseTreeGridEntity);
		}
		return outStr(BaseTreeGridEntity.buildTree(list,false));
	}
	
	/**
	* 复制一行并生成记录
	* @param xt_departinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtDepartinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtDepartinfo(String xt_departinfo_id,HttpServletRequest request){
		int i = 0;
		XtDepartinfo xt_Departinfo = xtDepartinfoService.getXtDepartinfoById(xt_departinfo_id);
		if(null != xt_Departinfo && !"".equals(xt_Departinfo)){
			xt_Departinfo.setXt_departinfo_id(UUID.toUUID());
			i=xtDepartinfoService.addXtDepartinfo(xt_Departinfo);
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
	@RequestMapping(value="/exportXtDepartinfo",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtDepartinfo(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 根据各种情况查找集合不分页（流程设计器中处理组 发起组等使用）
	 * @param xt_departinfo_id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryXtDepartinfoList",method={RequestMethod.POST,RequestMethod.GET})
	public String queryXtDepartinfoList(String xt_departinfo_id,HttpServletRequest request){
		List<XtDepartinfo> list = new ArrayList<XtDepartinfo>();
		if(null != xt_departinfo_id && !"".equals(xt_departinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_departinfo_id", xt_departinfo_id.split(","));
			list = xtDepartinfoService.queryXtDepartinfoList(condition);
		}
		return  outItemsStr(list);
	}
}

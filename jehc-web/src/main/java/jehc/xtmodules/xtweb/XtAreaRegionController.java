package jehc.xtmodules.xtweb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.annotation.AuthUneedLogin;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtAreaRegion;
import jehc.xtmodules.xtservice.XtAreaRegionService;

/**
* 行政区划表 
* 2017-05-04 14:54:34  邓纯杰
*/
@Controller
@RequestMapping("/xtAreaRegionController")
@Scope("prototype")
public class XtAreaRegionController extends BaseAction{
	@Autowired
	private XtAreaRegionService xtAreaRegionService;
	/**
	* 载入初始化页面
	* @param xt_area_region 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtAreaRegion",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtAreaRegion(XtAreaRegion xt_Area_Region,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-area-region/xt-area-region-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_area_region 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtAreaRegionListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtAreaRegionListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		String expanded = request.getParameter("expanded");
		String singleClickExpand = request.getParameter("singleClickExpand");
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<XtAreaRegion> xt_Area_RegionList = xtAreaRegionService.getXtAreaRegionListByCondition(condition);
		for(int i = 0; i < xt_Area_RegionList.size(); i++){
			XtAreaRegion xt_Area_Region = xt_Area_RegionList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(xt_Area_Region.getID());
			BaseTreeGridEntity.setPid(""+xt_Area_Region.getPARENT_ID());
			BaseTreeGridEntity.setText(xt_Area_Region.getNAME());
			BaseTreeGridEntity.setIcon("../deng/images/icons/target_point.png");
			BaseTreeGridEntity.setTempObject("行政编码："+xt_Area_Region.getCODE()+"<br>行政级别："+xt_Area_Region.getREGION_LEVEL());
			BaseTreeGridEntity.setContent(xt_Area_Region.getNAME_EN());
			BaseTreeGridEntity.setIntegerappend("经度："+xt_Area_Region.getLONGITUDE()+"<br>纬度："+xt_Area_Region.getLATITUDE());
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
	* 获取对象
	* @param ID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtAreaRegionById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtAreaRegionById(String ID,HttpServletRequest request){
		XtAreaRegion xt_Area_Region = xtAreaRegionService.getXtAreaRegionById(ID);
		return outDataStr(xt_Area_Region);
	}
	/**
	* 添加
	* @param xt_area_region 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtAreaRegion",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtAreaRegion(XtAreaRegion xt_Area_Region,HttpServletRequest request){
		int i = 0;
		if(null != xt_Area_Region && !"".equals(xt_Area_Region)){
			xt_Area_Region.setID(UUID.toUUID());
			i=xtAreaRegionService.addXtAreaRegion(xt_Area_Region);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_area_region 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtAreaRegion",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtAreaRegion(XtAreaRegion xt_Area_Region,HttpServletRequest request){
		int i = 0;
		if(null != xt_Area_Region && !"".equals(xt_Area_Region)){
			i=xtAreaRegionService.updateXtAreaRegion(xt_Area_Region);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param ID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtAreaRegion",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtAreaRegion(String ID,HttpServletRequest request){
		int i = 0;
		if(null != ID && !"".equals(ID)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("ID",ID.split(","));
			i=xtAreaRegionService.delXtAreaRegion(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param ID 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtAreaRegion",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtAreaRegion(String ID,HttpServletRequest request){
		int i = 0;
		XtAreaRegion xt_Area_Region = xtAreaRegionService.getXtAreaRegionById(ID);
		if(null != xt_Area_Region && !"".equals(xt_Area_Region)){
			xt_Area_Region.setID(UUID.toUUID());
			i=xtAreaRegionService.addXtAreaRegion(xt_Area_Region);
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
	@RequestMapping(value="/exportXtAreaRegion",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtAreaRegion(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 读取所有省份
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getPList",method={RequestMethod.POST,RequestMethod.GET})
	@AuthUneedLogin
	public String getPList(HttpServletRequest request){
		List<XtAreaRegion> list = getXtAreaRegionCache(null);
		return outItemsStr(list);
	}
	
	/**
	 * 读取城市
	 * @param code
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCList",method={RequestMethod.POST,RequestMethod.GET})
	@AuthUneedLogin
	public String getCList(String parentId,HttpServletRequest request){
		List<XtAreaRegion> list = new ArrayList<XtAreaRegion>();
		if(StringUtil.isEmpty(parentId)){
//			throw new ExceptionUtil("未能获取到省份编号");
			return outItemsStr(list);
		}
		list = getXtAreaRegionCache(parentId);
		return outItemsStr(list);
	}
	
	/**
	 * 读取区县
	 * @param code
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getDList",method={RequestMethod.POST,RequestMethod.GET})
	@AuthUneedLogin
	public String getDList(String parentId,HttpServletRequest request){
		List<XtAreaRegion> list = new ArrayList<XtAreaRegion>();
		if(StringUtil.isEmpty(parentId)){
//			throw new ExceptionUtil("未能获取到城市编号");
			return outItemsStr(list);
		}
		list = getXtAreaRegionCache(parentId);
		return outItemsStr(list);
	}
}

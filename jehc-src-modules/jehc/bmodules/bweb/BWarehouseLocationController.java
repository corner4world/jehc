package jehc.bmodules.bweb;
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

import jehc.bmodules.bmodel.BWarehouseLocation;
import jehc.bmodules.bservice.BWarehouseLocationService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础仓库库位 
* 2016-01-27 14:25:28  邓纯杰
*/
@Controller
@RequestMapping("/bWarehouseLocationController")
public class BWarehouseLocationController extends BaseAction{
	@Autowired
	private BWarehouseLocationService bWarehouseLocationService;
	/**
	* 载入初始化页面
	* @param b_warehouse_location 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBWarehouseLocation",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBWarehouseLocation(BWarehouseLocation b_Warehouse_Location,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-warehouse-location/b-warehouse-location-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_warehouse_location 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBWarehouseLocationListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBWarehouseLocationListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BWarehouseLocation> b_Warehouse_LocationList = bWarehouseLocationService.getBWarehouseLocationListByCondition(condition);
		PageInfo<BWarehouseLocation> page = new PageInfo<BWarehouseLocation>(b_Warehouse_LocationList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_warehouse_location_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBWarehouseLocationById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBWarehouseLocationById(String b_warehouse_location_id,HttpServletRequest request){
		BWarehouseLocation b_Warehouse_Location = bWarehouseLocationService.getBWarehouseLocationById(b_warehouse_location_id);
		return outDataStr(b_Warehouse_Location);
	}
	/**
	* 添加
	* @param b_warehouse_location 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBWarehouseLocation",method={RequestMethod.POST,RequestMethod.GET})
	public String addBWarehouseLocation(BWarehouseLocation b_Warehouse_Location,HttpServletRequest request){
		int i = 0;
		if(null != b_Warehouse_Location && !"".equals(b_Warehouse_Location)){
			b_Warehouse_Location.setB_warehouse_location_id(UUID.toUUID());
			i=bWarehouseLocationService.addBWarehouseLocation(b_Warehouse_Location);
		}
		if(i>0){
			outAudStr(true);
		}else{
			outAudStr(false);
		}
		return null;
	}
	/**
	* 修改
	* @param b_warehouse_location 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBWarehouseLocation",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBWarehouseLocation(BWarehouseLocation b_Warehouse_Location,HttpServletRequest request){
		int i = 0;
		if(null != b_Warehouse_Location && !"".equals(b_Warehouse_Location)){
			i=bWarehouseLocationService.updateBWarehouseLocation(b_Warehouse_Location);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_warehouse_location_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBWarehouseLocation",method={RequestMethod.POST,RequestMethod.GET})
	public String delBWarehouseLocation(String b_warehouse_location_id,HttpServletRequest request){
		int i = 0;
		if(null != b_warehouse_location_id && !"".equals(b_warehouse_location_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_warehouse_location_id",b_warehouse_location_id.split(","));
			i=bWarehouseLocationService.delBWarehouseLocation(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_warehouse_location_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBWarehouseLocation",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBWarehouseLocation(String b_warehouse_location_id,HttpServletRequest request){
		int i = 0;
		BWarehouseLocation b_Warehouse_Location = bWarehouseLocationService.getBWarehouseLocationById(b_warehouse_location_id);
		if(null != b_Warehouse_Location && !"".equals(b_Warehouse_Location)){
			b_Warehouse_Location.setB_warehouse_location_id(UUID.toUUID());
			i=bWarehouseLocationService.addBWarehouseLocation(b_Warehouse_Location);
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
	@RequestMapping(value="/exportBWarehouseLocation",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBWarehouseLocation(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

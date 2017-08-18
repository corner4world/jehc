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

import jehc.bmodules.bmodel.BWarehouse;
import jehc.bmodules.bservice.BWarehouseService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础仓库 
* 2016-01-27 14:21:55  邓纯杰
*/
@Controller
@RequestMapping("/bWarehouseController")
public class BWarehouseController extends BaseAction{
	@Autowired
	private BWarehouseService bWarehouseService;
	/**
	* 载入初始化页面
	* @param b_warehouse 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBWarehouse",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBWarehouse(BWarehouse b_Warehouse,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-warehouse/b-warehouse-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_warehouse 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBWarehouseListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBWarehouseListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BWarehouse> b_WarehouseList = bWarehouseService.getBWarehouseListByCondition(condition);
		PageInfo<BWarehouse> page = new PageInfo<BWarehouse>(b_WarehouseList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_warehouse_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBWarehouseById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBWarehouseById(String b_warehouse_id,HttpServletRequest request){
		BWarehouse b_Warehouse = bWarehouseService.getBWarehouseById(b_warehouse_id);
		return outDataStr(b_Warehouse);
	}
	/**
	* 添加
	* @param b_warehouse 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBWarehouse",method={RequestMethod.POST,RequestMethod.GET})
	public String addBWarehouse(BWarehouse b_Warehouse,HttpServletRequest request){
		int i = 0;
		if(null != b_Warehouse && !"".equals(b_Warehouse)){
			b_Warehouse.setB_warehouse_id(UUID.toUUID());
			b_Warehouse.setXt_userinfo_id(CommonUtils.getXtUid());
			b_Warehouse.setB_warehouse_ctime(CommonUtils.getSimpleDateFormat());
			i=bWarehouseService.addBWarehouse(b_Warehouse);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_warehouse 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBWarehouse",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBWarehouse(BWarehouse b_Warehouse,HttpServletRequest request){
		int i = 0;
		if(null != b_Warehouse && !"".equals(b_Warehouse)){
			b_Warehouse.setXt_userinfo_id(CommonUtils.getXtUid());
			b_Warehouse.setB_warehouse_mtime(CommonUtils.getSimpleDateFormat());
			i=bWarehouseService.updateBWarehouse(b_Warehouse);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_warehouse_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBWarehouse",method={RequestMethod.POST,RequestMethod.GET})
	public String delBWarehouse(String b_warehouse_id,HttpServletRequest request){
		int i = 0;
		if(null != b_warehouse_id && !"".equals(b_warehouse_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_warehouse_id",b_warehouse_id.split(","));
			i=bWarehouseService.delBWarehouse(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_warehouse_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBWarehouse",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBWarehouse(String b_warehouse_id,HttpServletRequest request){
		int i = 0;
		BWarehouse b_Warehouse = bWarehouseService.getBWarehouseById(b_warehouse_id);
		if(null != b_Warehouse && !"".equals(b_Warehouse)){
			b_Warehouse.setB_warehouse_id(UUID.toUUID());
			i=bWarehouseService.addBWarehouse(b_Warehouse);
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
	@RequestMapping(value="/exportBWarehouse",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBWarehouse(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

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

import jehc.bmodules.bmodel.BStock;
import jehc.bmodules.bservice.BStockService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础库存 
* 2016-01-27 14:28:46  邓纯杰
*/
@Controller
@RequestMapping("/bStockController")
public class BStockController extends BaseAction{
	@Autowired
	private BStockService bStockService;
	/**
	* 载入初始化页面
	* @param b_stock 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBStock",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBStock(BStock b_Stock,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-stock/b-stock-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_stock 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBStockListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBStockListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BStock> b_StockList = bStockService.getBStockListByCondition(condition);
		PageInfo<BStock> page = new PageInfo<BStock>(b_StockList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_stock_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBStockById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBStockById(String b_stock_id,HttpServletRequest request){
		BStock b_Stock = bStockService.getBStockById(b_stock_id);
		return outDataStr(b_Stock);
	}
	/**
	* 添加
	* @param b_stock 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBStock",method={RequestMethod.POST,RequestMethod.GET})
	public String addBStock(BStock b_Stock,HttpServletRequest request){
		int i = 0;
		if(null != b_Stock && !"".equals(b_Stock)){
			b_Stock.setB_stock_id(UUID.toUUID());
			i=bStockService.addBStock(b_Stock);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_stock 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBStock",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBStock(BStock b_Stock,HttpServletRequest request){
		int i = 0;
		if(null != b_Stock && !"".equals(b_Stock)){
			i=bStockService.updateBStock(b_Stock);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_stock_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBStock",method={RequestMethod.POST,RequestMethod.GET})
	public String delBStock(String b_stock_id,HttpServletRequest request){
		int i = 0;
		if(null != b_stock_id && !"".equals(b_stock_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_stock_id",b_stock_id.split(","));
			i=bStockService.delBStock(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_stock_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBStock",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBStock(String b_stock_id,HttpServletRequest request){
		int i = 0;
		BStock b_Stock = bStockService.getBStockById(b_stock_id);
		if(null != b_Stock && !"".equals(b_Stock)){
			b_Stock.setB_stock_id(UUID.toUUID());
			i=bStockService.addBStock(b_Stock);
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
	@RequestMapping(value="/exportBStock",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBStock(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

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

import jehc.bmodules.bmodel.BInvoice;
import jehc.bmodules.bservice.BInvoiceService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础发票 
* 2016-02-22 14:35:28  邓纯杰
*/
@Controller
@RequestMapping("/bInvoiceController")
public class BInvoiceController extends BaseAction{
	@Autowired
	private BInvoiceService bInvoiceService;
	/**
	* 载入初始化页面
	* @param b_invoice 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBInvoice",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBInvoice(String b_member_id,BInvoice b_Invoice,HttpServletRequest request){
		request.setAttribute("b_member_id", b_member_id);
		return new ModelAndView("pc/b-view/b-invoice/b-invoice-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_invoice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBInvoiceListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBInvoiceListByCondition(String b_member_id,BInvoice b_Invoice,BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		condition.put("b_member_id", b_member_id);
		commonHPager(condition,request);
		List<BInvoice> b_InvoiceList = bInvoiceService.getBInvoiceListByCondition(condition);
		PageInfo<BInvoice> page = new PageInfo<BInvoice>(b_InvoiceList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_invoice_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBInvoiceById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBInvoiceById(String b_invoice_id,HttpServletRequest request){
		BInvoice b_Invoice = bInvoiceService.getBInvoiceById(b_invoice_id);
		return outDataStr(b_Invoice);
	}
	/**
	* 添加
	* @param b_invoice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBInvoice",method={RequestMethod.POST,RequestMethod.GET})
	public String addBInvoice(BInvoice b_Invoice,HttpServletRequest request){
		int i = 0;
		if(null != b_Invoice && !"".equals(b_Invoice)){
			b_Invoice.setB_invoice_id(UUID.toUUID());
			b_Invoice.setB_invoice_ctime(CommonUtils.getSimpleDateFormat());
			i=bInvoiceService.addBInvoice(b_Invoice);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_invoice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBInvoice",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBInvoice(BInvoice b_Invoice,HttpServletRequest request){
		int i = 0;
		if(null != b_Invoice && !"".equals(b_Invoice)){
			b_Invoice.setB_invoice_mtime(CommonUtils.getSimpleDateFormat());
			i=bInvoiceService.updateBInvoice(b_Invoice);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_invoice_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBInvoice",method={RequestMethod.POST,RequestMethod.GET})
	public String delBInvoice(String b_invoice_id,HttpServletRequest request){
		int i = 0;
		if(null != b_invoice_id && !"".equals(b_invoice_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_invoice_id",b_invoice_id.split(","));
			i=bInvoiceService.delBInvoice(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_invoice_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBInvoice",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBInvoice(String b_invoice_id,HttpServletRequest request){
		int i = 0;
		BInvoice b_Invoice = bInvoiceService.getBInvoiceById(b_invoice_id);
		if(null != b_Invoice && !"".equals(b_Invoice)){
			b_Invoice.setB_invoice_id(UUID.toUUID());
			i=bInvoiceService.addBInvoice(b_Invoice);
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
	@RequestMapping(value="/exportBInvoice",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBInvoice(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}

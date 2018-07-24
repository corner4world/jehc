package jehc.paymentmodules.paymentweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
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
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.paymentmodules.paymentmodel.PaymentTransfer;
import jehc.paymentmodules.paymentservice.PaymentTransferService;

/**
* 转账 
* 2018-07-24 15:31:30  邓纯杰
*/
@Controller
@RequestMapping("/paymentTransferController")
public class PaymentTransferController extends BaseAction{
	@Autowired
	private PaymentTransferService paymentTransferService;
	/**
	* 载入初始化页面
	* @param payment_transfer 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadPaymentTransfer",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadPaymentTransfer(PaymentTransfer paymentTransfer,HttpServletRequest request){
		return new ModelAndView("pc/payment-view/payment-transfer/payment-transfer-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param payment_transfer 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getPaymentTransferListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getPaymentTransferListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<PaymentTransfer> paymentTransferList = paymentTransferService.getPaymentTransferListByCondition(condition);
		PageInfo<PaymentTransfer> page = new PageInfo<PaymentTransfer>(paymentTransferList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getPaymentTransferById",method={RequestMethod.POST,RequestMethod.GET})
	public String getPaymentTransferById(String id,HttpServletRequest request){
		PaymentTransfer paymentTransfer = paymentTransferService.getPaymentTransferById(id);
		return outDataStr(paymentTransfer);
	}
	/**
	* 添加
	* @param payment_transfer 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addPaymentTransfer",method={RequestMethod.POST,RequestMethod.GET})
	public String addPaymentTransfer(PaymentTransfer paymentTransfer,HttpServletRequest request){
		int i = 0;
		if(null != paymentTransfer && !"".equals(paymentTransfer)){
			paymentTransfer.setId(UUID.toUUID());
			i=paymentTransferService.addPaymentTransfer(paymentTransfer);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param payment_transfer 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updatePaymentTransfer",method={RequestMethod.POST,RequestMethod.GET})
	public String updatePaymentTransfer(PaymentTransfer paymentTransfer,HttpServletRequest request){
		int i = 0;
		if(null != paymentTransfer && !"".equals(paymentTransfer)){
			i=paymentTransferService.updatePaymentTransfer(paymentTransfer);
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
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delPaymentTransfer",method={RequestMethod.POST,RequestMethod.GET})
	public String delPaymentTransfer(String id,HttpServletRequest request){
		int i = 0;
		if(null != id && !"".equals(id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id",id.split(","));
			i=paymentTransferService.delPaymentTransfer(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyPaymentTransfer",method={RequestMethod.POST,RequestMethod.GET})
	public String copyPaymentTransfer(String id,HttpServletRequest request){
		int i = 0;
		PaymentTransfer paymentTransfer = paymentTransferService.getPaymentTransferById(id);
		if(null != paymentTransfer && !"".equals(paymentTransfer)){
			paymentTransfer.setId(UUID.toUUID());
			i=paymentTransferService.addPaymentTransfer(paymentTransfer);
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
	@RequestMapping(value="/exportPaymentTransfer",method={RequestMethod.POST,RequestMethod.GET})
	public void exportPaymentTransfer(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentTransferAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentTransferAdd(PaymentTransfer paymentTransfer,HttpServletRequest request){
		return new ModelAndView("pc/payment-view/payment-transfer/payment-transfer-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentTransferUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentTransferUpdate(String id,HttpServletRequest request, Model model){
		PaymentTransfer paymentTransfer = paymentTransferService.getPaymentTransferById(id);
		model.addAttribute("paymentTransfer", paymentTransfer);
		return new ModelAndView("pc/payment-view/payment-transfer/payment-transfer-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentTransferDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentTransferDetail(String id,HttpServletRequest request, Model model){
		PaymentTransfer paymentTransfer = paymentTransferService.getPaymentTransferById(id);
		model.addAttribute("paymentTransfer", paymentTransfer);
		return new ModelAndView("pc/payment-view/payment-transfer/payment-transfer-detail");
	}
}

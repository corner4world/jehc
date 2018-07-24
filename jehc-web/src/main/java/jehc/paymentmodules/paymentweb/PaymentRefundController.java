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
import jehc.paymentmodules.paymentmodel.PaymentRefund;
import jehc.paymentmodules.paymentservice.PaymentRefundService;

/**
* 支付退款 
* 2018-07-24 14:51:42  邓纯杰
*/
@Controller
@RequestMapping("/paymentRefundController")
public class PaymentRefundController extends BaseAction{
	@Autowired
	private PaymentRefundService paymentRefundService;
	/**
	* 载入初始化页面
	* @param payment_refund 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadPaymentRefund",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadPaymentRefund(PaymentRefund paymentRefund,HttpServletRequest request){
		return new ModelAndView("pc/payment-view/payment-refund/payment-refund-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param payment_refund 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getPaymentRefundListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getPaymentRefundListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<PaymentRefund> paymentRefundList = paymentRefundService.getPaymentRefundListByCondition(condition);
		PageInfo<PaymentRefund> page = new PageInfo<PaymentRefund>(paymentRefundList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getPaymentRefundById",method={RequestMethod.POST,RequestMethod.GET})
	public String getPaymentRefundById(String id,HttpServletRequest request){
		PaymentRefund paymentRefund = paymentRefundService.getPaymentRefundById(id);
		return outDataStr(paymentRefund);
	}
	/**
	* 添加
	* @param payment_refund 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addPaymentRefund",method={RequestMethod.POST,RequestMethod.GET})
	public String addPaymentRefund(PaymentRefund paymentRefund,HttpServletRequest request){
		int i = 0;
		if(null != paymentRefund && !"".equals(paymentRefund)){
			paymentRefund.setId(UUID.toUUID());
			i=paymentRefundService.addPaymentRefund(paymentRefund);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param payment_refund 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updatePaymentRefund",method={RequestMethod.POST,RequestMethod.GET})
	public String updatePaymentRefund(PaymentRefund paymentRefund,HttpServletRequest request){
		int i = 0;
		if(null != paymentRefund && !"".equals(paymentRefund)){
			i=paymentRefundService.updatePaymentRefund(paymentRefund);
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
	@RequestMapping(value="/delPaymentRefund",method={RequestMethod.POST,RequestMethod.GET})
	public String delPaymentRefund(String id,HttpServletRequest request){
		int i = 0;
		if(null != id && !"".equals(id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id",id.split(","));
			i=paymentRefundService.delPaymentRefund(condition);
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
	@RequestMapping(value="/copyPaymentRefund",method={RequestMethod.POST,RequestMethod.GET})
	public String copyPaymentRefund(String id,HttpServletRequest request){
		int i = 0;
		PaymentRefund paymentRefund = paymentRefundService.getPaymentRefundById(id);
		if(null != paymentRefund && !"".equals(paymentRefund)){
			paymentRefund.setId(UUID.toUUID());
			i=paymentRefundService.addPaymentRefund(paymentRefund);
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
	@RequestMapping(value="/exportPaymentRefund",method={RequestMethod.POST,RequestMethod.GET})
	public void exportPaymentRefund(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentRefundAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentRefundAdd(PaymentRefund paymentRefund,HttpServletRequest request){
		return new ModelAndView("pc/payment-view/payment-refund/payment-refund-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentRefundUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentRefundUpdate(String id,HttpServletRequest request, Model model){
		PaymentRefund paymentRefund = paymentRefundService.getPaymentRefundById(id);
		model.addAttribute("paymentRefund", paymentRefund);
		return new ModelAndView("pc/payment-view/payment-refund/payment-refund-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentRefundDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentRefundDetail(String id,HttpServletRequest request, Model model){
		PaymentRefund paymentRefund = paymentRefundService.getPaymentRefundById(id);
		model.addAttribute("paymentRefund", paymentRefund);
		return new ModelAndView("pc/payment-view/payment-refund/payment-refund-detail");
	}
}

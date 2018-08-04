package jehc.paymodules.payweb;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.bmodules.bmodel.BInvoice;
import jehc.paymentmodules.paymentmodel.PaymentAccount;
import jehc.paymentmodules.paymentservice.PaymentAccountService;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;

@RestController
@RequestMapping("/index")
public class IndexController extends BaseAction{
	@Autowired
	private PaymentAccountService paymentAccountService;
	/**
	* 加载初始化列表数据并分页
	* @param b_invoice 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	public String getBInvoiceListByCondition(String b_member_id,BInvoice b_Invoice,BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<PaymentAccount> paymentAccountList = paymentAccountService.getPaymentAccountListByCondition(condition);
		PageInfo<PaymentAccount> page = new PageInfo<PaymentAccount>(paymentAccountList);
		return outPageBootStr(page,request);
	}
	
	/**
	* 去付款
	* @param id 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/goPay",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadPaymentAccount(String id,HttpServletRequest request){
		if(!StringUtil.isEmpty(id)){
			PaymentAccount payment = paymentAccountService.getPaymentAccountById(id);
			request.setAttribute("payment", payment);
		}
		return new ModelAndView("pc/payment/pay");
	}
}

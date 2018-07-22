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
import jehc.paymentmodules.paymentmodel.PaymentAccount;
import jehc.paymentmodules.paymentservice.PaymentAccountService;

/**
* 支付账号配置 
* 2018-07-21 14:33:58  邓纯杰
*/
@Controller
@RequestMapping("/paymentAccountController")
public class PaymentAccountController extends BaseAction{
	@Autowired
	private PaymentAccountService paymentAccountService;
	/**
	* 载入初始化页面
	* @param payment_account 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadPaymentAccount",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadPaymentAccount(PaymentAccount paymentAccount,HttpServletRequest request){
		return new ModelAndView("pc/payment-view/payment-account/payment-account-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param payment_account 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getPaymentAccountListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getPaymentAccountListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<PaymentAccount> paymentAccountList = paymentAccountService.getPaymentAccountListByCondition(condition);
		PageInfo<PaymentAccount> page = new PageInfo<PaymentAccount>(paymentAccountList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getPaymentAccountById",method={RequestMethod.POST,RequestMethod.GET})
	public String getPaymentAccountById(String id,HttpServletRequest request){
		PaymentAccount paymentAccount = paymentAccountService.getPaymentAccountById(id);
		return outDataStr(paymentAccount);
	}
	/**
	* 添加
	* @param payment_account 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addPaymentAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String addPaymentAccount(PaymentAccount paymentAccount,HttpServletRequest request){
		int i = 0;
		if(null != paymentAccount && !"".equals(paymentAccount)){
			paymentAccount.setId(UUID.toUUID());
			paymentAccount.setCreate_by(getXtUid());
			paymentAccount.setCreate_time(getDate());
			i=paymentAccountService.addPaymentAccount(paymentAccount);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param payment_account 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updatePaymentAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String updatePaymentAccount(PaymentAccount paymentAccount,HttpServletRequest request){
		int i = 0;
		if(null != paymentAccount && !"".equals(paymentAccount)){
			i=paymentAccountService.updatePaymentAccount(paymentAccount);
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
	@RequestMapping(value="/delPaymentAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String delPaymentAccount(String id,HttpServletRequest request){
		int i = 0;
		if(null != id && !"".equals(id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id",id.split(","));
			i=paymentAccountService.delPaymentAccount(condition);
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
	@RequestMapping(value="/copyPaymentAccount",method={RequestMethod.POST,RequestMethod.GET})
	public String copyPaymentAccount(String id,HttpServletRequest request){
		int i = 0;
		PaymentAccount paymentAccount = paymentAccountService.getPaymentAccountById(id);
		if(null != paymentAccount && !"".equals(paymentAccount)){
			paymentAccount.setId(UUID.toUUID());
			i=paymentAccountService.addPaymentAccount(paymentAccount);
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
	@RequestMapping(value="/exportPaymentAccount",method={RequestMethod.POST,RequestMethod.GET})
	public void exportPaymentAccount(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentAccountAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentAccountAdd(PaymentAccount paymentAccount,HttpServletRequest request){
		return new ModelAndView("pc/payment-view/payment-account/payment-account-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentAccountUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentAccountUpdate(String id,HttpServletRequest request, Model model){
		PaymentAccount paymentAccount = paymentAccountService.getPaymentAccountById(id);
		model.addAttribute("paymentAccount", paymentAccount);
		return new ModelAndView("pc/payment-view/payment-account/payment-account-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentAccountDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentAccountDetail(String id,HttpServletRequest request, Model model){
		PaymentAccount paymentAccount = paymentAccountService.getPaymentAccountById(id);
		model.addAttribute("paymentAccount", paymentAccount);
		return new ModelAndView("pc/payment-view/payment-account/payment-account-detail");
	}
}

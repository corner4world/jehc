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
import jehc.paymentmodules.paymentmodel.PaymentRecord;
import jehc.paymentmodules.paymentservice.PaymentRecordService;

/**
* 支付记录 
* 2018-07-24 13:33:48  邓
*/
@Controller
@RequestMapping("/paymentRecordController")
public class PaymentRecordController extends BaseAction{
	@Autowired
	private PaymentRecordService paymentRecordService;
	/**
	* 载入初始化页面
	* @param payment_record 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadPaymentRecord",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadPaymentRecord(PaymentRecord paymentRecord,HttpServletRequest request){
		return new ModelAndView("pc/payment-view/payment-record/payment-record-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param payment_record 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getPaymentRecordListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getPaymentRecordListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<PaymentRecord> paymentRecordList = paymentRecordService.getPaymentRecordListByCondition(condition);
		PageInfo<PaymentRecord> page = new PageInfo<PaymentRecord>(paymentRecordList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getPaymentRecordById",method={RequestMethod.POST,RequestMethod.GET})
	public String getPaymentRecordById(String id,HttpServletRequest request){
		PaymentRecord paymentRecord = paymentRecordService.getPaymentRecordById(id);
		return outDataStr(paymentRecord);
	}
	/**
	* 添加
	* @param payment_record 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addPaymentRecord",method={RequestMethod.POST,RequestMethod.GET})
	public String addPaymentRecord(PaymentRecord paymentRecord,HttpServletRequest request){
		int i = 0;
		if(null != paymentRecord && !"".equals(paymentRecord)){
			paymentRecord.setId(UUID.toUUID());
			i=paymentRecordService.addPaymentRecord(paymentRecord);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param payment_record 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updatePaymentRecord",method={RequestMethod.POST,RequestMethod.GET})
	public String updatePaymentRecord(PaymentRecord paymentRecord,HttpServletRequest request){
		int i = 0;
		if(null != paymentRecord && !"".equals(paymentRecord)){
			i=paymentRecordService.updatePaymentRecord(paymentRecord);
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
	@RequestMapping(value="/delPaymentRecord",method={RequestMethod.POST,RequestMethod.GET})
	public String delPaymentRecord(String id,HttpServletRequest request){
		int i = 0;
		if(null != id && !"".equals(id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id",id.split(","));
			i=paymentRecordService.delPaymentRecord(condition);
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
	@RequestMapping(value="/copyPaymentRecord",method={RequestMethod.POST,RequestMethod.GET})
	public String copyPaymentRecord(String id,HttpServletRequest request){
		int i = 0;
		PaymentRecord paymentRecord = paymentRecordService.getPaymentRecordById(id);
		if(null != paymentRecord && !"".equals(paymentRecord)){
			paymentRecord.setId(UUID.toUUID());
			i=paymentRecordService.addPaymentRecord(paymentRecord);
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
	@RequestMapping(value="/exportPaymentRecord",method={RequestMethod.POST,RequestMethod.GET})
	public void exportPaymentRecord(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentRecordAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentRecordAdd(PaymentRecord paymentRecord,HttpServletRequest request){
		return new ModelAndView("pc/payment-view/payment-record/payment-record-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentRecordUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentRecordUpdate(String id,HttpServletRequest request, Model model){
		PaymentRecord paymentRecord = paymentRecordService.getPaymentRecordById(id);
		model.addAttribute("paymentRecord", paymentRecord);
		return new ModelAndView("pc/payment-view/payment-record/payment-record-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toPaymentRecordDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toPaymentRecordDetail(String id,HttpServletRequest request, Model model){
		PaymentRecord paymentRecord = paymentRecordService.getPaymentRecordById(id);
		model.addAttribute("paymentRecord", paymentRecord);
		return new ModelAndView("pc/payment-view/payment-record/payment-record-detail");
	}
}

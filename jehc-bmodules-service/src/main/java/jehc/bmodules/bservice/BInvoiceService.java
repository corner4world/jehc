package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BInvoice;

/**
* 基础发票 
* 2016-02-22 14:35:28  邓纯杰
*/
public interface BInvoiceService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BInvoice> getBInvoiceListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_invoice_id 
	* @return
	*/
	public BInvoice getBInvoiceById(String b_invoice_id);
	/**
	* 添加
	* @param b_invoice 
	* @return
	*/
	public int addBInvoice(BInvoice b_Invoice);
	/**
	* 修改
	* @param b_invoice 
	* @return
	*/
	public int updateBInvoice(BInvoice b_Invoice);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBInvoice(Map<String,Object> condition);
}

package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BInvoiceDao;
import jehc.bmodules.bmodel.BInvoice;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础发票 
* 2016-02-22 14:35:28  邓纯杰
*/
@Repository("bInvoiceDao")
public class BInvoiceDaoImpl  extends BaseDaoImpl implements BInvoiceDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BInvoice> getBInvoiceListByCondition(Map<String,Object> condition){
		return (List<BInvoice>)this.getList("getBInvoiceListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_invoice_id 
	* @return
	*/
	public BInvoice getBInvoiceById(String b_invoice_id){
		return (BInvoice)this.get("getBInvoiceById", b_invoice_id);
	}
	/**
	* 添加
	* @param b_invoice 
	* @return
	*/
	public int addBInvoice(BInvoice b_Invoice){
		return this.add("addBInvoice", b_Invoice);
	}
	/**
	* 修改
	* @param b_invoice 
	* @return
	*/
	public int updateBInvoice(BInvoice b_Invoice){
		return this.update("updateBInvoice", b_Invoice);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBInvoice(Map<String,Object> condition){
		return this.del("delBInvoice", condition);
	}
}

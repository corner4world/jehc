package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BInvoiceDao;
import jehc.bmodules.bmodel.BInvoice;
import jehc.bmodules.bservice.BInvoiceService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础发票 
* 2016-02-22 14:35:28  邓纯杰
*/
@Service("bInvoiceService")
public class BInvoiceServiceImpl extends BaseService implements BInvoiceService{
	@Autowired
	private BInvoiceDao bInvoiceDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BInvoice> getBInvoiceListByCondition(Map<String,Object> condition){
		try{
			return bInvoiceDao.getBInvoiceListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_invoice_id 
	* @return
	*/
	public BInvoice getBInvoiceById(String b_invoice_id){
		try{
			return bInvoiceDao.getBInvoiceById(b_invoice_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_invoice 
	* @return
	*/
	public int addBInvoice(BInvoice b_Invoice){
		int i = 0;
		try {
			i = bInvoiceDao.addBInvoice(b_Invoice);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_invoice 
	* @return
	*/
	public int updateBInvoice(BInvoice b_Invoice){
		int i = 0;
		try {
			i = bInvoiceDao.updateBInvoice(b_Invoice);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBInvoice(Map<String,Object> condition){
		int i = 0;
		try {
			i = bInvoiceDao.delBInvoice(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BStockDao;
import jehc.bmodules.bmodel.BStock;
import jehc.bmodules.bservice.BStockService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础库存 
* 2016-01-27 14:28:46  邓纯杰
*/
@Service("bStockService")
public class BStockServiceImpl extends BaseService implements BStockService{
	@Autowired
	private BStockDao bStockDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BStock> getBStockListByCondition(Map<String,Object> condition){
		try{
			return bStockDao.getBStockListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_stock_id 
	* @return
	*/
	public BStock getBStockById(String b_stock_id){
		try{
			return bStockDao.getBStockById(b_stock_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_stock 
	* @return
	*/
	public int addBStock(BStock b_Stock){
		int i = 0;
		try {
			i = bStockDao.addBStock(b_Stock);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_stock 
	* @return
	*/
	public int updateBStock(BStock b_Stock){
		int i = 0;
		try {
			i = bStockDao.updateBStock(b_Stock);
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
	public int delBStock(Map<String,Object> condition){
		int i = 0;
		try {
			i = bStockDao.delBStock(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

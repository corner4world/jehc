package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BStockDao;
import jehc.bmodules.bmodel.BStock;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础库存 
* 2016-01-27 14:28:46  邓纯杰
*/
@Repository("bStockDao")
public class BStockDaoImpl  extends BaseDaoImpl implements BStockDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BStock> getBStockListByCondition(Map<String,Object> condition){
		return (List<BStock>)this.getList("getBStockListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_stock_id 
	* @return
	*/
	public BStock getBStockById(String b_stock_id){
		return (BStock)this.get("getBStockById", b_stock_id);
	}
	/**
	* 添加
	* @param b_stock 
	* @return
	*/
	public int addBStock(BStock b_Stock){
		return this.add("addBStock", b_Stock);
	}
	/**
	* 修改
	* @param b_stock 
	* @return
	*/
	public int updateBStock(BStock b_Stock){
		return this.update("updateBStock", b_Stock);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBStock(Map<String,Object> condition){
		return this.del("delBStock", condition);
	}
	

	/**
	 * 锁定库存  b_stock_countable_sell 库存数量，b_stock_locks_number 可卖数
	 * @param b_Stock
	 * @return
	 */
	public int lockBStock(BStock b_Stock){
		return this.update("lockBStock", b_Stock);
	}
	
	/**
	 * 释放库存  b_stock_countable_sell 库存数量，b_stock_locks_number 可卖数 
	 * @param b_Stock
	 * @return
	 */
	public int releaseBStock(BStock b_Stock){
		return this.update("releaseBStock", b_Stock);
	}
}

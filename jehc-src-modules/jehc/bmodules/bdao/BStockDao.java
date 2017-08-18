package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BStock;

/**
* 基础库存 
* 2016-01-27 14:28:46  邓纯杰
*/
public interface BStockDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BStock> getBStockListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_stock_id 
	* @return
	*/
	public BStock getBStockById(String b_stock_id);
	/**
	* 添加
	* @param b_stock 
	* @return
	*/
	public int addBStock(BStock b_Stock);
	/**
	* 修改
	* @param b_stock 
	* @return
	*/
	public int updateBStock(BStock b_Stock);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBStock(Map<String,Object> condition);
}

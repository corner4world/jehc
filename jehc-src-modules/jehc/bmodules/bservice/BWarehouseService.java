package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BWarehouse;

/**
* 基础仓库 
* 2016-01-27 14:21:55  邓纯杰
*/
public interface BWarehouseService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BWarehouse> getBWarehouseListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_warehouse_id 
	* @return
	*/
	public BWarehouse getBWarehouseById(String b_warehouse_id);
	/**
	* 添加
	* @param b_warehouse 
	* @return
	*/
	public int addBWarehouse(BWarehouse b_Warehouse);
	/**
	* 修改
	* @param b_warehouse 
	* @return
	*/
	public int updateBWarehouse(BWarehouse b_Warehouse);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBWarehouse(Map<String,Object> condition);
}

package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BWarehouseLocation;

/**
* 基础仓库库位 
* 2016-01-27 14:25:28  邓纯杰
*/
public interface BWarehouseLocationService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BWarehouseLocation> getBWarehouseLocationListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_warehouse_location_id 
	* @return
	*/
	public BWarehouseLocation getBWarehouseLocationById(String b_warehouse_location_id);
	/**
	* 添加
	* @param b_warehouse_location 
	* @return
	*/
	public int addBWarehouseLocation(BWarehouseLocation b_Warehouse_Location);
	/**
	* 修改
	* @param b_warehouse_location 
	* @return
	*/
	public int updateBWarehouseLocation(BWarehouseLocation b_Warehouse_Location);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBWarehouseLocation(Map<String,Object> condition);
}

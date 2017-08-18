package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BWarehouseDao;
import jehc.bmodules.bmodel.BWarehouse;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础仓库 
* 2016-01-27 14:21:55  邓纯杰
*/
@Repository("bWarehouseDao")
public class BWarehouseDaoImpl  extends BaseDaoImpl implements BWarehouseDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BWarehouse> getBWarehouseListByCondition(Map<String,Object> condition){
		return (List<BWarehouse>)this.getList("getBWarehouseListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_warehouse_id 
	* @return
	*/
	public BWarehouse getBWarehouseById(String b_warehouse_id){
		return (BWarehouse)this.get("getBWarehouseById", b_warehouse_id);
	}
	/**
	* 添加
	* @param b_warehouse 
	* @return
	*/
	public int addBWarehouse(BWarehouse b_Warehouse){
		return this.add("addBWarehouse", b_Warehouse);
	}
	/**
	* 修改
	* @param b_warehouse 
	* @return
	*/
	public int updateBWarehouse(BWarehouse b_Warehouse){
		return this.update("updateBWarehouse", b_Warehouse);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBWarehouse(Map<String,Object> condition){
		return this.del("delBWarehouse", condition);
	}
}

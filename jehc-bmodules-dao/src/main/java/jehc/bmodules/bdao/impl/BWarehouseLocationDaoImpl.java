package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BWarehouseLocationDao;
import jehc.bmodules.bmodel.BWarehouseLocation;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础仓库库位 
* 2016-01-27 14:25:28  邓纯杰
*/
@Repository("bWarehouseLocationDao")
public class BWarehouseLocationDaoImpl  extends BaseDaoImpl implements BWarehouseLocationDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BWarehouseLocation> getBWarehouseLocationListByCondition(Map<String,Object> condition){
		return (List<BWarehouseLocation>)this.getList("getBWarehouseLocationListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_warehouse_location_id 
	* @return
	*/
	public BWarehouseLocation getBWarehouseLocationById(String b_warehouse_location_id){
		return (BWarehouseLocation)this.get("getBWarehouseLocationById", b_warehouse_location_id);
	}
	/**
	* 添加
	* @param b_warehouse_location 
	* @return
	*/
	public int addBWarehouseLocation(BWarehouseLocation b_Warehouse_Location){
		return this.add("addBWarehouseLocation", b_Warehouse_Location);
	}
	/**
	* 修改
	* @param b_warehouse_location 
	* @return
	*/
	public int updateBWarehouseLocation(BWarehouseLocation b_Warehouse_Location){
		return this.update("updateBWarehouseLocation", b_Warehouse_Location);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBWarehouseLocation(Map<String,Object> condition){
		return this.del("delBWarehouseLocation", condition);
	}
}

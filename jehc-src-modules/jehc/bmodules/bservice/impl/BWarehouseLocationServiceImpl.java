package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BWarehouseLocationDao;
import jehc.bmodules.bmodel.BWarehouseLocation;
import jehc.bmodules.bservice.BWarehouseLocationService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础仓库库位 
* 2016-01-27 14:25:28  邓纯杰
*/
@Service("bWarehouseLocationService")
public class BWarehouseLocationServiceImpl extends BaseService implements BWarehouseLocationService{
	@Autowired
	private BWarehouseLocationDao bWarehouseLocationDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BWarehouseLocation> getBWarehouseLocationListByCondition(Map<String,Object> condition){
		try{
			return bWarehouseLocationDao.getBWarehouseLocationListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_warehouse_location_id 
	* @return
	*/
	public BWarehouseLocation getBWarehouseLocationById(String b_warehouse_location_id){
		try{
			return bWarehouseLocationDao.getBWarehouseLocationById(b_warehouse_location_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_warehouse_location 
	* @return
	*/
	public int addBWarehouseLocation(BWarehouseLocation b_Warehouse_Location){
		int i = 0;
		try {
			i = bWarehouseLocationDao.addBWarehouseLocation(b_Warehouse_Location);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_warehouse_location 
	* @return
	*/
	public int updateBWarehouseLocation(BWarehouseLocation b_Warehouse_Location){
		int i = 0;
		try {
			i = bWarehouseLocationDao.updateBWarehouseLocation(b_Warehouse_Location);
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
	public int delBWarehouseLocation(Map<String,Object> condition){
		int i = 0;
		try {
			i = bWarehouseLocationDao.delBWarehouseLocation(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

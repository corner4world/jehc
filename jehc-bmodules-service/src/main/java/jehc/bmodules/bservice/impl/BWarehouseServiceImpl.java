package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BWarehouseDao;
import jehc.bmodules.bmodel.BWarehouse;
import jehc.bmodules.bservice.BWarehouseService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础仓库 
* 2016-01-27 14:21:55  邓纯杰
*/
@Service("bWarehouseService")
public class BWarehouseServiceImpl extends BaseService implements BWarehouseService{
	@Autowired
	private BWarehouseDao bWarehouseDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BWarehouse> getBWarehouseListByCondition(Map<String,Object> condition){
		try{
			return bWarehouseDao.getBWarehouseListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_warehouse_id 
	* @return
	*/
	public BWarehouse getBWarehouseById(String b_warehouse_id){
		try{
			return bWarehouseDao.getBWarehouseById(b_warehouse_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_warehouse 
	* @return
	*/
	public int addBWarehouse(BWarehouse b_Warehouse){
		int i = 0;
		try {
			i = bWarehouseDao.addBWarehouse(b_Warehouse);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_warehouse 
	* @return
	*/
	public int updateBWarehouse(BWarehouse b_Warehouse){
		int i = 0;
		try {
			i = bWarehouseDao.updateBWarehouse(b_Warehouse);
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
	public int delBWarehouse(Map<String,Object> condition){
		int i = 0;
		try {
			i = bWarehouseDao.delBWarehouse(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

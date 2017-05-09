package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_UnitDao;
import jehc.xtmodules.xtmodel.Xt_Unit;
import jehc.xtmodules.xtservice.Xt_UnitService;

/**
* 商品(产品)单位 
* 2015-05-24 08:43:08  邓纯杰
*/
@Service("xt_UnitService")
public class Xt_UnitServiceImpl extends BaseService implements Xt_UnitService{
	@Autowired
	private Xt_UnitDao xt_UnitDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Unit> getXtUnitListByCondition(Map<String,Object> condition){
		try {
			return xt_UnitDao.getXtUnitListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_unit_id 
	* @return
	*/
	public Xt_Unit getXtUnitById(String xt_unit_id){
		try {
			return xt_UnitDao.getXtUnitById(xt_unit_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_unit 
	* @return
	*/
	public int addXtUnit(Xt_Unit xt_Unit){
		int i = 0;
		try {
			i = xt_UnitDao.addXtUnit(xt_Unit);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_unit 
	* @return
	*/
	public int updateXtUnit(Xt_Unit xt_Unit){
		int i = 0;
		try {
			i = xt_UnitDao.updateXtUnit(xt_Unit);
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
	public int delXtUnit(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_UnitDao.delXtUnit(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

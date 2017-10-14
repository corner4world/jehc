package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtUnitDao;
import jehc.xtmodules.xtmodel.XtUnit;
import jehc.xtmodules.xtservice.XtUnitService;

/**
* 商品(产品)单位 
* 2015-05-24 08:43:08  邓纯杰
*/
@Service("xtUnitService")
public class XtUnitServiceImpl extends BaseService implements XtUnitService{
	@Autowired
	private XtUnitDao xtUnitDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtUnit> getXtUnitListByCondition(Map<String,Object> condition){
		try {
			return xtUnitDao.getXtUnitListByCondition(condition);
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
	public XtUnit getXtUnitById(String xt_unit_id){
		try {
			return xtUnitDao.getXtUnitById(xt_unit_id);
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
	public int addXtUnit(XtUnit xt_Unit){
		int i = 0;
		try {
			i = xtUnitDao.addXtUnit(xt_Unit);
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
	public int updateXtUnit(XtUnit xt_Unit){
		int i = 0;
		try {
			i = xtUnitDao.updateXtUnit(xt_Unit);
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
			i = xtUnitDao.delXtUnit(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

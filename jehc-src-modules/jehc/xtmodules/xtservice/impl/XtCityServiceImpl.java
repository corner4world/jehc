package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtCityDao;
import jehc.xtmodules.xtmodel.XtCity;
import jehc.xtmodules.xtservice.XtCityService;

/**
* 共用城市表; InnoDB free: 7168 kB 
* 2015-07-27 19:57:27  邓纯杰
*/
@Service("xtCityService")
public class XtCityServiceImpl extends BaseService implements XtCityService{
	@Autowired
	private XtCityDao xtCityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtCity> getXtCityListByCondition(Map<String,Object> condition){
		try{
			return xtCityDao.getXtCityListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_cityID 
	* @return
	*/
	public XtCity getXtCityById(String xt_cityID){
		try{
			return xtCityDao.getXtCityById(xt_cityID);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_city 
	* @return
	*/
	public int addXtCity(XtCity xt_City){
		int i = 0;
		try {
			i = xtCityDao.addXtCity(xt_City);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_city 
	* @return
	*/
	public int updateXtCity(XtCity xt_City){
		int i = 0;
		try {
			i = xtCityDao.updateXtCity(xt_City);
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
	public int delXtCity(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtCityDao.delXtCity(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

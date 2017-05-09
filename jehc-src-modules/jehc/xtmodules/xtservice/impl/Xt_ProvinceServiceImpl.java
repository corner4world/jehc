package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_ProvinceDao;
import jehc.xtmodules.xtmodel.Xt_Province;
import jehc.xtmodules.xtservice.Xt_ProvinceService;

/**
* 共用省份表; InnoDB free: 7168 kB 
* 2015-07-27 19:27:08  邓纯杰
*/
@Service("xt_ProvinceService")
public class Xt_ProvinceServiceImpl extends BaseService implements Xt_ProvinceService{
	@Autowired
	private Xt_ProvinceDao xt_ProvinceDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Province> getXtProvinceListByCondition(Map<String,Object> condition){
		try{
			return xt_ProvinceDao.getXtProvinceListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_provinceID 
	* @return
	*/
	public Xt_Province getXtProvinceById(String xt_provinceID){
		try{
			return xt_ProvinceDao.getXtProvinceById(xt_provinceID);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_province 
	* @return
	*/
	public int addXtProvince(Xt_Province xt_Province){
		int i = 0;
		try {
			i = xt_ProvinceDao.addXtProvince(xt_Province);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_province 
	* @return
	*/
	public int updateXtProvince(Xt_Province xt_Province){
		int i = 0;
		try {
			i = xt_ProvinceDao.updateXtProvince(xt_Province);
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
	public int delXtProvince(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_ProvinceDao.delXtProvince(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

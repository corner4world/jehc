package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtDistrictDao;
import jehc.xtmodules.xtmodel.XtDistrict;
import jehc.xtmodules.xtservice.XtDistrictService;

/**
* 共用地区表; InnoDB free: 6144 kB 
* 2015-07-27 20:08:27  邓纯杰
*/
@Service("xtDistrictService")
public class XtDistrictServiceImpl extends BaseService implements XtDistrictService{
	@Autowired
	private XtDistrictDao xtDistrictDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDistrict> getXtDistrictListByCondition(Map<String,Object> condition){
		try{
			return xtDistrictDao.getXtDistrictListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_districtID 
	* @return
	*/
	public XtDistrict getXtDistrictById(String xt_districtID){
		try{
			return xtDistrictDao.getXtDistrictById(xt_districtID);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_district 
	* @return
	*/
	public int addXtDistrict(XtDistrict xt_District){
		int i = 0;
		try {
			i = xtDistrictDao.addXtDistrict(xt_District);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_district 
	* @return
	*/
	public int updateXtDistrict(XtDistrict xt_District){
		int i = 0;
		try {
			i = xtDistrictDao.updateXtDistrict(xt_District);
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
	public int delXtDistrict(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtDistrictDao.delXtDistrict(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

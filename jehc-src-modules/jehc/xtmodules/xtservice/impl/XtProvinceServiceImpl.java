package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtProvinceDao;
import jehc.xtmodules.xtmodel.XtProvince;
import jehc.xtmodules.xtservice.XtProvinceService;

/**
* 共用省份表; InnoDB free: 7168 kB 
* 2015-07-27 19:27:08  邓纯杰
*/
@Service("xtProvinceService")
public class XtProvinceServiceImpl extends BaseService implements XtProvinceService{
	@Autowired
	private XtProvinceDao xtProvinceDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtProvince> getXtProvinceListByCondition(Map<String,Object> condition){
		try{
			return xtProvinceDao.getXtProvinceListByCondition(condition);
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
	public XtProvince getXtProvinceById(String xt_provinceID){
		try{
			return xtProvinceDao.getXtProvinceById(xt_provinceID);
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
	public int addXtProvince(XtProvince xt_Province){
		int i = 0;
		try {
			i = xtProvinceDao.addXtProvince(xt_Province);
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
	public int updateXtProvince(XtProvince xt_Province){
		int i = 0;
		try {
			i = xtProvinceDao.updateXtProvince(xt_Province);
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
			i = xtProvinceDao.delXtProvince(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

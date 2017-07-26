package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtDbinfoDao;
import jehc.xtmodules.xtmodel.XtDbinfo;
import jehc.xtmodules.xtservice.XtDbinfoService;

/**
* 数据库信息表 
* 2015-05-24 08:13:15  邓纯杰
*/
@Service("xtDbinfoService")
public class XtDbinfoServiceImpl extends BaseService implements XtDbinfoService{
	@Autowired
	private XtDbinfoDao xtDbinfoDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDbinfo> getXtDbinfoListByCondition(Map<String,Object> condition){
		try {
			return xtDbinfoDao.getXtDbinfoListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_dbinfo_id 
	* @return
	*/
	public XtDbinfo getXtDbinfoById(String xt_dbinfo_id){
		try {
			return xtDbinfoDao.getXtDbinfoById(xt_dbinfo_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_dbinfo 
	* @return
	*/
	public int addXtDbinfo(XtDbinfo xt_Dbinfo){
		int i = 0;
		try {
			i = xtDbinfoDao.addXtDbinfo(xt_Dbinfo);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_dbinfo 
	* @return
	*/
	public int updateXtDbinfo(XtDbinfo xt_Dbinfo){
		int i = 0;
		try {
			i = xtDbinfoDao.updateXtDbinfo(xt_Dbinfo);
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
	public int delXtDbinfo(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtDbinfoDao.delXtDbinfo(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtFunctioninfoRightDao;
import jehc.xtmodules.xtmodel.XtFunctioninfoRight;
import jehc.xtmodules.xtservice.XtFunctioninfoRightService;

/**
* 功能分配表 
* 2016-10-08 17:38:19  邓纯杰
*/
@Service("xtFunctioninfoRightService")
public class XtFunctioninfoRightServiceImpl extends BaseService implements XtFunctioninfoRightService{
	@Autowired
	private XtFunctioninfoRightDao xtFunctioninfoRightDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtFunctioninfoRight> getXtFunctioninfoRightListByCondition(Map<String,Object> condition){
		try{
			return xtFunctioninfoRightDao.getXtFunctioninfoRightListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_functioninfo_right_id 
	* @return
	*/
	public XtFunctioninfoRight getXtFunctioninfoRightById(String xt_functioninfo_right_id){
		try{
			return xtFunctioninfoRightDao.getXtFunctioninfoRightById(xt_functioninfo_right_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_functioninfo_right 
	* @return
	*/
	public int addXtFunctioninfoRight(XtFunctioninfoRight xt_Functioninfo_Right){
		int i = 0;
		try {
			i = xtFunctioninfoRightDao.addXtFunctioninfoRight(xt_Functioninfo_Right);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_functioninfo_right 
	* @return
	*/
	public int updateXtFunctioninfoRight(XtFunctioninfoRight xt_Functioninfo_Right){
		int i = 0;
		try {
			i = xtFunctioninfoRightDao.updateXtFunctioninfoRight(xt_Functioninfo_Right);
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
	public int delXtFunctioninfoRight(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtFunctioninfoRightDao.delXtFunctioninfoRight(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_functioninfo_rightList 
	* @return
	*/
	public int addBatchXtFunctioninfoRight(List<XtFunctioninfoRight> xt_Functioninfo_RightList){
		int i = 0;
		try {
			i = xtFunctioninfoRightDao.addBatchXtFunctioninfoRight(xt_Functioninfo_RightList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_functioninfo_rightList 
	* @return
	*/
	public int updateBatchXtFunctioninfoRight(List<XtFunctioninfoRight> xt_Functioninfo_RightList){
		int i = 0;
		try {
			i = xtFunctioninfoRightDao.updateBatchXtFunctioninfoRight(xt_Functioninfo_RightList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

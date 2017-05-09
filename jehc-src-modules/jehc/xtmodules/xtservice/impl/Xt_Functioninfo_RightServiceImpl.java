package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Functioninfo_RightDao;
import jehc.xtmodules.xtmodel.Xt_Functioninfo_Right;
import jehc.xtmodules.xtservice.Xt_Functioninfo_RightService;

/**
* 功能分配表 
* 2016-10-08 17:38:19  邓纯杰
*/
@Service("xt_Functioninfo_RightService")
public class Xt_Functioninfo_RightServiceImpl extends BaseService implements Xt_Functioninfo_RightService{
	@Autowired
	private Xt_Functioninfo_RightDao xt_Functioninfo_RightDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Functioninfo_Right> getXtFunctioninfoRightListByCondition(Map<String,Object> condition){
		try{
			return xt_Functioninfo_RightDao.getXtFunctioninfoRightListByCondition(condition);
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
	public Xt_Functioninfo_Right getXtFunctioninfoRightById(String xt_functioninfo_right_id){
		try{
			return xt_Functioninfo_RightDao.getXtFunctioninfoRightById(xt_functioninfo_right_id);
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
	public int addXtFunctioninfoRight(Xt_Functioninfo_Right xt_Functioninfo_Right){
		int i = 0;
		try {
			i = xt_Functioninfo_RightDao.addXtFunctioninfoRight(xt_Functioninfo_Right);
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
	public int updateXtFunctioninfoRight(Xt_Functioninfo_Right xt_Functioninfo_Right){
		int i = 0;
		try {
			i = xt_Functioninfo_RightDao.updateXtFunctioninfoRight(xt_Functioninfo_Right);
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
			i = xt_Functioninfo_RightDao.delXtFunctioninfoRight(condition);
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
	public int addBatchXtFunctioninfoRight(List<Xt_Functioninfo_Right> xt_Functioninfo_RightList){
		int i = 0;
		try {
			i = xt_Functioninfo_RightDao.addBatchXtFunctioninfoRight(xt_Functioninfo_RightList);
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
	public int updateBatchXtFunctioninfoRight(List<Xt_Functioninfo_Right> xt_Functioninfo_RightList){
		int i = 0;
		try {
			i = xt_Functioninfo_RightDao.updateBatchXtFunctioninfoRight(xt_Functioninfo_RightList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

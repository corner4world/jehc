package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Functioninfo_CommonDao;
import jehc.xtmodules.xtmodel.Xt_Functioninfo_Common;
import jehc.xtmodules.xtservice.Xt_Functioninfo_CommonService;

/**
* 公共功能 
* 2016-10-10 13:00:30  邓纯杰
*/
@Service("xt_Functioninfo_CommonService")
public class Xt_Functioninfo_CommonServiceImpl extends BaseService implements Xt_Functioninfo_CommonService{
	@Autowired
	private Xt_Functioninfo_CommonDao xt_Functioninfo_CommonDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Functioninfo_Common> getXtFunctioninfoCommonListByCondition(Map<String,Object> condition){
		try{
			return xt_Functioninfo_CommonDao.getXtFunctioninfoCommonListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_functioninfo_common_id 
	* @return
	*/
	public Xt_Functioninfo_Common getXtFunctioninfoCommonById(String xt_functioninfo_common_id){
		try{
			return xt_Functioninfo_CommonDao.getXtFunctioninfoCommonById(xt_functioninfo_common_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_functioninfo_common 
	* @return
	*/
	public int addXtFunctioninfoCommon(Xt_Functioninfo_Common xt_Functioninfo_Common){
		int i = 0;
		try {
			i = xt_Functioninfo_CommonDao.addXtFunctioninfoCommon(xt_Functioninfo_Common);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_functioninfo_common 
	* @return
	*/
	public int updateXtFunctioninfoCommon(Xt_Functioninfo_Common xt_Functioninfo_Common){
		int i = 0;
		try {
			i = xt_Functioninfo_CommonDao.updateXtFunctioninfoCommon(xt_Functioninfo_Common);
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
	public int delXtFunctioninfoCommon(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Functioninfo_CommonDao.delXtFunctioninfoCommon(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

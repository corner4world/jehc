package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtFunctioninfoCommonDao;
import jehc.xtmodules.xtmodel.XtFunctioninfoCommon;
import jehc.xtmodules.xtservice.XtFunctioninfoCommonService;

/**
* 公共功能 
* 2016-10-10 13:00:30  邓纯杰
*/
@Service("xtFunctioninfoCommonService")
public class XtFunctioninfoCommonServiceImpl extends BaseService implements XtFunctioninfoCommonService{
	@Autowired
	private XtFunctioninfoCommonDao xtFunctioninfoCommonDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtFunctioninfoCommon> getXtFunctioninfoCommonListByCondition(Map<String,Object> condition){
		try{
			return xtFunctioninfoCommonDao.getXtFunctioninfoCommonListByCondition(condition);
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
	public XtFunctioninfoCommon getXtFunctioninfoCommonById(String xt_functioninfo_common_id){
		try{
			return xtFunctioninfoCommonDao.getXtFunctioninfoCommonById(xt_functioninfo_common_id);
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
	public int addXtFunctioninfoCommon(XtFunctioninfoCommon xt_Functioninfo_Common){
		int i = 0;
		try {
			i = xtFunctioninfoCommonDao.addXtFunctioninfoCommon(xt_Functioninfo_Common);
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
	public int updateXtFunctioninfoCommon(XtFunctioninfoCommon xt_Functioninfo_Common){
		int i = 0;
		try {
			i = xtFunctioninfoCommonDao.updateXtFunctioninfoCommon(xt_Functioninfo_Common);
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
			i = xtFunctioninfoCommonDao.delXtFunctioninfoCommon(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

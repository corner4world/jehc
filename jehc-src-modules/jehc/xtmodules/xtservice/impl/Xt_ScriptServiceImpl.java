package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_ScriptDao;
import jehc.xtmodules.xtmodel.Xt_Script;
import jehc.xtmodules.xtservice.Xt_ScriptService;

/**
* 平台脚本 
* 2016-06-14 15:08:50  邓纯杰
*/
@Service("xt_ScriptService")
public class Xt_ScriptServiceImpl extends BaseService implements Xt_ScriptService{
	@Autowired
	private Xt_ScriptDao xt_ScriptDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Script> getXtScriptListByCondition(Map<String,Object> condition){
		try{
			return xt_ScriptDao.getXtScriptListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_script_id 
	* @return
	*/
	public Xt_Script getXtScriptById(String xt_script_id){
		try{
			return xt_ScriptDao.getXtScriptById(xt_script_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_script 
	* @return
	*/
	public int addXtScript(Xt_Script xt_Script){
		int i = 0;
		try {
			i = xt_ScriptDao.addXtScript(xt_Script);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_script 
	* @return
	*/
	public int updateXtScript(Xt_Script xt_Script){
		int i = 0;
		try {
			i = xt_ScriptDao.updateXtScript(xt_Script);
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
	public int delXtScript(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_ScriptDao.delXtScript(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

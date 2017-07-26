package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtGeneratorForbidtableDao;
import jehc.xtmodules.xtmodel.XtGeneratorForbidtable;
import jehc.xtmodules.xtservice.XtGeneratorForbidtableService;

/**
* 禁止使用代码生成器生成的表信息 
* 2016-09-26 10:55:48  邓纯杰
*/
@Service("xtGeneratorForbidtableService")
public class XtGeneratorForbidtableServiceImpl extends BaseService implements XtGeneratorForbidtableService{
	@Autowired
	private XtGeneratorForbidtableDao xtGeneratorForbidtableDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtGeneratorForbidtable> getXtGeneratorForbidtableListByCondition(Map<String,Object> condition){
		try{
			return xtGeneratorForbidtableDao.getXtGeneratorForbidtableListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_generator_forbidtable_id 
	* @return
	*/
	public XtGeneratorForbidtable getXtGeneratorForbidtableById(String xt_generator_forbidtable_id){
		try{
			return xtGeneratorForbidtableDao.getXtGeneratorForbidtableById(xt_generator_forbidtable_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_generator_forbidtable 
	* @return
	*/
	public int addXtGeneratorForbidtable(XtGeneratorForbidtable xt_Generator_Forbidtable){
		int i = 0;
		try {
			i = xtGeneratorForbidtableDao.addXtGeneratorForbidtable(xt_Generator_Forbidtable);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_generator_forbidtable 
	* @return
	*/
	public int updateXtGeneratorForbidtable(XtGeneratorForbidtable xt_Generator_Forbidtable){
		int i = 0;
		try {
			i = xtGeneratorForbidtableDao.updateXtGeneratorForbidtable(xt_Generator_Forbidtable);
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
	public int delXtGeneratorForbidtable(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtGeneratorForbidtableDao.delXtGeneratorForbidtable(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_generator_forbidtableList 
	* @return
	*/
	public int addBatchXtGeneratorForbidtable(List<XtGeneratorForbidtable> xt_Generator_ForbidtableList){
		int i = 0;
		try {
			i = xtGeneratorForbidtableDao.addBatchXtGeneratorForbidtable(xt_Generator_ForbidtableList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_generator_forbidtableList 
	* @return
	*/
	public int updateBatchXtGeneratorForbidtable(List<XtGeneratorForbidtable> xt_Generator_ForbidtableList){
		int i = 0;
		try {
			i = xtGeneratorForbidtableDao.updateBatchXtGeneratorForbidtable(xt_Generator_ForbidtableList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

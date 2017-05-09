package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Generator_ForbidtableDao;
import jehc.xtmodules.xtmodel.Xt_Generator_Forbidtable;
import jehc.xtmodules.xtservice.Xt_Generator_ForbidtableService;

/**
* 禁止使用代码生成器生成的表信息 
* 2016-09-26 10:55:48  邓纯杰
*/
@Service("xt_Generator_ForbidtableService")
public class Xt_Generator_ForbidtableServiceImpl extends BaseService implements Xt_Generator_ForbidtableService{
	@Autowired
	private Xt_Generator_ForbidtableDao xt_Generator_ForbidtableDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Generator_Forbidtable> getXtGeneratorForbidtableListByCondition(Map<String,Object> condition){
		try{
			return xt_Generator_ForbidtableDao.getXtGeneratorForbidtableListByCondition(condition);
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
	public Xt_Generator_Forbidtable getXtGeneratorForbidtableById(String xt_generator_forbidtable_id){
		try{
			return xt_Generator_ForbidtableDao.getXtGeneratorForbidtableById(xt_generator_forbidtable_id);
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
	public int addXtGeneratorForbidtable(Xt_Generator_Forbidtable xt_Generator_Forbidtable){
		int i = 0;
		try {
			i = xt_Generator_ForbidtableDao.addXtGeneratorForbidtable(xt_Generator_Forbidtable);
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
	public int updateXtGeneratorForbidtable(Xt_Generator_Forbidtable xt_Generator_Forbidtable){
		int i = 0;
		try {
			i = xt_Generator_ForbidtableDao.updateXtGeneratorForbidtable(xt_Generator_Forbidtable);
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
			i = xt_Generator_ForbidtableDao.delXtGeneratorForbidtable(condition);
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
	public int addBatchXtGeneratorForbidtable(List<Xt_Generator_Forbidtable> xt_Generator_ForbidtableList){
		int i = 0;
		try {
			i = xt_Generator_ForbidtableDao.addBatchXtGeneratorForbidtable(xt_Generator_ForbidtableList);
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
	public int updateBatchXtGeneratorForbidtable(List<Xt_Generator_Forbidtable> xt_Generator_ForbidtableList){
		int i = 0;
		try {
			i = xt_Generator_ForbidtableDao.updateBatchXtGeneratorForbidtable(xt_Generator_ForbidtableList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

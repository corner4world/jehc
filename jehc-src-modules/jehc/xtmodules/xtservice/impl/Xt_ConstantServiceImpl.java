package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_ConstantDao;
import jehc.xtmodules.xtmodel.Xt_Constant;
import jehc.xtmodules.xtservice.Xt_ConstantService;

/**
* 台平常量 
* 2015-05-24 08:47:31  邓纯杰
*/
@Service("xt_ConstantService")
public class Xt_ConstantServiceImpl extends BaseService implements Xt_ConstantService{
	@Autowired
	private Xt_ConstantDao xt_ConstantDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Constant> getXtConstantListByCondition(Map<String,Object> condition){
		try {
			return xt_ConstantDao.getXtConstantListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_constant_id 
	* @return
	*/
	public Xt_Constant getXtConstantById(String xt_constant_id){
		try {
			return xt_ConstantDao.getXtConstantById(xt_constant_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_constant 
	* @return
	*/
	public int addXtConstant(Xt_Constant xt_Constant){
		int i = 0;
		try {
			i = xt_ConstantDao.addXtConstant(xt_Constant);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_constant 
	* @return
	*/
	public int updateXtConstant(Xt_Constant xt_Constant){
		int i = 0;
		try {
			i = xt_ConstantDao.updateXtConstant(xt_Constant);
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
	public int delXtConstant(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_ConstantDao.delXtConstant(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 读取所有常量
	 * @param condition
	 * @return
	 */
	public List<Xt_Constant> getXtConstantListAllByCondition(Map<String,Object> condition){
		try {
			return xt_ConstantDao.getXtConstantListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}

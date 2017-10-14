package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtConstantDao;
import jehc.xtmodules.xtmodel.XtConstant;
import jehc.xtmodules.xtservice.XtConstantService;

/**
* 台平常量 
* 2015-05-24 08:47:31  邓纯杰
*/
@Service("xtConstantService")
public class XtConstantServiceImpl extends BaseService implements XtConstantService{
	@Autowired
	private XtConstantDao xtConstantDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtConstant> getXtConstantListByCondition(Map<String,Object> condition){
		try {
			return xtConstantDao.getXtConstantListByCondition(condition);
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
	public XtConstant getXtConstantById(String xt_constant_id){
		try {
			return xtConstantDao.getXtConstantById(xt_constant_id);
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
	public int addXtConstant(XtConstant xt_Constant){
		int i = 0;
		try {
			i = xtConstantDao.addXtConstant(xt_Constant);
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
	public int updateXtConstant(XtConstant xt_Constant){
		int i = 0;
		try {
			i = xtConstantDao.updateXtConstant(xt_Constant);
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
			i = xtConstantDao.delXtConstant(condition);
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
	public List<XtConstant> getXtConstantListAllByCondition(Map<String,Object> condition){
		try {
			return xtConstantDao.getXtConstantListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}

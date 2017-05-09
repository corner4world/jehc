package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_QuartzDao;
import jehc.xtmodules.xtmodel.Xt_Quartz;
import jehc.xtmodules.xtservice.Xt_QuartzService;

/**
* 任务调度配置信息表 
* 2015-10-29 16:50:03  邓纯杰
*/
@Service("xt_QuartzService")
public class Xt_QuartzServiceImpl extends BaseService implements Xt_QuartzService{
	@Autowired
	private Xt_QuartzDao xt_QuartzDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Quartz> getXtQuartzListByCondition(Map<String,Object> condition){
		try{
			return xt_QuartzDao.getXtQuartzListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public Xt_Quartz getXtQuartzById(String id){
		try{
			return xt_QuartzDao.getXtQuartzById(id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_quartz 
	* @return
	*/
	public int addXtQuartz(Xt_Quartz xt_Quartz){
		int i = 0;
		try {
			i = xt_QuartzDao.addXtQuartz(xt_Quartz);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_quartz 
	* @return
	*/
	public int updateXtQuartz(Xt_Quartz xt_Quartz){
		int i = 0;
		try {
			i = xt_QuartzDao.updateXtQuartz(xt_Quartz);
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
	public int delXtQuartz(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_QuartzDao.delXtQuartz(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 查找集合
	 * @param condition
	 * @return
	 */
	public List<Xt_Quartz> getXtQuartzListAllByCondition(Map<String,Object> condition){
		try{
			return xt_QuartzDao.getXtQuartzListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}

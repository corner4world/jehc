package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtQuartzDao;
import jehc.xtmodules.xtmodel.XtQuartz;
import jehc.xtmodules.xtservice.XtQuartzService;

/**
* 任务调度配置信息表 
* 2015-10-29 16:50:03  邓纯杰
*/
@Service("xtQuartzService")
public class XtQuartzServiceImpl extends BaseService implements XtQuartzService{
	@Autowired
	private XtQuartzDao xtQuartzDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtQuartz> getXtQuartzListByCondition(Map<String,Object> condition){
		try{
			return xtQuartzDao.getXtQuartzListByCondition(condition);
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
	public XtQuartz getXtQuartzById(String id){
		try{
			return xtQuartzDao.getXtQuartzById(id);
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
	public int addXtQuartz(XtQuartz xt_Quartz){
		int i = 0;
		try {
			i = xtQuartzDao.addXtQuartz(xt_Quartz);
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
	public int updateXtQuartz(XtQuartz xt_Quartz){
		int i = 0;
		try {
			i = xtQuartzDao.updateXtQuartz(xt_Quartz);
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
			i = xtQuartzDao.delXtQuartz(condition);
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
	public List<XtQuartz> getXtQuartzListAllByCondition(Map<String,Object> condition){
		try{
			return xtQuartzDao.getXtQuartzListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}

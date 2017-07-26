package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtMonitorMemDao;
import jehc.xtmodules.xtmodel.XtMonitorMem;
import jehc.xtmodules.xtservice.XtMonitorMemService;

/**
* 服务器内存 
* 2016-03-04 12:52:52  邓纯杰
*/
@Service("xtMonitorMemService")
public class XtMonitorMemServiceImpl extends BaseService implements XtMonitorMemService{
	@Autowired
	private XtMonitorMemDao xtMonitorMemDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtMonitorMem> getXtMonitorMemListByCondition(Map<String,Object> condition){
		try{
			return xtMonitorMemDao.getXtMonitorMemListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_monitor_mem_id 
	* @return
	*/
	public XtMonitorMem getXtMonitorMemById(String xt_monitor_mem_id){
		try{
			return xtMonitorMemDao.getXtMonitorMemById(xt_monitor_mem_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_monitor_mem 
	* @return
	*/
	public int addXtMonitorMem(XtMonitorMem xt_Monitor_Mem){
		int i = 0;
		try {
			i = xtMonitorMemDao.addXtMonitorMem(xt_Monitor_Mem);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_monitor_mem 
	* @return
	*/
	public int updateXtMonitorMem(XtMonitorMem xt_Monitor_Mem){
		int i = 0;
		try {
			i = xtMonitorMemDao.updateXtMonitorMem(xt_Monitor_Mem);
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
	public int delXtMonitorMem(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtMonitorMemDao.delXtMonitorMem(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

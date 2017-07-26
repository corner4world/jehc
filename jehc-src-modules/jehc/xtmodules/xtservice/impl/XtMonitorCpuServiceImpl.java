package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtMonitorCpuDao;
import jehc.xtmodules.xtmodel.XtMonitorCpu;
import jehc.xtmodules.xtservice.XtMonitorCpuService;

/**
* 服务器CPU运行 
* 2015-05-24 15:04:44  邓纯杰
*/
@Service("xtMonitorCpuService")
public class XtMonitorCpuServiceImpl extends BaseService implements XtMonitorCpuService{
	@Autowired
	private XtMonitorCpuDao xtMonitorCpuDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtMonitorCpu> getXtMonitorCpuListByCondition(Map<String,Object> condition){
		try {
			return xtMonitorCpuDao.getXtMonitorCpuListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_monitor_cpu_id 
	* @return
	*/
	public XtMonitorCpu getXtMonitorCpuById(String xt_monitor_cpu_id){
		try {
			return xtMonitorCpuDao.getXtMonitorCpuById(xt_monitor_cpu_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_monitor_cpu 
	* @return
	*/
	public int addXtMonitorCpu(XtMonitorCpu xt_Monitor_Cpu){
		int i = 0;
		try {
			i = xtMonitorCpuDao.addXtMonitorCpu(xt_Monitor_Cpu);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_monitor_cpu 
	* @return
	*/
	public int updateXtMonitorCpu(XtMonitorCpu xt_Monitor_Cpu){
		int i = 0;
		try {
			i = xtMonitorCpuDao.updateXtMonitorCpu(xt_Monitor_Cpu);
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
	public int delXtMonitorCpu(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtMonitorCpuDao.delXtMonitorCpu(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

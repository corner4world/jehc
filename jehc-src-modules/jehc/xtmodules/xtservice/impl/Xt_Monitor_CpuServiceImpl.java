package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Monitor_CpuDao;
import jehc.xtmodules.xtmodel.Xt_Monitor_Cpu;
import jehc.xtmodules.xtservice.Xt_Monitor_CpuService;

/**
* 服务器CPU运行 
* 2015-05-24 15:04:44  邓纯杰
*/
@Service("xt_Monitor_CpuService")
public class Xt_Monitor_CpuServiceImpl extends BaseService implements Xt_Monitor_CpuService{
	@Autowired
	private Xt_Monitor_CpuDao xt_Monitor_CpuDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Monitor_Cpu> getXtMonitorCpuListByCondition(Map<String,Object> condition){
		try {
			return xt_Monitor_CpuDao.getXtMonitorCpuListByCondition(condition);
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
	public Xt_Monitor_Cpu getXtMonitorCpuById(String xt_monitor_cpu_id){
		try {
			return xt_Monitor_CpuDao.getXtMonitorCpuById(xt_monitor_cpu_id);
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
	public int addXtMonitorCpu(Xt_Monitor_Cpu xt_Monitor_Cpu){
		int i = 0;
		try {
			i = xt_Monitor_CpuDao.addXtMonitorCpu(xt_Monitor_Cpu);
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
	public int updateXtMonitorCpu(Xt_Monitor_Cpu xt_Monitor_Cpu){
		int i = 0;
		try {
			i = xt_Monitor_CpuDao.updateXtMonitorCpu(xt_Monitor_Cpu);
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
			i = xt_Monitor_CpuDao.delXtMonitorCpu(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

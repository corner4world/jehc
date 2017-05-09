package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Monitor_MemDao;
import jehc.xtmodules.xtmodel.Xt_Monitor_Mem;
import jehc.xtmodules.xtservice.Xt_Monitor_MemService;

/**
* 服务器内存 
* 2016-03-04 12:52:52  邓纯杰
*/
@Service("xt_Monitor_MemService")
public class Xt_Monitor_MemServiceImpl extends BaseService implements Xt_Monitor_MemService{
	@Autowired
	private Xt_Monitor_MemDao xt_Monitor_MemDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Monitor_Mem> getXtMonitorMemListByCondition(Map<String,Object> condition){
		try{
			return xt_Monitor_MemDao.getXtMonitorMemListByCondition(condition);
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
	public Xt_Monitor_Mem getXtMonitorMemById(String xt_monitor_mem_id){
		try{
			return xt_Monitor_MemDao.getXtMonitorMemById(xt_monitor_mem_id);
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
	public int addXtMonitorMem(Xt_Monitor_Mem xt_Monitor_Mem){
		int i = 0;
		try {
			i = xt_Monitor_MemDao.addXtMonitorMem(xt_Monitor_Mem);
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
	public int updateXtMonitorMem(Xt_Monitor_Mem xt_Monitor_Mem){
		int i = 0;
		try {
			i = xt_Monitor_MemDao.updateXtMonitorMem(xt_Monitor_Mem);
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
			i = xt_Monitor_MemDao.delXtMonitorMem(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

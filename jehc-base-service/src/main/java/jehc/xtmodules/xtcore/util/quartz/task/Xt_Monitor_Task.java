package jehc.xtmodules.xtcore.util.quartz.task;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtdao.XtMonitorCpuDao;
import jehc.xtmodules.xtdao.XtMonitorDao;
import jehc.xtmodules.xtdao.XtMonitorMemDao;
import jehc.xtmodules.xtmodel.XtMonitor;
import jehc.xtmodules.xtmodel.XtMonitorCpu;
import jehc.xtmodules.xtmodel.XtMonitorMem;


/**
 * 监控任务
 * @author 邓纯杰
 *
 */
public class Xt_Monitor_Task extends Thread{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 业务逻辑处理
	 */
	public void service() {
		new Xt_Monitor_Task().start();
	}
	public void run(){
		try {
			//1监控CPU
			addXtMonitor();
			//2监控内存
			addXtMonitorMEM();
			//3监控主服务器
			addXtMonitorCPU();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 主服务器监控
	 */
	public void addXtMonitor(){
		Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr;
        XtMonitorDao xt_MonitorDao = (XtMonitorDao)SpringUtil.getBean("xtMonitorDao");
        try {
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress();
	        Map<String, String> map = System.getenv();
	        String userName = map.get("USERNAME");//获取用户名
	        String computerName = map.get("COMPUTERNAME");//获取计算机名
			XtMonitor xtMonitor = new XtMonitor();
			xtMonitor.setXt_monitor_userName(userName);
			xtMonitor.setXt_monitor_accountName(props.getProperty("user.name"));
			xtMonitor.setXt_monitor_comName(computerName);
			xtMonitor.setXt_monitor_localName(addr.getHostName());
			xtMonitor.setXt_monitor_jvm_totalMem(Integer.parseInt(""+r.totalMemory()));
			xtMonitor.setXt_monitor_jvm_Mem(Integer.parseInt(""+r.freeMemory()));
			xtMonitor.setXt_monitor_operate_sysName(props.getProperty("os.name"));
			xtMonitor.setXt_monitor_operate_org(props.getProperty("os.arch"));
			xtMonitor.setXt_monitor_jvm_cup_count(r.availableProcessors());
			xtMonitor.setXt_monitorIP(ip);
			xtMonitor.setXt_monitor_environment(props.getProperty("java.version"));
			xtMonitor.setXt_monitorPath(props.getProperty("java.home"));
			xtMonitor.setXt_monitor_id(UUID.toUUID());
			xtMonitor.setXt_monitorTime(BaseService.getSimpleDateFormat());
			int i = xt_MonitorDao.addXtMonitor(xtMonitor);
			if(i > 0){
				logger.info(BaseService.getSimpleDateFormat()+"--->主服务器信息获取成功");
			}else{
				logger.info(BaseService.getSimpleDateFormat()+"--->主服务器信息获取失败");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 内存监控
	 */
	public void addXtMonitorMEM(){
		XtMonitorMemDao xt_Monitor_MemDao = (XtMonitorMemDao)SpringUtil.getBean("xtMonitorMemDao");
		Sigar sigar = new Sigar();
        Mem mem;
		try {
			mem = sigar.getMem();
	        Swap swap = sigar.getSwap();
			XtMonitorMem xt_Monitor_Mem = new XtMonitorMem();
			xt_Monitor_Mem.setXt_monitor_memTotal(""+mem.getTotal() / 1024L);
			xt_Monitor_Mem.setXt_monitor_memCurrUse(""+mem.getUsed() / 1024L);
			xt_Monitor_Mem.setXt_monitor_memCurrSy(""+mem.getFree() / 1024L);
			xt_Monitor_Mem.setXt_monitor_memJhTotal(""+swap.getTotal() / 1024L);
			xt_Monitor_Mem.setXt_monitor_memJhCurrUse(""+swap.getUsed() / 1024L);
			xt_Monitor_Mem.setXt_monitor_memJhSy(""+swap.getFree() / 1024L);
			xt_Monitor_Mem.setXt_monitor_memTime(BaseService.getSimpleDateFormat());
			xt_Monitor_Mem.setXt_monitor_mem_id(UUID.toUUID());
			int i = xt_Monitor_MemDao.addXtMonitorMem(xt_Monitor_Mem);
			if(i > 0){
				logger.info(BaseService.getSimpleDateFormat()+"--->内存监控成功");
			}else{
				logger.info(BaseService.getSimpleDateFormat()+"--->内存监控失败");
			}
		} catch (SigarException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * CPU监控
	 */
	public void addXtMonitorCPU(){
		XtMonitorCpuDao xt_Monitor_CpuDao = (XtMonitorCpuDao)SpringUtil.getBean("xtMonitorCpuDao");
		String libs = System.getProperty("java.library.path");
        System.setProperty("java.library.path", libs);
        Sigar sigar = new Sigar();
        CpuInfo infos[];
		try {
			infos = sigar.getCpuInfoList();
			CpuPerc cpuList[] = null;
	        cpuList = sigar.getCpuPercList();
	        for(int i = 0; i < infos.length; i++) {
	        	XtMonitorCpu xt_Monitor_Cpu = new XtMonitorCpu();
	            CpuInfo info = infos[i];
	            CpuPerc cpu = cpuList[i];
	            xt_Monitor_Cpu.setXt_monitor_cpu_totalMHz(info.getMhz());
	            xt_Monitor_Cpu.setXt_monitor_cpu_producer(info.getVendor());
	            xt_Monitor_Cpu.setXt_monitor_cpu_cache(""+info.getCacheSize());
	            xt_Monitor_Cpu.setXt_monitor_cpu_user_use_rate(CpuPerc.format(cpu.getUser()));
	            xt_Monitor_Cpu.setXt_monitor_cpu_sys_use_rate(CpuPerc.format(cpu.getSys()));
	            xt_Monitor_Cpu.setXt_monitor_cpu_wait_use_rate(CpuPerc.format(cpu.getWait()));
	            xt_Monitor_Cpu.setXt_monitor_cpu_error_use_rate(CpuPerc.format(cpu.getNice()));
	            xt_Monitor_Cpu.setXt_monitor_cpu_currently_idle(CpuPerc.format(cpu.getIdle()));
	            xt_Monitor_Cpu.setXt_monitor_cpu_use_rate(CpuPerc.format(cpu.getCombined()));
	            xt_Monitor_Cpu.setXt_monitorNum(i+1);
	            xt_Monitor_Cpu.setXt_monitor_cpu_id(UUID.toUUID());
	            xt_Monitor_Cpu.setXt_monitor_cpuTime(BaseService.getSimpleDateFormat());
	            int j = xt_Monitor_CpuDao.addXtMonitorCpu(xt_Monitor_Cpu);
	            if(j > 0){
//	            	logger.info(CommonUtils.getSimpleDateFormat()+"--->内存监控成功");
				}else{
					logger.info(BaseService.getSimpleDateFormat()+"--->内存监控失败");
				}
	        }
		} catch (SigarException e) {
			e.printStackTrace();
		}
	}
}

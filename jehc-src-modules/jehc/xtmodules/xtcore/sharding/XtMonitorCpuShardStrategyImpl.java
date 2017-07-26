package jehc.xtmodules.xtcore.sharding;

import com.google.code.shardbatis.strategy.ShardStrategy;

import jehc.xtmodules.xtmodel.XtMonitorCpu;

public class XtMonitorCpuShardStrategyImpl implements ShardStrategy {
	/**
	 * 得到实际表名
	 * @param baseTableName 逻辑表名,一般是没有前缀或者是后缀的表名
	 * @param params 		mybatis执行某个statement时使用的参数
	 * @param mapperId 		mybatis配置的statement id
	 * @return
	 */
	public String getTargetTableName(String baseTableName, Object params, String mapperId) {
//		Integer k = 0;
//		if (params != null) {
//			Xt_Monitor_Cpu shardXt_Monitor_Cpu = (Xt_Monitor_Cpu) params;
//			Integer temp = shardXt_Monitor_Cpu.getXt_monitor_cpu_totalMHz();
//			k = temp % 2;
//		}
//		return baseTableName + "_" + k;
		return baseTableName;
	}
}
package jehc.xtmodules.xtcore.base.readwritedata;

import org.springframework.util.Assert;

public class JdbcContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	/** 主库（写库） **/
	public static final String MASTER_DATA_SOURCE = "dataSourceMaster";
	/** 从库数组形式(读库) **/
	public static final String[] SLAVE_DATA_SOURCE_LIST = {"dataSourceSlave"};
	public static void setDataSource(String dataSource) {
		Assert.notNull(dataSource, "dataSource cannot be null");
		contextHolder.set(dataSource);
	}
	public static void setMaster() {
		clearDataSource();
	}
	public static void setSlave() {
		int index = (int) (Math.random() * SLAVE_DATA_SOURCE_LIST.length);
		String slave = SLAVE_DATA_SOURCE_LIST[index];
		//此处可以随机切换任意一台从库数据源
		setDataSource(slave);
	}
	public static String getDataSource() {
		return (String) contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}

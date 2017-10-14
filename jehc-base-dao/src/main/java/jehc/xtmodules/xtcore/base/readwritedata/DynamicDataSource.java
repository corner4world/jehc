package jehc.xtmodules.xtcore.base.readwritedata;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据库读写分离
 * @author邓纯杰
 * 
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	protected Object determineCurrentLookupKey() {
		return JdbcContextHolder.getDataSource();
	}
}

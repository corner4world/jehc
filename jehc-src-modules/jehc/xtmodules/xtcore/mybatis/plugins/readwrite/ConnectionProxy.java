package jehc.xtmodules.xtcore.mybatis.plugins.readwrite;

import java.sql.Connection;

/**
 * 创建Connection代理接口
 * @author dengcj
 *
 */
public interface ConnectionProxy extends Connection {

    /**
     * 根据传入的读写分离需要的key路由到正确的connection
     * @param key 数据源标识
     * @return
     */
    Connection  getTargetConnection(String key);
}
package jehc.xtmodules.xtcore.websocket.test;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class WSCoreConfig implements ServerApplicationConfig{

	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {
		return null;
	}

	//注解的方式
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		System.err.println("===== START CONFIG =====\nscannedsize:" + scanned.size());
		return scanned;
	}
}

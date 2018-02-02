package jehc.junitmodules.junit.netty;

import org.junit.Test;

import jehc.junitmodules.junit.base.BaseJunit;
import jehc.xtmodules.xtcore.netty.server.TcpServer;

public class NettyJunit extends BaseJunit{
	@Test
	public void startServer(){
		TcpServer tServer = new TcpServer();
		tServer.startServer();
	}
}

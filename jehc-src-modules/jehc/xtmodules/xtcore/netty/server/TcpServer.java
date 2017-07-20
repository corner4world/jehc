package jehc.xtmodules.xtcore.netty.server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 采用NETTY方式
 * TCP启动服务类
 * @author 邓纯杰
 *
 */
public class TcpServer {
	private static final Logger log = LoggerFactory.getLogger(TcpServer.class);
	private static String IP;
	private static int PORT;
	/**用于分配处理业务线程的线程组个数 */
	protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;	//默认 Cpu*2
	/** 业务出现线程大小*/
	protected static final int BIZTHREADSIZE = 8;
	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
	protected void init() throws Exception {
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup);
		try {
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
					pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
					pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
					pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
					pipeline.addLast("idleStateHandler", new IdleStateHandler(20, 20, 10)); //心跳监测 读超时为20s，写超时为20s 全部空闲时间10s
					/**
					 * 该方法也可以使用如下替换
					 *pipeline.addLast(new TcpServerHandler());
					 */
					pipeline.addLast(getTcpServerHandler());
				}
			});
			//绑定端口，开始接收进来的连接
			ChannelFuture future = b.bind(IP, PORT).sync();
			if(future.isSuccess()){
				log.info("TCP服务已启动---------"+future.isSuccess());
			}else{
				log.info("TCP服务未能正常启动------"+future.isSuccess());
			}
			/**
			 * 该方法拥有关闭服务 
			 **/
			 future.channel().closeFuture().addListener(remover); //用这种监听来
		}catch(Exception e){
			log.info("TCP服务启动出现异常------"+e.getMessage());
		}
	}
	
	/**
	 * 关闭服务
	 */
	protected static void shutdown() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
		log.info("关闭TCP服务");
	}
	/**
	 * 启动服务
	 */
	public void startServer(){
		try {
			log.info("开始启动TCP服务...");
			init();
		} catch (Exception e) {
			log.error("开始启动TCP服务出现异常..."+e.getMessage());
		}
	}
	public static String getIP() {
		return IP;
	}
	public static int getPORT() {
		return PORT;
	}
	public void setIP(String ip) {
		IP = ip;
	}
	public void setPORT(int port) {
		PORT = port;
	}
	
	/**
	 * 获取TcpServerHandler
	 * @return
	 */
	public TcpServerHandler getTcpServerHandler(){
		return (TcpServerHandler)SpringUtil.getBean("tcpServerHandler");
	}
	
	 /**
	 * 监听ChannelFuture是否完成
	 */
	private final ChannelFutureListener remover = new ChannelFutureListener() {
        public void operationComplete(ChannelFuture future) throws Exception {
        	future.channel().closeFuture().sync();
//        	shutdown();
        }
    };
}

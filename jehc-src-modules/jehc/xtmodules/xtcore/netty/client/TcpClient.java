package jehc.xtmodules.xtcore.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 客户端
 * @author 邓纯杰
 *
 */
@Component
public class TcpClient {
	private static final Logger log = LoggerFactory.getLogger(TcpClient.class);
	public static String HOST = "127.0.0.1";
	public static int PORT = 9999;
	public static EventLoopGroup group = null;
	public static Bootstrap bootstrap = getBootstrap();
	public static Channel channel = getChannel(HOST,PORT);
	
	/**
	 * 初始化Bootstrap
	 * @return
	 */
	public static final Bootstrap getBootstrap(){
	    group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
				pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
				pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
				pipeline.addLast("idleStateHandler", new IdleStateHandler(20, 20, 10)); //心跳监测 读超时为10s，写超时为10s 全部空闲时间100s
				pipeline.addLast("handler", new TcpClientHandler());
			}
		});
		b.option(ChannelOption.SO_KEEPALIVE, true);
		return b;
	}

	public static final Channel getChannel(String host,int port){
		Channel channel = null;
		try {
			channel = bootstrap.connect(host, port).sync().channel();
		} catch (Exception e) {
			log.info(String.format("连接Server(IP[%s],PORT[%s])失败", host,port));
		    e.printStackTrace();
			return null;
		}
		return channel;
	}

	public static void sendMsg(String msg) throws Exception {
		if(channel==null && !channel.isActive()){
			log.info("重新连接....");
			channel = getChannel(HOST,PORT);
		}
		channel.writeAndFlush(msg).sync();
	}

    public static void main(String[] args) throws Exception {
		try {
		    //循环
			for(int i=0;i<1;i++){
				TcpClient.sendMsg("{\"from\": \"0001\",\"to\": \""+i+1+"\", \"msg\": \"" + i+1 + "\"}");
			}
		    //channel.closeFuture();
		} catch (Exception e) {
			log.error("推送消息出现异常"+e.getMessage());
		}finally{
		    //group.shutdownGracefully(); 
			log.info("操作完成");
		}
    }
}
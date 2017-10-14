package jehc.xtmodules.xtcore.netty.client;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端处理类
 * @author 邓纯杰
 *
 */
public class TcpClientHandler extends SimpleChannelInboundHandler<Object> {
	private static final Logger log = LoggerFactory.getLogger(TcpClientHandler.class);
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)throws Exception {
		log.info("client接收到服务器返回的消息:"+msg);
		System.out.println("client接收到服务器返回的消息:"+msg);
	}
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
            	log.info("READER_IDLE");
                // 超时关闭channel
                ctx.close();
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
            	log.info("WRITER_IDLE");
                // 超时关闭channel
                ctx.close();
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
            	log.info("ALL_IDLE");
                // 发送心跳
                ctx.channel().writeAndFlush("bobo...");
            }
        }
        super.userEventTriggered(ctx, evt); 
    }
}
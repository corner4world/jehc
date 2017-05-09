package jehc.xtmodules.xtcore.netty.server;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务处理类
 * @author 邓纯杰
 *
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter{
	private static final Logger log = LoggerFactory.getLogger(TcpServerHandler.class);
	public void channelRead0(ChannelHandlerContext ctx, Object msg)throws Exception {
		log.info("接收到Client消息:" + msg.toString());
		Channel incoming = ctx.channel();
		//业务处理
		ctx.channel().write(msg.toString());
		ctx.channel().writeAndFlush(new TextWebSocketFrame("[" + incoming.remoteAddress() + "]" + msg.toString()));
	}
	
	/**
	 * channel被激活时调用(Client连接时调用)
	 */
	public void channelActive(ChannelHandlerContext ctx) {
		log.info(ctx.channel().remoteAddress() + "   ----已激活");
		try {
			ctx.channel().writeAndFlush("连接成功...");
			log.info("-------------连接成功...");
		} catch (Exception e) {
			log.error(ctx.channel().remoteAddress() + "   ----连接失败");
		}
	}

	/**
	 * 心跳监听
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state().equals(IdleState.READER_IDLE)) { //读超时
				// 超时关闭channel
				ctx.close();
			} else if (event.state().equals(IdleState.WRITER_IDLE)) { //写超时
				// 超时关闭channel
				ctx.close();
			} else if (event.state().equals(IdleState.ALL_IDLE)) { //
				// 发送心跳
				ctx.channel().writeAndFlush("ping\n");
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	/**
	 * Client失去连接时调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
		if(ctx.channel().isOpen() && ctx.channel().isActive() ){
			log.info("-------------失去连接关闭");
			ctx.close();
		}
	}
}
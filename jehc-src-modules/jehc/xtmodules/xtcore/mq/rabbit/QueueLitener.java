package jehc.xtmodules.xtcore.mq.rabbit;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import jehc.xtmodules.xtcore.mq.rabbit.common.FastJsonMessageConverter;
import jehc.xtmodules.xtcore.mq.rabbit.common.RabbitMsg;

/**
 * 队列监听
 * @author 邓纯杰
 *
 */
public class QueueLitener implements  MessageListener{
	public void onMessage(Message message) {
		FastJsonMessageConverter jsonConv=new FastJsonMessageConverter();
		RabbitMsg m=jsonConv.fromMessage(message, RabbitMsg.class);
		System.out.println(" data :" + m.getContent());
	}

}

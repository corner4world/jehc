package jehc.junitmodules.junit.rabbitjunit;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jehc.xtmodules.xtcore.mq.rabbit.common.RabbitMsg;

public class MqJunit {
	@Test
	public void rabbit(){
		try {
			ApplicationContext  ctx=new ClassPathXmlApplicationContext("classpath:/xtCore/sources/mq/rabbit.xml");
//			AmqpTemplate amqpTemplate=(AmqpTemplate)ctx.getBean("amqpTemplate");
			RabbitTemplate rabbitTemplate=(RabbitTemplate)ctx.getBean("amqpTemplate");
			RabbitMsg m = new RabbitMsg();
			m.setContent("测试消息");
			 //确认消息是否到达broker服务器，也就是只确认是否正确到达exchange中即可，只要正确的到达exchange中，broker即可确认该消息返回给客户端ack。
			rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
			     public void confirm(CorrelationData correlationData, boolean ack) {
			         if (ack) {
			             System.out.println("消息确认成功");
			         } else {
			             //处理丢失的消息（nack）
			            System.out.println("消息确认失败");
			         }
			     }
			});
			rabbitTemplate.convertAndSend("queue_1_k", m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

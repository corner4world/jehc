package jehc.junitmodules.junit.rabbitjunit;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jehc.xtmodules.xtcore.base.BaseJunit;
import jehc.xtmodules.xtcore.util.springutil.GetApplicationContext;
public class MqJunit extends BaseJunit{
	@Test
	public void rabbit(){
//		ApplicationContext  ctx = new ClassPathXmlApplicationContext("classpath*:/jehc/xtmodules/xtcore/sources/mq/rabbit.xml");
		AmqpTemplate rabbitTemplate=(AmqpTemplate)GetApplicationContext.getBean("rabbitTemplate");
		rabbitTemplate.convertAndSend("queue1k", "生产者发送一条紧急通知任务");
	}
}

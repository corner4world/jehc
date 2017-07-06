package jehc.xtmodules.xtcore.mq.rabbit;

/**
 * 队列监听
 * @author 邓纯杰
 *
 */
public class QueueListenter{
	public void receiveMessage(String msg) {
		System.out.println("接收的数据格式:" + msg);
	}

}

package jehc.xtmodules.xtcore.mq.rabbit.interfac;

/**
 * 生产者发送
 * @author dengcj
 *
 */
public interface RabbitProducer {
	/**
     * 发送消息到指定队列
     * @param queueKey
     * @param object
     */
    public void sendDataToQueue(String queueKey, Object object);
}

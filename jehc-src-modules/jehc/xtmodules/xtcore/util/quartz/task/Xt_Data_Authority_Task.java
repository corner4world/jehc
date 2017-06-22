package jehc.xtmodules.xtcore.util.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.base.BaseService;

/**
 * 数据权限Job
 * @author 邓纯杰
 *
 */
public class Xt_Data_Authority_Task  extends Thread{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 业务逻辑处理
	 */
	public void service() {
		new Xt_Data_Authority_Task().start();
	}
	
	public void run(){
		try {
			excute();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void excute(){
		logger.info("----------开始推送数据权限--------------");
		BaseService baseService = new BaseService();
		baseService.addPushDataAuthority();
		logger.info("----------结束推送数据权限--------------");
	}
}

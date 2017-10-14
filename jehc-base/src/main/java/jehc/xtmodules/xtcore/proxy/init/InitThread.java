package jehc.xtmodules.xtcore.proxy.init;
import jehc.xtmodules.xtcore.proxy.shcedule.CheckThread;


public class InitThread {
	/**
	 * 
	 * Web工程启动时，开始检测线程
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public void init(){
		//线程名称
		CheckThread checkThread = new CheckThread("checkThread");
		checkThread.start();
	}
}

package jehc.xtmodules.xtcore.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtmodel.XtModifyRecord;
import jehc.xtmodules.xtservice.XtModifyRecordService;

/**
 * 统一字段变更记录日志
 * @author 邓纯杰
 *
 */
public class BaseXtModifyRecordRun  extends Thread {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private List<XtModifyRecord> recordList;
	//无参构造函数
	public BaseXtModifyRecordRun(){
		
	}
	//有参构造函数
	public BaseXtModifyRecordRun(List<XtModifyRecord> recordList){
		this.recordList = recordList;
	}
	
	public void run(){
		try {
			addXtModifyRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void addXtModifyRecord(){
		try {
			XtModifyRecordService xtModifyRecordService = (XtModifyRecordService)SpringUtil.getBean("xtModifyRecordService");
			if(null != recordList && recordList.size() > 0){
				log.info("----------开始记录变更记录日志--------------");
				xtModifyRecordService.putBatchXtModifyRecord(recordList);
				log.info("----------结束记录变更记录日志--------------");
			}
		} catch (Exception e) {
			log.error("----------记录变更记录失败:"+recordList.toString()+"--------------");
		}
	}
}

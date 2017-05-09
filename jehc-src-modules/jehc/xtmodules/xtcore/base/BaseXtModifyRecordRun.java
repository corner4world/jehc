package jehc.xtmodules.xtcore.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ReflectUtil;
import jehc.xtmodules.xtcore.util.logger.Log4j;
import jehc.xtmodules.xtcore.util.springutil.GetApplicationContext;
import jehc.xtmodules.xtmodel.Xt_Modify_Record;
import jehc.xtmodules.xtservice.Xt_Modify_RecordService;

/**
 * 统一字段变更记录日志
 * @author 邓纯杰
 *
 */
public class BaseXtModifyRecordRun  extends Thread {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private List<Xt_Modify_Record> recordList;
	//无参构造函数
	public BaseXtModifyRecordRun(){
		
	}
	//有参构造函数
	public BaseXtModifyRecordRun(List<Xt_Modify_Record> recordList){
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
			Xt_Modify_RecordService xt_Modify_RecordService = (Xt_Modify_RecordService)GetApplicationContext.getBean("xt_Modify_RecordService");
			if(null != recordList && recordList.size() > 0){
				log.info("----------开始记录变更记录日志--------------");
				xt_Modify_RecordService.putBatchXtModifyRecord(recordList);
				log.info("----------结束记录变更记录日志--------------");
			}
		} catch (Exception e) {
			log.error("----------记录变更记录失败:"+recordList.toString()+"--------------");
		}
	}
	
//	/**
//	 * 数据修改对比统计
//	 * 
//	 * @param oldT 修改前
//	 * @param newT 修改后
//	 * @param className 类名
//	 * @param <T>
//	 * @return
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public static <T> List callBackListUpdateInfo(T oldT, T newT, String className) throws Exception{
//		List<Xt_Modify_Record> list = new ArrayList<Xt_Modify_Record>();
//        Class onwClass = Class.forName(className);
//        Field[] fields = onwClass.getDeclaredFields();
//        for (Field f : fields) {
//            //过滤 static、 final、private static final字段
//            if (f.getModifiers() == 16 || f.getModifiers() == 8
//                    || f.getModifiers() == 26) {
//                continue;
//            }
//            Column annotationColumn = f.getAnnotation(Column.class);
//            if (annotationColumn == null) {
//                continue;
//            }
//            Object oldV = ReflectUtil.getFieldValue(oldT,f.getName(),f.getType());
//            Object newV = ReflectUtil.getFieldValue(newT,f.getName(),f.getType());
//            if (newV != null && !newV.equals(oldV) && StringUtils.isNotBlank(newV.toString())) {
//            	Xt_Modify_Record record = new Xt_Modify_Record();
//            	record.setXt_modify_record_aftervalue(""+newV);
//            	record.setXt_modify_record_beforevalue(""+oldV);
//            	record.setXt_modify_record_ctime(CommonUtils.getSimpleDateFormat());
//            	record.setXt_modify_record_field(f.getName());
//            	record.setXt_modify_record_modules(className);
//            	list.add(record);
//            }
//        }
//        return list;
//    }
}

package jehc.xtmodules.xtcore.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import jehc.xtmodules.xtcore.base.BaseXtModifyRecordRun;
import jehc.xtmodules.xtcore.util.logger.Log4j;
import jehc.xtmodules.xtmodel.Xt_Modify_Record;

/**
 * 实体比较
 * 
 * @author 邓纯杰
 * 
 */
public class BeanCompareUtil {
	Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 数据修改对比统计
	 * 
	 * @param oldT 修改前
	 * @param newT 修改后
	 * @param className 类名
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> String callBackUpdateInfo(T oldT, T newT, String className) throws Exception{
        Class onwClass = Class.forName(className);
        StringBuffer updateInfo = new StringBuffer();
        Field[] fields = onwClass.getDeclaredFields();
        for (Field f : fields) {
            //过滤 static、 final、private static final字段
            if (f.getModifiers() == 16 || f.getModifiers() == 8
                    || f.getModifiers() == 26) {
                continue;
            }
            Object oldV = ReflectUtil.getFieldValue(oldT,f.getName());
            Object newV = ReflectUtil.getFieldValue(newT,f.getName());
            if (newV != null && !newV.equals(oldV) && StringUtils.isNotBlank(newV.toString())) {
            	updateInfo.append(f.getName() + " 从 " +oldV + "修改成" + newV + "<br>");
            }
        }
        return updateInfo.toString();
    }
	
	/**
	 * 数据修改对比统计
	 * 
	 * @param oldT 修改前
	 * @param newT 修改后
	 * @param className 类名
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> List callBackListUpdateInfo(T oldT, T newT, Class<?> c) throws Exception{
		List<Xt_Modify_Record> list = new ArrayList<Xt_Modify_Record>();
        Class onwClass = c.getClass();
        Field[] fields = onwClass.getDeclaredFields();
        for (Field f : fields) {
            //过滤 static、 final、private static final字段
            if (f.getModifiers() == 16 || f.getModifiers() == 8
                    || f.getModifiers() == 26) {
                continue;
            }
            /**
            AnnotationColumn annotationColumn = f.getAnnotation(AnnotationColumn.class);
            if (annotationColumn == null) {
                continue;
            }
            **/
            f.setAccessible(true);
            Object oldV = ReflectUtil.getFieldValue(oldT,f.getName());
            Object newV = ReflectUtil.getFieldValue(newT,f.getName());
            String oValue = "";
            if(null != oldV){
            	oValue = oValue.toString();
            }
            String nValue = "";
            if(null != newV){
            	nValue = newV.toString();
            }
//            if (nValue != null && !nValue.equals(oValue) && StringUtils.isNotBlank(oValue.toString())) {
            if (!nValue.equals(oValue)) {
            	Xt_Modify_Record record = new Xt_Modify_Record();
            	record.setXt_modify_record_aftervalue(""+newV);
            	record.setXt_modify_record_beforevalue(""+oldV);
            	record.setXt_modify_record_ctime(CommonUtils.getSimpleDateFormat());
            	record.setXt_modify_record_field(f.getName());
            	record.setXt_modify_record_modules(onwClass.getName());
            	list.add(record);
            }
            
            /**
            if ((null != f.get(oldT) && 
            	StringUtils.isNotEmpty(f.get(oldT).toString()) && 
            	!f.get(oldT).equals(f.get(newT))) || (null != f.get(newT) && 
            	StringUtils.isNotEmpty(f.get(newT).toString())  && 
            	!f.get(newT).equals(f.get(oldT)))) {  
            	System.out.println(f.get(newT).toString());
            	System.out.println(f.getName());
            	
            	Object oldV = ReflectUtil.getFieldValue(oldT,f.getName());
                Object newV = ReflectUtil.getFieldValue(newT,f.getName());
                String oValue = "";
                if(null != oldV){
                	oValue = oValue.toString();
                }
                String nValue = "";
                if(null != newV){
                	nValue = newV.toString();
                }
            	Xt_Modify_Record record = new Xt_Modify_Record();
            	record.setXt_modify_record_aftervalue(""+nValue);
            	record.setXt_modify_record_beforevalue(""+oldV);
            	record.setXt_modify_record_ctime(CommonUtils.getSimpleDateFormat());
            	record.setXt_modify_record_field(f.getName());
            	record.setXt_modify_record_modules(onwClass.getName());
            	list.add(record);
            }
            **/
        }
        return list;
    }
	
	/**
	 * 执行变更记录
	 * @param <T>
	 * @param oldT
	 * @param newT
	 * @param className
	 */
	@SuppressWarnings("unchecked")
	public static <T> void aRecord(T oldT, T newT, String modules,String business_id){
		try {
			JSONObject oldJson = JsonUtil.toJsonObj(oldT);
			JSONObject newJson = JsonUtil.toJsonObj(newT);
			List<Xt_Modify_Record> list = new ArrayList<Xt_Modify_Record>();
			Iterator iterator = oldJson.keys();
			while(iterator.hasNext()){
	            String key = (String) iterator.next();
	            String oldV = oldJson.getString(key);
	            String newV = newJson.getString(key);
	            if(!oldV.equals(newV)){
	            	Xt_Modify_Record record = new Xt_Modify_Record();
	            	record.setXt_modify_record_aftervalue(""+newV);
	            	record.setXt_modify_record_beforevalue(""+oldV);
	            	record.setXt_modify_record_ctime(CommonUtils.getSimpleDateFormat());
	            	record.setXt_modify_record_field(key);
	            	record.setXt_modify_record_modules(modules);
	            	list.add(record);
	            }
			}
			for(int i = 0; i < list.size(); i++){
				list.get(i).setXt_modify_record_id(UUID.toUUID());
				list.get(i).setBusiness_id(business_id);
				list.get(i).setXt_userinfo_id(CommonUtils.getXtUid());
			}
			new BaseXtModifyRecordRun(list).run();
		} catch (Exception e) {
		}
	}
	
	/**
	 * 执行变更记录并过滤字段
	 * @param <T>
	 * @param oldT
	 * @param newT
	 * @param className
	 */
	@SuppressWarnings("unchecked")
	public static <T> void aRecord(T oldT, T newT, String modules,String business_id,List<String> fieldList){
		try {
			JSONObject oldJson = JsonUtil.toJsonObj(oldT);
			JSONObject newJson = JsonUtil.toJsonObj(newT);
			List<Xt_Modify_Record> list = new ArrayList<Xt_Modify_Record>();
			Iterator iterator = oldJson.keys();
			while(iterator.hasNext()){
	            String key = (String) iterator.next();
	            if(!fieldList.isEmpty() && fieldList.size() > 0){
	            	for(String field:fieldList){
	            		if(field.equals(key)){
	            			String oldV = oldJson.getString(key);
	        	            String newV = newJson.getString(key);
	        	            if(!oldV.equals(newV)){
	        	            	Xt_Modify_Record record = new Xt_Modify_Record();
	        	            	record.setXt_modify_record_aftervalue(""+newV);
	        	            	record.setXt_modify_record_beforevalue(""+oldV);
	        	            	record.setXt_modify_record_ctime(CommonUtils.getSimpleDateFormat());
	        	            	record.setXt_modify_record_field(key);
	        	            	record.setXt_modify_record_modules(modules);
	        	            	list.add(record);
	        	            }
	            		}
	            	}
	            }
			}
			for(int i = 0; i < list.size(); i++){
				list.get(i).setXt_modify_record_id(UUID.toUUID());
				list.get(i).setBusiness_id(business_id);
				list.get(i).setXt_userinfo_id(CommonUtils.getXtUid());
			}
			new BaseXtModifyRecordRun(list).run();
		} catch (Exception e) {
		}
	}
}

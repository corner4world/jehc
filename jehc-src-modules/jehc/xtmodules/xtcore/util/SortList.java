package jehc.xtmodules.xtcore.util;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import jehc.xtmodules.xtmodel.XtUserinfo;



/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author 邓纯杰
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SortList<E> {
	/** 
	 * 对List对象按照某个成员变量进行排序 
	 * @param list       List对象 
	 * @param sortField  排序的属性名称 
	 * @param sortMode   排序方式：ASC，DESC 任选其一 
	 */  
	public static <T> void Sort(List<T> list, final String sortField, final String sortMode) {  
	    if(list == null || list.size() < 2) {  
	        return;  
	    }  
	    Collections.sort(list, new Comparator<T>() {  
	        public int compare(T o1, T o2) {  
	            try {  
	                Class clazz = o1.getClass();  
	                Field field = clazz.getDeclaredField(sortField); //获取成员变量  
	                field.setAccessible(true); //设置成可访问状态  
	                String typeName = field.getType().getName().toLowerCase(); //转换成小写  
	  
	                Object v1 = field.get(o1); //获取field的值  
	                Object v2 = field.get(o2); //获取field的值  
	                boolean ASC_order = (sortMode == null || "ASC".equalsIgnoreCase(sortMode));  
	                //判断字段数据类型，并比较大小  
	                if(typeName.endsWith("string")) {  
	                    String value1 = v1.toString();  
	                    String value2 = v2.toString();  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("short")) {  
	                    Short value1 = Short.parseShort(v1.toString());  
	                    Short value2 = Short.parseShort(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("byte")) {  
	                    Byte value1 = Byte.parseByte(v1.toString());  
	                    Byte value2 = Byte.parseByte(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("char")) {  
	                    Integer value1 = (int)(v1.toString().charAt(0));  
	                    Integer value2 = (int)(v2.toString().charAt(0));  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("int") || typeName.endsWith("integer")) {  
	                    Integer value1 = Integer.parseInt(v1.toString());  
	                    Integer value2 = Integer.parseInt(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("long")) {  
	                    Long value1 = Long.parseLong(v1.toString());  
	                    Long value2 = Long.parseLong(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("float")) {  
	                    Float value1 = Float.parseFloat(v1.toString());  
	                    Float value2 = Float.parseFloat(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("double")) {  
	                    Double value1 = Double.parseDouble(v1.toString());  
	                    Double value2 = Double.parseDouble(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("boolean")) {  
	                    Boolean value1 = Boolean.parseBoolean(v1.toString());  
	                    Boolean value2 = Boolean.parseBoolean(v2.toString());  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("date")) {  
	                    Date value1 = (Date)(v1);  
	                    Date value2 = (Date)(v2);  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else if(typeName.endsWith("timestamp")) {  
	                    Timestamp value1 = (Timestamp)(v1);  
	                    Timestamp value2 = (Timestamp)(v2);  
	                    return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);  
	                }  
	                else {  
	                    //调用对象的compareTo()方法比较大小  
	                    Method method = field.getType().getDeclaredMethod("compareTo", new Class[]{field.getType()});  
	                    method.setAccessible(true); //设置可访问权限  
	                    int result  = (Integer)method.invoke(v1, new Object[]{v2});  
	                    return ASC_order ? result : result*(-1);  
	                }  
	            }  
	            catch (Exception e) {  
	                String err = e.getLocalizedMessage();  
	                System.out.println(err);  
	                e.printStackTrace();  
	            }  
	            return 0; //未知类型，无法比较大小  
	        }  
	    }); 
	}
	
	public static void main(String[]args){
		SortList<XtUserinfo> sortList = new SortList<XtUserinfo>();  
		List<XtUserinfo> xt_UserinfoList = new ArrayList<XtUserinfo>();
		sortList.Sort(xt_UserinfoList, "xt_userinfo_workYear", "desc");
		System.out.println(xt_UserinfoList.get(xt_UserinfoList.size()-1).getXt_userinfo_workYear());  
	}
}


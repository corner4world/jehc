package jehc.xtmodules.xtcore.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jehc.xtmodules.xtcore.allutils.StringUtil;

/**
 * 遍历Map的多种方法
 * @author 邓纯杰
 *
 */
public class MapUtils {
	/**
	 * 方法一
	 * 获取Key Value或者其他的对象 Object可以为对象如Students然后通过Students.属性名获取值
	 * @param condition
	 */
	public static void entrySet(Map<String, Object> condition){
		Set<Map.Entry<String, Object>> set = condition.entrySet();    
	    for(Iterator<Map.Entry<String, Object>> it = set.iterator();it.hasNext();){   
		  Map.Entry<String, Object> entry = (Map.Entry<String, Object>)it.next();      
		  entry.getKey();
		  entry.getValue();
	    }
	}
	/**
	 * 方法二
	 * 利用keyset进行遍历，
	 * 它的优点在于可以根据你所想要的key值得到你想要的 values，
	 * 更具灵活性！！
	 */
	@SuppressWarnings("unchecked")
	public static void workByKeySet(Map<String, Object> map){
		Set<String> key = map.keySet();
		for(Iterator it = key.iterator(); it.hasNext();){
			String s = (String) it.next();
			System.out.println(map.get(s));
		}
	}
	
	/**
	 * 方法三
	 * 最常规的一种遍历方法，
	 * 最常规就是最常用的
	 * @param map
	 */
	public static void work(Map<String, Object> map){
	   Collection<Object> c = map.values();     
	   Iterator<Object> it = c.iterator();  
	   while(it.hasNext()){
		   System.out.println(it.next()); 
	   }
	}
	
	/** 
     * 移除map中空key或者value空值 
     * @param map 
     */  
    public static void removeNullEntry(Map map){  
        removeNullKey(map);  
        removeNullValue(map);  
    }  
      
    /** 
     * 移除map的空key 
     * @param map 
     * @return 
     */  
    public static void removeNullKey(Map map){  
        Set set = map.keySet();  
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {  
            Object obj = (Object) iterator.next();  
            remove(obj, iterator);  
        }  
    }  
      
    /** 
     * 移除map中的value空值 
     * @param map 
     * @return 
     */  
    public static void removeNullValue(Map map){  
        Set set = map.keySet();  
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {  
            Object obj = (Object) iterator.next();  
            Object value =(Object)map.get(obj);  
            remove(value, iterator);  
        }  
    }  
      
    /** 
     * Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。  
     * Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，这个索引表的内容不会同步改变， 
     * 所以当索引指针往后移动的时候就找不到要迭代的对象，所以按照 fail-fast 原则 Iterator 会马上抛出 java.util.ConcurrentModificationException 异常。 
     * 所以 Iterator 在工作的时候是不允许被迭代的对象被改变的。 
     * 但你可以使用 Iterator 本身的方法 remove() 来删除对象， Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性。 
     * @param obj 
     * @param iterator 
     */  
    private static void remove(Object obj,Iterator iterator){  
        if(obj instanceof String){  
            String str = (String)obj;  
            if(StringUtil.isEmpty(str)){  
                iterator.remove();  
            }  
        }else if(obj instanceof Collection){  
            Collection col = (Collection)obj;  
            if(col==null||col.isEmpty()){  
                iterator.remove();  
            }  
              
        }else if(obj instanceof Map){  
            Map temp = (Map)obj;  
            if(temp==null || temp.values().isEmpty()){  
                iterator.remove();  
            }  
              
        }else if(obj instanceof Object[]){  
            Object[] array =(Object[])obj;  
            if(array==null||array.length<=0){  
                iterator.remove();  
            }  
        }else{  
            if(obj==null){  
                iterator.remove();  
            }  
        }  
    }  
      
      
    public static void main(String[] args) {  
        Map map = new HashMap();  
        /**
        map.put(1, "第一个值是数字");  
        map.put("2", "第2个值是字符串");  
        map.put(new String[]{"1","2"},"第3个值是数组");  
        map.put(new ArrayList(), "第4个值是List");  
        map.put(new HashMap(), "Map 无值");  
        map.put("5", "第5个");  
        **/
        map.put("6",null);  
        map.put("7", "");  
        map.put("8", "  ");  
        /**
        System.out.println(map);  
        MapUtils.removeNullKey(map);  
        System.out.println();  
        System.out.println(map); 
        **/ 
        MapUtils.removeNullValue(map);  
        System.out.println(map);  
    }  
}

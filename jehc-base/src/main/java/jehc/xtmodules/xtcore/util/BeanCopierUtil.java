package jehc.xtmodules.xtcore.util;

import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReentrantLock;  
import net.sf.cglib.beans.BeanCopier;  

/**
 * 深度复制工具类
 * @author admistrator
 *
 */
public class BeanCopierUtil {
	private static Lock initLock =new ReentrantLock();  
    private static Map<String,BeanCopier> beanCopierMap =new HashMap<String,BeanCopier>();  
  
    /** 
     * 初始化 BeanCopier 
     * @param source 
     * @param target 
     * @return 
     */  
    private static BeanCopier initCopier(Class source,Class target){  
        initLock.lock();  
        BeanCopier find = beanCopierMap.get(source.getName()+"_"+target.getName());  
        if(find!=null){  
            initLock.unlock();  
            return find;  
        }  
        BeanCopier beanCopier = BeanCopier.create(source,target,false);  
        beanCopierMap.put(source.getName()+"_"+target.getName(),beanCopier);  
        initLock.unlock();  
        return beanCopier;  
    }  
  
  
    /** 
     * 获取BeanCopier 
     * @param source 
     * @param target 
     * @return 
     */  
    private static  BeanCopier getBeanCopier(Class source,Class target){  
        BeanCopier beanCopier = beanCopierMap.get(source.getClass().getName()+"_"+target.getName());  
        if(beanCopier!=null){  
            return beanCopier;  
        }  
        return initCopier(source,target);  
    }  
  
  
    /** 
     * Pojo 类型转换（浅复制，字段名&类型相同则被复制） 
     * @param source 
     * @param targetClass 
     * @param <T> 
     * @return 
     */  
    public static <T> T convert(Object source, Class<T> targetClass){  
        if(source==null){  
            return null;  
        }  
        BeanCopier beanCopier = getBeanCopier(source.getClass(),targetClass);  
        try {  
            T target = targetClass.newInstance();  
            beanCopier.copy(source,target,null);  
            return target;  
  
        }catch (Exception e){  
            throw new RuntimeException("对象拷贝失败"+source+"_"+targetClass);  
        }  
    }  
  
    /** 
     * Pojo 类型转换（浅复制，字段名&类型相同则被复制） 
     * @param source 
     * @param targetClass 
     * @param <E> 
     * @return 
     */  
    public static <E> List<E> convert(List source, Class<E> targetClass){  
        if(source==null){  
            return null;  
        }  
        try {  
            if(source.isEmpty()){  
                return source.getClass().newInstance();  
            }  
            List result = source.getClass().newInstance();  
  
            for(Object each: source){  
                result.add(convert(each,targetClass));  
            }  
            return result;  
        }catch (Exception e){  
            throw new RuntimeException("对象拷贝失败"+source+"_"+targetClass);  
        }  
    }  
  
    public static void main(String[] args) {  
//    	BeanCopierUtil.convert(source, targetClass);
//    	TSysRole tSysRole =new TSysRole();  
//        tSysRole.setRoleDesc("12346");  
//        tSysRole.setRoleName("adcasdasd");  
//        tSysRole.setRoleId(187);  
//        RoleVo result = PojoConvertUtil.convert(tSysRole, RoleVo.class);  
  
//        List<TSysRole> org =  new ArrayList<>();  
//        org.add(tSysRole);  
  
//        TSysUser tSysUser =new TSysUser();  
//        tSysUser.setRole(tSysRole);  
//  
//        UserVo tar = BeanCopierUtil.convert(tSysUser,UserVo.class);  
//        System.out.println(tar); 
    } 
}

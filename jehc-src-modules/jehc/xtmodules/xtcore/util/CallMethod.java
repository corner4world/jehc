package jehc.xtmodules.xtcore.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 通过反射执行类方法
 * @author 邓纯杰
 * 
 */
public class CallMethod {
	private static Logger logger = Logger.getLogger(CallMethod.class);
	@SuppressWarnings("unchecked")
	public static Map callMethod(String clsName,String method, Map<String,Object> dataIn)
    {

        Class cls = null;
        try
        {
            cls = Class.forName(clsName);
        }
        catch (ClassNotFoundException e)
        {
        	logger.error("调用类["+clsName+"]出现异常,原因:"+e);
        }
        Class partypes[] = new Class[1];
        partypes[0] = Map.class;
        Method meth = null;
        try
        {
            meth = cls.getMethod(method, partypes);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }

        Object returnValue = null;
        try
        {
        	returnValue = meth.invoke(cls.newInstance(), dataIn);
        }
        catch (InstantiationException e) {
			e.printStackTrace();
		}
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }

        Map dataOut = (Map) returnValue;

        return dataOut;
    }
}

package jehc.xtmodules.xtcore.threadlocalutil;


public class ThreadLocalUtil {
	private static ThreadLocal<ThreadVariables> THREAD_VARIABLES = new ThreadLocal<ThreadVariables>() {
        protected ThreadVariables initialValue() {
            return new ThreadVariables();
        }
    };
 
    public static Object getThreadVariable(String name) {
        return THREAD_VARIABLES.get().get(name);
    }
 
    public static Object getThreadVariable(String name, InitialValue initialValue) {
        Object o = THREAD_VARIABLES.get().get(name);
        if (o == null) {
            THREAD_VARIABLES.get().put(name, initialValue.create());
            return getThreadVariable(name);
        } else {
            return o;
        }
    }
 
    public static void setThreadVariable(String name, Object value) {
        THREAD_VARIABLES.get().put(name, value);
    }
 
    public static void destroy() {
    	if(null != THREAD_VARIABLES){
    		THREAD_VARIABLES.remove();
    	}
    	THREAD_VARIABLES = null;
    }
}

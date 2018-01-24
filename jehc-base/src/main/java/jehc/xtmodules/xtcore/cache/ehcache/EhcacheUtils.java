package jehc.xtmodules.xtcore.cache.ehcache;

public interface EhcacheUtils {
	 /** 
     * 获取给定Key的Read锁 
     * @param key 
     */  
    public void acquireReadLockOnKey(Object key);
    
    /** 
     * 获取给定Key的Write锁 
     * @param key 
     */  
    public void acquireWriteLockOnKey(Object key);  
    
    /** 
     * 尝试着获取给定Key的Read锁，如果在给定timeout时间内还没有获取到对应的Read锁，则返回false，否则返回true。 
     * @param key 
     * @param timeout 超时时间，单位是毫秒 
     * @return表示是否获取到了对应的Read锁 
     * @throws InterruptedException 
     */  
    public boolean tryReadLockOnKey(Object key, long timeout) throws InterruptedException;  
    
    /** 
     * 尝试着获取给定Key的Write锁，如果在给定timeout时间内还没有获取到对应的Write锁，则返回false，否则返回true。 
     * @param key 
     * @param timeout 超时时间，单位是毫秒 
     * @return表示是否获取到了对应的Write锁 
     * @throws InterruptedException 
     */  
    public boolean tryWriteLockOnKey(Object key, long timeout) throws InterruptedException;  
   
    /** 
     * 释放所持有的给定Key的Read锁 
     * @param key 
     */  
    public void releaseReadLockOnKey(Object key);  
   
    /** 
     * 释放所持有的给定Key的Write锁 
     * @param key 
     */  
    public void releaseWriteLockOnKey(Object key);  
}

package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Appkey_AppsecretDao;
import jehc.xtmodules.xtmodel.Xt_Appkey_Appsecret;
import jehc.xtmodules.xtservice.Xt_Appkey_AppsecretService;

/**
* xt_appkey_appsecret 
* 2016-08-28 14:37:16  邓纯杰
*/
@Service("xt_Appkey_AppsecretService")
public class Xt_Appkey_AppsecretServiceImpl extends BaseService implements Xt_Appkey_AppsecretService{
	@Autowired
	private Xt_Appkey_AppsecretDao xt_Appkey_AppsecretDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Appkey_Appsecret> getXtAppkeyAppsecretListByCondition(Map<String,Object> condition){
		try{
			return xt_Appkey_AppsecretDao.getXtAppkeyAppsecretListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_appkey_appsecret_id 
	* @return
	*/
	public Xt_Appkey_Appsecret getXtAppkeyAppsecretById(String xt_appkey_appsecret_id){
		try{
			return xt_Appkey_AppsecretDao.getXtAppkeyAppsecretById(xt_appkey_appsecret_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int addXtAppkeyAppsecret(Xt_Appkey_Appsecret xt_Appkey_Appsecret){
		int i = 0;
		try {
			i = xt_Appkey_AppsecretDao.addXtAppkeyAppsecret(xt_Appkey_Appsecret);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int updateXtAppkeyAppsecret(Xt_Appkey_Appsecret xt_Appkey_Appsecret){
		int i = 0;
		try {
			i = xt_Appkey_AppsecretDao.updateXtAppkeyAppsecret(xt_Appkey_Appsecret);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtAppkeyAppsecret(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Appkey_AppsecretDao.delXtAppkeyAppsecret(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

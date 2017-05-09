package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Appkey_AppsecretDao;
import jehc.xtmodules.xtmodel.Xt_Appkey_Appsecret;

/**
* xt_appkey_appsecret 
* 2016-08-28 14:37:16  邓纯杰
*/
@Repository("xt_Appkey_AppsecretDao")
public class Xt_Appkey_AppsecretDaoImpl  extends BaseDaoImpl implements Xt_Appkey_AppsecretDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Appkey_Appsecret> getXtAppkeyAppsecretListByCondition(Map<String,Object> condition){
		return (List<Xt_Appkey_Appsecret>)this.getList("getXtAppkeyAppsecretListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_appkey_appsecret_id 
	* @return
	*/
	public Xt_Appkey_Appsecret getXtAppkeyAppsecretById(String xt_appkey_appsecret_id){
		return (Xt_Appkey_Appsecret)this.get("getXtAppkeyAppsecretById", xt_appkey_appsecret_id);
	}
	/**
	* 添加
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int addXtAppkeyAppsecret(Xt_Appkey_Appsecret xt_Appkey_Appsecret){
		return this.add("addXtAppkeyAppsecret", xt_Appkey_Appsecret);
	}
	/**
	* 修改
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int updateXtAppkeyAppsecret(Xt_Appkey_Appsecret xt_Appkey_Appsecret){
		return this.update("updateXtAppkeyAppsecret", xt_Appkey_Appsecret);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtAppkeyAppsecret(Map<String,Object> condition){
		return this.del("delXtAppkeyAppsecret", condition);
	}
}

package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Appkey_Appsecret;

/**
* xt_appkey_appsecret 
* 2016-08-28 14:37:16  邓纯杰
*/
public interface Xt_Appkey_AppsecretDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Appkey_Appsecret> getXtAppkeyAppsecretListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_appkey_appsecret_id 
	* @return
	*/
	public Xt_Appkey_Appsecret getXtAppkeyAppsecretById(String xt_appkey_appsecret_id);
	/**
	* 添加
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int addXtAppkeyAppsecret(Xt_Appkey_Appsecret xt_Appkey_Appsecret);
	/**
	* 修改
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int updateXtAppkeyAppsecret(Xt_Appkey_Appsecret xt_Appkey_Appsecret);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtAppkeyAppsecret(Map<String,Object> condition);
}

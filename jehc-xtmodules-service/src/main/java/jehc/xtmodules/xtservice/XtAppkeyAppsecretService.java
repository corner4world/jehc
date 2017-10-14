package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtAppkeyAppsecret;

/**
* xt_appkey_appsecret 
* 2016-08-28 14:37:16  邓纯杰
*/
public interface XtAppkeyAppsecretService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtAppkeyAppsecret> getXtAppkeyAppsecretListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_appkey_appsecret_id 
	* @return
	*/
	public XtAppkeyAppsecret getXtAppkeyAppsecretById(String xt_appkey_appsecret_id);
	/**
	* 添加
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int addXtAppkeyAppsecret(XtAppkeyAppsecret xt_Appkey_Appsecret);
	/**
	* 修改
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int updateXtAppkeyAppsecret(XtAppkeyAppsecret xt_Appkey_Appsecret);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtAppkeyAppsecret(Map<String,Object> condition);
}

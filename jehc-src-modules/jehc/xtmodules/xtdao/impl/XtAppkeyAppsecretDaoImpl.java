package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtAppkeyAppsecretDao;
import jehc.xtmodules.xtmodel.XtAppkeyAppsecret;

/**
* xt_appkey_appsecret 
* 2016-08-28 14:37:16  邓纯杰
*/
@Repository("xtAppkeyAppsecretDao")
public class XtAppkeyAppsecretDaoImpl  extends BaseDaoImpl implements XtAppkeyAppsecretDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtAppkeyAppsecret> getXtAppkeyAppsecretListByCondition(Map<String,Object> condition){
		return (List<XtAppkeyAppsecret>)this.getList("getXtAppkeyAppsecretListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_appkey_appsecret_id 
	* @return
	*/
	public XtAppkeyAppsecret getXtAppkeyAppsecretById(String xt_appkey_appsecret_id){
		return (XtAppkeyAppsecret)this.get("getXtAppkeyAppsecretById", xt_appkey_appsecret_id);
	}
	/**
	* 添加
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int addXtAppkeyAppsecret(XtAppkeyAppsecret xt_Appkey_Appsecret){
		return this.add("addXtAppkeyAppsecret", xt_Appkey_Appsecret);
	}
	/**
	* 修改
	* @param xt_appkey_appsecret 
	* @return
	*/
	public int updateXtAppkeyAppsecret(XtAppkeyAppsecret xt_Appkey_Appsecret){
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

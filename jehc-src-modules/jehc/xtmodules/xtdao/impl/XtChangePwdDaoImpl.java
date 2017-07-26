package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtChangePwdDao;
import jehc.xtmodules.xtmodel.XtChangePwd;

/**
* 密码找回中心 
* 2016-10-21 16:41:55  邓纯杰
*/
@Repository("xtChangePwdDao")
public class XtChangePwdDaoImpl  extends BaseDaoImpl implements XtChangePwdDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtChangePwd> getXtChangePwdListByCondition(Map<String,Object> condition){
		return (List<XtChangePwd>)this.getList("getXtChangePwdListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_change_pwd_id 
	* @return
	*/
	public XtChangePwd getXtChangePwdById(String xt_change_pwd_id){
		return (XtChangePwd)this.get("getXtChangePwdById", xt_change_pwd_id);
	}
	/**
	* 添加
	* @param xt_change_pwd 
	* @return
	*/
	public int addXtChangePwd(XtChangePwd xt_Change_Pwd){
		return this.add("addXtChangePwd", xt_Change_Pwd);
	}
	/**
	* 修改
	* @param xt_change_pwd 
	* @return
	*/
	public int updateXtChangePwd(XtChangePwd xt_Change_Pwd){
		return this.update("updateXtChangePwd", xt_Change_Pwd);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtChangePwd(Map<String,Object> condition){
		return this.del("delXtChangePwd", condition);
	}
}

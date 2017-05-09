package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Change_PwdDao;
import jehc.xtmodules.xtmodel.Xt_Change_Pwd;

/**
* 密码找回中心 
* 2016-10-21 16:41:55  邓纯杰
*/
@Repository("xt_Change_PwdDao")
public class Xt_Change_PwdDaoImpl  extends BaseDaoImpl implements Xt_Change_PwdDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Change_Pwd> getXtChangePwdListByCondition(Map<String,Object> condition){
		return (List<Xt_Change_Pwd>)this.getList("getXtChangePwdListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_change_pwd_id 
	* @return
	*/
	public Xt_Change_Pwd getXtChangePwdById(String xt_change_pwd_id){
		return (Xt_Change_Pwd)this.get("getXtChangePwdById", xt_change_pwd_id);
	}
	/**
	* 添加
	* @param xt_change_pwd 
	* @return
	*/
	public int addXtChangePwd(Xt_Change_Pwd xt_Change_Pwd){
		return this.add("addXtChangePwd", xt_Change_Pwd);
	}
	/**
	* 修改
	* @param xt_change_pwd 
	* @return
	*/
	public int updateXtChangePwd(Xt_Change_Pwd xt_Change_Pwd){
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

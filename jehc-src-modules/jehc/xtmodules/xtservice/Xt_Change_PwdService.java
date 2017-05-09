package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Change_Pwd;

/**
* 密码找回中心 
* 2016-10-21 16:41:55  邓纯杰
*/
public interface Xt_Change_PwdService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Change_Pwd> getXtChangePwdListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_change_pwd_id 
	* @return
	*/
	public Xt_Change_Pwd getXtChangePwdById(String xt_change_pwd_id);
	/**
	* 添加
	* @param xt_change_pwd 
	* @return
	*/
	public int addXtChangePwd(Xt_Change_Pwd xt_Change_Pwd);
	/**
	* 修改
	* @param xt_change_pwd 
	* @return
	*/
	public int updateXtChangePwd(Xt_Change_Pwd xt_Change_Pwd);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtChangePwd(Map<String,Object> condition);
}

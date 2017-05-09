package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_SmsDao;
import jehc.xtmodules.xtmodel.Xt_Sms;

/**
* 短信配置表 
* 2015-06-04 13:35:07  邓纯杰
*/
@Repository("xt_SmsDao")
public class Xt_SmsDaoImpl  extends BaseDaoImpl implements Xt_SmsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Sms> getXtSmsListByCondition(Map<String,Object> condition){
		return (List<Xt_Sms>)this.getList("getXtSmsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_sms_id 
	* @return
	*/
	public Xt_Sms getXtSmsById(String xt_sms_id){
		return (Xt_Sms)this.get("getXtSmsById", xt_sms_id);
	}
	/**
	* 添加
	* @param xt_sms 
	* @return
	*/
	public int addXtSms(Xt_Sms xt_Sms){
		return this.add("addXtSms", xt_Sms);
	}
	/**
	* 修改
	* @param xt_sms 
	* @return
	*/
	public int updateXtSms(Xt_Sms xt_Sms){
		return this.update("updateXtSms", xt_Sms);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtSms(Map<String,Object> condition){
		return this.del("delXtSms", condition);
	}
}

package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Sms;

/**
* 短信配置表 
* 2015-06-04 13:35:07  邓纯杰
*/
public interface Xt_SmsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Sms> getXtSmsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_sms_id 
	* @return
	*/
	public Xt_Sms getXtSmsById(String xt_sms_id);
	/**
	* 添加
	* @param xt_sms 
	* @return
	*/
	public int addXtSms(Xt_Sms xt_Sms);
	/**
	* 修改
	* @param xt_sms 
	* @return
	*/
	public int updateXtSms(Xt_Sms xt_Sms);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtSms(Map<String,Object> condition);
}

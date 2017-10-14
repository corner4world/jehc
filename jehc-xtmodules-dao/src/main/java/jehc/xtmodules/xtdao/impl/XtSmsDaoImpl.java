package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtSmsDao;
import jehc.xtmodules.xtmodel.XtSms;

/**
* 短信配置表 
* 2015-06-04 13:35:07  邓纯杰
*/
@Repository("xtSmsDao")
public class XtSmsDaoImpl  extends BaseDaoImpl implements XtSmsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtSms> getXtSmsListByCondition(Map<String,Object> condition){
		return (List<XtSms>)this.getList("getXtSmsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_sms_id 
	* @return
	*/
	public XtSms getXtSmsById(String xt_sms_id){
		return (XtSms)this.get("getXtSmsById", xt_sms_id);
	}
	/**
	* 添加
	* @param xt_sms 
	* @return
	*/
	public int addXtSms(XtSms xt_Sms){
		return this.add("addXtSms", xt_Sms);
	}
	/**
	* 修改
	* @param xt_sms 
	* @return
	*/
	public int updateXtSms(XtSms xt_Sms){
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

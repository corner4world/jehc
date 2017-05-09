package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_SmsDao;
import jehc.xtmodules.xtmodel.Xt_Sms;
import jehc.xtmodules.xtservice.Xt_SmsService;

/**
* 短信配置表 
* 2015-06-04 13:35:07  邓纯杰
*/
@Service("xt_SmsService")
public class Xt_SmsServiceImpl extends BaseService implements Xt_SmsService{
	@Autowired
	private Xt_SmsDao xt_SmsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Sms> getXtSmsListByCondition(Map<String,Object> condition){
		try{
			return xt_SmsDao.getXtSmsListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_sms_id 
	* @return
	*/
	public Xt_Sms getXtSmsById(String xt_sms_id){
		try{
			return xt_SmsDao.getXtSmsById(xt_sms_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_sms 
	* @return
	*/
	public int addXtSms(Xt_Sms xt_Sms){
		int i = 0;
		try {
			i = xt_SmsDao.addXtSms(xt_Sms);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_sms 
	* @return
	*/
	public int updateXtSms(Xt_Sms xt_Sms){
		int i = 0;
		try {
			i = xt_SmsDao.updateXtSms(xt_Sms);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtSms(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_SmsDao.delXtSms(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

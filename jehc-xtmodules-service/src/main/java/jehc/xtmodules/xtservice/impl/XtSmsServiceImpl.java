package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtSmsDao;
import jehc.xtmodules.xtmodel.XtSms;
import jehc.xtmodules.xtservice.XtSmsService;

/**
* 短信配置表 
* 2015-06-04 13:35:07  邓纯杰
*/
@Service("xtSmsService")
public class XtSmsServiceImpl extends BaseService implements XtSmsService{
	@Autowired
	private XtSmsDao xtSmsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtSms> getXtSmsListByCondition(Map<String,Object> condition){
		try{
			return xtSmsDao.getXtSmsListByCondition(condition);
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
	public XtSms getXtSmsById(String xt_sms_id){
		try{
			return xtSmsDao.getXtSmsById(xt_sms_id);
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
	public int addXtSms(XtSms xt_Sms){
		int i = 0;
		try {
			i = xtSmsDao.addXtSms(xt_Sms);
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
	public int updateXtSms(XtSms xt_Sms){
		int i = 0;
		try {
			i = xtSmsDao.updateXtSms(xt_Sms);
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
			i = xtSmsDao.delXtSms(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

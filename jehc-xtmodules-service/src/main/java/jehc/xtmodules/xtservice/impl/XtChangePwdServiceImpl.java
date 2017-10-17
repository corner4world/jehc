package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtChangePwdDao;
import jehc.xtmodules.xtmodel.XtChangePwd;
import jehc.xtmodules.xtservice.XtChangePwdService;

/**
* 密码找回中心 
* 2016-10-21 16:41:55  邓纯杰
*/
@Service("xtChangePwdService")
public class XtChangePwdServiceImpl extends BaseService implements XtChangePwdService{
	@Autowired
	private XtChangePwdDao xtChangePwdDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtChangePwd> getXtChangePwdListByCondition(Map<String,Object> condition){
		try{
			return xtChangePwdDao.getXtChangePwdListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_change_pwd_id 
	* @return
	*/
	public XtChangePwd getXtChangePwdById(String xt_change_pwd_id){
		try{
			return xtChangePwdDao.getXtChangePwdById(xt_change_pwd_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_change_pwd 
	* @return
	*/
	public int addXtChangePwd(XtChangePwd xt_Change_Pwd){
		int i = 0;
		try {
			i = xtChangePwdDao.addXtChangePwd(xt_Change_Pwd);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_change_pwd 
	* @return
	*/
	public int updateXtChangePwd(XtChangePwd xt_Change_Pwd){
		int i = 0;
		try {
			i = xtChangePwdDao.updateXtChangePwd(xt_Change_Pwd);
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
	public int delXtChangePwd(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtChangePwdDao.delXtChangePwd(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

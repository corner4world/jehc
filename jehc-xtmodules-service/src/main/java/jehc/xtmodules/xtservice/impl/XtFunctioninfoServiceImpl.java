package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtDataAuthorityDao;
import jehc.xtmodules.xtdao.XtDataAuthorityDefaultDao;
import jehc.xtmodules.xtdao.XtDataAuthorityDepartDao;
import jehc.xtmodules.xtdao.XtDataAuthorityPostDao;
import jehc.xtmodules.xtdao.XtFunctioninfoDao;
import jehc.xtmodules.xtdao.XtUserinfoDao;
import jehc.xtmodules.xtmodel.XtFunctioninfo;
import jehc.xtmodules.xtservice.XtFunctioninfoService;

/**
* 功能表 
* 2015-06-01 20:44:49  邓纯杰
*/
@Service("xtFunctioninfoService")
public class XtFunctioninfoServiceImpl extends BaseService implements XtFunctioninfoService{
	@Autowired
	private XtFunctioninfoDao xtFunctioninfoDao;
	@Autowired
	private XtDataAuthorityDepartDao xtDataAuthorityDepartDao;
	@Autowired
	private XtDataAuthorityPostDao xtDataAuthorityPostDao;
	@Autowired
	private XtDataAuthorityDefaultDao xtDataAuthorityDefaultDao;
	@Autowired
	private XtDataAuthorityDao xtDataAuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtFunctioninfo> getXtFunctioninfoListByCondition(Map<String,Object> condition){
		try{
			return xtFunctioninfoDao.getXtFunctioninfoListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_functioninfo_id 
	* @return
	*/
	public XtFunctioninfo getXtFunctioninfoById(String xt_functioninfo_id){
		try{
			return xtFunctioninfoDao.getXtFunctioninfoById(xt_functioninfo_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_functioninfo 
	* @return
	*/
	public int addXtFunctioninfo(XtFunctioninfo xt_Functioninfo){
		int i = 0;
		try {
			i = xtFunctioninfoDao.addXtFunctioninfo(xt_Functioninfo);
			if(xt_Functioninfo.getXt_functioninfoIsAuthority() == 0){
				//统一推送
				addPushDataAuthority();
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_functioninfo 
	* @return
	*/
	public int updateXtFunctioninfo(XtFunctioninfo xt_Functioninfo){
		int i = 0;
		try {
			String xt_functioninfo_id = xt_Functioninfo.getXt_functioninfo_id();
			int isAuthority = xtFunctioninfoDao.getXtFunctioninfoById(xt_functioninfo_id).getXt_functioninfoIsAuthority();
			i = xtFunctioninfoDao.updateXtFunctioninfo(xt_Functioninfo);
			//如果原来是非数据权限 现在是数据权限 则要统一推送一下
			if(isAuthority == 1 && xt_Functioninfo.getXt_functioninfoIsAuthority() == 0){
				//统一推送
				addPushDataAuthority();
			}
			//如果原来是数据权限 现在取消了 则要处理数据权限中数据将其删除掉
			if(isAuthority == 0 && xt_Functioninfo.getXt_functioninfoIsAuthority() == 1){
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("xt_functioninfo_id", xt_functioninfo_id);
				xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
				xtDataAuthorityDepartDao.delXtDataAuthorityDepartList(condition);
				xtDataAuthorityDefaultDao.delXtDataAuthorityDefaultAllByCondition(condition);
				xtDataAuthorityPostDao.delXtDataAuthorityPostList(condition);
			}
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
	public int delXtFunctioninfo(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtFunctioninfoDao.delXtFunctioninfo(condition);
			//统一推送
			addPushDataAuthority();
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 读取所有功能
	 * @param condition
	 * @return
	 */
	public List<XtFunctioninfo> getXtFunctioninfoList(Map<String,Object> condition){
		try{
			return xtFunctioninfoDao.getXtFunctioninfoList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	////////////////////数据权限///////////////////
	/**
	 * 查询所有功能数据并分组 
	 * @param condition
	 * @return
	 */
	public List<XtFunctioninfo> getXtFunctioninfoListForData(Map<String,Object> condition){
		try{
			return xtFunctioninfoDao.getXtFunctioninfoListForData(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	 * 
	 * @param condition
	 * @return
	 */
	public List<XtFunctioninfo> getXtFunctioninfoAllForData(Map<String,Object> condition){
		try{
			return xtFunctioninfoDao.getXtFunctioninfoAllForData(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}

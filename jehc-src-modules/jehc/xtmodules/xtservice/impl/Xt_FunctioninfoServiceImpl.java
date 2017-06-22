package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Data_AuthorityDao;
import jehc.xtmodules.xtdao.Xt_Data_Authority_DefaultDao;
import jehc.xtmodules.xtdao.Xt_Data_Authority_DepartDao;
import jehc.xtmodules.xtdao.Xt_Data_Authority_PostDao;
import jehc.xtmodules.xtdao.Xt_FunctioninfoDao;
import jehc.xtmodules.xtdao.Xt_UserinfoDao;
import jehc.xtmodules.xtmodel.Xt_Functioninfo;
import jehc.xtmodules.xtservice.Xt_FunctioninfoService;

/**
* 功能表 
* 2015-06-01 20:44:49  邓纯杰
*/
@Service("xt_FunctioninfoService")
public class Xt_FunctioninfoServiceImpl extends BaseService implements Xt_FunctioninfoService{
	@Autowired
	private Xt_FunctioninfoDao xt_FunctioninfoDao;
	@Autowired
	private Xt_Data_Authority_DepartDao xt_Data_Authority_DepartDao;
	@Autowired
	private Xt_Data_Authority_PostDao xt_Data_Authority_PostDao;
	@Autowired
	private Xt_Data_Authority_DefaultDao xt_Data_Authority_DefaultDao;
	@Autowired
	private Xt_Data_AuthorityDao xt_Data_AuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Functioninfo> getXtFunctioninfoListByCondition(Map<String,Object> condition){
		try{
			return xt_FunctioninfoDao.getXtFunctioninfoListByCondition(condition);
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
	public Xt_Functioninfo getXtFunctioninfoById(String xt_functioninfo_id){
		try{
			return xt_FunctioninfoDao.getXtFunctioninfoById(xt_functioninfo_id);
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
	public int addXtFunctioninfo(Xt_Functioninfo xt_Functioninfo){
		int i = 0;
		try {
			i = xt_FunctioninfoDao.addXtFunctioninfo(xt_Functioninfo);
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
	public int updateXtFunctioninfo(Xt_Functioninfo xt_Functioninfo){
		int i = 0;
		try {
			String xt_functioninfo_id = xt_Functioninfo.getXt_functioninfo_id();
			int isAuthority = xt_FunctioninfoDao.getXtFunctioninfoById(xt_functioninfo_id).getXt_functioninfoIsAuthority();
			i = xt_FunctioninfoDao.updateXtFunctioninfo(xt_Functioninfo);
			//如果原来是非数据权限 现在是数据权限 则要统一推送一下
			if(isAuthority == 1 && xt_Functioninfo.getXt_functioninfoIsAuthority() == 0){
				//统一推送
				addPushDataAuthority();
			}
			//如果原来是数据权限 现在取消了 则要处理数据权限中数据将其删除掉
			if(isAuthority == 0 && xt_Functioninfo.getXt_functioninfoIsAuthority() == 1){
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("xt_functioninfo_id", xt_functioninfo_id);
				xt_Data_AuthorityDao.delXtDataAuthorityByCondition(condition);
				xt_Data_Authority_DepartDao.delXtDataAuthorityDepartList(condition);
				xt_Data_Authority_DefaultDao.delXtDataAuthorityDefaultAllByCondition(condition);
				xt_Data_Authority_PostDao.delXtDataAuthorityPostList(condition);
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
			i = xt_FunctioninfoDao.delXtFunctioninfo(condition);
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
	public List<Xt_Functioninfo> getXtFunctioninfoList(Map<String,Object> condition){
		try{
			return xt_FunctioninfoDao.getXtFunctioninfoList(condition);
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
	public List<Xt_Functioninfo> getXtFunctioninfoListForData(Map<String,Object> condition){
		try{
			return xt_FunctioninfoDao.getXtFunctioninfoListForData(condition);
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
	public List<Xt_Functioninfo> getXtFunctioninfoAllForData(Map<String,Object> condition){
		try{
			return xt_FunctioninfoDao.getXtFunctioninfoAllForData(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}

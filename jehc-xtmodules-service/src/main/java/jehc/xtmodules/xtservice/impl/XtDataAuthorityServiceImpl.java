package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtDataAuthorityDao;
import jehc.xtmodules.xtmodel.XtDataAuthority;
import jehc.xtmodules.xtservice.XtDataAuthorityService;

/**
* 数据权限; InnoDB free: 10240 kB 
* 2015-10-04 14:42:34  邓纯杰
*/
@Service("xtDataAuthorityService")
public class XtDataAuthorityServiceImpl extends BaseService implements XtDataAuthorityService{
	@Autowired
	private XtDataAuthorityDao xtDataAuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtDataAuthority> getXtDataAuthorityListByCondition(Map<String,Object> condition){
		try{
			return xtDataAuthorityDao.getXtDataAuthorityListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_data_authority_id 
	* @return
	*/
	public XtDataAuthority getXtDataAuthorityById(String xt_data_authority_id){
		try{
			return xtDataAuthorityDao.getXtDataAuthorityById(xt_data_authority_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_data_authority 
	* @return
	*/
	public int addXtDataAuthority(List<XtDataAuthority> xt_Data_AuthorityList,String xt_userinfo_id,String id,String xt_menuinfo_id,String xt_data_authorityType){
		int i = 0;
		try {
			//1先删除原来数据
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_userinfo_id", xt_userinfo_id);
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			condition.put("xt_data_authorityType", xt_data_authorityType);
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			//2添加新数据
			for(int j = 0; j < xt_Data_AuthorityList.size(); j++){
				xtDataAuthorityDao.addXtDataAuthority(xt_Data_AuthorityList.get(j));
			}
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_data_authority 
	* @return
	*/
	public int updateXtDataAuthority(XtDataAuthority xt_Data_Authority){
		int i = 0;
		try {
			i = xtDataAuthorityDao.updateXtDataAuthority(xt_Data_Authority);
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
	public int delXtDataAuthority(Map<String,Object> condition){
		int i = 0;
		try {
			 xtDataAuthorityDao.delXtDataAuthority(condition);
			 i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 根据条件删除（用于清空）
	* @param condition 
	* @return
	*/
	public int delXtDataAuthorityByCondition(Map<String,Object> condition){
		int i = 0;
		try {
			 xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			 i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 读取所有数据
	 * @param condition
	 * @return
	 */
	public List<XtDataAuthority> getXtDataAuthorityListAllByCondition(Map<String,Object> condition){
		try{
			return xtDataAuthorityDao.getXtDataAuthorityListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	 * 获取所有为登录使用 
	 * @param condition
	 * @return
	 */
	public List<XtDataAuthority> getXtDataAuthorityListForLogin(Map<String,Object> condition){
		try{
			return xtDataAuthorityDao.getXtDataAuthorityListForLogin(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	 * 批量添加
	 * @param xt_Data_AuthorityList
	 * @return
	 */
	public int addBatchXtDataAuthority(List<XtDataAuthority> xt_Data_AuthorityList){
		int i = 0;
		try {
			xtDataAuthorityDao.addBatchXtDataAuthority(xt_Data_AuthorityList);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

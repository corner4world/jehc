package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Data_AuthorityDao;
import jehc.xtmodules.xtmodel.Xt_Data_Authority;
import jehc.xtmodules.xtservice.Xt_Data_AuthorityService;

/**
* 数据权限; InnoDB free: 10240 kB 
* 2015-10-04 14:42:34  邓纯杰
*/
@Service("xt_Data_AuthorityService")
public class Xt_Data_AuthorityServiceImpl extends BaseService implements Xt_Data_AuthorityService{
	@Autowired
	private Xt_Data_AuthorityDao xt_Data_AuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Data_Authority> getXtDataAuthorityListByCondition(Map<String,Object> condition){
		try{
			return xt_Data_AuthorityDao.getXtDataAuthorityListByCondition(condition);
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
	public Xt_Data_Authority getXtDataAuthorityById(String xt_data_authority_id){
		try{
			return xt_Data_AuthorityDao.getXtDataAuthorityById(xt_data_authority_id);
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
	public int addXtDataAuthority(List<Xt_Data_Authority> xt_Data_AuthorityList,String xt_userinfo_id,String id,String xt_menuinfo_id,String xt_data_authorityType){
		int i = 0;
		try {
			//1先删除原来数据
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_userinfo_id", xt_userinfo_id);
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			condition.put("xt_data_authorityType", xt_data_authorityType);
			xt_Data_AuthorityDao.delXtDataAuthorityByCondition(condition);
			//2添加新数据
			for(int j = 0; j < xt_Data_AuthorityList.size(); j++){
				xt_Data_AuthorityDao.addXtDataAuthority(xt_Data_AuthorityList.get(j));
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
	public int updateXtDataAuthority(Xt_Data_Authority xt_Data_Authority){
		int i = 0;
		try {
			i = xt_Data_AuthorityDao.updateXtDataAuthority(xt_Data_Authority);
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
			 xt_Data_AuthorityDao.delXtDataAuthority(condition);
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
			 xt_Data_AuthorityDao.delXtDataAuthorityByCondition(condition);
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
	public List<Xt_Data_Authority> getXtDataAuthorityListAllByCondition(Map<String,Object> condition){
		try{
			return xt_Data_AuthorityDao.getXtDataAuthorityListAllByCondition(condition);
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
	public List<Xt_Data_Authority> getXtDataAuthorityListForLogin(Map<String,Object> condition){
		try{
			return xt_Data_AuthorityDao.getXtDataAuthorityListForLogin(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}

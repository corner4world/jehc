package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.xtmodules.xtservice.Xt_Data_Authority_DepartService;
import jehc.xtmodules.xtdao.Xt_Data_AuthorityDao;
import jehc.xtmodules.xtdao.Xt_Data_Authority_DepartDao;
import jehc.xtmodules.xtmodel.Xt_Data_Authority_Depart;

/**
* 数据权限按部门设置 
* 2017-06-20 14:36:19  邓纯杰
*/
@Service("xt_Data_Authority_DepartService")
public class Xt_Data_Authority_DepartServiceImpl extends BaseService implements Xt_Data_Authority_DepartService{
	@Autowired
	private Xt_Data_Authority_DepartDao xt_Data_Authority_DepartDao;
	@Autowired
	private Xt_Data_AuthorityDao xt_Data_AuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Data_Authority_Depart> getXtDataAuthorityDepartListByCondition(Map<String,Object> condition){
		try{
			return xt_Data_Authority_DepartDao.getXtDataAuthorityDepartListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_data_authority_depart_id 
	* @return
	*/
	public Xt_Data_Authority_Depart getXtDataAuthorityDepartById(String xt_data_authority_depart_id){
		try{
			Xt_Data_Authority_Depart xt_Data_Authority_Depart = xt_Data_Authority_DepartDao.getXtDataAuthorityDepartById(xt_data_authority_depart_id);
			return xt_Data_Authority_Depart;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_data_authority_depart 
	* @return
	*/
	public int addXtDataAuthorityDepart(Xt_Data_Authority_Depart xt_Data_Authority_Depart){
		int i = 0;
		try {
			i = xt_Data_Authority_DepartDao.addXtDataAuthorityDepart(xt_Data_Authority_Depart);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_data_authority_depart 
	* @return
	*/
	public int updateXtDataAuthorityDepart(Xt_Data_Authority_Depart xt_Data_Authority_Depart){
		int i = 0;
		try {
			i = xt_Data_Authority_DepartDao.updateXtDataAuthorityDepart(xt_Data_Authority_Depart);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_depart 
	* @return
	*/
	public int updateXtDataAuthorityDepartBySelective(Xt_Data_Authority_Depart xt_Data_Authority_Depart){
		int i = 0;
		try {
			i = xt_Data_Authority_DepartDao.updateXtDataAuthorityDepartBySelective(xt_Data_Authority_Depart);
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
	public int delXtDataAuthorityDepart(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Data_Authority_DepartDao.delXtDataAuthorityDepart(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 根据条件删除
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityDepartAllByCondition(Map<String,Object> condition){
		int i = 0;
		try {
			xt_Data_AuthorityDao.delXtDataAuthorityByCondition(condition);
			xt_Data_Authority_DepartDao.delXtDataAuthorityDepartList(condition);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 删除集合根据拥有者及菜单编号
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityDepartList(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Data_Authority_DepartDao.delXtDataAuthorityDepartList(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_data_authority_departList 
	* @return
	*/
	public int addBatchXtDataAuthorityDepart(List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList,String xt_departinfo_id,String id,String xt_menuinfo_id){
		int i = 0;
		try {
			//1先删除原来数据
			Map<String, Object> condition = new HashMap<String, Object>();
			if(StringUtil.isEmpty(xt_departinfo_id)){
				throw new ExceptionUtil("未能获取到部门编号----xt_departinfo_id");
			}
			if(StringUtil.isEmpty(xt_menuinfo_id)){
				throw new ExceptionUtil("未能获取到菜单编号----xt_menuinfo_id");
			}
			condition.put("xt_departinfo_id", xt_departinfo_id);
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			xt_Data_Authority_DepartDao.delXtDataAuthorityDepartList(condition);
			//2添加新数据
			if(null != xt_Data_Authority_DepartList && xt_Data_Authority_DepartList.size() > 0){
				i = xt_Data_Authority_DepartDao.addBatchXtDataAuthorityDepart(xt_Data_Authority_DepartList);
			}else{
				i = 1;
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_data_authority_departList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDepart(List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList){
		int i = 0;
		try {
			i = xt_Data_Authority_DepartDao.updateBatchXtDataAuthorityDepart(xt_Data_Authority_DepartList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_departList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDepartBySelective(List<Xt_Data_Authority_Depart> xt_Data_Authority_DepartList){
		int i = 0;
		try {
			i = xt_Data_Authority_DepartDao.updateBatchXtDataAuthorityDepartBySelective(xt_Data_Authority_DepartList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

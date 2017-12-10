package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.xtmodules.xtservice.XtDataAuthorityDepartService;
import jehc.xtmodules.xtdao.XtDataAuthorityDao;
import jehc.xtmodules.xtdao.XtDataAuthorityDepartDao;
import jehc.xtmodules.xtmodel.XtDataAuthorityDepart;

/**
* 数据权限按部门设置 
* 2017-06-20 14:36:19  邓纯杰
*/
@Service("xtDataAuthorityDepartService")
public class XtDataAuthorityDepartServiceImpl extends BaseService implements XtDataAuthorityDepartService{
	@Autowired
	private XtDataAuthorityDepartDao xtDataAuthorityDepartDao;
	@Autowired
	private XtDataAuthorityDao xtDataAuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDataAuthorityDepart> getXtDataAuthorityDepartListByCondition(Map<String,Object> condition){
		try{
			return xtDataAuthorityDepartDao.getXtDataAuthorityDepartListByCondition(condition);
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
	public XtDataAuthorityDepart getXtDataAuthorityDepartById(String xt_data_authority_depart_id){
		try{
			XtDataAuthorityDepart xt_Data_Authority_Depart = xtDataAuthorityDepartDao.getXtDataAuthorityDepartById(xt_data_authority_depart_id);
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
	public int addXtDataAuthorityDepart(XtDataAuthorityDepart xt_Data_Authority_Depart){
		int i = 0;
		try {
			i = xtDataAuthorityDepartDao.addXtDataAuthorityDepart(xt_Data_Authority_Depart);
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
	public int updateXtDataAuthorityDepart(XtDataAuthorityDepart xt_Data_Authority_Depart){
		int i = 0;
		try {
			i = xtDataAuthorityDepartDao.updateXtDataAuthorityDepart(xt_Data_Authority_Depart);
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
	public int updateXtDataAuthorityDepartBySelective(XtDataAuthorityDepart xt_Data_Authority_Depart){
		int i = 0;
		try {
			i = xtDataAuthorityDepartDao.updateXtDataAuthorityDepartBySelective(xt_Data_Authority_Depart);
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
			i = xtDataAuthorityDepartDao.delXtDataAuthorityDepart(condition);
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
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			xtDataAuthorityDepartDao.delXtDataAuthorityDepartList(condition);
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
			i = xtDataAuthorityDepartDao.delXtDataAuthorityDepartList(condition);
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
	public int addBatchXtDataAuthorityDepart(List<XtDataAuthorityDepart> xt_Data_Authority_DepartList,String xt_departinfo_id,String id,String xt_menuinfo_id){
		int i = 0;
		try {
			//1先删除原表
			Map<String, Object> condition = new HashMap<String, Object>();
			if(StringUtil.isEmpty(xt_departinfo_id)){
				throw new ExceptionUtil("未能获取到部门编号----xt_departinfo_id");
			}
			if(StringUtil.isEmpty(xt_menuinfo_id)){
				throw new ExceptionUtil("未能获取到菜单编号----xt_menuinfo_id");
			}
			condition.put("xt_departinfo_id", xt_departinfo_id);
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			xtDataAuthorityDepartDao.delXtDataAuthorityDepartList(condition);
			//2删除 执行表
			condition = new HashMap<String, Object>();
			condition.put("xt_data_authorityType", "2");
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			//3添加新数据
			if(null != xt_Data_Authority_DepartList && xt_Data_Authority_DepartList.size() > 0){
				for(XtDataAuthorityDepart xt_Data_Authority_Depart:xt_Data_Authority_DepartList){
					xtDataAuthorityDepartDao.addXtDataAuthorityDepart(xt_Data_Authority_Depart);
				}
				//兼容oracle与mysql语法 废弃批量插入
//				xtDataAuthorityDepartDao.addBatchXtDataAuthorityDepart(xt_Data_Authority_DepartList);
			}
			i = 1;
			//4统一推送
			addPushDataAuthority();
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
	public int updateBatchXtDataAuthorityDepart(List<XtDataAuthorityDepart> xt_Data_Authority_DepartList){
		int i = 0;
		try {
			i = xtDataAuthorityDepartDao.updateBatchXtDataAuthorityDepart(xt_Data_Authority_DepartList);
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
	public int updateBatchXtDataAuthorityDepartBySelective(List<XtDataAuthorityDepart> xt_Data_Authority_DepartList){
		int i = 0;
		try {
			i = xtDataAuthorityDepartDao.updateBatchXtDataAuthorityDepartBySelective(xt_Data_Authority_DepartList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}

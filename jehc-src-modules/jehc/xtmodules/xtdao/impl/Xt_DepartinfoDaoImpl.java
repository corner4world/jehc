package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_DepartinfoDao;
import jehc.xtmodules.xtmodel.Xt_Departinfo;

/**
* 部门信息表(departInfo) 
* 2015-05-13 15:46:38  邓纯杰
*/
@Repository("xt_DepartinfoDao")
public class Xt_DepartinfoDaoImpl  extends BaseDaoImpl implements Xt_DepartinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Departinfo> getXtDepartinfoListByCondition(Map<String,Object> condition){
		return (List<Xt_Departinfo>)this.getList("getXtDepartinfoListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_departinfo_id 
	* @return
	*/
	public Xt_Departinfo getXtDepartinfoById(String xt_departinfo_id){
		return (Xt_Departinfo)this.get("getXtDepartinfoById", xt_departinfo_id);
	}
	/**
	* 添加
	* @param xt_departinfo 
	* @return
	*/
	public int addXtDepartinfo(Xt_Departinfo xt_Departinfo){
		return this.add("addXtDepartinfo", xt_Departinfo);
	}
	/**
	* 修改
	* @param xt_departinfo 
	* @return
	*/
	public int updateXtDepartinfo(Xt_Departinfo xt_Departinfo){
		return this.update("updateXtDepartinfo", xt_Departinfo);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDepartinfo(Map<String,Object> condition){
		return this.del("delXtDepartinfo", condition);
	}
	
	/**
	 * 部门根目录集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Departinfo> getXtDepartinfoList(){
		return (List<Xt_Departinfo>)this.getList("getXtDepartinfoList", null);
	}
	
	/**
	 * 查找子集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Departinfo> getXtDepartinfoListChild(Map<String,Object> condition){
		return (List<Xt_Departinfo>)this.getList("getXtDepartinfoListChild", condition);
	}
	
	/**
	 * 查找所有集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Departinfo> getXtDepartinfoListAll(Map<String,Object> condition){
		return (List<Xt_Departinfo>)this.getList("getXtDepartinfoListAll", condition); 
	}
	
	/**
	 * 根据各种情况查找集合不分页（流程设计器中处理组 发起组等使用）
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Departinfo> queryXtDepartinfoList(Map<String,Object> condition){
		return (List<Xt_Departinfo>)this.getList("queryXtDepartinfoList", condition); 
	}
}

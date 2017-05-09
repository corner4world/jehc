package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_M_RDao;
import jehc.xtmodules.xtmodel.Xt_M_R;

/**
* 资源角色; InnoDB free: 6144 kB 
* 2015-08-04 16:27:46  邓纯杰
*/
@Repository("xt_M_RDao")
public class Xt_M_RDaoImpl  extends BaseDaoImpl implements Xt_M_RDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_M_R> getXtMRListByCondition(Map<String,Object> condition){
		return (List<Xt_M_R>)this.getList("getXtMRListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_m_r_id 
	* @return
	*/
	public Xt_M_R getXtMRById(String xt_m_r_id){
		return (Xt_M_R)this.get("getXtMRById", xt_m_r_id);
	}
	/**
	* 添加
	* @param xt_m_r 
	* @return
	*/
	public int addXtMR(Xt_M_R xt_M_R){
		return this.add("addXtMR", xt_M_R);
	}
	/**
	* 修改
	* @param xt_m_r 
	* @return
	*/
	public int updateXtMR(Xt_M_R xt_M_R){
		return this.update("updateXtMR", xt_M_R);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMR(Map<String,Object> condition){
		return this.del("delXtMR", condition);
	}
	/**
	 * 批量添加
	 * @param xt_M_RList
	 * @return
	 */
	public int addBatchXtMR(List<Xt_M_R> xt_M_RList){
		return this.add("addBatchXtMR", xt_M_RList);
	}
}

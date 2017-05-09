package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_M_R;

/**
* 资源角色; InnoDB free: 6144 kB 
* 2015-08-04 16:27:46  邓纯杰
*/
public interface Xt_M_RDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_M_R> getXtMRListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_m_r_id 
	* @return
	*/
	public Xt_M_R getXtMRById(String xt_m_r_id);
	/**
	* 添加
	* @param xt_m_r 
	* @return
	*/
	public int addXtMR(Xt_M_R xt_M_R);
	/**
	* 修改
	* @param xt_m_r 
	* @return
	*/
	public int updateXtMR(Xt_M_R xt_M_R);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMR(Map<String,Object> condition);
	
	/**
	 * 批量添加
	 * @param xt_M_RList
	 * @return
	 */
	public int addBatchXtMR(List<Xt_M_R> xt_M_RList);
}

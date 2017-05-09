package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Ip_Frozen;

/**
* 平台IP冻结账户 
* 2016-02-29 10:41:23  邓纯杰
*/
public interface Xt_Ip_FrozenDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Ip_Frozen> getXtIpFrozenListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_ip_frozen_id 
	* @return
	*/
	public Xt_Ip_Frozen getXtIpFrozenById(String xt_ip_frozen_id);
	/**
	* 添加
	* @param xt_ip_frozen 
	* @return
	*/
	public int addXtIpFrozen(Xt_Ip_Frozen xt_Ip_Frozen);
	/**
	* 修改
	* @param xt_ip_frozen 
	* @return
	*/
	public int updateXtIpFrozen(Xt_Ip_Frozen xt_Ip_Frozen);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtIpFrozen(Map<String,Object> condition);
	
	/**
	 * 获取所有集合
	 * @param condition
	 * @return
	 */
	public List<Xt_Ip_Frozen> getXtIpFrozenListAllByCondition(Map<String,Object> condition);
}

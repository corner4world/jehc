package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtIpFrozenDao;
import jehc.xtmodules.xtmodel.XtIpFrozen;

/**
* 平台IP冻结账户 
* 2016-02-29 10:41:23  邓纯杰
*/
@Repository("xtIpFrozenDao")
public class XtIpFrozenDaoImpl  extends BaseDaoImpl implements XtIpFrozenDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtIpFrozen> getXtIpFrozenListByCondition(Map<String,Object> condition){
		return (List<XtIpFrozen>)this.getList("getXtIpFrozenListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_ip_frozen_id 
	* @return
	*/
	public XtIpFrozen getXtIpFrozenById(String xt_ip_frozen_id){
		return (XtIpFrozen)this.get("getXtIpFrozenById", xt_ip_frozen_id);
	}
	/**
	* 添加
	* @param xt_ip_frozen 
	* @return
	*/
	public int addXtIpFrozen(XtIpFrozen xt_Ip_Frozen){
		return this.add("addXtIpFrozen", xt_Ip_Frozen);
	}
	/**
	* 修改
	* @param xt_ip_frozen 
	* @return
	*/
	public int updateXtIpFrozen(XtIpFrozen xt_Ip_Frozen){
		return this.update("updateXtIpFrozen", xt_Ip_Frozen);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtIpFrozen(Map<String,Object> condition){
		return this.del("delXtIpFrozen", condition);
	}
	
	/**
	 * 获取所有集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtIpFrozen> getXtIpFrozenListAllByCondition(Map<String,Object> condition){
		return (List<XtIpFrozen>)this.getList("getXtIpFrozenListAllByCondition",condition);
	}
}

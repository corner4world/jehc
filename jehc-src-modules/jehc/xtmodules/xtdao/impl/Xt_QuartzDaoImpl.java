package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_QuartzDao;
import jehc.xtmodules.xtmodel.Xt_Quartz;

/**
* 任务调度配置信息表 
* 2015-10-29 16:50:03  邓纯杰
*/
@Repository("xt_QuartzDao")
public class Xt_QuartzDaoImpl  extends BaseDaoImpl implements Xt_QuartzDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Quartz> getXtQuartzListByCondition(Map<String,Object> condition){
		return (List<Xt_Quartz>)this.getList("getXtQuartzListByCondition",condition);
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public Xt_Quartz getXtQuartzById(String id){
		return (Xt_Quartz)this.get("getXtQuartzById", id);
	}
	/**
	* 添加
	* @param xt_quartz 
	* @return
	*/
	public int addXtQuartz(Xt_Quartz xt_Quartz){
		return this.add("addXtQuartz", xt_Quartz);
	}
	/**
	* 修改
	* @param xt_quartz 
	* @return
	*/
	public int updateXtQuartz(Xt_Quartz xt_Quartz){
		return this.update("updateXtQuartz", xt_Quartz);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtQuartz(Map<String,Object> condition){
		return this.del("delXtQuartz", condition);
	}
	/**
	 * 查找集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Quartz> getXtQuartzListAllByCondition(Map<String,Object> condition){
		return (List<Xt_Quartz>)this.getList("getXtQuartzListAllByCondition",condition);
	}
}

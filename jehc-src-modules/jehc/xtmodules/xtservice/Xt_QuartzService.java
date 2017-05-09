package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Quartz;

/**
* 任务调度配置信息表 
* 2015-10-29 16:50:03  邓纯杰
*/
public interface Xt_QuartzService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Quartz> getXtQuartzListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public Xt_Quartz getXtQuartzById(String id);
	/**
	* 添加
	* @param xt_quartz 
	* @return
	*/
	public int addXtQuartz(Xt_Quartz xt_Quartz);
	/**
	* 修改
	* @param xt_quartz 
	* @return
	*/
	public int updateXtQuartz(Xt_Quartz xt_Quartz);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtQuartz(Map<String,Object> condition);
	/**
	 * 查找集合
	 * @param condition
	 * @return
	 */
	public List<Xt_Quartz> getXtQuartzListAllByCondition(Map<String,Object> condition);
}

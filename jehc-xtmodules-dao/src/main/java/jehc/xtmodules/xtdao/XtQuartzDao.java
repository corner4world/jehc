package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtQuartz;

/**
* 任务调度配置信息表 
* 2015-10-29 16:50:03  邓纯杰
*/
public interface XtQuartzDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtQuartz> getXtQuartzListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public XtQuartz getXtQuartzById(String id);
	/**
	* 添加
	* @param xt_quartz 
	* @return
	*/
	public int addXtQuartz(XtQuartz xt_Quartz);
	/**
	* 修改
	* @param xt_quartz 
	* @return
	*/
	public int updateXtQuartz(XtQuartz xt_Quartz);
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
	public List<XtQuartz> getXtQuartzListAllByCondition(Map<String,Object> condition);
}

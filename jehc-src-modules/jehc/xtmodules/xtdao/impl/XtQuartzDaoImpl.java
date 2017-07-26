package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtQuartzDao;
import jehc.xtmodules.xtmodel.XtQuartz;

/**
* 任务调度配置信息表 
* 2015-10-29 16:50:03  邓纯杰
*/
@Repository("xtQuartzDao")
public class XtQuartzDaoImpl  extends BaseDaoImpl implements XtQuartzDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtQuartz> getXtQuartzListByCondition(Map<String,Object> condition){
		return (List<XtQuartz>)this.getList("getXtQuartzListByCondition",condition);
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public XtQuartz getXtQuartzById(String id){
		return (XtQuartz)this.get("getXtQuartzById", id);
	}
	/**
	* 添加
	* @param xt_quartz 
	* @return
	*/
	public int addXtQuartz(XtQuartz xt_Quartz){
		return this.add("addXtQuartz", xt_Quartz);
	}
	/**
	* 修改
	* @param xt_quartz 
	* @return
	*/
	public int updateXtQuartz(XtQuartz xt_Quartz){
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
	public List<XtQuartz> getXtQuartzListAllByCondition(Map<String,Object> condition){
		return (List<XtQuartz>)this.getList("getXtQuartzListAllByCondition",condition);
	}
}

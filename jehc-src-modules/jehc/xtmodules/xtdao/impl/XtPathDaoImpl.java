package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtPathDao;
import jehc.xtmodules.xtmodel.XtPath;

/**
* 文件路径设置 
* 2015-05-15 14:55:22  邓纯杰
*/
@Repository("xtPathDao")
public class XtPathDaoImpl  extends BaseDaoImpl implements XtPathDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtPath> getXtPathListByCondition(Map<String,Object> condition){
		return (List<XtPath>)this.getList("getXtPathListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_path_id 
	* @return
	*/
	public XtPath getXtPathById(String xt_path_id){
		return (XtPath)this.get("getXtPathById", xt_path_id);
	}
	/**
	* 添加
	* @param xt_path 
	* @return
	*/
	public int addXtPath(XtPath xt_Path){
		return this.add("addXtPath", xt_Path);
	}
	/**
	* 修改
	* @param xt_path 
	* @return
	*/
	public int updateXtPath(XtPath xt_Path){
		return this.update("updateXtPath", xt_Path);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPath(Map<String,Object> condition){
		return this.del("delXtPath", condition);
	}
	/**
	 * 查找所有平台路径
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtPath> getXtPathListAllByCondition(Map<String,Object> condition){
		return (List<XtPath>)this.getList("getXtPathListAllByCondition",condition); 
	}
}

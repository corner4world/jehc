package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtPath;

/**
* 文件路径设置 
* 2015-05-15 14:55:22  邓纯杰
*/
public interface XtPathDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtPath> getXtPathListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_path_id 
	* @return
	*/
	public XtPath getXtPathById(String xt_path_id);
	/**
	* 添加
	* @param xt_path 
	* @return
	*/
	public int addXtPath(XtPath xt_Path);
	/**
	* 修改
	* @param xt_path 
	* @return
	*/
	public int updateXtPath(XtPath xt_Path);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPath(Map<String,Object> condition);
	/**
	 * 查找所有平台路径
	 * @param condition
	 * @return
	 */
	public List<XtPath> getXtPathListAllByCondition(Map<String,Object> condition);
}

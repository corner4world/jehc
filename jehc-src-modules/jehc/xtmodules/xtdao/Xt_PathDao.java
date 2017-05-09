package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Path;

/**
* 文件路径设置 
* 2015-05-15 14:55:22  邓纯杰
*/
public interface Xt_PathDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Path> getXtPathListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_path_id 
	* @return
	*/
	public Xt_Path getXtPathById(String xt_path_id);
	/**
	* 添加
	* @param xt_path 
	* @return
	*/
	public int addXtPath(Xt_Path xt_Path);
	/**
	* 修改
	* @param xt_path 
	* @return
	*/
	public int updateXtPath(Xt_Path xt_Path);
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
	public List<Xt_Path> getXtPathListAllByCondition(Map<String,Object> condition);
}

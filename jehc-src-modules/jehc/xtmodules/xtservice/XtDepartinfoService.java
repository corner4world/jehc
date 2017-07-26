package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtDepartinfo;

/**
* 部门信息表(departInfo) 
* 2015-05-13 15:46:38  邓纯杰
*/
public interface XtDepartinfoService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDepartinfo> getXtDepartinfoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_departinfo_id 
	* @return
	*/
	public XtDepartinfo getXtDepartinfoById(String xt_departinfo_id);
	/**
	* 添加
	* @param xt_departinfo 
	* @return
	*/
	public int addXtDepartinfo(XtDepartinfo xt_Departinfo);
	/**
	* 修改
	* @param xt_departinfo 
	* @return
	*/
	public int updateXtDepartinfo(XtDepartinfo xt_Departinfo);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDepartinfo(Map<String,Object> condition);
	
	/**
	 * 部门根目录集合
	 * @return
	 */
	public List<XtDepartinfo> getXtDepartinfoList();
	
	/**
	 * 查找子集合
	 * @param condition
	 * @return
	 */
	public List<XtDepartinfo> getXtDepartinfoListChild(Map<String,Object> condition);
	/**
	 * 查找所有集合
	 * @param condition
	 * @return
	 */
	public List<XtDepartinfo> getXtDepartinfoListAll(Map<String,Object> condition);
	
	/**
	 * 查找所有集合
	 * @param condition
	 * @return
	 */
	public List<XtDepartinfo> queryXtDepartinfoList(Map<String,Object> condition);
}

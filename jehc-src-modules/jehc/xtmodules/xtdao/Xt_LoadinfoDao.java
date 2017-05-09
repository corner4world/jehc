package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Loadinfo;

/**
* 页面加载信息 
* 2015-05-13 21:20:57  邓纯杰
*/
public interface Xt_LoadinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Loadinfo> getXtLoadinfoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_loadinfo_id 
	* @return
	*/
	public Xt_Loadinfo getXtLoadinfoById(String xt_loadinfo_id);
	/**
	* 添加
	* @param xt_loadinfo 
	* @return
	*/
	public int addXtLoadinfo(Xt_Loadinfo xt_Loadinfo);
	/**
	* 修改
	* @param xt_loadinfo 
	* @return
	*/
	public int updateXtLoadinfo(Xt_Loadinfo xt_Loadinfo);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtLoadinfo(Map<String,Object> condition);
	
	/**
	 * 分组统计并平均值算法
	 * @return
	 */
	public List<Xt_Loadinfo> getXtLoadingGroupList();
}

package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Kwords;

/**
* 关键词（敏感词） 
* 2016-10-08 15:03:41  邓纯杰
*/
public interface Xt_KwordsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Kwords> getXtKwordsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_kwords_id 
	* @return
	*/
	public Xt_Kwords getXtKwordsById(String xt_kwords_id);
	/**
	* 添加
	* @param xt_kwords 
	* @return
	*/
	public int addXtKwords(Xt_Kwords xt_Kwords);
	/**
	* 修改
	* @param xt_kwords 
	* @return
	*/
	public int updateXtKwords(Xt_Kwords xt_Kwords);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtKwords(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_kwordsList 
	* @return
	*/
	public int addBatchXtKwords(List<Xt_Kwords> xt_KwordsList);
	/**
	* 批量修改
	* @param xt_kwordsList 
	* @return
	*/
	public int updateBatchXtKwords(List<Xt_Kwords> xt_KwordsList);
}

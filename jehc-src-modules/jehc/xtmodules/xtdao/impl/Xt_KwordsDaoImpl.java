package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_KwordsDao;
import jehc.xtmodules.xtmodel.Xt_Kwords;

/**
* 关键词（敏感词） 
* 2016-10-08 15:03:41  邓纯杰
*/
@Repository("xt_KwordsDao")
public class Xt_KwordsDaoImpl  extends BaseDaoImpl implements Xt_KwordsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Kwords> getXtKwordsListByCondition(Map<String,Object> condition){
		return (List<Xt_Kwords>)this.getList("getXtKwordsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_kwords_id 
	* @return
	*/
	public Xt_Kwords getXtKwordsById(String xt_kwords_id){
		return (Xt_Kwords)this.get("getXtKwordsById", xt_kwords_id);
	}
	/**
	* 添加
	* @param xt_kwords 
	* @return
	*/
	public int addXtKwords(Xt_Kwords xt_Kwords){
		return this.add("addXtKwords", xt_Kwords);
	}
	/**
	* 修改
	* @param xt_kwords 
	* @return
	*/
	public int updateXtKwords(Xt_Kwords xt_Kwords){
		return this.update("updateXtKwords", xt_Kwords);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtKwords(Map<String,Object> condition){
		return this.del("delXtKwords", condition);
	}
	/**
	* 批量添加
	* @param xt_kwordsList 
	* @return
	*/
	public int addBatchXtKwords(List<Xt_Kwords> xt_KwordsList){
		return this.add("addBatchXtKwords", xt_KwordsList);
	}
	/**
	* 批量修改
	* @param xt_kwordsList 
	* @return
	*/
	public int updateBatchXtKwords(List<Xt_Kwords> xt_KwordsList){
		return this.update("updateBatchXtKwords", xt_KwordsList);
	}
}

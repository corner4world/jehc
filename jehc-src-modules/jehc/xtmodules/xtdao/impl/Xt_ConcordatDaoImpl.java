package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_ConcordatDao;
import jehc.xtmodules.xtmodel.Xt_Concordat;

/**
* 合同管理 
* 2015-05-24 08:39:49  邓纯杰
*/
@Repository("xt_ConcordatDao")
public class Xt_ConcordatDaoImpl  extends BaseDaoImpl implements Xt_ConcordatDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Concordat> getXtConcordatListByCondition(Map<String,Object> condition){
		return (List<Xt_Concordat>)this.getList("getXtConcordatListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_concordat_id 
	* @return
	*/
	public Xt_Concordat getXtConcordatById(String xt_concordat_id){
		return (Xt_Concordat)this.get("getXtConcordatById", xt_concordat_id);
	}
	/**
	* 添加
	* @param xt_concordat 
	* @return
	*/
	public int addXtConcordat(Xt_Concordat xt_Concordat){
		return this.add("addXtConcordat", xt_Concordat);
	}
	/**
	* 修改
	* @param xt_concordat 
	* @return
	*/
	public int updateXtConcordat(Xt_Concordat xt_Concordat){
		return this.update("updateXtConcordat", xt_Concordat);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtConcordat(Map<String,Object> condition){
		return this.del("delXtConcordat", condition);
	}
}

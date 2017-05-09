package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_KnowledgeDao;
import jehc.xtmodules.xtmodel.Xt_Knowledge;

/**
* 平台知识内容 
* 2015-06-07 20:09:38  邓纯杰
*/
@Repository("xt_KnowledgeDao")
public class Xt_KnowledgeDaoImpl  extends BaseDaoImpl implements Xt_KnowledgeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Knowledge> getXtKnowledgeListByCondition(Map<String,Object> condition){
		return (List<Xt_Knowledge>)this.getList("getXtKnowledgeListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_knowledge_id 
	* @return
	*/
	public Xt_Knowledge getXtKnowledgeById(String xt_knowledge_id){
		return (Xt_Knowledge)this.get("getXtKnowledgeById", xt_knowledge_id);
	}
	/**
	* 添加
	* @param xt_knowledge 
	* @return
	*/
	public int addXtKnowledge(Xt_Knowledge xt_Knowledge){
		return this.add("addXtKnowledge", xt_Knowledge);
	}
	/**
	* 修改
	* @param xt_knowledge 
	* @return
	*/
	public int updateXtKnowledge(Xt_Knowledge xt_Knowledge){
		return this.update("updateXtKnowledge", xt_Knowledge);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtKnowledge(Map<String,Object> condition){
		return this.del("delXtKnowledge", condition);
	}
}

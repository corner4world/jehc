package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Knowledge;

/**
* 平台知识内容 
* 2015-06-07 20:09:38  邓纯杰
*/
public interface Xt_KnowledgeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Knowledge> getXtKnowledgeListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_knowledge_id 
	* @return
	*/
	public Xt_Knowledge getXtKnowledgeById(String xt_knowledge_id);
	/**
	* 添加
	* @param xt_knowledge 
	* @return
	*/
	public int addXtKnowledge(Xt_Knowledge xt_Knowledge);
	/**
	* 修改
	* @param xt_knowledge 
	* @return
	*/
	public int updateXtKnowledge(Xt_Knowledge xt_Knowledge);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtKnowledge(Map<String,Object> condition);
}
